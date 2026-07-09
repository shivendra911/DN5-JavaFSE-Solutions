-- Ex 1: Control Structures
-- Bank Management System

-- setup schema (run this once to get a clean slate)
BEGIN
   FOR t IN (SELECT table_name FROM user_tables WHERE table_name IN ('TRANSACTIONS', 'LOANS', 'ACCOUNTS', 'CUSTOMERS', 'EMPLOYEES')) LOOP
      EXECUTE IMMEDIATE 'DROP TABLE ' || t.table_name || ' CASCADE CONSTRAINTS';
   END LOOP;
END;
/

CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    DOB DATE,
    Balance NUMBER DEFAULT 0,
    LastModified DATE DEFAULT SYSDATE,
    IsVIP VARCHAR2(5) DEFAULT 'FALSE' CHECK (IsVIP IN ('TRUE', 'FALSE'))
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20) CHECK (AccountType IN ('Savings', 'Checking', 'Business')),
    Balance NUMBER DEFAULT 0,
    LastModified DATE DEFAULT SYSDATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE DEFAULT SYSDATE,
    Amount NUMBER NOT NULL,
    TransactionType VARCHAR2(20) CHECK (TransactionType IN ('Deposit', 'Withdrawal', 'Transfer')),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER NOT NULL,
    InterestRate NUMBER(5, 2) NOT NULL,
    StartDate DATE DEFAULT SYSDATE,
    EndDate DATE NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100) NOT NULL,
    Position VARCHAR2(50),
    Salary NUMBER CHECK (Salary > 0),
    Department VARCHAR2(50),
    HireDate DATE DEFAULT SYSDATE
);

-- dummy data
INSERT INTO Customers (CustomerID, Name, DOB, Balance) VALUES (1, 'Sarah Jenkins', TO_DATE('1952-04-12', 'YYYY-MM-DD'), 25000);
INSERT INTO Customers (CustomerID, Name, DOB, Balance) VALUES (2, 'Michael Chen', TO_DATE('1988-09-25', 'YYYY-MM-DD'), 8500);
INSERT INTO Customers (CustomerID, Name, DOB, Balance) VALUES (3, 'Aisha Patel', TO_DATE('1995-11-03', 'YYYY-MM-DD'), 1200);
INSERT INTO Customers (CustomerID, Name, DOB, Balance) VALUES (4, 'Robert Lewis', TO_DATE('1948-02-18', 'YYYY-MM-DD'), 55000);
INSERT INTO Customers (CustomerID, Name, DOB, Balance) VALUES (5, 'Emma Thompson', TO_DATE('2001-07-30', 'YYYY-MM-DD'), 450);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES (101, 1, 'Savings', 20000);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES (102, 1, 'Checking', 5000);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES (103, 2, 'Checking', 8500);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES (104, 3, 'Savings', 1200);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES (105, 4, 'Business', 55000);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance) VALUES (106, 5, 'Checking', 450);

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (1001, 1, 15000, 5.5, SYSDATE - 30, SYSDATE + 15);

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (1002, 2, 8000, 6.0, SYSDATE - 100, SYSDATE + 200);

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (1003, 3, 25000, 7.5, SYSDATE - 60, SYSDATE + 5);

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (1004, 4, 100000, 4.0, SYSDATE - 365, SYSDATE + 700);

COMMIT;

SET SERVEROUTPUT ON;

-- 1: apply 1% discount to loan interest for seniors (> 60)
DECLARE
    v_age NUMBER;
BEGIN
    FOR rec IN (SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate 
                FROM Customers c 
                JOIN Loans l ON c.CustomerID = l.CustomerID) 
    LOOP
        -- calc age in years
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;

            DBMS_OUTPUT.PUT_LINE('discount applied for ' || rec.Name || ' (age ' || v_age || ') on loan ' || rec.LoanID);
        END IF;
    END LOOP;
    
    COMMIT;
END;
/

-- 2: update vip status based on balance
DECLARE
    v_msg VARCHAR2(100);
BEGIN
    FOR rec IN (SELECT CustomerID, Name, Balance FROM Customers) 
    LOOP
        CASE 
            WHEN rec.Balance >= 50000 THEN
                UPDATE Customers SET IsVIP = 'TRUE' WHERE CustomerID = rec.CustomerID;
                v_msg := 'is now VIP';
                
            WHEN rec.Balance >= 10000 THEN
                UPDATE Customers SET IsVIP = 'FALSE' WHERE CustomerID = rec.CustomerID;
                v_msg := 'is preferred tier';
                
            ELSE
                UPDATE Customers SET IsVIP = 'FALSE' WHERE CustomerID = rec.CustomerID;
                v_msg := 'needs more balance for vip';
        END CASE;
        
        DBMS_OUTPUT.PUT_LINE(rec.Name || ' ' || v_msg || ' - bal: $' || rec.Balance);
    END LOOP;
    COMMIT;
END;
/

-- 3: print reminders for loans due in 30 days
DECLARE
    CURSOR c_due IS
        SELECT l.LoanID, c.Name, l.EndDate, l.LoanAmount
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
        
    v_rec c_due%ROWTYPE;
BEGIN
    OPEN c_due;
    LOOP
        FETCH c_due INTO v_rec;
        EXIT WHEN c_due%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('reminder: loan ' || v_rec.LoanID || ' for ' || v_rec.Name || 
                             ' is due on ' || TO_CHAR(v_rec.EndDate, 'DD-MON-YYYY'));
    END LOOP;
    CLOSE c_due;
END;
/

-- 4: run monthly interest accrual for savings accounts (3 month sim)
DECLARE
    v_month NUMBER := 1;
    v_rate CONSTANT NUMBER := 0.005; -- 0.5%
    e_neg_bal EXCEPTION;
BEGIN
    WHILE v_month <= 3 
    LOOP
        DBMS_OUTPUT.PUT_LINE('--- month ' || v_month || ' ---');
        
        FOR acc IN (SELECT AccountID, CustomerID, Balance FROM Accounts WHERE AccountType = 'Savings') 
        LOOP
            BEGIN
                IF acc.Balance < 0 THEN
                    RAISE e_neg_bal;
                END IF;
                
                -- add interest
                UPDATE Accounts
                SET Balance = Balance + (Balance * v_rate),
                    LastModified = SYSDATE
                WHERE AccountID = acc.AccountID;
                
                DBMS_OUTPUT.PUT_LINE('acc ' || acc.AccountID || ' new bal: $' || 
                                     ROUND(acc.Balance + (acc.Balance * v_rate), 2));
                                     
            EXCEPTION
                WHEN e_neg_bal THEN
                    DBMS_OUTPUT.PUT_LINE('err: acc ' || acc.AccountID || ' is negative, no interest');
                WHEN OTHERS THEN
                    DBMS_OUTPUT.PUT_LINE('error on acc ' || acc.AccountID || ': ' || SQLERRM);
            END;
        END LOOP;
        
        v_month := v_month + 1;
    END LOOP;
    
    -- rollback sim changes
    ROLLBACK;
END;
/

-- Exercise 3: Stored Procedures
-- Bank Management System

SET SERVEROUTPUT ON;

-- 1. Process Monthly Interest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
    num_accounts NUMBER := 0;
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';
    
    num_accounts := SQL%ROWCOUNT;
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Interest applied to ' || num_accounts || ' savings accounts.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

BEGIN
    ProcessMonthlyInterest;
END;
/

-- 2. Update Employee Bonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_dept VARCHAR2,
    p_bonus NUMBER
)
AS
    rows_updated NUMBER := 0;
BEGIN
    IF p_bonus <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Bonus must be greater than 0');
        RETURN;
    END IF;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus / 100)
    WHERE Department = p_dept;
    
    rows_updated := SQL%ROWCOUNT;

    IF rows_updated > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Bonus applied to ' || rows_updated || ' employees');
    ELSE
        DBMS_OUTPUT.PUT_LINE('No employees found in department');
    END IF;

    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

BEGIN
    UpdateEmployeeBonus('IT', 10);
END;
/

-- 3. Transfer Funds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from NUMBER,
    p_to NUMBER,
    p_amount NUMBER
)
AS
    from_balance NUMBER;
BEGIN
    IF p_amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Transfer amount must be positive');
        RETURN;
    END IF;

    -- get balance and lock row
    SELECT Balance INTO from_balance
    FROM Accounts
    WHERE AccountID = p_from
    FOR UPDATE;

    IF from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Not enough funds');
        ROLLBACK;
        RETURN;
    END IF;

    -- deduct money
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from;

    -- add money
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to;
    
    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Receiver account not found');
        ROLLBACK;
        RETURN;
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' successfully');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Sender account not found');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

BEGIN
    TransferFunds(101, 102, 500);
END;
/

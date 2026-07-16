CREATE SCHEMA IF NOT EXISTS ormlearn;
USE ormlearn;

CREATE TABLE IF NOT EXISTS country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);

INSERT INTO country (co_code, co_name) VALUES ('IN', 'India');
INSERT INTO country (co_code, co_name) VALUES ('US', 'United States');
INSERT INTO country (co_code, co_name) VALUES ('AF', 'Afghanistan');
INSERT INTO country (co_code, co_name) VALUES ('AL', 'Albania');
INSERT INTO country (co_code, co_name) VALUES ('AU', 'Australia');
INSERT INTO country (co_code, co_name) VALUES ('BR', 'Brazil');
INSERT INTO country (co_code, co_name) VALUES ('CA', 'Canada');
INSERT INTO country (co_code, co_name) VALUES ('CN', 'China');
INSERT INTO country (co_code, co_name) VALUES ('DE', 'Germany');
INSERT INTO country (co_code, co_name) VALUES ('FR', 'France');
INSERT INTO country (co_code, co_name) VALUES ('GB', 'United Kingdom');
INSERT INTO country (co_code, co_name) VALUES ('JP', 'Japan');
INSERT INTO country (co_code, co_name) VALUES ('RU', 'Russian Federation');
INSERT INTO country (co_code, co_name) VALUES ('ZA', 'South Africa');

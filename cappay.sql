CREATE DATABASE cap_db;

CREATE TABLE user_tb (
	id SERIAL PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL,
	creation_date TIMESTAMP
);

CREATE TABLE accounts (
  id SERIAL PRIMARY KEY,
  user_id INT8 NOT NULL,
  account_status VARCHAR(35),
  account_type VARCHAR(35),
  balance FLOAT8 NOT NULL,
  creation_date TIMESTAMP
  last_change_date TIMESTAMP,
  FOREIGN KEY (user_id)
      REFERENCES user_tb (id)
);
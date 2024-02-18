CREATE TABLE transactions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    month_transactions VARCHAR(50),
    type VARCHAR(50),
    description VARCHAR(255),
    amount DECIMAL(10, 2),
    PRIMARY KEY (id)
);

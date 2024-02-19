ALTER TABLE transactions
ADD COLUMN user_id BIGINT,
ADD CONSTRAINT fk_transactions_user_id FOREIGN KEY (user_id) REFERENCES users(id);

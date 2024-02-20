ALTER TABLE users add active tinyint;
update users set active = 1;


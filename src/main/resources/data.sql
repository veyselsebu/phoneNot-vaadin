CREATE TABLE IF NOT EXISTS PhoneBook (id IDENTITY PRIMARY KEY , name VARCHAR(30),surname varchar(30),phoneNumber varchar(11));
delete from PhoneBook;
CREATE TABLE IF NOT EXISTS PhoneBookCopy (id IDENTITY PRIMARY KEY , name VARCHAR(30),surname varchar(30),phoneNumber varchar(11));
delete from PhoneBookCopy;
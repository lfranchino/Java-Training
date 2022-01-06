CREATE TABLE Users (
    accountId int NOT NULL AUTO_INCREMENT,
    username varchar(25) NOT NULL,
    password varchar(256) NOT NULL,
    CONSTRAINT pk_accounts_accountId PRIMARY KEY (accountId)
);
CREATE TABLE Users (
    accountId int NOT NULL AUTO_INCREMENT,
    username varchar(25) NOT NULL,
    password varchar(25) NOT NULL,
    token varchar(25),
    CONSTRAINT pk_accounts_accountId PRIMARY KEY (accountId)
);

INSERT INTO Users(username, password, token) VALUES ('Louie', '07frl28b', NULL);
INSERT INTO Users(username, password, token) VALUES ('Alfredo', '03frf294', NULL);

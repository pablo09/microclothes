CREATE TABLE USER_ROLE (
  ID BIGINT NOT NULL UNIQUE,
  USERNAME VARCHAR(100) NOT NULL,
  ROLE_ID BIGINT NOT NULL,

  PRIMARY KEY (ID),
  FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME),
  FOREIGN KEY(ROLE_ID) REFERENCES ROLE(ID)
);

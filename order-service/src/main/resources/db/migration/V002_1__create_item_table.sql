CREATE TABLE ITEM (
  ID bigint not null unique,
  NAME VARCHAR(100) not null,
  COLOR VARCHAR(100) not null,
  SIZE VARCHAR(100) not null,
  AMOUNT DECMIAL not null,
  CURRENCY VARCHAR(5) not null,

  PRIMARY KEY(ID)
)
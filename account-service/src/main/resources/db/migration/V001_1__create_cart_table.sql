CREATE TABLE CART (
  ID bigint not null unique,
  USERNAME VARCHAR(100) not null unique,
  STOCK_ITEM_IDS VARCHAR(100),

  PRIMARY KEY(ID)
)
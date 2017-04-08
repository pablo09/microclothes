CREATE TABLE STOCK_ITEM_SPECIMEN (
  id bigint NOT NULL UNIQUE,
  stock_item_id bigint NOT NULL,
  size VARCHAR(5) NOT NULL,
  amount int NOT NULL,

  PRIMARY KEY (id)
);

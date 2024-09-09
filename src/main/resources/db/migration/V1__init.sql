CREATE TABLE "purchase_orders" (
  "id" serial PRIMARY KEY,
  "customer_name" varchar(200),
  "email" varchar(200),
  "mailing_address" varchar(1000),
  "status" int,
  "create_at" timestamp
);

CREATE TABLE "items" (
  "id" serial PRIMARY KEY,
  "product_id" bigint,
  "quantity" int,
  "create_at" timestamp,
  "purchase_order_id" bigint
);

ALTER TABLE "items" ADD FOREIGN KEY ("purchase_order_id") REFERENCES "purchase_orders" ("id");
CREATE TABLE "purchase_orders" (
  "id" serial PRIMARY KEY,
  "customer_name" varchar(200),
  "email" varchar(200),
  "mailing_address" varchar(1000),
  "status" int,
  "create_at" timestamp
);
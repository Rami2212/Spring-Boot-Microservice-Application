ALTER TABLE t_orders
    ADD COLUMN order_number VARCHAR(255) NOT NULL;

ALTER TABLE t_orders
DROP COLUMN customer_id;
CREATE TABLE brand(
    id                  bigserial NOT NULL,
    name                VARCHAR(50) NOT NULL,
    CONSTRAINT pk_brand_id PRIMARY KEY(id)
);

CREATE TABLE prices(
    id                  bigserial NOT NULL,
    brand_id            int4 NOT NULL,
    start_date          TIMESTAMP NOT NULL,
    end_date            TIMESTAMP NOT NULL,
    price_list          int4 NOT NULL,
    product_id          int4 NOT NULL,
    priority            int4 NOT NULL,
    price               float4 NOT NULL,
    curr                VARCHAR(3) NOT NULL,
    CONSTRAINT pk_price_id PRIMARY KEY(id),
    CONSTRAINT fk_brand_id FOREIGN KEY (brand_id) REFERENCES brand(id)
);
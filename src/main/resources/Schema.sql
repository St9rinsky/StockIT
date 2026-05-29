CREATE TABLE IF NOT EXISTS categories (
                            id UUID PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
                            id UUID PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            role  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
                          id UUID PRIMARY KEY,
                          name VARCHAR(150) NOT NULL,
                          sku VARCHAR(80) NOT NULL UNIQUE,
                          category_id UUID NOT NULL,
                          active BOOLEAN NOT NULL DEFAULT TRUE,
                          low_stock_threshold INT NOT NULL DEFAULT 10,

                          CONSTRAINT fk_product_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS locations (
                           id UUID PRIMARY KEY,
                           name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS stock_movements (
                                 id UUID PRIMARY KEY,
                                 product_id UUID NOT NULL,
                                 location_id UUID NOT NULL,
                                 movement_type VARCHAR(30) NOT NULL,
                                 movement_reason VARCHAR(50) NOT NULL,
                                 quantity INT NOT NULL,
                                 user_id UUID NOT NULL,
                                 created_at TIMESTAMP NOT NULL,

                                CONSTRAINT fk_movement_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES users(id),

                                 CONSTRAINT fk_movement_product
                                     FOREIGN KEY (product_id)
                                         REFERENCES products(id),

                                 CONSTRAINT fk_movement_location
                                     FOREIGN KEY (location_id)
                                         REFERENCES locations(id),

                                 CONSTRAINT chk_quantity_positive
                                     CHECK (quantity > 0)
);
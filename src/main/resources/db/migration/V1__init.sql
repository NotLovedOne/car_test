CREATE TABLE cars (
                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      make VARCHAR NOT NULL,
                      model VARCHAR NOT NULL,
                      year INT CHECK (year BETWEEN 1886 AND EXTRACT(YEAR FROM CURRENT_DATE)),
                      price NUMERIC CHECK (price > 0),
                      vin CHAR(17) UNIQUE NOT NULL
);

INSERT INTO cars (make, model, year, price, vin) VALUES
                                                     ('Toyota', 'Corolla', 2020, 20000, 'JTDKBRFU8K3071234'),
                                                     ('Honda', 'Civic', 2019, 18000, 'SHHFK8G74KU800001'),
                                                     ('Ford', 'Focus', 2018, 17000, '1FAFP34P7WK135790'),
                                                     ('Nissan', 'Altima', 2022, 24000, '1N4BL4BV4KC112345'),
                                               ('Chevrolet', 'Equinox', 2020, 31000, '2GNAXNEV1K6123456');

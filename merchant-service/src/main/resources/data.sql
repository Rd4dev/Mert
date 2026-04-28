-- Ensure the 'merchant' table exists
CREATE TABLE IF NOT EXISTS merchant
(
    id              UUID PRIMARY KEY,
    merchant_name            VARCHAR(255)        NOT NULL,
    store_name      VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    registered_date DATE                NOT NULL,
    phone_number    VARCHAR(15) UNIQUE  NOT NULL
);

-- Insert merchants only if they don't already exist
INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174000',
       'John Carter',
       'Carter Retail Hub',
       'john.carter@example.com',
       'New York, USA',
       '2024-01-10',
       '+10000000001'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174000');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174001',
       'Emma Thompson',
       'Thompson Fashion',
       'emma.thompson@example.com',
       'London, UK',
       '2024-02-12',
       '+440000000002'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174001');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174002',
       'Liam Anderson',
       'Anderson Electronics',
       'liam.anderson@example.com',
       'Toronto, Canada',
       '2023-11-05',
       '+120000000003'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174002');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174003',
       'Olivia Martinez',
       'Martinez Grocery',
       'olivia.martinez@example.com',
       'Madrid, Spain',
       '2024-03-01',
       '+340000000004'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174003');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174004',
       'Noah Schneider',
       'Schneider Auto Parts',
       'noah.schneider@example.com',
       'Berlin, Germany',
       '2023-12-20',
       '+490000000005'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174004');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174005',
       'Sophia Rossi',
       'Rossi Boutique',
       'sophia.rossi@example.com',
       'Rome, Italy',
       '2024-01-25',
       '+390000000006'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174005');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174006',
       'Lucas Dubois',
       'Dubois Wine Store',
       'lucas.dubois@example.com',
       'Paris, France',
       '2023-10-18',
       '+330000000007'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174006');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174007',
       'Ava Johnson',
       'Johnson Tech Supplies',
       'ava.johnson@example.com',
       'San Francisco, USA',
       '2024-04-05',
       '+100000000008'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174007');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174008',
       'Ethan Brown',
       'Brown Hardware Store',
       'ethan.brown@example.com',
       'Sydney, Australia',
       '2023-09-12',
       '+610000000009'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174008');


INSERT INTO merchant (id, merchant_name, store_name, email, address, registered_date, phone_number)
SELECT '423e4567-e89b-12d3-a456-426614174009',
       'Mia Wilson',
       'Wilson Home Decor',
       'mia.wilson@example.com',
       'Dublin, Ireland',
       '2024-02-28',
       '+353000000010'
WHERE NOT EXISTS (SELECT 1 FROM merchant WHERE id = '423e4567-e89b-12d3-a456-426614174009');
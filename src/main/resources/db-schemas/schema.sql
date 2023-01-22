create table tester (
 user_id INT NOT NULL PRIMARY KEY,
 user_text VARCHAR(50) NOT NULL,
 user_description VARCHAR(100) NOT NULL DEFAULT 'Generic Description'
)
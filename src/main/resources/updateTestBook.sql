INSERT INTO category_entity(name) VALUES ('Повесть');
INSERT INTO category_entity(name) VALUES ('Сказка');
INSERT INTO category_entity(name) VALUES ('Ужасы');

INSERT INTO book_entity(title, category_id) VALUES ('Молчание', 1);
INSERT INTO book_entity(title, category_id) VALUES ('Старик и море', 2);
INSERT INTO book_entity(title, category_id) VALUES ('Тишина', 3);

INSERT INTO author_entity(name) VALUES ('Дюма');
INSERT INTO author_entity(name) VALUES ('Стивен Кинг');
INSERT INTO author_entity(name) VALUES ('Хемингуэй');

-- Молчание написана Дюма и Кингом
INSERT INTO author_books(book_id, author_id) VALUES (1, 1);
INSERT INTO author_books(book_id, author_id) VALUES (1, 2);
-- Старик и море написана Хемингуэем
INSERT INTO author_books(book_id, author_id) VALUES (2, 3);
-- Тишина написана Кингом
INSERT INTO author_books(book_id, author_id) VALUES (3, 2);

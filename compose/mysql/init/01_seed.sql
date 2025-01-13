INSERT INTO authors (author_name, created_at, updated_at, deleted_at) VALUES
('村上春樹', CURRENT_TIMESTAMP, NULL, NULL),
('Kazuo Ishiguro', CURRENT_TIMESTAMP, NULL, NULL),
('Yukio Mishima', CURRENT_TIMESTAMP, NULL, NULL),
('東野圭吾', CURRENT_TIMESTAMP, NULL, NULL),
('Natsuo Kirino', CURRENT_TIMESTAMP, NULL, NULL);

INSERT INTO publishers (publisher_name, created_at, updated_at, deleted_at) VALUES
('講談社', CURRENT_TIMESTAMP, NULL, NULL),
('新潮社', CURRENT_TIMESTAMP, NULL, NULL),
('Penguin Random House', CURRENT_TIMESTAMP, NULL, NULL),
('Tuttle Publishing', CURRENT_TIMESTAMP, NULL, NULL),
('HarperCollins', CURRENT_TIMESTAMP, NULL, NULL);

INSERT INTO books (isbn, title, author_id, publisher_id, published_at, created_at, updated_at, deleted_at) VALUES
('9784101000014', 'ノルウェイの森', 1, 1, '1987-09-04 00:00:00', CURRENT_TIMESTAMP, NULL, NULL),
('9780571224142', 'Never Let Me Go', 2, 3, '2005-03-03 00:00:00', CURRENT_TIMESTAMP, NULL, NULL),
('9780679752702', 'The Sailor Who Fell from Grace with the Sea', 3, 3, '1963-04-01 00:00:00', CURRENT_TIMESTAMP, NULL, NULL),
('9784805310553', '容疑者Xの献身', 4, 4, '2005-08-06 00:00:00', CURRENT_TIMESTAMP, NULL, NULL),
('9784770030873', 'Out', 5, 5, '1997-06-20 00:00:00', CURRENT_TIMESTAMP, NULL, NULL);

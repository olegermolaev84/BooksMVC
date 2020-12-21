INSERT INTO authority (authority) VALUES('ADMIN_ROLE');
INSERT INTO authority (authority) VALUES('REGULAR_ROLE');

INSERT INTO users (username, password, authority_id, exparation_date, locked, crendentials_expired, enabled)
	VALUES('admin', '$2a$10$9cA.jP.Z4hZjD4SVDt7m0eENwi/j3EoS.rijhOJnseeet6Ow3OdSi', 
	(SELECT id FROM authority a WHERE a.authority = 'ADMIN_ROLE'),
	'2100-01-01', FALSE, FALSE, TRUE);

INSERT INTO genre (name) VALUES('Поэмы');
INSERT INTO genre (name) VALUES('Стихи');
INSERT INTO genre (name) VALUES('Сказки');
INSERT INTO genre (name) VALUES('Романы');
INSERT INTO genre (name) VALUES('Повести');

INSERT INTO author (date_of_born, date_of_died, name) VALUES('1799-05-26', '1837-01-29', 'Пушкин Александр Сергеевич');
INSERT INTO author (date_of_born, date_of_died, name) VALUES('1809-03-20', '1852-02-21', 'Гоголь Николай Васильевич');
INSERT INTO author (date_of_born, date_of_died, name) VALUES('1964-02-21', null,'Водолазкин Евгений Германович');
INSERT INTO author (date_of_born, date_of_died, name) VALUES('1880-11-16', '1921-08-07', 'Блок Александр Александрович');
INSERT INTO author (date_of_born, date_of_died, name) VALUES('1952-06-07', null, 'Донцова Дарья');
INSERT INTO author (date_of_born, date_of_died, name) VALUES('1897-10-03', '1937-04-13', 'Ильф Илья Арнольдович');
INSERT INTO author (date_of_born, date_of_died, name) VALUES('1902-12-13', '1942-07-02', 'Петров Евгений');

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Золотой теленок', 'url', 1931, (SELECT id FROM genre WHERE name='Романы'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Золотой теленок'), 
	 (SELECT id FROM author WHERE name='Ильф Илья Арнольдович'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Золотой теленок'), 
	 (SELECT id FROM author WHERE name='Петров Евгений'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Двенадцать стульев', 'url', 1928, (SELECT id FROM genre WHERE name='Романы'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Двенадцать стульев'), 
	 (SELECT id FROM author WHERE name='Ильф Илья Арнольдович'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Двенадцать стульев'), 
	 (SELECT id FROM author WHERE name='Петров Евгений'));


INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Евгений Онегин', 'url', 1830, (SELECT id FROM genre WHERE name='Романы'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Евгений Онегин'), 
	 (SELECT id FROM author WHERE name='Пушкин Александр Сергеевич'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Руслан и Людмила', 'url', 1820, (SELECT id FROM genre WHERE name='Поэмы'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Руслан и Людмила'), 
	 (SELECT id FROM author WHERE name='Пушкин Александр Сергеевич'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Сказка о царе Салтане', 'url', 1831, (SELECT id FROM genre WHERE name='Сказки'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Сказка о царе Салтане'), 
	 (SELECT id FROM author WHERE name='Пушкин Александр Сергеевич'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Двенадцать', 'url', 1918, (SELECT id FROM genre WHERE name='Поэмы'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Двенадцать'), 
	 (SELECT id FROM author WHERE name='Блок Александр Александрович'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Ветер принес издалека', 'url', 1901, (SELECT id FROM genre WHERE name='Стихи'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Ветер принес издалека'), 
	 (SELECT id FROM author WHERE name='Блок Александр Александрович'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Вхожу я в темные храмы', 'url', 1902, (SELECT id FROM genre WHERE name='Стихи'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Вхожу я в темные храмы'), 
	 (SELECT id FROM author WHERE name='Блок Александр Александрович'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Душа молчит. В холодном небе', 'url', 1901, (SELECT id FROM genre WHERE name='Стихи'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Душа молчит. В холодном небе'), 
	 (SELECT id FROM author WHERE name='Блок Александр Александрович'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Нос', 'url', 1836, (SELECT id FROM genre WHERE name='Повести'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Нос'), 
	 (SELECT id FROM author WHERE name='Гоголь Николай Васильевич'));

INSERT INTO book (name, url, year_of_publishing, genre_id) VALUES
	('Вечера на хуторе близ Диканьки', 'url', 1832, (SELECT id FROM genre WHERE name='Повести'));
INSERT INTO book_author (book_id, author_id) VALUES
	((SELECT id FROM book WHERE name='Вечера на хуторе близ Диканьки'), 
	 (SELECT id FROM author WHERE name='Гоголь Николай Васильевич'));

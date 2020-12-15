create table genre 
(id int8 not null, 
name varchar(256) not null, 
primary key (id)
);

create table author (
id int8 not null, 
name varchar(256) not null,
date_of_born date not null,
date_of_died date,  
primary key (id)
);

create table book (
id int8 not null, 
name varchar(256) not null, 
url varchar(256), 
year_of_publishing int4 not null, 
genre_id int8 references genre (id), 
primary key (id)
);

create table book_author (
book_id int8 references book (id), 
author_id int8 references author (id), 
primary key (book_id, author_id)
);

INSERT INTO genre VALUES(0, 'Поэмы');
INSERT INTO genre VALUES(1, 'Стихи');
INSERT INTO genre VALUES(2, 'Сказки');
INSERT INTO genre VALUES(3, 'Романы');
INSERT INTO genre VALUES(4, 'Повести');

INSERT INTO author VALUES(0, 'Пушкин Александр Сергеевич', '1799-05-26', '1837-01-29');
INSERT INTO author VALUES(1, 'Гоголь Николай Васильевич', '1809-03-20', '1852-02-21');
INSERT INTO author VALUES(2, 'Водолазкин Евгений Германович', '1964-02-21', null);
INSERT INTO author VALUES(3, 'Блок Александр Александрович', '1880-11-16', '1921-08-07');
INSERT INTO author VALUES(4, 'Донцова Дарья', '1952-06-07', null);
INSERT INTO author VALUES(5, 'Ильф Илья Арнольдович', '1897-10-03', '1937-04-13');
INSERT INTO author VALUES(6, 'Петров Евгений', '1902-12-13', '1942-07-02');

INSERT INTO book        VALUES(0, 'Золотой теленок', 'url', 1931, 3);
INSERT INTO book_author VALUES(0, 5);
INSERT INTO book_author VALUES(0, 6);
INSERT INTO book        VALUES(1, 'Двенадцать стульев', 'url', 1928, 3);
INSERT INTO book_author VALUES(1, 5);
INSERT INTO book_author VALUES(1, 6);
INSERT INTO book        VALUES(2, 'Евгений Онегин', 'url', 1830, 3);
INSERT INTO book_author VALUES(2, 0);
INSERT INTO book        VALUES(3, 'Руслан и Людмила', 'url', 1820, 0);
INSERT INTO book_author VALUES(3, 0);
INSERT INTO book        VALUES(4, 'Сказка о царе Салтане', 'url', 1831, 2);
INSERT INTO book_author VALUES(4, 0);
INSERT INTO book        VALUES(5, 'Двенадцать', 'url', 1918, 0);
INSERT INTO book_author VALUES(5, 3);
INSERT INTO book        VALUES(6, 'Ветер принес издалека', 'url', 1901, 1);
INSERT INTO book_author VALUES(6, 3);
INSERT INTO book        VALUES(7, 'Вхожу я в темные храмы', 'url', 1902, 1);
INSERT INTO book_author VALUES(7, 3);
INSERT INTO book        VALUES(8, 'Душа молчит. В холодном небе', 'url', 1901, 1);
INSERT INTO book_author VALUES(8, 3);
INSERT INTO book        VALUES(9, 'Нос', 'url', 1836, 4);
INSERT INTO book_author VALUES(9, 1);
INSERT INTO book        VALUES(10, 'Вечера на хуторе близ Диканьки', 'url', 1832, 4);
INSERT INTO book_author VALUES(10, 1);
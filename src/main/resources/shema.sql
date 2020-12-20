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

create table authority 
(id int8 not null, 
authority varchar(15) not null, 
primary key (id)
);

create table users 
(id serial, 
username varchar(25) not null, 
password varchar(256) not null,
authority_id int8 references authority (id),
exparation_date date not null,
locked boolean not null,
crendentials_expired boolean not null,
enabled boolean not null,
primary key (id)
);

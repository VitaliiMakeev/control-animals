DROP DATABASE IF EXISTS HumanFriends;
create database HumanFriends;
use HumanFriends;

/*
create table pets 
(
	id serial,
    name varchar(20) not null,
    type_pets varchar(20) not null,
    birth_day DATE not null,
    commands varchar(100) not null
);

create table pack_animals 
(
	id serial,
    name varchar(20) not null,
    type_pets varchar(20) not null,
    birth_day DATE not null,
    commands varchar(100) not null
);
*/



DROP TABLE IF EXISTS pets;
create table pets 
(
	id serial,
    name_pets varchar(20) not null
);

DROP TABLE IF EXISTS pack_animals;
create table pack_animals
(
	id serial,
    name_pack varchar(20) not null
);

DROP TABLE IF EXISTS dogs;
create table dogs 
(
	id serial,
    name varchar(20) not null,
    type_anim BIGINT UNSIGNED not null,
    birth_day DATE not null,
    commands varchar(100) not null,
    FOREIGN KEY (type_anim) REFERENCES pets(id)
);

DROP TABLE IF EXISTS cats;
create table cats 
(
	id serial,
    name varchar(20) not null,
    type_anim BIGINT UNSIGNED not null,
    birth_day DATE not null,
    commands varchar(100) not null,
    FOREIGN KEY (type_anim) REFERENCES pets(id)
);

DROP TABLE IF EXISTS hamsters;
create table hamsters 
(
	id serial,
    name varchar(20) not null,
    type_anim BIGINT UNSIGNED not null,
    birth_day DATE not null,
    commands varchar(100) not null,
    FOREIGN KEY (type_anim) REFERENCES pets(id)
);

DROP TABLE IF EXISTS horses;
create table horses 
(
	id serial,
    name varchar(20) not null,
    type_anim BIGINT UNSIGNED not null,
    birth_day DATE not null,
    commands varchar(100) not null,
    FOREIGN KEY (type_anim) REFERENCES pack_animals(id)
);

DROP TABLE IF EXISTS camels;
create table camels 
(
	id serial,
    name varchar(20) not null,
    type_anim BIGINT UNSIGNED not null,
    birth_day DATE not null,
    commands varchar(100) not null,
    FOREIGN KEY (type_anim) REFERENCES pack_animals(id)
);

DROP TABLE IF EXISTS donkeys;
create table donkeys 
(
	id serial,
    name varchar(20) not null,
    type_anim BIGINT UNSIGNED not null,
    birth_day DATE not null,
    commands varchar(100) not null,
    FOREIGN KEY (type_anim) REFERENCES pack_animals(id)
);

insert into pets (name_pets) values ('dog'), ('cat'), ('hamster');
insert into pack_animals (name_pack) values ('horse'), ('camel'), ('donkey');

insert into dogs (name, type_anim, birth_day, commands) values 
	('Fido', 1, '2020-01-01', 'Sit, Stay, Fetch'),
	('Buddy', 1, '2018-12-10', 'Sit, Paw, Bark'),
    ('Bella', 1, '2019-11-11', 'Sit, Stay, Roll');

insert into cats (name, type_anim, birth_day, commands) values 
	('Whiskers', 2, '2019-05-15', 'Sit, Pounce'),
	('Smudge', 2, '2020-02-20', 'Sit, Pounce, Scratch'),
    ('Oliver', 2, '2020-06-30', 'Meow, Scratch, Jump');

insert into hamsters (name, type_anim, birth_day, commands) values
	('Hammy', 3, '2021-03-10', 'Roll, Hide'),
    ('Peanut', 3, '2021-08-01', 'Roll, Spin');


insert into horses (name, type_anim, birth_day, commands) values 
	('Thunder', 1, '2015-07-21', 'Trot, Canter, Gallop'), 
	('Storm', 1, '2014-05-05', 'Trot, Canter'),
    ('Blaze', 1, '2016-02-29', 'Trot, Jump, Gallop');

insert into camels (name, type_anim, birth_day, commands) values 
	('Sandy', 2, '2016-11-03', 'Walk, Carry Load'),
	('Dune', 2, '2018-12-12', 'Walk, Sit'),
    ('Sahara', 2, '2015-08-14', 'Walk, Run');

insert into donkeys (name, type_anim, birth_day, commands) values 
	('Eeyore', 3, '2017-09-18', 'Walk, Carry Load, Bray'),
	('Burro', 3, '2019-01-23', 'Walk, Bray, Kick');

/*
INSERT INTO horses (name, type_anim, birth_day, commands)
SELECT name, type_anim, birth_day, commands
FROM donkeys;

select * from horses;

delete from camels;

DROP TABLE IF EXISTS ot1_do3;
create table ot1_do3 
(
	id serial,
    name varchar(20) not null,
    age varchar(20) not null
);


DELIMITER &&
CREATE FUNCTION age (date_st DATE)
RETURNS varchar(20) READS SQL DATA
BEGIN
	DECLARE result varchar(20);
    DECLARE year_t int;
    DECLARE month_t int;
    set year_t = YEAR(NOW()) - YEAR(date_st);
    set month_t = month(NOW()) - month(date_st);
    set result = concat(year_t, ' г. ', month_t, ' мес.');
RETURN result;
END&&
DELIMITER ;

select * from ot1_do3;

INSERT INTO ot1_do3 (name, age)
SELECT name, age(birth_day) as age
FROM horses
where (YEAR(NOW()) - YEAR(birth_day)) < 3
union
SELECT name, age(birth_day) as age
FROM dogs
where (YEAR(NOW()) - YEAR(birth_day)) < 3
union
SELECT name, age(birth_day) as age
FROM cats
where (YEAR(NOW()) - YEAR(birth_day)) < 3
union
SELECT name, age(birth_day) as age
FROM hamsters
where (YEAR(NOW()) - YEAR(birth_day)) < 3;
*/

DROP TABLE IF EXISTS all_animals;
create table all_animals 
(
	id serial,
    name varchar(20) not null,
    type_anim varchar(20) not null,
    birth_day DATE not null,
    commands varchar(100) not null
);

INSERT INTO all_animals (name, type_anim, birth_day, commands)
SELECT horses.name, pack_animals.name_pack as type_anim, horses.birth_day, horses.commands
FROM horses
join pack_animals on type_anim = pack_animals.id
union
SELECT dogs.name, pets.name_pets as type_anim, dogs.birth_day, dogs.commands
FROM dogs
join pets on type_anim = pets.id
union
SELECT cats.name, pets.name_pets as type_anim, cats.birth_day, cats.commands
FROM cats
join pets on type_anim = pets.id
union
SELECT hamsters.name, pets.name_pets as type_anim, hamsters.birth_day, hamsters.commands
FROM hamsters
join pets on type_anim = pets.id;

select * from all_animals;




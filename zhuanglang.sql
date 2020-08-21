create database zhuanglang;
create user 'zhuanglang'@'localhost' identified by 'fwxx';
grant all privileges on zhuanglang.* to 'zhuanglang'@'localhost';

use zhuanglang;
drop table if exists teacher;
create table teacher(
    username char(32) PRIMARY KEY,
    pwd varchar(255) DEFAULT NULL,
	salt varchar(255) DEFAULT NULL,
    nkuorzhuanglang ENUM('NKU','ZHUANGLANG') not null,
    name varchar(20) not null,
    person_no varchar(10),
    mail varchar(255) not null
);
insert into teacher values(
	'Alexhaoge','7c403719af397878d6d48c385a3607f9','qQT06+5F7zymT7CcJPnhYQ==','NKU','吕',null,'root@alexhaoge.xyz'
);

drop table if exists resources;
drop table if exists class;
drop table if exists section;
drop table if exists book;
create table book(
	id int primary key auto_increment,
    `name` char(10) not null,
    upload char(20),
    discipline enum('IT','MUSIC','PE','ART') not null,
    constraint bookuploader
    foreign key(upload) references teacher(username)
);
create table section(
	id int primary key auto_increment,
    `number` int not null,
    upload char(20),
    note char(255),
    belong int not null,
    constraint sectionuploader
    foreign key(upload) references teacher(username),
    constraint bookbelong
    foreign key(belong) references book(id)
);
create table class(
	id int primary key auto_increment,
    `number` int not null,
    upload char(20),
    note char(255),
    belong int not null,
    constraint classuploader
    foreign key(upload) references teacher(username),
    constraint sectionbelong
    foreign key(belong) references section(id)
);
create table resources(
	id int primary key auto_increment,
    md5 char(32),
    `name` char(10) not null,
    `path` char(64) not null,
    upload char(20),
    videoorslide enum('VIDEO','SLIDE') not null,
    bilibili char(12),
    note char(255),
    belong int not null,
    constraint resourceuploader
    foreign key(upload) references teacher(username),
    constraint classbelong
    foreign key(belong) references class(id)
);

insert into book(`name`, upload, discipline) values
('八年级上', 'Alexhaoge', 'IT'),
('八年级下', 'Alexhaoge', 'IT');
insert into section(`number`, upload, note, belong) values
(1, 'Alexhaoge', 'Test', 1),
(3, 'Alexhaoge', 'Test', 1),
(2, 'Alexhaoge', 'Test', 1),
(2, 'Alexhaoge', 'Test', 2),
(3, 'Alexhaoge', 'Test', 2);
insert into class(`number`, upload, note, belong) values
(1, 'Alexhaoge', 'Test', 1),
(3, 'Alexhaoge', 'Test', 1),
(2, 'Alexhaoge', 'Test', 1),
(1, 'Alexhaoge', 'Test', 2),
(2, 'Alexhaoge', 'Test', 2);
insert into resources(`name`,`path`, upload, videoorslide, note, belong) values
('1.pdf', 'IT/1/1/1/1.pdf', 'Alexhaoge', 'SLIDE', 'Test', 1),
('1.mp4', 'IT/1/1/1/1.mp4', 'Alexhaoge', 'VIDEO', 'Test', 1);
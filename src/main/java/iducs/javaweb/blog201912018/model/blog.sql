create sequence seq_blog201912018 increment by 1 start with 1;

create table b201912018
(
    id      number(11) not null primary key,

    title   varchar2(30) not null,
    content      varchar2(100) not null,
    author    varchar2(30) not null,
    email   varchar2(30),
    regDate     varchar2(30)
);

insert into b201912018 values (seq_blog201912018.nextval, 'a3', '블로그내용23','sw', 'cometrue','2022-12-21');

update b201912018 set title='asd', content='asd' , author='강아지', email = 'dog@dog' where id='2';

select * from b201912018;


drop sequence seq_member;
drop table b201912018;
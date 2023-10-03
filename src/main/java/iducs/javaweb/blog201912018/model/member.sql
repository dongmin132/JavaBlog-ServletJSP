create sequence seq_member increment by 1 start with 1;

create table m201912018
(
    id number(11) not null primary key,
    email varchar2(30) not null unique,
    pw varchar2(20) not null,
    name varchar2(30) not null,
    phone varchar2(50),
    address varchar2(100)
);

insert into m201912018 values (seq_member.nextval, 'sw@induk.ac.kr', 'cometrue', 'induk comso', '12345', 'nowon');


commit;

select * from m201912018;


drop sequence seq_member;
drop table member;
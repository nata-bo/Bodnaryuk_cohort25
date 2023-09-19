drop table if exists event;

create table event (
                       id identity primary key,
                       title varchar(50),
                       description varchar(100)
);
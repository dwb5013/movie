create database movie;
\c movie

create table users
(
    username varchar not null
        constraint users_pk
            primary key,
    password varchar not null,
    enabled  boolean not null
);

alter table users
    owner to postgres;

create table authorities
(
    username  varchar not null,
    authority varchar not null
);

alter table authorities
    owner to postgres;

create index ix_auth_username
    on authorities (username, authority);

create table favorite
(
    id       serial
        constraint favorite_pk
            primary key,
    username varchar,
    movie_id varchar
);

alter table favorite
    owner to postgres;

create unique index favorite_id_uindex
    on favorite (id);

alter table authorities
    owner to postgres;

insert into favorite(username, movie_id)
VALUES ('admin', 'VOBPH4EBLM3ZXDCTJ-L-');

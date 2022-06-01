create database movie;
create table "users"
(
    username varchar not null
        constraint users_pk
            primary key,
    password varchar not null,
    enabled  boolean not null
);

alter table "users"
    owner to postgres;

create table "authorities"
(
    username  varchar not null,
    authority varchar not null
);

alter table "authorities"
    owner to postgres;

create index ix_auth_username
    on "authorities" (username, authority);

create table "favorite"
(
    id       serial
        constraint favorite_pk
            primary key,
    username varchar,
    movie_id varchar
);

alter table "favorite"
    owner to postgres;

create unique index favorite_id_uindex
    on "favorite" (id);

alter table "authorities"
    owner to postgres;


insert into "authorities"(username, authority)
VALUES ('user', 'ROLE_USER');

insert into "authorities"(username, authority)
VALUES ('bob', 'ROLE_USER');

insert into "users"(username, password, enabled)
values ('user',
        '{bcrypt}$2a$10$hOrYkqxtWXQnrpaGG06MVuZ6edzg3BzeDg0wAB8bR59gi5a5hDhQq',
        true);

insert
into "users"(username, password, enabled)
values ('bob',
        '{bcrypt}$2a$10$03.4U1I9WrkfiMVEda/4yOnZkMvVXVRVQi2kNkew/skDe7KGUeaii',
        true);

insert into "favorite"(username, movie_id)
VALUES ('BOB', 'VOBPH4EBLM3ZXDCTJ-L-');
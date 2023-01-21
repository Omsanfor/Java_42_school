DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

create table if not exists chat.User
(
    id SERIAL PRIMARY KEY,
    login text UNIQUE not null,
    password text not null
    );

create table if not exists chat.Chatroom
(
    id SERIAL PRIMARY KEY,
    name text UNIQUE not null,
    owner SERIAL,
    FOREIGN KEY (owner)  REFERENCES chat.User (id)
    );

create table if not exists chat.Message
(
    id SERIAL PRIMARY KEY,
    author SERIAL,
    room SERIAL,
    text_msg text not null,
    datetime timestamp,
    FOREIGN KEY (author)  REFERENCES chat.User (id),
    FOREIGN KEY (room)  REFERENCES chat.Chatroom (id)
    );

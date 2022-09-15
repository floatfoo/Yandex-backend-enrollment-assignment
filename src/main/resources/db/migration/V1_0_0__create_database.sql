create type system_item_type as enum (
    'file',
    'folder'
    );

create table system_item (
    date timestamp default current_timestamp
    );

create table folder (
    id varchar(255) primary key,
    parent_id varchar(255) references folder(id)
) inherits (system_item);

create table file (
    id varchar(255) primary key,
    parent_id varchar(255) references folder(id),
    size integer,
    url text
) inherits (system_item);
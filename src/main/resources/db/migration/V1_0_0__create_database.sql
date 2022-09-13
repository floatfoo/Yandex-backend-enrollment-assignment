create type system_item_type as enum (
    'file',
    'folder'
    );

create table system_item (
    date timestamptz default current_timestamp
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

create table folder_children (
    parent_folder varchar(255) references folder(id),
    child_folder varchar(255) references folder(id),
    child_file varchar(255) references file(id),
    PRIMARY KEY (parent_folder, child_folder, child_file)
);
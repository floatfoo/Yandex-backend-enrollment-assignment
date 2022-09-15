create table system_item (
    date timestamptz
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

create table folder_history (
    id varchar(255),
    start_date timestamptz,
    end_date timestamptz,
    parent_id varchar(255),
    parent_start_date timestamptz,
    parent_end_date timestamptz,
    UNIQUE (parent_id, parent_start_date, parent_end_date),
    PRIMARY KEY (id, start_date, end_date),
    FOREIGN KEY (parent_id, parent_start_date, parent_end_date) REFERENCES folder_history(id, start_date, end_date)
);

create table file_history (
    id varchar(255),
    start_date timestamp,
    end_date timestamp,
    parent_id varchar(255),
    parent_start_date timestamptz,
    parent_end_date timestamptz,
    size integer,
    url text,
    PRIMARY KEY (id, start_date, end_date),
    FOREIGN KEY (parent_id, parent_start_date, parent_end_date) REFERENCES folder_history(id, start_date, end_date)
);
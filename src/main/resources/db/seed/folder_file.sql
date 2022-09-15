INSERT INTO folder(id)
VALUES ('DIR_1');

INSERT INTO folder(id, parent_id)
VALUES ('DIR_2', 'DIR_1');

INSERT INTO folder(id, parent_id)
VALUES ('DIR_3', 'DIR_1');

INSERT INTO file(id, url, parent_id)
VALUES ('FILE_1', '~/Documents/Projects', 'DIR_1');

INSERT INTO file(id, url, parent_id)
VALUES ('FILE_2', '~/Documents/Projects', 'DIR_2');

INSERT INTO file(id, url, parent_id)
VALUES ('FILE_3', '~/Documents/Projects', 'DIR_3');
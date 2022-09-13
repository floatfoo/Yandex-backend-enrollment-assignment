INSERT INTO folder(id, type)
VALUES ('DIR_1', 'folder');

INSERT INTO folder(id, type, parent_id)
VALUES ('DIR_2', 'folder', 'DIR_1');

INSERT INTO folder(id, type, parent_id)
VALUES ('DIR_3', 'folder', 'DIR_1');

INSERT INTO file(id, type, url, parent_id)
VALUES ('FILE_1', 'file', '~/Documents/Projects', 'DIR_1');

INSERT INTO file(id, type, url, parent_id)
VALUES ('FILE_2', 'file', '~/Documents/Projects', 'DIR_2');

INSERT INTO file(id, type, url, parent_id)
VALUES ('FILE_3', 'file', '~/Documents/Projects', 'DIR_3');

INSERT INTO folder_children(parent_folder, child_folder, child_file)
VALUES ('DIR_1', 'DIR_2', 'FILE_1');
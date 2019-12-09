INSERT INTO users (username, password, enabled)
values ('user',
        '$2y$12$uibsCxjdbW6txxSbO8MN/uFnO1JOCnu1.rLFTcu3nkVQq9RmhTsOW',
        1);

INSERT INTO authorities (username, authority)
values ('user', 'ROLE_USER');
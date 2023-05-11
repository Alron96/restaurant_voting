INSERT INTO USERS (EMAIL, NAME, PASSWORD)
VALUES ('admin@gmail.com', 'admin', '{noop}admin_password'),
       ('user_1@gmail.ru', 'user_1', '{noop}user_password_1'),
       ('user_2@gmail.ru', 'user_2', '{noop}user_password_2'),
       ('guest@gmail.ru', 'guest', '{noop}guest_password');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3);

INSERT INTO RESTAURANT (NAME)
VALUES ('Клод Мане'),
       ('Хачапури и Вино'),
       ('KFC');

INSERT INTO VOTE (USER_ID, VOTE_DATE, RESTAURANT_ID)
VALUES (2, now(), 1),

       (2, now() - 1, 2),
       (3, now() - 1, 2);

INSERT INTO DISH (NAME, DISH_DATE, PRICE, RESTAURANT_ID)
VALUES ('Бефстроганов', now(), 1500, 1),
       ('Борщ', now(), 1000, 1),
       ('Оливье', now(), 700, 1),

       ('Вчерашний Бефстроганов', now() - 1, 1500, 1),
       ('Вчерашний Борщ', now() - 1, 1000, 1),
       ('Вчерашнее Оливье', now() - 1, 700, 1),

       ('Хачапури', now(), 800, 2),
       ('Вино', now(), 1200, 2),

       ('Вчерашний Хачапури', now() - 1, 800, 2),
       ('Вчерашнее Вино', now() - 1, 1200, 2),

       ('Кола', now(), 100, 3),
       ('Бургер', now(), 150, 3),
       ('Баскет', now(), 400, 3),
       ('Мороженое', now(), 100, 3),

       ('Вчерашняя Кола', now() - 1, 100, 3),
       ('Вчерашний Бургер', now() - 1, 150, 3),
       ('Вчерашний Баскет', now() - 1, 400, 3);
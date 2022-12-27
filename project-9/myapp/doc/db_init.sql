CREATE TABLE user(
    `id` VARCHAR(40) NOT NULL,
    `name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    `user` VARCHAR(50) ,
    `password` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
    PRIMARY KEY (`id`)
)

insert into user ()
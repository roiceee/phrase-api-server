CREATE SCHEMA IF NOT EXISTS`phrase-api` ;

CREATE TABLE IF NOT EXISTS`phrase-api`.`jokes` (
                                                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `phrase` MEDIUMTEXT NOT NULL,
                                                       PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS`phrase-api`.`quotes` (
                                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `author` VARCHAR(45) NOT NULL,
                                                        `phrase` MEDIUMTEXT NOT NULL,
                                                        PRIMARY KEY (`id`));
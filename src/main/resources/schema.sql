CREATE SCHEMA IF NOT EXISTS`quote-joke-api` ;

CREATE TABLE IF NOT EXISTS`quote-joke-api`.`jokes` (
                                                       `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `joke` MEDIUMTEXT NOT NULL,
                                                       PRIMARY KEY (`id`));

ALTER TABLE `quote-joke-api`.`jokes`
    CHANGE COLUMN `joke` `phrase` MEDIUMTEXT NOT NULL ;

CREATE TABLE IF NOT EXISTS`quote-joke-api`.`quotes` (
                                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `author` VARCHAR(45) NOT NULL,
                                                        `quote` MEDIUMTEXT NOT NULL,
                                                        PRIMARY KEY (`id`));
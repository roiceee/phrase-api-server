CREATE SCHEMA IF NOT EXISTS`phrase-api` ;

CREATE TABLE IF NOT EXISTS `jokes` (
                         `id` int DEFAULT NULL,
                         `phrase` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `quotes` (
                          `id` int DEFAULT NULL,
                          `author` text,
                          `phrase` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `api_keys` (
                            `id` varchar(255) NOT NULL,
                            `api_key` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `phrase-api`.`request_count` (
                                              `request_number` INT NOT NULL AUTO_INCREMENT,
                                              `api_key` VARCHAR(255) NOT NULL,
                                              PRIMARY KEY (`request_number`));
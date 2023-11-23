CREATE SCHEMA IF NOT EXISTS `hangil` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

use hangil;

DROP TABLE IF EXISTS `hangil`.`memo` ;
DROP TABLE IF EXISTS `hangil`.`board_file` ;
DROP TABLE IF EXISTS `hangil`.`hash_tag` ;
DROP TABLE IF EXISTS `hangil`.`search_content` ;
DROP TABLE IF EXISTS `hangil`.`board_storage_content` ;
DROP TABLE IF EXISTS `hangil`.`board_storage` ;
DROP TABLE IF EXISTS `hangil`.`plan_storage_content` ;
DROP TABLE IF EXISTS `hangil`.`plan_storage` ;
DROP TABLE IF EXISTS `hangil`.`board` ;
DROP TABLE IF EXISTS `hangil`.`user` ;

CREATE TABLE IF NOT EXISTS `hangil`.`user` (
    `user_id` VARCHAR(45) NOT NULL,
    `user_pw` VARCHAR(45) NOT NULL,
    `user_name` VARCHAR(45) NOT NULL,
    `user_nickname` VARCHAR(45) NOT NULL,
    `profile_picture` VARCHAR(255) DEFAULT 'QmRuPFZMo63vKqtYE7sz7zS3MCbYRKn262uJp6zeJ3irKH',
    `user_token` varchar(1000) null default null,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `hangil`.`board` (
  `board_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `board_title` VARCHAR(45) NOT NULL,
  `board_content` VARCHAR(2000) NOT NULL,
  `board_like` INT NULL DEFAULT 0,
  `board_place` VARCHAR(300) NOT NULL,
  `board_latitude` DOUBLE NULL DEFAULT 0.0,
  `board_longitude` DOUBLE NULL DEFAULT 0.0,
  `board_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`board_no`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_user_id_to_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `hangil`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `hangil`.`memo` (
  `memo_no` INT NOT NULL AUTO_INCREMENT,
  `board_no` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `memo_content` VARCHAR(2000) NOT NULL,
  `memo_date` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`memo_no`),
  INDEX `board_no_idx` (`board_no` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_no`
    FOREIGN KEY (`board_no`)
    REFERENCES `hangil`.`board` (`board_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `hangil`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `hangil`.`board_file` (
  `board_file_no` INT NOT NULL AUTO_INCREMENT,
  `board_no` INT NOT NULL,
  `board_file_cid` VARCHAR(255) NULL,
  PRIMARY KEY (`board_file_no`),
  INDEX `board_file_board_no_to_board_board_no_fk_idx` (`board_no` ASC) VISIBLE,
  CONSTRAINT `board_file_board_no_to_board_board_no_fk`
    FOREIGN KEY (`board_no`)
    REFERENCES `hangil`.`board` (`board_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `hangil`.`hash_tag` (
  `hash_tag_no` INT NOT NULL AUTO_INCREMENT,
  `hash_tag_content` VARCHAR(100) NOT NULL,
  `board_no` INT NOT NULL,
  PRIMARY KEY (`hash_tag_no`),
  INDEX `hash_tag_board_no_to_board_board_no_fk_idx` (`board_no` ASC) VISIBLE,
  CONSTRAINT `hash_tag_board_no_to_board_board_no_fk`
    FOREIGN KEY (`board_no`)
    REFERENCES `hangil`.`board` (`board_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `hangil`.`search_content` (
    `search_data` VARCHAR(50) NOT NULL,
    `search_count` INT NULL,
    PRIMARY KEY (`search_data`)
);

CREATE TABLE `hangil`.`board_storage` (
  `board_storage_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `board_storage_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`board_storage_no`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_storage_user_id_to_user_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `hangil`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE TABLE `hangil`.`plan_storage` (
  `plan_storage_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `plan_storage_name` varchar(45) NOT NULL,
  PRIMARY KEY (`plan_storage_no`),
  KEY `plan_storage_user_id_to_user_user_id_fk_idx` (`user_id`),
  CONSTRAINT `plan_storage_user_id_to_user_user_id_fk` 
    FOREIGN KEY (`user_id`) 
    REFERENCES `user` (`user_id`)
) ;

CREATE TABLE `hangil`.`board_storage_content` (
  `board_storage_content_no` INT NOT NULL AUTO_INCREMENT,
  `board_storage_no` INT NOT NULL,
  `board_no` INT NOT NULL,
  PRIMARY KEY (`board_storage_content_no`),
  INDEX `board_storage_no_idx` (`board_storage_no` ASC) VISIBLE,
  INDEX `board_no_idx` (`board_no` ASC) VISIBLE,
  CONSTRAINT `storage_content_board_storage_no_to_storage_board_storage_no_fk`
    FOREIGN KEY (`board_storage_no`)
    REFERENCES `hangil`.`board_storage` (`board_storage_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `board_storage_content_board_no_to_board_board_no_fk`
    FOREIGN KEY (`board_no`)
    REFERENCES `hangil`.`board` (`board_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `plan_storage_content` (
  `plan_storage_content_no` int NOT NULL AUTO_INCREMENT,
  `plan_storage_no` int NOT NULL,
  `board_no` int NOT NULL,
  PRIMARY KEY (`plan_storage_content_no`),
  KEY `plan_storage_content_plan_storage_no_to_plan_storage_plan_s_idx` (`plan_storage_no`),
  KEY `board_board_no_to_plan_storage_content_board_no_idx` (`board_no`),
  CONSTRAINT `board_board_no_to_plan_storage_content_board_no` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`),
  CONSTRAINT `plan_content_plan_storage_no_to_plan_storage_plan_storage_no_fk` FOREIGN KEY (`plan_storage_no`) REFERENCES `plan_storage` (`plan_storage_no`)
);

insert into user(user_id, user_pw, user_name, user_nickname)
value("ssafy", "1234", "김싸피", "싸피");

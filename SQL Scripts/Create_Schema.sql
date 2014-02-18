SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `Group3Hospital` ;
CREATE SCHEMA IF NOT EXISTS `Group3Hospital` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Group3Hospital` ;

-- -----------------------------------------------------
-- Table `Group3Hospital`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`employee` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`employee` (
  `employeeid` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `telephone_number` VARCHAR(45) NULL,
  `date_of_employment` VARCHAR(45) NULL,
  PRIMARY KEY (`employeeid`),
  FULLTEXT INDEX `FULLTEXT FIRST` (`first_name` ASC),
  FULLTEXT INDEX `FULLTEXT LAST` (`last_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`assistant_nurse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`assistant_nurse` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`assistant_nurse` (
  `employeeid` INT NOT NULL,
  PRIMARY KEY (`employeeid`),
  CONSTRAINT `fk_assistant_nurse_employee1`
    FOREIGN KEY (`employeeid`)
    REFERENCES `Group3Hospital`.`employee` (`employeeid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`nurse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`nurse` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`nurse` (
  `employeeid` INT NOT NULL,
  PRIMARY KEY (`employeeid`),
  INDEX `fk_employee_id_idx` (`employeeid` ASC),
  CONSTRAINT `fk_nurse_employee1`
    FOREIGN KEY (`employeeid`)
    REFERENCES `Group3Hospital`.`employee` (`employeeid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`doctor` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`doctor` (
  `employeeid` INT NOT NULL,
  PRIMARY KEY (`employeeid`),
  CONSTRAINT `fk_doctor_employee`
    FOREIGN KEY (`employeeid`)
    REFERENCES `Group3Hospital`.`employee` (`employeeid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`workplace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`workplace` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`workplace` (
  `workplaceid` INT NOT NULL,
  `workplace_name` VARCHAR(255) NULL,
  PRIMARY KEY (`workplaceid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`ward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`ward` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`ward` (
  `workplaceid` INT NOT NULL,
  PRIMARY KEY (`workplaceid`),
  CONSTRAINT `fk_ward_workplace1`
    FOREIGN KEY (`workplaceid`)
    REFERENCES `Group3Hospital`.`workplace` (`workplaceid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`clinic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`clinic` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`clinic` (
  `workplaceid` INT NOT NULL,
  PRIMARY KEY (`workplaceid`),
  CONSTRAINT `fk_clinic_workplace1`
    FOREIGN KEY (`workplaceid`)
    REFERENCES `Group3Hospital`.`workplace` (`workplaceid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`employee_workplace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`employee_workplace` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`employee_workplace` (
  `employeeid` INT NOT NULL,
  `workplaceid` INT NOT NULL,
  PRIMARY KEY (`employeeid`, `workplaceid`),
  INDEX `fk_employee_workplace_workplace1_idx` (`workplaceid` ASC),
  CONSTRAINT `fk_employee_workplace_workplace1`
    FOREIGN KEY (`workplaceid`)
    REFERENCES `Group3Hospital`.`workplace` (`workplaceid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_workplace_employee1`
    FOREIGN KEY (`employeeid`)
    REFERENCES `Group3Hospital`.`employee` (`employeeid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`specialization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`specialization` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`specialization` (
  `specializationid` INT NOT NULL,
  `specialization_name` VARCHAR(45) NULL,
  PRIMARY KEY (`specializationid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`doctor_specialization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`doctor_specialization` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`doctor_specialization` (
  `employeeid` INT NOT NULL,
  `specializationid` INT NOT NULL,
  PRIMARY KEY (`employeeid`, `specializationid`),
  INDEX `fk_employee_specializations_specializations1_idx` (`specializationid` ASC),
  CONSTRAINT `fk_employee_specializations_doctor1`
    FOREIGN KEY (`employeeid`)
    REFERENCES `Group3Hospital`.`doctor` (`employeeid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_specializations_specializations1`
    FOREIGN KEY (`specializationid`)
    REFERENCES `Group3Hospital`.`specialization` (`specializationid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`nurse_specialization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`nurse_specialization` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`nurse_specialization` (
  `employeeid` INT NOT NULL,
  `specializationid` INT NOT NULL,
  PRIMARY KEY (`employeeid`, `specializationid`),
  INDEX `fk_nurse_specializations_specializations1_idx` (`specializationid` ASC),
  CONSTRAINT `fk_nurse_specializations_nurse1`
    FOREIGN KEY (`employeeid`)
    REFERENCES `Group3Hospital`.`nurse` (`employeeid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nurse_specializations_specializations1`
    FOREIGN KEY (`specializationid`)
    REFERENCES `Group3Hospital`.`specialization` (`specializationid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Group3Hospital`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Group3Hospital`.`user` ;

CREATE TABLE IF NOT EXISTS `Group3Hospital`.`user` (
  `user_id` INT NOT NULL,
  `password` VARCHAR(255) NULL,
  `salt` INT(64) NULL,
  `type` INT(8) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

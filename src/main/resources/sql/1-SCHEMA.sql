DROP SCHEMA IF EXISTS `computer-database-db`;
CREATE SCHEMA IF NOT EXISTS `computer-database-db`;
USE `computer-database-db`;

DROP TABLE IF EXISTS computer;
DROP TABLE IF EXISTS company;

CREATE TABLE company (
  id   BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE computer (
  id           BIGINT    NOT NULL AUTO_INCREMENT,
  name         VARCHAR(255),
  introduced   TIMESTAMP NULL,
  discontinued TIMESTAMP NULL,
  company_id   BIGINT             DEFAULT NULL,
  CONSTRAINT pk_computer PRIMARY KEY (id)
);

CREATE TABLE admin (
  id       BIGINT       NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  CONSTRAINT pk_admin PRIMARY KEY (id)
);

ALTER TABLE computer
  ADD CONSTRAINT fk_computer_company_1 FOREIGN KEY (company_id) REFERENCES company (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;
CREATE INDEX ix_computer_company_1
  ON computer (company_id);
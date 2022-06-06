-- Create --

CREATE SCHEMA IF NOT EXISTS diary_api;

CREATE TABLE
    IF NOT EXISTS
    diary_api.diary(
                       id    SERIAL PRIMARY KEY,
                       value INT
);


-- Roll back --

DROP SCHEMA IF EXISTS diary_api;

DROP TABLE IF EXISTS diary_api.diary;
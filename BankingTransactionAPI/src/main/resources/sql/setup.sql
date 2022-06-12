-- Create --

CREATE SCHEMA IF NOT EXISTS banking_transaction_api;
CREATE TABLE
    IF NOT EXISTS
    banking_transaction_api.transactions(
                       id    SERIAL PRIMARY KEY,
                       descriptionOrderAccount VARCHAR,
                       ibanOrderAccount VARCHAR,
                       bicOrderAccount VARCHAR,
                       bankNameOrderAccount VARCHAR,
                       bookingDay DATE,
                       valueDate DATE,
                       paymentPartyName VARCHAR,
                       paymentPartyIBAN VARCHAR,
                       paymentPartyBIC VARCHAR,
                       bookingText VARCHAR,
                       usageText VARCHAR,
                       amount NUMERIC,
                       currency VARCHAR,
                       creditBalanceAfterBooking NUMERIC,
                       notice VARCHAR,
                       category VARCHAR,
                       taxRelevant VARCHAR,
                       creditorID VARCHAR,
                       mandateReference VARCHAR
);


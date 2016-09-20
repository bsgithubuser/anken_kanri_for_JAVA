-- updateMUsersByLoginStateToLogin.sql
UPDATE
    M_USERS
SET
    LOGIN_STATE = TRUE,
    UPDATE_DATE = CURRENT_TIMESTAMP
WHERE
    USER_ID = /*userId*/

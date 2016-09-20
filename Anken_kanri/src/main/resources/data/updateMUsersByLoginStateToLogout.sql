-- updateMUsersByLoginStateToLogout.sql
UPDATE
    M_USERS
SET
    LOGIN_STATE = FALSE,
    UPDATE_DATE = CURRENT_TIMESTAMP
WHERE
    USER_ID = /*userId*/

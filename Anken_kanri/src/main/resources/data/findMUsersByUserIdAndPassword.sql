-- findMUserByUserIdAndPassword.sql
SELECT
    USER_ID,
    USER_NAME,
    PASSWORD,
    LOGIN_STATE,
    ADMIN
FROM
    M_USERS
WHERE
    USER_ID = /*userId*/
AND
    PASSWORD = /*password*/

SELECT
    PRJ_ID,
    PRJ_NAME,
    USER_ID,
    CMPN_ID,
    PRJ_SKL_ID,
    GEN_DATE,
    PERI_FROM,
    PERI_TO,
    LONG_TERM_FLG,
    SAME_DAY_FLG,
    ANY_TIME_FLG,
    EXTENTION_FLG,
    ORVERVIEW,
    OTHER,
    UPDATE_DATE
FROM
    T_PROJ_INFO
WHERE
    PRJ_ID = /*prjId*/
 AND
    DEL_FLG = false
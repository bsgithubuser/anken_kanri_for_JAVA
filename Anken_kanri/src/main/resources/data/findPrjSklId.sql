-- findPrjSklId.sql
SELECT
    prj_skl_id
FROM
    t_proj_skill
/*IF skillId != null*/
WHERE
     skl_id IN /*skillId*/(1,2)
/*END*/
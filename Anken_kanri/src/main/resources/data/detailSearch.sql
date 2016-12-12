-- detailSearch.sql
SELECT
    t_proj_info.prj_id,
    t_proj_info.prj_name,
    m_cmpn.cmpn_name,
    m_users.user_name,
    t_proj_info.gen_date,
    t_proj_info.peri_from,
    t_proj_info.peri_to,
    t_proj_info.long_term_flg,
    t_proj_info.same_day_flg,
    t_proj_info.any_time_flg,
    extracted_skill.skill_name,
    t_proj_info.extention_flg,
    t_proj_info.update_date
FROM
    t_proj_info
INNER JOIN
    m_cmpn
ON
    t_proj_info.cmpn_id = m_cmpn.cmpn_id
    /*IF compName != ""*/
    AND
    m_cmpn.cmpn_name LIKE '%' || /*compName*/'compName' || '%'
    /*END*/
INNER JOIN
    m_users
ON
    t_proj_info.user_id = m_users.user_id
    /*IF userName != "全検索"*/
    AND
    m_users.user_name LIKE '%' || /*userName*/'userName' || '%'
    /*END*/
INNER JOIN
    (
      SELECT
          t_proj_skill.prj_skl_id,
          m_skill.skill_id,
          m_skill.skill_name
      FROM
          t_proj_skill
      INNER JOIN
          m_skill
      ON
          t_proj_skill.skl_id = m_skill.skill_id
      /*IF prjSklIdCnt != 0*/
      WHERE
      t_proj_skill.prj_skl_id IN /*prjSklId*/(1,2)
      /*END*/
    ) extracted_skill
ON
    t_proj_info.prj_skl_id = extracted_skill.prj_skl_id
WHERE
    /*IF periStDt != null && periEnDt != null*/
    t_proj_info.gen_date BETWEEN /*periStDt*/ AND /*periEnDt*/
    AND
    /*END*/

    /*IF periStDt == null || periEnDt == null*/
      /*IF periStDt != null*/
       t_proj_info.gen_date >= /*periStDt*/
       AND
      /*END*/
      /*IF periEnDt != null*/
       t_proj_info.gen_date <= /*periEnDt*/
       AND
      /*END*/

      /*IF lngTrmFlg == true*/
       t_proj_info.long_term_flg = /*lngTrmFlg*/
       AND
      /*END*/
      /*IF smDyFlg == true*/
       t_proj_info.same_day_flg = /*smDyFlg*/
       AND
      /*END*/
      /*IF anyTmFlg == true*/
       t_proj_info.any_time_flg = /*anyTmFlg*/
       AND
      /*END*/
      /*IF searchExt != "none"*/
       t_proj_info.extention_flg = /*exteFlg*/
       AND
      /*END*/

      /*IF overview != ""*/
       t_proj_info.orverview LIKE '%' || /*overview*/'overview' || '%'
       AND
      /*END*/
    /*END*/
   t_proj_info.del_flg = false
ORDER BY
    t_proj_info.gen_date,
    t_proj_info.prj_id,
    m_users.user_name


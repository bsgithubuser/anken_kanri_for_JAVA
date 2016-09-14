package jp.co.bsja.anken.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * MSkillエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/09/12 14:42:12")
public class MSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    /** skillIdプロパティ */
    @Id
    @Column(precision = 10, nullable = false, unique = true)
    public Integer skillId;

    /** skillNameプロパティ */
    @Column(length = 40, nullable = false, unique = false)
    public String skillName;

    /** createDateプロパティ */
    @Column(nullable = true, unique = false)
    public Timestamp createDate;

    /** updateDateプロパティ */
    @Column(nullable = true, unique = false)
    public Timestamp updateDate;
}
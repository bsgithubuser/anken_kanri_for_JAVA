package jp.co.bsja.anken.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TProjSkillエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/09/12 14:42:12")
public class TProjSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    /** prjSklIdプロパティ */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer prjSklId;

    /** sklIdプロパティ */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer sklId;

    /** otherプロパティ */
    @Column(length = 20, nullable = false, unique = false)
    public String other;

    /** createDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** updateDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;
}
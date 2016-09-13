package jp.co.bsja.anken.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MSkill}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/09/12 17:12:05")
public class MSkillNames {

    /**
     * skillIdのプロパティ名を返します。
     * 
     * @return skillIdのプロパティ名
     */
    public static PropertyName<Integer> skillId() {
        return new PropertyName<Integer>("skillId");
    }

    /**
     * skillNameのプロパティ名を返します。
     * 
     * @return skillNameのプロパティ名
     */
    public static PropertyName<String> skillName() {
        return new PropertyName<String>("skillName");
    }

    /**
     * createDateのプロパティ名を返します。
     * 
     * @return createDateのプロパティ名
     */
    public static PropertyName<Timestamp> createDate() {
        return new PropertyName<Timestamp>("createDate");
    }

    /**
     * updateDateのプロパティ名を返します。
     * 
     * @return updateDateのプロパティ名
     */
    public static PropertyName<Timestamp> updateDate() {
        return new PropertyName<Timestamp>("updateDate");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MSkillNames extends PropertyName<MSkill> {

        /**
         * インスタンスを構築します。
         */
        public _MSkillNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MSkillNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _MSkillNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * skillIdのプロパティ名を返します。
         *
         * @return skillIdのプロパティ名
         */
        public PropertyName<Integer> skillId() {
            return new PropertyName<Integer>(this, "skillId");
        }

        /**
         * skillNameのプロパティ名を返します。
         *
         * @return skillNameのプロパティ名
         */
        public PropertyName<String> skillName() {
            return new PropertyName<String>(this, "skillName");
        }

        /**
         * createDateのプロパティ名を返します。
         *
         * @return createDateのプロパティ名
         */
        public PropertyName<Timestamp> createDate() {
            return new PropertyName<Timestamp>(this, "createDate");
        }

        /**
         * updateDateのプロパティ名を返します。
         *
         * @return updateDateのプロパティ名
         */
        public PropertyName<Timestamp> updateDate() {
            return new PropertyName<Timestamp>(this, "updateDate");
        }
    }
}
package jp.co.bsja.anken.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link TProjSkill}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/09/12 17:12:05")
public class TProjSkillNames {

    /**
     * prjSklIdのプロパティ名を返します。
     * 
     * @return prjSklIdのプロパティ名
     */
    public static PropertyName<Integer> prjSklId() {
        return new PropertyName<Integer>("prjSklId");
    }

    /**
     * sklIdのプロパティ名を返します。
     * 
     * @return sklIdのプロパティ名
     */
    public static PropertyName<Integer> sklId() {
        return new PropertyName<Integer>("sklId");
    }

    /**
     * otherのプロパティ名を返します。
     * 
     * @return otherのプロパティ名
     */
    public static PropertyName<String> other() {
        return new PropertyName<String>("other");
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
    public static class _TProjSkillNames extends PropertyName<TProjSkill> {

        /**
         * インスタンスを構築します。
         */
        public _TProjSkillNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TProjSkillNames(final String name) {
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
        public _TProjSkillNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * prjSklIdのプロパティ名を返します。
         *
         * @return prjSklIdのプロパティ名
         */
        public PropertyName<Integer> prjSklId() {
            return new PropertyName<Integer>(this, "prjSklId");
        }

        /**
         * sklIdのプロパティ名を返します。
         *
         * @return sklIdのプロパティ名
         */
        public PropertyName<Integer> sklId() {
            return new PropertyName<Integer>(this, "sklId");
        }

        /**
         * otherのプロパティ名を返します。
         *
         * @return otherのプロパティ名
         */
        public PropertyName<String> other() {
            return new PropertyName<String>(this, "other");
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
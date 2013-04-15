package com.huinfo.auth.as.model;

import java.util.ArrayList;
import java.util.List;

public class TrustedDomainExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public TrustedDomainExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("domain in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("domain not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andClientidIsNull() {
            addCriterion("clientID is null");
            return (Criteria) this;
        }

        public Criteria andClientidIsNotNull() {
            addCriterion("clientID is not null");
            return (Criteria) this;
        }

        public Criteria andClientidEqualTo(Long value) {
            addCriterion("clientID =", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotEqualTo(Long value) {
            addCriterion("clientID <>", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidGreaterThan(Long value) {
            addCriterion("clientID >", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidGreaterThanOrEqualTo(Long value) {
            addCriterion("clientID >=", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLessThan(Long value) {
            addCriterion("clientID <", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLessThanOrEqualTo(Long value) {
            addCriterion("clientID <=", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidIn(List<Long> values) {
            addCriterion("clientID in", values, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotIn(List<Long> values) {
            addCriterion("clientID not in", values, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidBetween(Long value1, Long value2) {
            addCriterion("clientID between", value1, value2, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotBetween(Long value1, Long value2) {
            addCriterion("clientID not between", value1, value2, "clientid");
            return (Criteria) this;
        }

        public Criteria andAttributeNameIsNull() {
            addCriterion("attribute_name is null");
            return (Criteria) this;
        }

        public Criteria andAttributeNameIsNotNull() {
            addCriterion("attribute_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttributeNameEqualTo(String value) {
            addCriterion("attribute_name =", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameNotEqualTo(String value) {
            addCriterion("attribute_name <>", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameGreaterThan(String value) {
            addCriterion("attribute_name >", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameGreaterThanOrEqualTo(String value) {
            addCriterion("attribute_name >=", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameLessThan(String value) {
            addCriterion("attribute_name <", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameLessThanOrEqualTo(String value) {
            addCriterion("attribute_name <=", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameLike(String value) {
            addCriterion("attribute_name like", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameNotLike(String value) {
            addCriterion("attribute_name not like", value, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameIn(List<String> values) {
            addCriterion("attribute_name in", values, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameNotIn(List<String> values) {
            addCriterion("attribute_name not in", values, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameBetween(String value1, String value2) {
            addCriterion("attribute_name between", value1, value2, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeNameNotBetween(String value1, String value2) {
            addCriterion("attribute_name not between", value1, value2, "attributeName");
            return (Criteria) this;
        }

        public Criteria andAttributeValueIsNull() {
            addCriterion("attribute_value is null");
            return (Criteria) this;
        }

        public Criteria andAttributeValueIsNotNull() {
            addCriterion("attribute_value is not null");
            return (Criteria) this;
        }

        public Criteria andAttributeValueEqualTo(String value) {
            addCriterion("attribute_value =", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueNotEqualTo(String value) {
            addCriterion("attribute_value <>", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueGreaterThan(String value) {
            addCriterion("attribute_value >", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueGreaterThanOrEqualTo(String value) {
            addCriterion("attribute_value >=", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueLessThan(String value) {
            addCriterion("attribute_value <", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueLessThanOrEqualTo(String value) {
            addCriterion("attribute_value <=", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueLike(String value) {
            addCriterion("attribute_value like", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueNotLike(String value) {
            addCriterion("attribute_value not like", value, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueIn(List<String> values) {
            addCriterion("attribute_value in", values, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueNotIn(List<String> values) {
            addCriterion("attribute_value not in", values, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueBetween(String value1, String value2) {
            addCriterion("attribute_value between", value1, value2, "attributeValue");
            return (Criteria) this;
        }

        public Criteria andAttributeValueNotBetween(String value1, String value2) {
            addCriterion("attribute_value not between", value1, value2, "attributeValue");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trusted_domain
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 14 16:52:01 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trusted_domain
     *
     * @mbggenerated Sun Apr 14 16:52:01 CST 2013
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
package com.huinfo.auth.as.model;

import java.util.ArrayList;
import java.util.List;

public class ClientScopesExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public ClientScopesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
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
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
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

        public Criteria andClientIdIsNull() {
            addCriterion("CLIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("CLIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(Long value) {
            addCriterion("CLIENT_ID =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(Long value) {
            addCriterion("CLIENT_ID <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(Long value) {
            addCriterion("CLIENT_ID >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CLIENT_ID >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(Long value) {
            addCriterion("CLIENT_ID <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(Long value) {
            addCriterion("CLIENT_ID <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<Long> values) {
            addCriterion("CLIENT_ID in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<Long> values) {
            addCriterion("CLIENT_ID not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(Long value1, Long value2) {
            addCriterion("CLIENT_ID between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(Long value1, Long value2) {
            addCriterion("CLIENT_ID not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andElementIsNull() {
            addCriterion("element is null");
            return (Criteria) this;
        }

        public Criteria andElementIsNotNull() {
            addCriterion("element is not null");
            return (Criteria) this;
        }

        public Criteria andElementEqualTo(String value) {
            addCriterion("element =", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotEqualTo(String value) {
            addCriterion("element <>", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementGreaterThan(String value) {
            addCriterion("element >", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementGreaterThanOrEqualTo(String value) {
            addCriterion("element >=", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementLessThan(String value) {
            addCriterion("element <", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementLessThanOrEqualTo(String value) {
            addCriterion("element <=", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementLike(String value) {
            addCriterion("element like", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotLike(String value) {
            addCriterion("element not like", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementIn(List<String> values) {
            addCriterion("element in", values, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotIn(List<String> values) {
            addCriterion("element not in", values, "element");
            return (Criteria) this;
        }

        public Criteria andElementBetween(String value1, String value2) {
            addCriterion("element between", value1, value2, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotBetween(String value1, String value2) {
            addCriterion("element not between", value1, value2, "element");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table client_scopes
     *
     * @mbggenerated do_not_delete_during_merge Sat Apr 13 14:41:24 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table client_scopes
     *
     * @mbggenerated Sat Apr 13 14:41:24 CST 2013
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
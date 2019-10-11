package com.java.jd.bean;

import java.util.List;

public class FieldBean {
    private String field;
    private List<String> values;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "FieldBean [field=" + field + ", values=" + values + "]";
    }

}

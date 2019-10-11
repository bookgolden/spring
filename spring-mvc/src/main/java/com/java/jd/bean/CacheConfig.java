package com.java.jd.bean;

import java.util.List;

public class CacheConfig {

    private String objName;
    private String className;
    private Class clazz;
    private List<FieldBean> fieldBeans;
    private List<String> fieldNames;

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<FieldBean> getFieldBeans() {
        return fieldBeans;
    }

    public void setFieldBeans(List<FieldBean> fieldBeans) {
        this.fieldBeans = fieldBeans;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    @Override
    public String toString() {
        return "CacheConfig [objName=" + objName + ", className=" + className + ", clazz=" + clazz + ", fieldBeans="
                + fieldBeans + ", fieldNames=" + fieldNames + "]";
    }

}

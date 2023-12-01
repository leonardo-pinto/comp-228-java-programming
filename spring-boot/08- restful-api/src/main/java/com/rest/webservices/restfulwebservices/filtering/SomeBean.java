package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties("value1") ignore at class level instead of field level
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String value1;
//    @JsonIgnore
    private String value2;

    public SomeBean(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }
}

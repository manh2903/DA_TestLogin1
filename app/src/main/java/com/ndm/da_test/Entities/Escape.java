package com.ndm.da_test.Entities;

import java.io.Serializable;

public class Escape implements Serializable {

    private String name;

    public Escape(String name) {
        this.name = name;
    }

    public Escape() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


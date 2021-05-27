package com.dlnegoce.exam.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatalogPOJO {
    private String name;
    private List<Item> children;

    public CatalogPOJO(@JsonProperty("name") String name, @JsonProperty("children")List<Item> children){
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        s.append(this.name).append("\n");

        for(Item item : children)
            s.append(item.toString()).append("\n");

        return s.toString();
    }
}

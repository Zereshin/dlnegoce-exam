package com.dlnegoce.exam.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatalogPOJO {
    private String name;
    private List<ItemPOJO> children;

    public CatalogPOJO(@JsonProperty("name") String name, @JsonProperty("children")List<ItemPOJO> children){
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemPOJO> getChildren() {
        return children;
    }

    public void setChildren(List<ItemPOJO> children) {
        this.children = children;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        s.append(this.name).append("\n");

        for(ItemPOJO item : children)
            s.append(item.toString()).append("\n");

        return s.toString();
    }
}

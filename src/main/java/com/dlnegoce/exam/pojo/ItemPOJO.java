package com.dlnegoce.exam.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.util.List;

public class ItemPOJO {
    private String code;
    private String path;
    private String name;
    private int count;
    private List<ItemPOJO> children;

    public ItemPOJO(@JsonProperty("code") String code, @JsonProperty("path") String path, @JsonProperty("name") String name, @JsonProperty("count") int count, @JsonProperty("children") List<ItemPOJO> children) {
        this.code = code;
        this.path = path;
        this.name = name;
        this.count = count;
        this.children = children;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

        s.append(String.format("%" + StringUtils.countOccurrencesOf(this.path, "/") + "s", (children.isEmpty())?"|":"-"));
        s.append(this.name).append((this.children.isEmpty())?" " + this.count:"").append("\n");

        for(ItemPOJO item : children)
            s.append(item.toString());

        return s.toString();
    }
}

package com.yovelas.entity;

import com.alibaba.fastjson.JSON;

public class Progress {

    private String name;
    private int status;
    private int total;
    private int current;

    public void increase(){
        current++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", total=" + total +
                ", current=" + current +
                '}';
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }

}

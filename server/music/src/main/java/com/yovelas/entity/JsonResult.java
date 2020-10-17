package com.yovelas.entity;

public class JsonResult {

    public enum Massage{FAIL,SUCCESS}
    private int status;
    private String massage;
    private Object data;

    public JsonResult() {
        this.status = 400;
        this.massage = "FAIL";
        this.data = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if(status != 200 && status != 400){
            status = 400;
        }else {
            this.status = status;
        }
    }

    public String getMassage() {
        return massage;
    }

    public JsonResult setMassage(String massage) {
        this.massage = massage;
        return  this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }
}

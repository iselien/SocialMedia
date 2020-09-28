package io.github.yovelas.entity;

public class JsonResult {

    private int status;
    private String message;
    private Object data;

    public JsonResult() {
        this.status = -1;
        this.message = "FAIL";
        this.data = null;
    }

    public int getStatus() {
        return status;
    }

    public JsonResult setStatus(int status) {
            this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResult setMessage(String message) {
        this.message = message;
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

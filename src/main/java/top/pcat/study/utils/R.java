package top.pcat.study.utils;

import lombok.Data;

@Data
public class R {
    private Integer status;
    private String msg;
    private Object data;


    public R(){}

    public R(int status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public R(int status,String msg ,Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


}

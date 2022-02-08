package top.pcat.study.utils;

import lombok.Data;

@Data
public class R {
    private Integer status;
    private String description;
    private Object data;


    public R(){}

    public R(int status,String description) {
        this.status = status;
        this.description = description;
    }

    public R(int status,String description ,Object data) {
        this.status = status;
        this.description = description;
        this.data = data;
    }


}

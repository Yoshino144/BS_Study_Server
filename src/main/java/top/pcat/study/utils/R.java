package top.pcat.study.utils;

import lombok.Data;

@Data
public class R {
    private Integer flag;

    private Object data;


    public R(){}

    public R(int flag) {
        this.flag = flag;
    }

    public R(int flag, Object data) {
        this.flag = flag;
        this.data = data;
    }


}

package com.kyle.pojo;

/**
 * Created by Java on 2016/11/3.
 */
public class Complex extends BaseButton{
    private BaseButton[] sub_button;

    public BaseButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(BaseButton[] sub_button) {
        this.sub_button = sub_button;
    }
}

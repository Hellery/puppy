package com.puppy.vo;

import lombok.Data;

@Data
public class Msg {

    private String title;
    private String content;
    private String extraInfo;

    public Msg(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }
}

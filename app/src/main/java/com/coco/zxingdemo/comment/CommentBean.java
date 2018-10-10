package com.coco.zxingdemo.comment;

/**
 * Created by ydx on 18-10-10.
 */

public class CommentBean {
    String name;//评论者
    String content;//评论内容

    public CommentBean() {
    }

    public CommentBean(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

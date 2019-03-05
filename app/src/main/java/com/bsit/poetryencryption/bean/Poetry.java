package com.bsit.poetryencryption.bean;

import java.io.Serializable;

/**
 * @Description 诗词实体
 * @Author fpp
 * @Date 2019/3/5 0005 22:46
 */
public class Poetry implements Serializable {

    private String name;
    private String content;
    private String encryptResult;

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

    public String getEncryptResult() {
        return encryptResult;
    }

    public void setEncryptResult(String encryptResult) {
        this.encryptResult = encryptResult;
    }
}

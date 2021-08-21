package com.example.tryapi;

public class SportModel {

    private String tvName;
    private String tvDescription;
    private String ivImage;

    public SportModel(String tvName, String tvDescription, String ivImage) {
        this.tvName = tvName;
        this.tvDescription = tvDescription;
        this.ivImage = ivImage;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvDescription() {
        return tvDescription;
    }

    public void setTvDescription(String tvDescription) {
        this.tvDescription = tvDescription;
    }

    public String getIvImage() {
        return ivImage;
    }

    public void setIvImage(String ivImage) {
        this.ivImage = ivImage;
    }
}

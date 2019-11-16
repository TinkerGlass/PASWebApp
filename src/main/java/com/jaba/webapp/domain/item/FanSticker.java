package com.jaba.webapp.domain.item;

public class FanSticker {
    private String stickerName;
    private String stickerContent;
    private int stickerPoints;

    public FanSticker() {}

    public FanSticker(String stickerName, String stickerContent, int stickerPoints) {
        this.stickerName = stickerName;
        this.stickerContent = stickerContent;
        this.stickerPoints = stickerPoints;
    }

    public String getStickerName() {
        return stickerName;
    }

    public void setStickerName(String stickerName) {
        this.stickerName = stickerName;
    }

    public String getStickerContent() {
        return stickerContent;
    }

    public void setStickerContent(String stickerContent) {
        this.stickerContent = stickerContent;
    }

    public int getStickerPoints() {
        return stickerPoints;
    }

    public void setStickerPoints(int stickerPoints) {
        this.stickerPoints = stickerPoints;
    }
}

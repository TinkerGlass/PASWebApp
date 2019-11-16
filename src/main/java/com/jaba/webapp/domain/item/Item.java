package com.jaba.webapp.domain.item;

import java.math.BigDecimal;
import java.util.Date;


public abstract class Item {
    private Long id;
    private BigDecimal price;
    private String title;
    private Date releaseDate;
    private FanSticker sticker;

    public Item(String title, BigDecimal price, Date releaseDate, FanSticker sticker) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.sticker = sticker;
    }

    public Item() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public FanSticker getSticker() {
        return sticker;
    }

    public void setSticker(FanSticker sticker) {
        this.sticker = sticker;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Item) && getId() != null && ((Item)o).getId().equals(getId());
    }
}

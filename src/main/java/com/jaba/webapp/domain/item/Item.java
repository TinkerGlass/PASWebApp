package com.jaba.webapp.domain.item;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


public abstract class Item {
    private Long id;
    @NotNull(message = "{general.validation.empty}")
    @DecimalMin(value="0", message = "{general.validation.minValue}")
    private BigDecimal price;
    @NotBlank(message = "{general.validation.empty}")
    private String title;
    @NotNull(message = "{general.validation.empty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @Valid
    private FanSticker sticker;
    private boolean available = true;

    public Item(String title, BigDecimal price, Date releaseDate, FanSticker sticker) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.sticker = sticker;
        this.available = true;
    }


    public Item(String title, BigDecimal price, Date releaseDate, FanSticker sticker, boolean available) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.sticker = sticker;
        this.available = available;
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

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Item) && getId() != null && ((Item)o).getId().equals(getId());
    }
}

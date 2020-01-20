package com.jaba.webapp.domain.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Album.class, name = "album"),
        @JsonSubTypes.Type(value = Video.class, name = "video")
})
public abstract class Item {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("price")
    @NotNull(message = "{general.validation.empty}")
    @DecimalMin(value="0", message = "{general.validation.minValue}")
    private BigDecimal price;
    @JsonProperty("title")
    @NotBlank(message = "{general.validation.empty}")
    private String title;
    @JsonProperty("releaseDate")
    @NotNull(message = "{general.validation.empty}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @Valid
    @JsonProperty("Sticker")
    private FanSticker sticker;
    @JsonProperty("available")
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

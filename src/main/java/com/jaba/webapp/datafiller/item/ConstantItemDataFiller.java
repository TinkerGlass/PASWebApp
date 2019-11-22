package com.jaba.webapp.datafiller.item;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.FanSticker;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.repository.item.ItemRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.time.Instant;

public class ConstantItemDataFiller implements ItemDataFiller {

    private final FanSticker sticker =  new FanSticker("You're a fan", "Greatest fan yeah woo hoo no idea what this sticker thing is", 2137);
    private final FanSticker sticker2 =  new FanSticker("Yet another sticker", "Congratulations, this sticker does absolutely nothing", 12);
    private final FanSticker sticker3 =  new FanSticker("Mysterious sticker", "ThIS is A 不思議なステンプ", 4);

    final Album[] albums = {
            new Album("Unknown Pleasures", "Joy Division", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(298209600)), 10, BigDecimal.valueOf(3999, 2), sticker, true),
            new Album("Under Stars", "AURORA", Album.Genre.POP, Date.from(Instant.ofEpochSecond(1416916800)), 1, BigDecimal.valueOf(3999, 2), sticker, false),
            new Album("13's reborn", "Girugamesh", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(1161950400)), 12, BigDecimal.valueOf(2999, 2), sticker, false),
            new Album("Withering To Death.", "Dir En Grey", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(1115640000)), 14, BigDecimal.valueOf(6499, 2), sticker2, true),
            new Album("Wszystko jedno", "happysad", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(1088683200)), 14, BigDecimal.valueOf(2999, 2), sticker, true),
            new Album("Possessor", "Gost", Album.Genre.EDM, Date.from(Instant.ofEpochSecond(1521806400)), 11, BigDecimal.valueOf(1999, 2), sticker, true),
            new Album("Skull", "Gost", Album.Genre.EDM, Date.from(Instant.ofEpochSecond(1372680000)), 7, BigDecimal.valueOf(1999, 2), sticker, true),
            new Album("White Light", "Sidewalks and Skeletons", Album.Genre.EDM, Date.from(Instant.ofEpochSecond(1425556800)), 14, BigDecimal.valueOf(1999, 2), sticker2, true),
            new Album("Melodrama", "Lorde", Album.Genre.POP, Date.from(Instant.ofEpochSecond(1497614400)), 11, BigDecimal.valueOf(1999, 2), sticker2, true),
            new Album("Umowa o Dzieło", "Taco Hemingway", Album.Genre.HIPHOP, Date.from(Instant.ofEpochSecond(1435406400)), 8, BigDecimal.valueOf(3999, 2), sticker, true),
            new Album("Rubinstein Collection, Vol. 4", "Arthur Rubinstein", Album.Genre.CLASSICAL, Date.from(Instant.ofEpochSecond(933768000)), 6, BigDecimal.valueOf(945, 2), sticker, true),
            new Album("Spirited Away (Original Soundtrack)", "Joe Hisaishi", Album.Genre.CLASSICAL, Date.from(Instant.ofEpochSecond(995457600)), 21, BigDecimal.valueOf(5000, 2), sticker, true)
    };

    final Video[] videos = {
            new Video("Perfect Blue", "Satoshi Kon", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(888667200)), 81, BigDecimal.valueOf(5999, 2), sticker2, true),
            new Video("Edward Scissorhands", "Tim Burton", new Video.Genre[] {Video.Genre.FANTASY, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(660484800)), 105, BigDecimal.valueOf(1949, 2), sticker2, true),
            new Video("Hotaru no Haka", "Isao Takahata", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(577195200)), 89, BigDecimal.valueOf(3999, 2), sticker2, true),
            new Video("Corpse Bride", "Tim Burton", new Video.Genre[] {Video.Genre.MUSICAL, Video.Genre.COMEDY, Video.Genre.FANTASY}, Date.from(Instant.ofEpochSecond(1126094400)), 76, BigDecimal.valueOf(1945, 2), sticker, true),
            new Video("Sweeney Todd: The Demon Barber of Fleet Street", "Tim Burton", new Video.Genre[] {Video.Genre.MUSICAL, Video.Genre.DRAMA, Video.Genre.COMEDY}, Date.from(Instant.ofEpochSecond(1203681600)), 116, BigDecimal.valueOf(1945, 2), sticker, true),
            new Video("AKIRA", "Katsuhiro Outomo", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.SCIFI}, Date.from(Instant.ofEpochSecond(585057600)), 124, BigDecimal.valueOf(2999, 2), sticker3, true),
            new Video("Kill Bill: Vol 1", "Quentin Tarantino", new Video.Genre[] {Video.Genre.THRILLER, Video.Genre.ACTION}, Date.from(Instant.ofEpochSecond(1064836800)), 111, BigDecimal.valueOf(1999, 2), sticker2, true),
            new Video("Sen to Chihiro no Kamikakushi", "Hayao Miyazaki", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.FANTASY}, Date.from(Instant.ofEpochSecond(995630400)), 125, BigDecimal.valueOf(2999, 2), sticker3, true),
            new Video("Shin seiki Evangelion Gekijô-ban: Air", "Hideaki Anno", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.SCIFI, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(869313600)), 97, BigDecimal.valueOf(2999, 2), sticker, true)
    };

    @Override
    public void fillItems(ItemRepository repository) {
        for(Album album : albums) {
            repository.addItem(album);
        }
        for(Video video : videos) {
            repository.addItem(video);
        }
    }

}

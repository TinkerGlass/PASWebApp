package com.jaba.webapp.datacontext.datafiller;

import com.jaba.webapp.domain.item.Album;
import com.jaba.webapp.domain.item.Video;
import com.jaba.webapp.domain.user.RootUser;

import java.util.Date;
import java.time.Instant;
import java.util.List;

public class ConstantItemDataFiller implements ItemDataFiller {

    Album albums[] = {
            new Album("Unknown Pleasures", "Joy Division", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(298209600)), 10),
            new Album("Under Stars", "AURORA", Album.Genre.POP, Date.from(Instant.ofEpochSecond(1416916800)), 1),
            new Album("13's reborn", "Girugamesh", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(1161950400)), 12),
            new Album("Withering To Death.", "Dir En Grey", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(1115640000)), 14),
            new Album("Wszystko jedno", "happysad", Album.Genre.ROCK, Date.from(Instant.ofEpochSecond(1088683200)), 14),
            new Album("Possessor", "Gost", Album.Genre.EDM, Date.from(Instant.ofEpochSecond(1521806400)), 11),
            new Album("Skull", "Gost", Album.Genre.EDM, Date.from(Instant.ofEpochSecond(1372680000)), 7),
            new Album("White Light", "Sidewalks and Skeletons", Album.Genre.EDM, Date.from(Instant.ofEpochSecond(1425556800)), 14),
            new Album("Melodrama", "Lorde", Album.Genre.POP, Date.from(Instant.ofEpochSecond(1497614400)), 11),
            new Album("Umowa o Dzieło", "Taco Hemingway", Album.Genre.HIPHOP, Date.from(Instant.ofEpochSecond(1435406400)), 8),
            new Album("Rubinstein Collection, Vol. 4", "Arthur Rubinstein", Album.Genre.CLASSICAL, Date.from(Instant.ofEpochSecond(933768000)), 6),
            new Album("Spirited Away (Original Soundtrack)", "Joe Hisaishi", Album.Genre.CLASSICAL, Date.from(Instant.ofEpochSecond(995457600)), 21)
    };

    Video videos[] = {
            new Video("Perfect Blue", "Satoshi Kon", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(888667200)), 81),
            new Video("Edward Scissorhands", "Tim Burton", new Video.Genre[] {Video.Genre.FANTASY, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(660484800)), 105),
            new Video("Hotaru no Haka", "Isao Takahata", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(577195200)), 89),
            new Video("Corpse Bride", "Tim Burton", new Video.Genre[] {Video.Genre.MUSICAL, Video.Genre.COMEDY, Video.Genre.FANTASY}, Date.from(Instant.ofEpochSecond(1126094400)), 76),
            new Video("Sweeney Todd: The Demon Barber of Fleet Street", "Tim Burton", new Video.Genre[] {Video.Genre.MUSICAL, Video.Genre.DRAMA, Video.Genre.COMEDY}, Date.from(Instant.ofEpochSecond(1203681600)), 116),
            new Video("AKIRA", "Katsuhiro Outomo", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.SCIFI}, Date.from(Instant.ofEpochSecond(585057600)), 124),
            new Video("Kill Bill: Vol 1", "Quentin Tarantino", new Video.Genre[] {Video.Genre.THRILLER, Video.Genre.ACTION}, Date.from(Instant.ofEpochSecond(1064836800)), 111),
            new Video("Sen to Chihiro no Kamikakushi", "Hayao Miyazaki", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.FANTASY}, Date.from(Instant.ofEpochSecond(995630400)), 125),
            new Video("Shin seiki Evangelion Gekijô-ban: Air", "Hideaki Anno", new Video.Genre[] {Video.Genre.ANIME, Video.Genre.SCIFI, Video.Genre.DRAMA}, Date.from(Instant.ofEpochSecond(869313600)), 97)
    };

    @Override
    public void fillAlbums(List<Album> list) {
        for(Album album : albums) {
            album.onCreate(RootUser.getRootUser());
            list.add(album);
        }
    }

    @Override
    public void fillVideos(List<Video> list) {
        for(Video video : videos) {
            video.onCreate(RootUser.getRootUser());
            list.add(video);
        }
    }
}

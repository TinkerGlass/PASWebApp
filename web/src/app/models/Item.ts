export class Item {
  type: string;
  id: number;
  price: number;
  title: string;
  releaseDate: Date;
  sticker: FanSticker;
  available: boolean;
  genre: AlbumGenre | VideoGenre;

  // Album
  author: string;
  tracks: number;

  // Video
  director: string;
  minutes: number;
}

export enum AlbumGenre {
  POP, ROCK, HIPHOP, CLASSICAL, EDM
}


export enum VideoGenre {
    COMEDY, DRAMA, SCIFI, HORROR, ANIME, FANTASY, MUSICAL, THRILLER, ACTION
}

export enum ItemType {
  ALBUM = 'album', VIDEO = 'video'
}

export class FanSticker {
  stickerName: string;
  stickerContent: string;
  stickerPoints: number;
}

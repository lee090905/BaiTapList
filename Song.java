package day10.BaiTapList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Song {
    private int id;
    private String title;
    private String artist;
    private double duration;

    public Song(int id, String title, String artist, double duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                '}';
    }
}

class SongCollection {
    private Set<Song> songs;

    public SongCollection() {
        this.songs = new HashSet<>();
    }

    public boolean addSong(Song song) {
        return songs.add(song);
    }

    public Song searchByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public List<Song> songsByArtist(String artist) {
        List<Song> artistSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                artistSongs.add(song);
            }
        }
        return artistSongs;
    }

    public List<Song> sortByDuration() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingDouble(Song::getDuration));
        return sortedSongs;
    }

    public boolean removeSongById(int id) {
        return songs.removeIf(song -> song.getId() == id);
    }

    public void displayAllSongs() {
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}

class Main {
    public static void main(String[] args) {
        SongCollection collection = new SongCollection();

        // Thêm bài hát vào bộ sưu tập
        collection.addSong(new Song(1, "Song One", "Artist A", 3.5));
        collection.addSong(new Song(2, "Song Two", "Artist B", 4.0));
        collection.addSong(new Song(3, "Song Three", "Artist A", 2.8));

        // Hiển thị tất cả bài hát
        System.out.println("All songs:");
        collection.displayAllSongs();

        // Tìm kiếm bài hát theo title
        System.out.println("\nSearch by title 'Song Two':");
        Song foundSong = collection.searchByTitle("Song Two");
        System.out.println(foundSong != null ? foundSong : "Song not found");

        // Hiển thị tất cả bài hát của một nghệ sĩ
        System.out.println("\nSongs by Artist A:");
        List<Song> artistSongs = collection.songsByArtist("Artist A");
        for (Song song : artistSongs) {
            System.out.println(song);
        }

        // Sắp xếp các bài hát theo duration
        System.out.println("\nSongs sorted by duration:");
        List<Song> sortedSongs = collection.sortByDuration();
        for (Song song : sortedSongs) {
            System.out.println(song);
        }

        // Xóa bài hát theo id
        System.out.println("\nRemoving song with id 2:");
        boolean removed = collection.removeSongById(2);
        System.out.println(removed ? "Song removed" : "Song not found");

        // Hiển thị tất cả bài hát sau khi xóa
        System.out.println("\nAll songs after removal:");
        collection.displayAllSongs();
    }
}

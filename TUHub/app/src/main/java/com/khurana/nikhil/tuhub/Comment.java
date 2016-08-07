package com.khurana.nikhil.tuhub;

/**
 * Created by nikhil on 03-05-2016.
 */
public class Comment {
    String Time,Title,Posted;

    public Comment(String time, String title, String posted) {
        Time = time;
        Title = title;
        Posted = posted;
    }

    public Comment() {
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPosted() {
        return Posted;
    }

    public void setPosted(String posted) {
        Posted = posted;
    }
}

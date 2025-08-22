package com.example.youtube_video_search3;

public class video {
    private String title, description, publishTime, channelTitle, thumbnailUrl;

    public video(String title, String description, String publishTime,
                     String channelTitle, String thumbnailUrl) {
        this.title = title;
        this.description = description;
        this.publishTime = publishTime;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPublishTime() { return publishTime; }
    public String getChannelTitle() { return channelTitle; }
    public String getThumbnailUrl() { return thumbnailUrl; }

}

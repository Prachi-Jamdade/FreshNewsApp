package com.example.freshnews.Entities;

public class NewsArticles {
    private String title;
    private String description;
    private String urlToImage;
    private String url;
    private String content;

    public NewsArticles(String newsTitle, String newsDescription, String imageUrl, String newsUrl, String newsContent) {
        this.title = newsTitle;
        this.description = newsDescription;
        this.urlToImage = imageUrl;
        this.url = newsUrl;
        this.content = newsContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

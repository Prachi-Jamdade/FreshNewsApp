package com.example.freshnews.Entities;

public class NewsCategory {

    private String newsCategory;
    private String newsCategoryImageUrl;

    public NewsCategory(String newsCategory, String newsCategoryImageUrl) {
        this.newsCategory = newsCategory;
        this.newsCategoryImageUrl = newsCategoryImageUrl;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsCategoryImageUrl() {
        return newsCategoryImageUrl;
    }

    public void setNewsCategoryImageUrl(String newsCategoryImageUrl) {
        this.newsCategoryImageUrl = newsCategoryImageUrl;
    }
}

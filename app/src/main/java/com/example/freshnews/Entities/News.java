package com.example.freshnews.Entities;

import java.util.ArrayList;

public class News {
    private int totalResults;
    private String status;
    private ArrayList<NewsArticles> articles;

    public News(int totalResults, String status, ArrayList<NewsArticles> newsArticles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = newsArticles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NewsArticles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsArticles> articles) {
        this.articles = articles;
    }
}

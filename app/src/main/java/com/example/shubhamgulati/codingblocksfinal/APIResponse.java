package com.example.shubhamgulati.codingblocksfinal;

import java.util.ArrayList;

public class APIResponse {
    int totalResults;

    ArrayList<articles> articles;

    public APIResponse(int totalResults, ArrayList<articles> articles) {
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public APIResponse() {
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<articles> articles) {
        this.articles = articles;
    }
}

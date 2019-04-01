package com.example.shubhamgulati.codingblocksfinal;

import android.os.Parcel;
import android.os.Parcelable;

public class articles  implements Parcelable{
    source source;
    String author,title,description,url,urlToImage,publishedAt;
    long id;

    public articles(com.example.shubhamgulati.codingblocksfinal.source source, String author, String title, String description,
                    String url, String urlToImage, String publishedAt) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.id = System.currentTimeMillis();
    }

    public articles() {
    }

    public com.example.shubhamgulati.codingblocksfinal.source getSource() {
        return source;
    }

    public void setSource(com.example.shubhamgulati.codingblocksfinal.source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected articles(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        id = in.readLong();
    }

    public static final Creator<articles> CREATOR = new Creator<articles>() {
        @Override
        public articles createFromParcel(Parcel in) {
            return new articles(in);
        }

        @Override
        public articles[] newArray(int size) {
            return new articles[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
        dest.writeLong(id);
    }
}

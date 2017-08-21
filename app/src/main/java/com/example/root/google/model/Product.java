package com.example.root.google.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/06/17.
 */

public class Product {

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getPrice_display() {
        return price_display;
    }

    public void setPrice_display(String price_display) {
        this.price_display = price_display;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @SerializedName("vendor")
    private String vendor;
    @SerializedName("name")
    private String name;
    @SerializedName("uri")
    private String uri;
    @SerializedName("image_uri")
    private String image_uri;
    @SerializedName("price_display")
    private String price_display;
    @SerializedName("price")
    private String price;
    @SerializedName("rating")
    private Integer rating;
    @SerializedName("city")
    private String city;

    public Product(String vendor,
                   String name,
                   String uri,
                   String image_uri,
                   String price_display,
                   String price,
                   Integer rating,
                   String city){
        this.vendor=vendor;
        this.name=name;
        this.uri=uri;
        this.image_uri=image_uri;
        this.price_display=price_display;
        this.price=price;
        this.rating = rating;
        this.city = city;
    }





}

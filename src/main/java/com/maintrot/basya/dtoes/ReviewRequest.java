package com.maintrot.basya.dtoes;

public class ReviewRequest {
    private Long clientId;
    private String text;
    private int rating;

    public ReviewRequest () {}

    public ReviewRequest(Long clientId, String text, int rating) {
        this.clientId = clientId;
        this.text = text;
        this.rating = rating;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

package com.maintrot.basya.dtoes;

public class TagRequest {
    private Long clientId;
    private Long masterId;
    private String text;

    public TagRequest() {}

    public TagRequest(Long clientId, Long masterId, String text) {
        this.clientId = clientId;
        this.masterId = masterId;
        this.text = text;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

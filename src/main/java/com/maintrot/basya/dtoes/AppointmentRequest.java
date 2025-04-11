package com.maintrot.basya.dtoes;

public class AppointmentRequest {
    private Long clientId;
    private Long masterId;
    private Long serviceId;
    private String date;
    private String time;
    private String photo;
    private String text;

    public AppointmentRequest () {}

    public AppointmentRequest(Long clientId, Long masterId, Long serviceId, String date, String time, String photo, String text) {
        this.clientId = clientId;
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
        this.photo = photo;
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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

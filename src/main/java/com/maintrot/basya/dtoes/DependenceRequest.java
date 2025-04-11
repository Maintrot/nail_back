package com.maintrot.basya.dtoes;

public class DependenceRequest {

    private String name;
    private Boolean aBoolean;
    private int number;
    private String text;

    public DependenceRequest() {}

    public DependenceRequest(String name, Boolean aBoolean, int number, String text) {
        this.name = name;
        this.aBoolean = aBoolean;
        this.number = number;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

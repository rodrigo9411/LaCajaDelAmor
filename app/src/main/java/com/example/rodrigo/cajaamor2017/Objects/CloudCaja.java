package com.example.rodrigo.cajaamor2017.Objects;

public class CloudCaja {

    private String number;
    private String status;
    private String holder;
    private String phone;
    private String responsible;

    public CloudCaja() {
    }

    public CloudCaja(String number, String status, String holder, String phone, String responsible) {
        this.number = number;
        this.status = status;
        this.holder = holder;
        this.phone = phone;
        this.responsible = responsible;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}

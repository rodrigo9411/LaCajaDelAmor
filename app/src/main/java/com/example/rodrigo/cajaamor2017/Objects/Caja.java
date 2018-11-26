package com.example.rodrigo.cajaamor2017.Objects;

/**
 * Created by Rodrigo on 11/26/2017.
 */

public class Caja {

    private String sId;
    private String sNumero;
    private String sFamilia;
    private String sDestino;
    private String sStatus;
    private String sEntregada;
    private String sTele;
    private String sDonacion;
    private String sRespon;

    public Caja() {
    }

    public Caja(String sId, String sNumero, String sFamilia, String sDestino, String sStatus, String sEntregada, String sTele, String sDonacion, String sRespon) {
        this.sId = sId;
        this.sNumero = sNumero;
        this.sFamilia = sFamilia;
        this.sDestino = sDestino;
        this.sStatus = sStatus;
        this.sEntregada = sEntregada;
        this.sTele = sTele;
        this.sDonacion = sDonacion;
        this.sRespon = sRespon;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsNumero() {
        return sNumero;
    }

    public void setsNumero(String sNumero) {
        this.sNumero = sNumero;
    }

    public String getsFamilia() {
        return sFamilia;
    }

    public void setsFamilia(String sFamilia) {
        this.sFamilia = sFamilia;
    }

    public String getsDestino() {
        return sDestino;
    }

    public void setsDestino(String sDestino) {
        this.sDestino = sDestino;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getsEntregada() {
        return sEntregada;
    }

    public void setsEntregada(String sEntregada) {
        this.sEntregada = sEntregada;
    }

    public String getsTele() {
        return sTele;
    }

    public void setsTele(String sTele) {
        this.sTele = sTele;
    }

    public String getsDonacion() {
        return sDonacion;
    }

    public void setsDonacion(String sDonacion) {
        this.sDonacion = sDonacion;
    }

    public String getsRespon() {
        return sRespon;
    }

    public void setsRespon(String sRespon) {
        this.sRespon = sRespon;
    }

}

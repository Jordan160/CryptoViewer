package com.jvetter.cryptoviewer;

import com.google.gson.annotations.SerializedName;

public class CryptoData {
    @SerializedName("time")
    private long time;
    @SerializedName("close")
    private double closingPrice;
    @SerializedName("high")
    private double highPrice;
    @SerializedName("low")
    private double lowPrice;
    @SerializedName("open")
    private double openingPrice;
    @SerializedName("volumefrom")
    private double volumeFrom;
    @SerializedName("volumeTo")
    private double volumeTo;
    @SerializedName("conversionType")
    private String conversionType;
    @SerializedName("conversionSymbol")
    private String conversionSymbol;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getVolumeFrom() {
        return volumeFrom;
    }

    public void setVolumeFrom(double volumeFrom) {
        this.volumeFrom = volumeFrom;
    }

    public double getVolumeTo() {
        return volumeTo;
    }

    public void setVolumeTo(double volumeTo) {
        this.volumeTo = volumeTo;
    }

    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public String getConversionSymbol() {
        return conversionSymbol;
    }

    public void setConversionSymbol(String conversionSymbol) {
        this.conversionSymbol = conversionSymbol;
    }
}





//        time : 1614011280
//        close : 53515.52
//        high : 53520.41
//        low : 53436.38
//        open : 53519.88
//        volumefrom : 101.51
//        volumeto : 5428385.04
//        conversionType : "direct"
//        conversionSymbol : ""
package com.jvetter.cryptoviewer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoResponse {
    @SerializedName("Data")
    private List<CryptoData> cryptoDataList;

    public List<CryptoData> getCryptoDataList() {
        return cryptoDataList;
    }

    public void setCryptoDataList(List<CryptoData> cryptoDataList) {
        this.cryptoDataList = cryptoDataList;
    }
}

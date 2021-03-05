package com.jvetter.cryptoviewer.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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

    public static CryptoResponse getCryptoResponse(String url) {
        CryptoResponse cryptoResponse = parseCryptoResponse(getCryptoInfo(url));

        return cryptoResponse;
    }

    private static String getCryptoInfo(String url) {
        System.out.println("Calling GET API from URL: " + url);
        URL address = null;
        try {
            address = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(address.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(reader);
        String contents = "";
        String line = "";
        while (true) {
            try {
                if (!((line = buffer.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.length() == 0) {
                break;
            }
            contents += line;
        }
        return contents;
    }

    private static CryptoResponse parseCryptoResponse(String cryptoData) {
        Gson gson = new Gson();
        CryptoResponse cryptoResponse = gson.fromJson(cryptoData, CryptoResponse.class);

        return cryptoResponse;
    }
}

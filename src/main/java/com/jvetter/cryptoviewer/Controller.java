package com.jvetter.cryptoviewer;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Controller {
    public final int ITEMS_AMT = 10;
    public BarChart chart;
    private final String CRYPTO_DAY_URL = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private final String CRYPTO_HOUR_URL = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private final String CRYPTO_MIN_URL = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private final String DAY_PATTERN = "MM/dd";
    private final String HOUR_PATTERN = "K:mm";

    @FXML
    ToggleGroup toggleGroup;

    @FXML
    LineChart lineChart;

    @FXML
    VBox chartVBox; // assuming your button container is a HBox

    @FXML
    ToggleButton myButton;
    private void selectButton() {
        //event.getTarget();
    }

    @FXML
    private void handleClick(MouseEvent event) {
        System.out.println(event.getButton());
        System.out.println(event.getX());
        System.out.println(event.getY());
    }

    @FXML
    private void dayButtonClicked() {
        lineChart.getData().clear();
        populateChart(CRYPTO_DAY_URL, DAY_PATTERN);
    }

    @FXML
    private void hourButtonClicked() {
        lineChart.getData().clear();
        populateChart(CRYPTO_HOUR_URL, HOUR_PATTERN);
    }

    @FXML
    private void minButtonClicked() {
        lineChart.getData().clear();
        populateChart(CRYPTO_MIN_URL, HOUR_PATTERN);
    }

    private String retrieveUrlResponse(String url) {
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

    private void populateChart(String url, String pattern) {
        String response = retrieveUrlResponse(url);

        Gson gson = new Gson();
        CryptoResponse cryptoResponse = gson.fromJson(response, CryptoResponse.class);
        cryptoResponse.getCryptoDataList();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> bc = new LineChart<String, Number>(xAxis, yAxis);
        XYChart.Series cryptoSeries = new XYChart.Series();
        lineChart.setLegendVisible(false);
        cryptoSeries.nameProperty().setValue(null);
        for (CryptoData cryptoData : cryptoResponse.getCryptoDataList()) {
            Instant instant = Instant.ofEpochSecond(cryptoData.getTime());
            Date date = Date.from(instant);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
            String formattedDate = dateFormatter.format(date);

            cryptoSeries.getData().add(new XYChart.Data(formattedDate, cryptoData.getLowPrice()));
        }

        lineChart.getData().addAll(cryptoSeries);
        lineChart.setVisible(true);
    }

    public void initialize() {
        populateChart(CRYPTO_DAY_URL, DAY_PATTERN);
    }
}

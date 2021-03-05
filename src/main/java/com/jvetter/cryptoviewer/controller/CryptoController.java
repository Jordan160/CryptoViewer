package com.jvetter.cryptoviewer.controller;

import com.jvetter.cryptoviewer.model.CryptoData;
import com.jvetter.cryptoviewer.model.CryptoResponse;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

import static com.jvetter.cryptoviewer.model.CryptoData.getFormattedTime;
import static com.jvetter.cryptoviewer.model.CryptoResponse.*;

public class CryptoController {
    private final String CRYPTO_DAY_URL = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private final String CRYPTO_HOUR_URL = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private final String CRYPTO_MIN_URL = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private final String DAY_PATTERN = "MM/dd";
    private final String HOUR_PATTERN = "K:mm";

    @FXML
    ToggleGroup toggleGroup;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

    @FXML
    VBox chartVBox;

    @FXML
    Label currentPriceLabel;

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

    public void initialize() {
        populateChart(CRYPTO_DAY_URL, DAY_PATTERN);
    }

    private void populateChart(String url, String pattern) {
        CryptoResponse cryptoResponse = getCryptoResponse(url);

        XYChart.Series<String, Number> cryptoSeries = new XYChart.Series();
        lineChart.setLegendVisible(false);
        for (CryptoData cryptoData : cryptoResponse.getCryptoDataList()) {
            XYChart.Data chartData = new XYChart.Data(getFormattedTime(cryptoData.getTime(), pattern), cryptoData.getOpeningPrice());
            cryptoSeries.getData().add(chartData);
        }

        lineChart.getData().addAll(cryptoSeries);
        lineChart.setVisible(true);

        currentPriceLabel.setText("Current Price " + "\n$" + cryptoResponse.getCryptoDataList().get(cryptoResponse.getCryptoDataList().size() -1).getClosingPrice());

        for (XYChart.Series<String, Number> series : lineChart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                for (CryptoData cryptoData: cryptoResponse.getCryptoDataList()) {
                    if (data.getXValue().equalsIgnoreCase(getFormattedTime(cryptoData.getTime(), pattern))) {
                        Tooltip.install(data.getNode(), new Tooltip(
                                " Time: " + getFormattedTime(cryptoData.getTime(), pattern) + "\n Open: $" + cryptoData.getOpeningPrice()
                                        + "\n Low: $" + cryptoData.getLowPrice() + "\n High: $" + cryptoData.getHighPrice() + "\n Close: $" + cryptoData.getClosingPrice()));
                    }
                }
            }
        }
    }
}

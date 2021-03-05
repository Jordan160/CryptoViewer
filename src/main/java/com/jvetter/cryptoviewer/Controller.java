package com.jvetter.cryptoviewer;

import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static com.jvetter.cryptoviewer.CryptoData.getFormattedTime;

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

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

    @FXML
    VBox chartVBox; // assuming your button container is a HBox

    @FXML
    Label currentPriceLabel;

    @FXML
    Label linePointGraphPrice;

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
        //cryptoResponse.getCryptoDataList();

//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
        //final LineChart<String, Number> bc = new LineChart<String, Number>(xAxis, yAxis);
        //lineChart.getXAxis() = xAxis;
        XYChart.Series<String, Number> cryptoSeries = new XYChart.Series();
        lineChart.setLegendVisible(false);
        for (CryptoData cryptoData : cryptoResponse.getCryptoDataList()) {
            Instant instant = Instant.ofEpochSecond(cryptoData.getTime());
            Date date = Date.from(instant);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
            String formattedDate = dateFormatter.format(date);
            formattedDate = formattedDate.equalsIgnoreCase("0:00") ? "12:00" : formattedDate;
            XYChart.Data chartData = new XYChart.Data(formattedDate, cryptoData.getOpeningPrice());


            //chartData.setNode();
//
//            final Node node = chartData.getNode();
//
//            node.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
//                if (newValue) {
//                    //stickyNotesStage.show();
//                } else {
//                    //stickyNotesStage.hide();
//                }
//            });
            //node.setOnMouseDragged(new MouseHandler(data,xAxis,yAxis));
            //chartData.getNode().hoverProperty().

//            StackPane stickyNotesPane = new StackPane();
//            stickyNotesPane.setPrefSize(200, 200);
//            stickyNotesPane.setStyle("-fx-background-color: yellow;");
//
//            Popup popup = new Popup();
//            popup.getContent().add(stickyNotesPane);
//
//            StackPane notedPane = new StackPane();
//
//            chartData.getNode().hoverProperty().addListener((obs, oldVal, newValue) -> {
//                if (newValue) {
//                    //Bounds bnds = notedPane.localToScreen(notedPane.getLayoutBounds());
//                    //double x = bnds.getMinX() - (stickyNotesPane.getWidth() / 2) + (notedPane.getWidth() / 2);
//                    //double y = bnds.getMinY() - stickyNotesPane.getHeight();
//                    //popup.show(stickyNotesPane, 1, 2);
//                } else {
//                    //popup.hide();
//                }
//            });
            //chartData.getNode().hoverProperty();
            //cryptoSeries.getData().add(new XYChart.Data(formattedDate, cryptoData.getOpeningPrice()));
            cryptoSeries.getData().add(chartData);
        }

        lineChart.getData().addAll(cryptoSeries);
        lineChart.setVisible(true);

        currentPriceLabel.setText("Current Price " + "\n$" + cryptoResponse.getCryptoDataList().get(cryptoResponse.getCryptoDataList().size() -1).getClosingPrice());

        for (XYChart.Series<String, Number> s : lineChart.getData()) {
            for (XYChart.Data<String, Number> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        d.getXValue().toString() + "\n" +
                                "Number Of Events : " + d.getYValue()));

                //Adding class on hover
                d.getNode().setOnMouseEntered(
                        event -> d.getNode().getStyleClass().add("onHover"));
                //d.getNode().setOnMouseEntered(new );
                d.getNode().hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            for (CryptoData cryptoData: cryptoResponse.getCryptoDataList()) {

                                if (d.getXValue().equalsIgnoreCase(getFormattedTime(cryptoData.getTime(), pattern))) {
                                    linePointGraphPrice.setText(
                                            " Time: " + getFormattedTime(cryptoData.getTime(), pattern) + "\n Opening: $" + cryptoData.getOpeningPrice()
                                                    + "\n Closing: $" + cryptoData.getClosingPrice() + "\n Low: $" + cryptoData.getLowPrice()
                                                    + "\n High: $" + cryptoData.getHighPrice());
                                }
                            }
                        } else {
                            linePointGraphPrice.setText("");
                        }
                    }
                });

                //d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
            }
        }
    }

    public void initialize() {
        populateChart(CRYPTO_DAY_URL, DAY_PATTERN);
    }
}

package com.jvetter.cryptoviewer;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolTipOnlineChart extends Application {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void start(Stage stage) throws ParseException {
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Events");

        final LineChart<String,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Events");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");


        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("Events this Year");
        series.getData().add(new XYChart.Data("11/Jan/2014", 23));
        series.getData().add(new XYChart.Data("09/Feb/2014", 14));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/Mar/2014"), 15));
//        series.getData().add(new XYChart.Data(dateFormat.parse("14/Apr/2014"), 24));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/May/2014"), 34));
//        series.getData().add(new XYChart.Data(dateFormat.parse("07/Jun/2014"), 36));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/Jul/2014"), 22));
//        series.getData().add(new XYChart.Data(dateFormat.parse("21/Aug/2014"), 45));
//        series.getData().add(new XYChart.Data(dateFormat.parse("04/Sep/2014"), 43));
//        series.getData().add(new XYChart.Data(dateFormat.parse("22/Oct/2014"), 17));
//        series.getData().add(new XYChart.Data(dateFormat.parse("30/Nov/2014"), 29));
//        series.getData().add(new XYChart.Data(dateFormat.parse("10/Dec/2014"), 25));


        Scene scene  = new Scene(lineChart,800,600);
        //scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();

        /**
         * Browsing through the Data and applying ToolTip
         * as well as the class on hover
         */
        for (XYChart.Series<String, Number> s : lineChart.getData()) {
            for (XYChart.Data<String, Number> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        d.getXValue().toString() + "\n" +
                                "Number Of Events : " + d.getYValue()));

                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
                d.getNode().hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        System.out.println("yo");
                    }
                });

                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.jvetter.cryptoviewer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller {
    public final int ITEMS_AMT = 10;
    public BarChart chart;

    @FXML
    ToggleGroup toggleGroup;

    @FXML
    private void buttonClicked() {
        if (toggleGroup.getSelectedToggle() != null) {
            System.out.println(toggleGroup.getSelectedToggle());
        }
    }
//
//    public void initialize(URL location, ResourceBundle resources) {
//        XYChart.Series<String, Double> series = new XYChart.Series<>();
//        Random rnd = new Random();
//        for (int i=0; i<this.ITEMS_AMT; i++){
//            Double value = new Double(rnd.nextDouble());
//            XYChart.Data<String, Double> item = new XYChart.Data<String,
//                    Double>( Integer.toString(i), value);
//            series.getData().add(item);
//        }
//
//        chart = new BarChart();
//        chart.getData().add(series);
//        TableColumn col = new TableColumn("Value");
//        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures,
//                        ObservableValue>() {
//            @Override
//            public ObservableValue call(TableColumn.CellDataFeatures param) {
//                XYChart.Data d = (XYChart.Data) param.getValue();
//                return new SimpleDoubleProperty((Double)d.getYValue());
//            }
//        });
//
//    }
//    @FXML
//    ToggleButton myButton;
//    private void selectButton() {
//        //event.getTarget();
//    }

//    @FXML
//    private void handleClick(MouseEvent event) {
//        System.out.println(event.getButton());
//        System.out.println(event.getX());
//        System.out.println(event.getY());
//    }
}

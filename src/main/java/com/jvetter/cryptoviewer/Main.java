package com.jvetter.cryptoviewer;

import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String url = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
        //String url = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
        //String url = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
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

        Gson gson = new Gson();
        CryptoResponse cryptoResponse = gson.fromJson(contents, CryptoResponse.class);
        cryptoResponse.getCryptoDataList();

        URL url1 = new File("src/main/java/com/jvetter/cryptoviewer/cryptochart.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url1);


        //final CategoryAxis xAxis = new CategoryAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> bc =
                new LineChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Country");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");

        for (CryptoData cryptoData: cryptoResponse.getCryptoDataList()) {
//            long seconds = cryptoData.getTime();
//                long s = seconds % 60;
//                long m = (seconds / 60) % 60;
//                long h = (seconds / (60 * 60)) % 24;
//
//            long day = (1000 * 60 * 60 * 24); // 24 hours in milliseconds
//            long time = day * 39; // for example, 39 days
//
//            java.util.Date timet=new java.util.Date((long)seconds*1000);
//            timet.getDay();
//            System.out.println(timet.getDay());

//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(seconds);
//            int mDay = calendar.get(Calendar.DAY_OF_MONTH);

//            long days = (s/ (60*60*24*1000));

            Instant instant = Instant.ofEpochSecond( cryptoData.getTime() );
            Date date = Date.from( instant );

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy K:mm");
            //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            //SimpleDateFormat formatter = new SimpleDateFormat("K:mm");
            String formattedDate = formatter.format(date);


//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            System.out.println(calendar.get(Calendar.HOUR));
//            System.out.println(calendar.get(Calendar.MINUTE));
//            System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
//            System.out.println(LocalDate.of(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_WEEK).getDayOfWeek().name());

            //int days = (int) (seconds / (1000*60*60*24));
            //long days = TimeUnit.MILLISECONDS.toDays(seconds);

//            Date dateObj = null;
//            try {
//                final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
//                dateObj = sdf.parse(h + ":" + m);
//                System.out.println(dateObj);
//                System.out.println(new SimpleDateFormat("K:mm").format(dateObj));
//            } catch (final ParseException e) {
//                e.printStackTrace();
//            }
                //return String.format("%d:%02d:%02d", h,m,s);


            series1.getData().add(new XYChart.Data(formattedDate, cryptoData.getLowPrice()));
            //series1.getData().add(new XYChart.Data(new SimpleDateFormat("K:mm").format(formattedDate), cryptoData.getLowPrice()));
            //series1.getData().add(new XYChart.Data(new SimpleDateFormat("K:mm").format(dateObj), cryptoData.getLowPrice()));
            //series1.getData().add(new XYChart.Data<String, Double>(String.valueOf(days), cryptoData.getLowPrice()));
//            series1.getData().add(new XYChart.Data(20148.82, brazil));
//            series1.getData().add(new XYChart.Data(10000, france));
//            series1.getData().add(new XYChart.Data(35407.15, italy));
//            series1.getData().add(new XYChart.Data(12000, usa));

        }
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);

        //Parent root = FXMLLoader.load(getClass().getResource("cryptochart.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        //primaryStage.setScene(new Scene(root, 550, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

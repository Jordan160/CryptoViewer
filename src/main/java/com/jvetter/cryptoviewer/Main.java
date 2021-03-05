package com.jvetter.cryptoviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url1 = new File("src/main/java/com/jvetter/cryptoviewer/cryptochart.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url1);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
//        String url = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
//        //String url = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
//        //String url = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
//        System.out.println("Calling GET API from URL: " + url);
//        URL address = null;
//        try {
//            address = new URL(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        InputStreamReader reader = null;
//        try {
//            reader = new InputStreamReader(address.openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BufferedReader buffer = new BufferedReader(reader);
//        String contents = "";
//        String line = "";
//        while (true) {
//            try {
//                if (!((line = buffer.readLine()) != null)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (line.length() == 0) {
//                break;
//            }
//            contents += line;
//        }
//
//        Gson gson = new Gson();
//        CryptoResponse cryptoResponse = gson.fromJson(contents, CryptoResponse.class);
//        cryptoResponse.getCryptoDataList();
//
//
//
//        //final CategoryAxis xAxis = new CategoryAxis();
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        final LineChart<String,Number> bc =
//                new LineChart<String,Number>(xAxis,yAxis);
//        bc.setTitle("Country Summary");
//        xAxis.setLabel("Value");
//        xAxis.setTickLabelRotation(90);
//        yAxis.setLabel("Country");
//
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("2003");
//
//        for (CryptoData cryptoData: cryptoResponse.getCryptoDataList()) {
//            Instant instant = Instant.ofEpochSecond( cryptoData.getTime() );
//            Date date = Date.from( instant );
//            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy K:mm");
//            //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            //SimpleDateFormat formatter = new SimpleDateFormat("K:mm");
//            String formattedDate = formatter.format(date);
//            series1.getData().add(new XYChart.Data(formattedDate, cryptoData.getLowPrice()));
//        }
//        Scene scene = new Scene(bc,600,600);
//        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

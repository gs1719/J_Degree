package com.example.jdegree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;
    XYChart.Series<String, Number> series, series1, series2, series3, series4, series5, series6, series7, series8, series9, series10, series11;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        series = new XYChart.Series<>();
        series.setName("pch_cannonlake-virtual-0");
        series1 = new XYChart.Series<>();
        series1.setName("nvme-pci-0300");
        series2 = new XYChart.Series<>();
        series2.setName("Avg Core");
        series3 = new XYChart.Series<>();
        series3.setName("Core0");
        series4 = new XYChart.Series<>();
        series4.setName("Core1");
        series5 = new XYChart.Series<>();
        series5.setName("Core2");
        series6 = new XYChart.Series<>();
        series6.setName("Core3");
        series7 = new XYChart.Series<>();
        series7.setName("iwlwifi_1-virtual-0");
        series8 = new XYChart.Series<>();
        series8.setName("acpitz-acpi-0");
//        series9 = new XYChart.Series<>();
//        series9.setName("Core9");
//        series10 = new XYChart.Series<>();
//        series10.setName("Core10");
//        series11 = new XYChart.Series<>();
//        series11.setName("Core11");

        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);
        lineChart.getData().add(series4);
        lineChart.getData().add(series5);
        lineChart.getData().add(series6);
        lineChart.getData().add(series7);
        lineChart.getData().add(series8);
//        lineChart.getData().add(series9);
//        lineChart.getData().add(series10);
//        lineChart.getData().add(series11);
        Thread thread = new Thread(new TemperatureReader());
        thread.start();
    }


    class TemperatureReader implements Runnable {
        @Override
        public void run() {

            try {
                int ctr = 1;
                while (true) {
                Pattern p = Pattern.compile("[+]....");

                Process process;
                process = Runtime.getRuntime().exec("sensors");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";
                int corenumber = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        System.out.println("Match Found = " + m.group().substring(1));
                        Double temp = Double.parseDouble(m.group().substring(1));
                        if (temp > 0) {
                            switch (corenumber++) {
                                case 0:
                                    series.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 1:
                                    series1.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 2:
                                    series2.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 3:
                                    series3.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 4:
                                    series4.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 5:
                                    series5.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 6:
                                    series6.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 7:
                                    series7.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
                                case 8:
                                    series8.getData().add(new XYChart.Data<>(ctr + "", temp));
                                    break;
//                                case 9:
//                                    series9.getData().add(new XYChart.Data<>(ctr + "", temp));
//                                    break;
//                                case 10:
//                                    series10.getData().add(new XYChart.Data<>(ctr + "", temp));
//                                    break;
//                                case 11:
//                                    series11.getData().add(new XYChart.Data<>(ctr + "", temp));
//                                    break;
                            }
                        }
                    }
//                        System.out.println(line);
                }
                ctr++;
                System.out.println("*-----------------------------------*");
                Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
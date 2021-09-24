package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<String, Number> TimeBar;

    @FXML
    private BarChart<String, Number> WordBar;

    @FXML
    private BarChart<String, Number> LevBar;

    @FXML
    private Button butTable;

    @FXML
    private Button butWord;

    @FXML
    private TextField MyInWord;

    @FXML
    private TextField MyTable3;

    @FXML
    private TextField MyTable4;

    @FXML
    private TextField MyTable5;

    @FXML
    private TextField MyTable6;

    @FXML
    private TextField MyTable7;

    @FXML
    private TextField MyTable8;

    @FXML
    private TextField MyTable9;

    @FXML
    private TextField MyTable10;

    @FXML
    private TextField MyTable2;

    @FXML
    private TextField MyFilePath;

    @FXML
    private Button butPath;

    @FXML
    private TextField MyTable1;

    @FXML
    private ChoiceBox<String> allFiles;


    String[] sign;
    HashTable[] hashTables;
    String Path;
    boolean check1 = false;
    boolean check2 = false;
//    HashTable hashtable;
    int countHashFile = 0;

    @FXML
    void initialize() {
        allFiles.getItems().add("Словарь 1");
        allFiles.getItems().add("Словарь 2");
        allFiles.getItems().add("Словарь 3");
        allFiles.getItems().add("Словарь 4");
        allFiles.getItems().add("Словарь 5");
        allFiles.getItems().add("Словарь 6");
        allFiles.getItems().add("Словарь 7");
        allFiles.getItems().add("Словарь 8");
        allFiles.getItems().add("Словарь 9");
        allFiles.getItems().add("Словарь 10");

        hashTables = new HashTable[10];
        // Десериализация 10 словарей
        for (int i = 1; i <= 10; i++) {
            System.out.println("Распаковка словаря " + i);
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Словарь " + i + ".dat")))
            {
                hashTables[i]  = (HashTable)ois.readObject();
                //System.out.println(Arrays.toString(hashTables[i].table)); // вывод всего что есть
                System.out.println("Словарь " + i + " распакован\n");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage() + "\n");
            }
        }

//        allFiles.setOnAction(e -> {
//            System.out.println(allFiles.getValue());
//            // замена параметра для работы с поиском (switch case)
//        });

//        butTable.setOnAction(e -> {
//
//            sign = new String[10];
//            sign[0] = MyTable1.getText();
//            sign[1] = MyTable2.getText();
//            sign[2] = MyTable3.getText();
//            sign[3] = MyTable4.getText();
//            sign[4] = MyTable5.getText();
//            sign[5] = MyTable6.getText();
//            sign[6] = MyTable7.getText();
//            sign[7] = MyTable8.getText();
//            sign[8] = MyTable9.getText();
//            sign[9] = MyTable10.getText();
//
//            System.out.println(Arrays.toString(sign));
//            check1 = true;
//            hashtable = new HashTable(10,sign);
//
//            // Десериализация
////            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cipherHashTable" + countHashFile + ".dat")))
////            {
////                HashTable hashtable=(HashTable)ois.readObject();
////                // вывод всего что есть
////                System.out.println(Arrays.toString(hashtable.table));
////            }
////            catch(Exception ex){
////
////                System.out.println(ex.getMessage());
////            }
//        });
//
//        butPath.setOnAction(actionEvent -> {
//            if (check1) {
//                Path = MyFilePath.getText();
//
//              hashtable = new HashTable(10,sign);
//              hashtable.parse(Path); // считали слова из файла и закодировали под нашу таблицу кодирования
//
//                System.out.println(Arrays.toString(hashtable.table));
//                System.out.println("ПУПАЛУПА");
////                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cipherHashTable" + countHashFile + ".dat"))) {
////                    countHashFile++;
////                    //hashtable = new HashTable(10,sign);
////                    System.out.println("ПУПАЛУПА");
////                    hashtable.parse(Path); // считали слова из файла и закодировали под нашу таблицу кодирования
////                    oos.writeObject(hashtable);
////                }
////                catch(Exception ex){
////                    System.out.println(ex.getMessage());
////                }
//
//
//                System.out.println(Path);
//                check2 = true;
//            } else {
//                MyFilePath.setText("Загрузите таблицу кодировани");
//            }
//        });
//
//        butWord.setOnAction(actionEvent -> {
//            if (check2) {
//                String Word;
//                Word = MyInWord.getText();
//                Word = Word.toLowerCase(Locale.ROOT);
//
//
//                long startTime;
//                long endTime;
//                long startEndTime = 0;
//                int[] WordLev = hashtable.search(Word);
//                // поиск
//
////                for (int i = 0; i < 100; i++) {
//                    startTime = System.nanoTime();//System.currentTimeMillis();
//                    hashtable.search(Word);
//                    endTime = System.nanoTime();//System.currentTimeMillis();
////                    startEndTime += endTime - startTime;
////                }
////                startEndTime = startEndTime / 100;
//
//
//                XYChart.Series<String, Number> timeSeries = new XYChart.Series<>();
//                timeSeries.setName(Word);
//                timeSeries.getData().add(new XYChart.Data<>("Время", endTime - startTime));
//                System.out.println(endTime - startTime);
//                TimeBar.getData().add(timeSeries);
//
//                XYChart.Series<String, Number> wordSeries = new XYChart.Series<>();
//                wordSeries.setName(Word);
//                wordSeries.getData().add(new XYChart.Data<>("Кол-во слов", WordLev[0]));
//                System.out.println(WordLev[0]);
//                WordBar.getData().add(wordSeries);
//
//                XYChart.Series<String, Number> levSeries = new XYChart.Series<>();
//                levSeries.setName(Word);
//                levSeries.getData().add(new XYChart.Data<>("Вызовы Растояния Левенштейна", WordLev[1]));
//                System.out.println(WordLev[1]);
//                LevBar.getData().add(levSeries);
//
//                System.out.println(Word);
//            } else {
//                MyInWord.setText("Загрузите таблицу кодировани и словарь");
//            }
//        });
    }
}

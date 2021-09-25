package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    @FXML
    private Button saveButton;

    @FXML
    private Text textError;

    String[] sign;
    String Path;
    HashTable[] hashTables;
    int number = 0;
    boolean check1 = false;
    boolean check2 = false;

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
        for (int i = 0; i < 10; i++) {
            System.out.print("Распаковка словаря " + (i + 1) + " - ");
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Словарь " + (i + 1) + ".dat")))
            {
                //hashTables[i] = new HashTable(10,sign);
                hashTables[i] = (HashTable)ois.readObject();
                //System.out.println(Arrays.toString(hashTables[i].table)); // вывод всего что есть
                System.out.println("Словарь " + (i + 1) + " распакован\n");
            }
            catch(Exception ex){
                System.out.println(ex.getMessage() + "\n");
            }
        }

        // кнопка сохранения таблицы
        butTable.setOnAction(e -> {

            sign = new String[10];
            sign[0] = MyTable1.getText();
            sign[1] = MyTable2.getText();
            sign[2] = MyTable3.getText();
            sign[3] = MyTable4.getText();
            sign[4] = MyTable5.getText();
            sign[5] = MyTable6.getText();
            sign[6] = MyTable7.getText();
            sign[7] = MyTable8.getText();
            sign[8] = MyTable9.getText();
            sign[9] = MyTable10.getText();

            System.out.println(Arrays.toString(sign));
            if (!sign[0].isEmpty() || !sign[1].isEmpty()
                    || !sign[2].isEmpty() || !sign[3].isEmpty()
                    || !sign[4].isEmpty() || !sign[5].isEmpty()
                    || !sign[6].isEmpty() || !sign[7].isEmpty()
                    || !sign[8].isEmpty() || !sign[9].isEmpty())
            {
                check1 = true;
                textError.setText("Таблица сохранена");
            } else {
                textError.setText("Таблица пуста");
            }


        });

        // кнопка сохранения пути
        butPath.setOnAction(actionEvent -> {
                Path = MyFilePath.getText();
                System.out.println(Path);
                if (!Path.isEmpty()) {
                    check2 = true;
                    textError.setText("Путь сохранён");
                } else {
                    textError.setText("Путь пуст");
                }

        });

        // кнопка сохранения
        saveButton.setOnAction(e -> {
            number = 0;
            // поверка выбора поля сохранения
                // проверка наличия таблицы
                    // проверка наличия пути
                    // сохраняем в массив hashtables
                    // сереализуем
            if (allFiles.getValue() != null) {
                if(check1 && check2) {
                    textError.setText("Сохраняем словарь");
                    for (int i = 1; i <= 10; i++) { // определение словаря в массиве
                        if (allFiles.getValue().equals("Словарь " + i)) {
                            number = i;
                            System.out.println(number);
                        }
                    }
                    //парсинг
                    hashTables[number - 1] = new HashTable(10, sign);
                    hashTables[number - 1].parse(Path);

                    //сереализация
                    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Словарь " + number + ".dat"))) {
                        oos.writeObject(hashTables[number - 1]);
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }

                    textError.setText("Словарь сохранён в поле " + number);
                } else {
                    textError.setText("Загрузите таблицу и путь");
                }
            } else {
                textError.setText("Выберите словарь для сохранения");
            }
        });

        // кнопка поиска слова
        butWord.setOnAction(e -> {
            textError.setText("");
            number = 0;
            for (int i = 1; i <= 10; i++) { // определение словаря в массиве
                if (allFiles.getValue().equals("Словарь " + i)) {
                    number = i;
                    System.out.println(number);
                }
            }

            if (/*!allFiles.getValue().equals("") &&*/ hashTables[number-1] != null) {
                String Word;
                Word = MyInWord.getText();
                Word = Word.toLowerCase(Locale.ROOT);


                long startTime;
                long endTime;
                long startEndTime = 0;
                int[] WordLev = hashTables[number - 1].search(Word);
                // поиск

                for (int i = 0; i < 25; i++) {
                    startTime = System.nanoTime();//System.currentTimeMillis();
                    hashTables[number - 1].search(Word);
                    endTime = System.nanoTime();//System.currentTimeMillis();
                    startEndTime += endTime - startTime;
                }
                startEndTime = startEndTime / 25;


                XYChart.Series<String, Number> timeSeries = new XYChart.Series<>();
                timeSeries.setName("Словарь " + number + ": " + Word);
                timeSeries.getData().add(new XYChart.Data<>("Время", startEndTime/*endTime - startTime*/));
                System.out.println(startEndTime/*endTime - startTime*/);
                TimeBar.getData().add(timeSeries);

                XYChart.Series<String, Number> wordSeries = new XYChart.Series<>();
                wordSeries.setName("Словарь " + number + ": " + Word);
                wordSeries.getData().add(new XYChart.Data<>("Кол-во слов", WordLev[0]));
                System.out.println(WordLev[0]);
                WordBar.getData().add(wordSeries);

                XYChart.Series<String, Number> levSeries = new XYChart.Series<>();
                levSeries.setName("Словарь " + number + ": " + Word);
                levSeries.getData().add(new XYChart.Data<>("Вызовы Растояния Левенштейна", WordLev[1]));
                System.out.println(WordLev[1]);
                LevBar.getData().add(levSeries);

                System.out.println(Word);

            } else {
                textError.setText("Выберите не пустой словарь");
            }
        });


    }
}

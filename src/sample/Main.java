package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Хэширование по сигнатуре");
        primaryStage.setScene(new Scene(root, 1020, 586));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
//
       // Scanner in = new Scanner(System.in);

        launch(args);
//        String[] sign = new String[10];
//
//        sign[0] = "абв";
//        sign[1] = "где";
//        sign[2] = "ёжз";
//        sign[3] = "ийк";
//        sign[4] = "лмн";
//        sign[5] = "опрс";
//        sign[6] = "туфх";
//        sign[7] = "цчшщ";
//        sign[8] = "ъыьэ";
//        sign[9] = "юя-.";
//        HashTable H = new HashTable(10, sign);
//        H.parse("C:/Users/BedTed/Desktop/russian 50 000.txt");
//        //System.out.println(Arrays.toString(H.table));
//        for (int i = 0; i < 11; i++) {
//            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Словарь " + i + ".dat"))) {
////                    countHashFile++;
//                //hashtable = new HashTable(10,sign);
//                System.out.println("ПУПАЛУПА");
////                    hashtable.parse(Path); // считали слова из файла и закодировали под нашу таблицу кодирования
//                oos.writeObject(H);
//            }
//            catch(Exception ex){
//                System.out.println(ex.getMessage());
//            }
//        }


        // Десериализация
//        HashTable hashtable;
//            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Словарь 0"  + ".dat")))
//            {
//                hashtable = (HashTable)ois.readObject();
//                System.out.println(Arrays.toString(hashtable.table)); // вывод всего что есть
//            }
//            catch(Exception ex){
//
//                System.out.println(ex.getMessage());
//            }

//        System.out.println(Arrays.toString(hashtable.table));

    }
}

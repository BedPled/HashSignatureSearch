package sample;


import java.io.*;
import java.util.LinkedList;
import java.util.BitSet;
import java.util.Locale;

public class HashTable implements Serializable {
    String[] sign;
    String Path;
    int signLen;
    int size;
    //int isUse[];
    LinkedList<String>[] table;

//    @Override
//    public String toString() {
//        return "SavedGame{" +
//                "territoriesInfo=" + Arrays.toString(territoriesInfo) +
//                ", resourcesInfo=" + Arrays.toString(resourcesInfo) +
//                ", diplomacyInfo=" + Arrays.toString(diplomacyInfo) +
//                '}';
//    }

    HashTable(int signLen, String[] sign) {
        this.sign = new String[signLen];
        System.arraycopy(sign, 0, this.sign, 0, signLen);
        this.signLen = signLen;
        size = (int)Math.pow(2, signLen);
        table = new LinkedList[size];
    }

    private int numSignChar (char a) { // получение бита сивола 'a' в таблице шифрования
        int help_sign = 1; // было 1

        for (int i = signLen - 1; i >=  0; i--) {
            if (this.sign[i].indexOf(a) == -1) {
                help_sign = help_sign << 1;
            } else {
                break;
            }
        }
        return help_sign;
    }

    private int HashFunction (String word) { // получение номера сигнатуры слова word
        int sign = 0;
        int help_sign;

        for (int i = 0; i < word.length(); i++) {
            help_sign = numSignChar(word.charAt(i));
            sign = sign | help_sign;
        }
        if (sign >= 1024) return 0;
        return sign;
    }

    private void add (String word) {
        word = word.toLowerCase(Locale.ROOT);
        int numberOfSign = HashFunction(word);
        if (numberOfSign >= 1024) {
            System.out.println(numberOfSign);
            System.out.println(word);
        }
//        System.out.println(table[numberOfSign]);

        if (table[numberOfSign] == null) {
            table[numberOfSign] = new LinkedList<String>();
            table[numberOfSign].add(word);
        } else if (!table[numberOfSign].contains(word)) {
            table[numberOfSign].add(word);
        }
    }

    private static int levenstain(String str1,  String str2) { // расстояние левенштейна
        int[] Di_1 = new int[str2.length() + 1];
        int[] Di = new int[str2.length() + 1];

        for (int j = 0; j <= str2.length(); j++) {
            Di[j] = j; // (i == 0)
        }

        for (int i = 1; i <= str1.length(); i++) {
            System.arraycopy(Di, 0, Di_1, 0, Di_1.length);

            Di[0] = i; // (j == 0)
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) != str2.charAt(j - 1)) ? 1 : 0;
                Di[j] = min(
                        Di_1[j] + 1,
                        Di[j - 1] + 1,
                        Di_1[j - 1] + cost
                );
            }
        }

        return Di[Di.length - 1];
    }

    private static int min(int n1, int n2, int n3) { // для расстояния Левенштейна
        return Math.min(Math.min(n1, n2), n3);
    }

    public int[] search (String word) { // поиск слова в таблице
        int numberOfSign = HashFunction(word);
        int numberOfSignHelp = numberOfSign;
        int help_sign = 1;
        int [] out = new int[2];
        out[0] = 1;
        out[1] = 0;

        if (table[numberOfSign] != null && table[numberOfSign].contains(word)) {
            return out;
        } else {
            int minLevinstein = Integer.MAX_VALUE;
            int numbLevinstein;
            LinkedList<String> similarWords = new LinkedList<String>();

            // этап 1: поиск в той же строке с наибольшимим совпадениями
            for (int i = 0; i < table[numberOfSign].size(); i++) {
                numbLevinstein = levenstain(table[numberOfSign].get(i), word);
                out[1]++;
                if (numbLevinstein == minLevinstein) {
                    similarWords.add(table[numberOfSign].get(i));
                    out[0]++;
                } else if (numbLevinstein < minLevinstein) {
                    similarWords.clear();
                    minLevinstein = numbLevinstein;
                    similarWords.add(table[numberOfSign].get(i));
                    out[0] = 1;
                }
            }

            // этап 2: поиск в каждой строке с поменянным битом
            for (int i = 0; i < signLen; i++) {
                numberOfSignHelp = numberOfSign ^ help_sign;
                if (help_sign < 1024 && table[numberOfSignHelp] != null)
                for (int j = 0; j < table[numberOfSignHelp].size(); j++) {
                        numbLevinstein = levenstain(table[numberOfSignHelp].get(j), word);
                        out[1]++;
                        if (numbLevinstein == minLevinstein) {
                            similarWords.add(table[numberOfSignHelp].get(j));
                            out[0]++;
                        } else if (numbLevinstein < minLevinstein) {
                            similarWords.clear();
                            minLevinstein = numbLevinstein;
                            similarWords.add(table[numberOfSignHelp].get(j));
                            out[0] = 1;
                        }
                }

                help_sign = help_sign << 1;
            }
        }
        return out;
    }

    public void parse (String FilePath) {
       // String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя-";
        try {
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(FilePath);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                if (line.indexOf(' ') == -1) {
                    add(line); // добавляем в словарь
                }
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
};



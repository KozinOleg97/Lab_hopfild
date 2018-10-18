package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static <T> T[][] multiplyByMatrix(T[][] m1, T[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length


        T[][] mResult = new T[mRRowLength][mRColLength];
        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }



    public static double[] multiplyByVector(double[] vector, double[][] matrix) {


        int n = matrix[0].length;//столбцы
        int m = matrix.length;//строки

        double x = 0;
        double[] res = new double[vector.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x += vector[i] * matrix[j][i];
            }
            res[i] = x;
            x = 0.0;
        }
        return res;
    }


    public static Integer[][] transpose(Integer[][] matrix) throws IllegalArgumentException {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int rowLength = matrix[0].length;
        for (Integer[] ai : matrix) {
            if (rowLength != ai.length) {
                throw new IllegalArgumentException("Non-equal rows");
            }
        }

        Integer[][] tMatrix = new Integer[rowLength][];
        for (int i = 0; i < rowLength; i++) {
            tMatrix[i] = new Integer[matrix.length];
        }
        for (int i = 0; i < matrix.length; i++) {
            Integer[] tArr = matrix[i];
            for (int j = 0; j < rowLength; j++) {
                tMatrix[j][i] = tArr[j];
            }
        }
        return tMatrix;
    }

    public static String[] singleChars(String s) {
        return s.split("(?!^)");
    }

    public static String[] readToStrMas(String fileName) {
        try {
            Path filePath = new File(fileName).toPath();
            Charset charset = Charset.defaultCharset();
            List<String> stringList = null;
            stringList = Files.readAllLines(filePath, charset);

            if (stringList.isEmpty()) {
                return null;
            }


            String[] stringArray = stringList.toArray(new String[]{});

            return stringArray;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[][] convertStrMasToMatrix(String[] strArray) {
        int rows = strArray.length;
        int columns = strArray[0].length();
        String[][] mas = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            mas[i] = singleChars(strArray[i]);
        }

        return mas;
    }


    public static Integer[][] convertStrMasToIntMasByMap(String[][] strMas, HashMap<String, Integer> dictionary) {
        int rows = strMas.length;
        int columns = strMas[0].length;
        Integer[][] mas = new Integer[rows][columns];

        for (int i = 0; i < rows; i++) {         // rows from m1
            for (int j = 0; j < columns; j++) {
                mas[i][j] = dictionary.get(strMas[i][j]);
            }
        }

        return mas;
    }

    public static <T> void matrPrint(T[][] matr) {
        for (int i = 0; i < matr.length; i++) {
            System.out.println(Arrays.toString(matr[i]));
        }

    }

    public static void main(String[] args) {

        Integer[][] W = null;
        Integer[][] image;


        HashMap<String, Integer> convertDict = new HashMap<String, Integer>();
        convertDict.put("-", -1);
        convertDict.put("#", 1);

        /*String[] m = readToStrMas("1.txt");
        String[][] example = convertStrMasToMatrix(m);
        Integer[][] qq = convertStrMasToIntMasByMap(example, convertDict);*/


        String endSymb = "1";
        String curName;

        Scanner in = new Scanner(System.in);

        while (endSymb != "0") {
            System.out.println("0 - Выход; 1 - Обучение; 2 - Работа;");
            endSymb = in.nextLine();

            switch (endSymb) {
                case "0":
                    System.out.println("Выход");
                    endSymb = "0";
                    break;
                case "1":
                    System.out.println("Введи имя образа для обучения");
                    curName = in.nextLine();

                    image = convertStrMasToIntMasByMap(
                            convertStrMasToMatrix(
                                    readToStrMas(curName + ".txt"))
                            , convertDict);

                    if (W == null){
                        int rows = image.length;
                        int columns = image[0].length;
                        W = new Integer[rows][columns];

                        W = multiplyByMatrix(image, transpose(image).);
                    }

                        matrPrint(image);
                    break;
                case "2":
                    System.out.println("Введи имя образа для работы");
                    curName = in.nextLine();

                    image = convertStrMasToIntMasByMap(
                            convertStrMasToMatrix(
                                    readToStrMas(curName + ".txt"))
                            , convertDict);

                    matrPrint(image);
                    break;
                case "3":

                    break;
                default:
                    System.out.println("Ошибка ввода");

            }


        }
        in.close();


    }
}

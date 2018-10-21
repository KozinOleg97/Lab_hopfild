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


    public static double[][] multiplyByMatrix(double[][] m1, double[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length

        double[][] mResult = new double[mRRowLength][mRColLength];

        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }


    public static Integer[][] multiplyByMatrix(Integer[][] m1, Integer[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length


        Integer[][] mResult = new Integer[mRRowLength][mRColLength];

        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {
                mResult[i][j] = 0;
            }
        }

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

    public static String readToStr(String fileName) {
        try {
            Path filePath = new File(fileName).toPath();
            Charset charset = Charset.defaultCharset();
            List<String> stringList = null;
            stringList = Files.readAllLines(filePath, charset);

            if (stringList.isEmpty()) {
                return null;
            }

            String stringArray = "";

            for (String elem : stringList
            ) {
                stringArray += elem;
            }

            return stringArray;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] convertStrToMas(String str) {

        String[] mas = new String[str.length()];

        mas = str.split("");

        return mas;
    }


    public static Integer[][] convertStrMasToIntMasByMap(String[] strMas, HashMap<String, Integer> dictionary) {
        int rows = strMas.length;
        int columns = 1;
        Integer[][] mas = new Integer[columns][rows];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++)
                mas[i][j] = dictionary.get(strMas[j]);
        }

        return mas;
    }

    public static String[][] convertIntMasToStrMasByMap(Integer[][] intMas, HashMap<Integer, String> dictionary, int size) {
        int rows = intMas.length;
        //int columns = intMas[0].length;
        String[][] strMas = new String[size][size];

        for (int i = 0; i < rows/size; i++) {
            for (int j=i*size; j<(i+1)*size; j++){
                strMas[i][j-(size*i)] = dictionary.get(intMas[j][0]);
            }
            //str += dictionary.get(intMas[i]);
        }

        return strMas;
    }


    public static <T> void matrPrint(T[][] matr) {
        for (int i = 0; i < matr.length; i++) {
            System.out.println(Arrays.toString(matr[i]));
        }

    }

    public static Integer[][] addMatrix(Integer[][] m1, Integer[][] m2) throws NullPointerException {

        int rows1 = m1.length;
        int columns1 = m1[0].length;

        int rows2 = m2.length;
        int columns2 = m2[0].length;

        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new NullPointerException("Wrong Matrix to Add");
        } else {
            Integer[][] resMatr = new Integer[rows1][columns1];

            for (int i = 0; i < rows1; i++) {         // rows from m1
                for (int j = 0; j < columns1; j++) {
                    resMatr[i][j] = 0;
                }
            }


            for (int i = 0; i < rows1; i++) {
                for (int j = 0; j < columns1; j++) {
                    resMatr[i][j] += m1[i][j] + m2[i][j];
                }
            }
            return resMatr;
        }
    }

    public static Integer[][] fActiv(Integer[][] m1) {

        int rows1 = m1.length;
        int columns1 = m1[0].length;

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns1; j++) {
                if (m1[i][j] >= 0) {
                    m1[i][j] = 1;
                } else m1[i][j] = -1;
            }
        }

        return m1;
    }

    public static void main(String[] args) {

        Integer[][] W = null;
        Integer[][] image;


        HashMap<String, Integer> convertDict = new HashMap<String, Integer>();
        convertDict.put("-", -1);
        convertDict.put("#", 1);

        HashMap<Integer, String> convertDict2 = new HashMap<Integer, String>();
        convertDict2.put(-1, "-");
        convertDict2.put(1, "#");

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
                            convertStrToMas(
                                    readToStr(curName + ".txt"))
                            , convertDict);

                    if (W == null) {
                        int rows = image.length;
                        //int columns = image[0].length;
                        //W = new Integer[][];

                       /* Integer[][] a = new Integer[][]{
                            {1},
                            {-1},
                            {1},
                            {1}
                        };

                        Integer[][] x = transpose(a);

                        Integer[][] b = new Integer[][]{
                                {1,-1,1,1}
                        };

                        Integer [][] c = multiplyByMatrix(a,b);

                        System.out.println();*/
                        W = multiplyByMatrix(transpose(image), image);
                    } else {
                        W = addMatrix(W, multiplyByMatrix(transpose(image), image));
                    }

                    //matrPrint(image);
                    break;
                case "2":
                    System.out.println("Введи имя образа для работы");
                    curName = in.nextLine();

                    image = convertStrMasToIntMasByMap(
                            convertStrToMas(
                                    readToStr(curName + ".txt"))
                            , convertDict);

                    int rows = W.length;
                    int columns = W[0].length;

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {
                            if (i == j) {
                                W[i][j] = 0;
                            }
                        }
                    }

                    Integer[][] ResImage;
                    ResImage = multiplyByMatrix(W, transpose(image));
                    ResImage = fActiv(ResImage);

                    String[][] resImageStr = convertIntMasToStrMasByMap(ResImage, convertDict2,(int)Math.sqrt(rows));

                    matrPrint(resImageStr);
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

package org.example;

/*

    В левом верхнем углу прямоугольной таблицы размером N×M находится черепашка.
    В каждой клетке таблицы записано некоторое число.
    Черепашка может перемещаться вправо или вниз, при этом маршрут черепашки заканчивается в правом нижнем углу таблицы.

    Подсчитаем сумму чисел, записанных в клетках, через которую проползла черепашка (включая начальную и конечную клетку). Найдите наибольшее возможное значение этой суммы и маршрут, на котором достигается эта сумма.

    Формат ввода
    В первой строке входных данных записаны два натуральных числа N и M, не превосходящих 100 — размеры таблицы.
    Далее идет N строк, каждая из которых содержит M чисел, разделенных пробелами — описание таблицы.
    Все числа в клетках таблицы целые и могут принимать значения от 0 до 100.

    Формат вывода
    Первая строка выходных данных содержит максимальную возможную сумму, вторая — маршрут, на котором достигается эта сумма.
    Маршрут выводится в виде последовательности, которая должна содержать N-1 букву D, означающую передвижение вниз и M-1 букву R, означающую передвижение направо.
    Если таких последовательностей несколько, необходимо вывести ровно одну (любую) из них.

    Пример 1
    Ввод
    5 5
    9 9 9 9 9
    3 0 0 0 0
    9 9 9 9 9
    6 6 6 6 8
    9 9 9 9 9
    Вывод
    74
    D D R R R R D D

 */

import java.util.Scanner;

public class _3_Вывести_маршрут_максимальной_стоимости {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];

        // Ввод матрицы
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Вычисление сумм для первой строки и первого столбца
        for (int i = 1; i < n; i++) {
            matrix[i][0] += matrix[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            matrix[0][i] += matrix[0][i - 1];
        }

        StringBuilder stringBuilder = new StringBuilder();

        // Вычисление минимального пути
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int h1 = 0;
                int l1 = 0;

                int height = matrix[i - 1][j];
                int length = matrix[i][j - 1];

                if (height > h1) {
                    h1 = height;
                }
                if (length > l1) {
                    l1 = length;
                }

                if (h1 > l1 && (j % 2 != 0 || j == m - 1)) {
                    stringBuilder.append("D");
                } else if (h1 < l1 && (j % 2 != 0 || j == m - 1)) {
                    stringBuilder.append("R");
                }
                matrix[i][j] += Math.max(height, length);
            }

//            if (h1 > l1) {
//                stringBuilder.append("D");
//            } else {
//                stringBuilder.append("R");
//            }

        }

        for (int i = stringBuilder.length() - 1; i > 0; i--) {
            stringBuilder.insert(i, ' ');
        }

        System.out.println(matrix[n - 1][m - 1]);
        System.out.println(stringBuilder);
    }

}

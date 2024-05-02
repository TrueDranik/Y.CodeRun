package org.example;

/*
    Задан массив a размера n.
    Необходимо посчитать количество уникальных элементов в данном массиве.
    Элемент называется уникальным, если встречается в массиве ровно один раз.

    Формат ввода
    В первой строке входных данных подается одно целое число n (1≤n≤10^5).
    Во второй строке входных данных подается n целых чисел, разделенных пробелами --- a1, a2, …,an(1≤ai≤10^9).

    Формат вывода
    В единственной строке выведите ответ на задачу.

    Пример 1
    Ввод
    5
    1 2 3 4 5
    Вывод
    5

    Пример 2
    Ввод
    5
    1 2 3 4 4
    Вывод
    3

    Пример 3
    Ввод
    10
    9 3 10 5 7 6 4 1 2 8
    Вывод
    10

 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _155_Уникальные_элементы {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Map<Integer, Integer> elementCounts = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            elementCounts.put(num, elementCounts.getOrDefault(num, 0) + 1);
        }

        int uniqueCount = 0;
        for (int count : elementCounts.values()) {
            if (count == 1) {
                uniqueCount++;
            }
        }

        System.out.println(uniqueCount);
    }

}

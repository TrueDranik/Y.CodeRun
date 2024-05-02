package org.example;

/*
    1. Средний элемент

    Рассмотрим три числа a, b и c. Упорядочим их по возрастанию.
    Какое число будет стоять между двумя другими?

    Пример 1
    Ввод 1 2 3
    Вывод 2

    Пример 2
    Ввод 1000 -1000 0
    Вывод 0

    Пример 3
    Ввод 3 1 3
    Вывод 3

 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class _1_Средний_элемент {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();
        String[] parts = input.split(" ");
        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }

        int i = middleElement(numbers);

        writer.write(String.valueOf(i));

        reader.close();
        writer.close();
    }

    private static int middleElement(int[] numbers) {
        Arrays.sort(numbers);

        return numbers[1];
    }

}

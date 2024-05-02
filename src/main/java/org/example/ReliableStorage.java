package org.example;

import java.util.Scanner;

public class ReliableStorage {
    // Функция для проверки возможности распределения данных без потери записей
    static boolean canDistributeData(long[] servers) {
        long sum = 0;
        long maxServer = 0;

        // Вычисляем сумму и находим максимальное количество записей на сервере
        for (long server : servers) {
            sum += server;
            maxServer = Math.max(maxServer, server);
        }

        // Проверяем на четность сумму
        if (sum % 2 != 0)
            return false;

        // Проверяем, что каждый сервер хранит не менее половины всех записей
        for (long server : servers) {
            if (server < sum / 2)
                return false;
        }

        // Проверяем битовое представление
        long xor = 0;
        for (long server : servers) {
            xor ^= server;
        }

        // Если xor равен 0, то все ок
        return xor == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // количество серверов
        long[] servers = new long[n]; // массив для хранения количества записей на сервере

        // Читаем количество записей на каждом сервере
        for (int i = 0; i < n; i++) {
            servers[i] = scanner.nextLong();
        }

        // Проверяем возможность распределения данных и выводим результат
        if (canDistributeData(servers))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}



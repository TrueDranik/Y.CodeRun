package org.example;

/*
    В мире программистов и математиков все студенты много учатся, но любят и отдыхать.
    Для многих лучшим отдыхом будет ежевечерний просмотр любимых видео за чашечкой чая.
    Это может быть какой-то фильм, сериал, лекция по любимому предмету или просто мемы про кошечек.

    Мы опросили 100 студентов и выяснили, что ни один из них не упускает возможности посмотреть вечером любимые видео.
    В файле sample_final.csv хранится информация о том, какие фильмы смотрели опрошенные студенты на неделе с 1 по 7 апреля 2024 года.
    Оказалось, что выборка, которую нам удалось собрать является репрезентативной
    (то есть отражает все многообразие возможных фильмов и говорит нам о том, что по ее параметрам можно оценить просмотры на других неделях).

    В связи с этим мы просим вас узнать:

    С какой вероятностью в Четверг студенты смотрят "Видосы про собачек"?
    Основываясь на имеющихся данных, вычислите вероятность того, что через неделю 11 апреля 2024 года ровно 8 студентов из 100 посмотрят "Видосы про собачек"?
    Основываясь на имеющихся данных, вычислите вероятность того, что через неделю 11 апреля 2024 года более 8 студентов из 100 посмотрят "Видосы про собачек"?

    Все ответы стоит округлить до 4 знаков после запятой по правилам математического округления.
    Ответ на каждый пункт представьте в виде десятичной дроби. При вводе ответов разделяйте их пробелом.
    Если Вы не можете дать ответ на какой-то из вопросов, в качестве ответа отправьте 1.

    Пример ввода ответа: 0.0100 0.0679 0.9470.

    Подсказка: Для решения этой задачи стоит обратиться к биномиальному распределению.
    Разобрал подробнее в файле binomial-distribution.tex
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VideoProbabilityCalculator {
    public static void main(String[] args) {
        // Путь к файлу CSV
        String csvFile = "contest/sample_final.csv";
        // Параметры для расчета
        int totalStudents = 100;
        int numStudentsWatchingDogVideosThursday = 0;
        int numStudentsWatchingDogVideosNextWeek = 0;

        ClassLoader classLoader = ElectronicDiary.class.getClassLoader();
        // Загружаем ресурс (файл .csv) как поток InputStream
        InputStream inputStream = classLoader.getResourceAsStream(csvFile);

        try {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                // Чтение файла построчно
                while ((line = br.readLine()) != null) {
                    // Разделение строки по запятой
                    String[] data = line.split(",");
                    // Получение дня недели и видеозаписи
                    String dayOfWeek = data[4].trim();
                    String video = data[5].trim();

                    // Проверка, смотрели ли студенты "Видосы про собачек" в Четверг
                    if (dayOfWeek.equals("Четверг") && video.equals("Видосы про собачек")) {
                        numStudentsWatchingDogVideosThursday++;
                    }

                    // Проверка, смотрели ли студенты "Видосы про собачек" на следующей неделе
                    if (video.equals("Видосы про собачек")) {
                        numStudentsWatchingDogVideosNextWeek++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Вероятность того, что в Четверг студенты смотрят "Видосы про собачек"
        double probabilityThursday = (double) numStudentsWatchingDogVideosThursday / totalStudents;

        // Вероятность того, что через неделю 11 апреля 2024 года ровно 8 студентов из 100 посмотрят "Видосы про собачек"
        double probabilityNextWeekExactly8 = calculateBinomialProbability(numStudentsWatchingDogVideosNextWeek, totalStudents, 8);

        // Вероятность того, что через неделю 11 апреля 2024 года более 8 студентов из 100 посмотрят "Видосы про собачек"
        double probabilityNextWeekMoreThan8 = 1 - calculateCumulativeBinomialProbability(numStudentsWatchingDogVideosNextWeek, totalStudents, 8);

        // Округление результатов до 4 знаков после запятой
        probabilityThursday = Math.round(probabilityThursday * 10000.0) / 10000.0;
        probabilityNextWeekExactly8 = Math.round(probabilityNextWeekExactly8 * 10000.0) / 10000.0;
        probabilityNextWeekMoreThan8 = Math.round(probabilityNextWeekMoreThan8 * 10000.0) / 10000.0;

        // Вывод результатов
        System.out.println(probabilityThursday + " " + probabilityNextWeekExactly8 + " " + probabilityNextWeekMoreThan8);
    }

    // Метод для вычисления биномиальной вероятности P(X = k)
    private static double calculateBinomialProbability(int n, int total, int k) {
        double p = (double) n / total;
        double q = 1 - p;
        double binomialCoefficient = calculateBinomialCoefficient(total, k);
        return binomialCoefficient * Math.pow(p, k) * Math.pow(q, total - k);
    }

    // Метод для вычисления кумулятивной биномиальной вероятности P(X <= k)
    private static double calculateCumulativeBinomialProbability(int n, int total, int k) {
        double cumulativeProbability = 0;
        for (int i = 0; i <= k; i++) {
            cumulativeProbability += calculateBinomialProbability(n, total, i);
        }
        return cumulativeProbability;
    }

    // Метод для вычисления биномиального коэффициента (количество сочетаний)
    private static double calculateBinomialCoefficient(int n, int k) {
        double numerator = factorial(n);
        double denominator = factorial(k) * factorial(n - k);
        return numerator / denominator;
    }

    // Метод для вычисления факториала числа
    private static double factorial(int n) {
        if (n == 0) {
            return 1;
        }
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

package org.example;

/*
    Необходимо привести SQL запрос в каноническу форму
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanonicalQuery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите запрос (введите ';' для завершения ввода):");

        StringBuilder input = new StringBuilder();
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            input.append(line).append("\n");
            // Если строка заканчивается на ';', прекращаем считывание
            if (line.trim().endsWith(";")) {
                break;
            }
        }

        String canonicalQuery = getCanonicalQuery(input.toString());
        System.out.println("Каноническая форма запроса:");
        System.out.println(canonicalQuery);
    }

    public static String getCanonicalQuery(String input) {
        input = input.replaceAll("\n", " ");
        input = input.replaceAll("\"[^\"]*\"", "?");

        // Заменяем значения параметров в секции in на ...
        Pattern pattern = Pattern.compile("\\bin\\s*\\([^)]*\\)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String inSection = matcher.group();
            // Сохраняем пробелы перед in и после )
            String spaces = inSection.replaceAll("\\bin\\s*|\\(.*", "");
            input = input.replace(inSection, "in (...) " + spaces);
        }

        // Восстанавливаем пробелы вокруг ключевых слов и операторов
        input = input.replaceAll("\\b(select|from|where|and|or|in)\\b", " $1 ");
        // Убираем пробел после ifnull
        // Сохраняем пробелы перед открывающими и закрывающими скобками
        input = input.replaceAll("\\s*\\(\\s*", " (").replaceAll("\\s*\\)\\s*", ") ");
        // Убираем пробелы перед и после скобок в секции where
        input = input.replaceAll("\\bwhere\\b\\s*\\(", " where (").replaceAll("\\)\\s*\\)", "))");
        input = input.replaceAll("\\bifnull\\s+\\(", "ifnull(");
        // Убираем пробел перед ;
        input = input.replaceAll("\\s*;", ";");
        input = input.replaceAll("\\s+", " "); // Убираем лишние пробелы
        input = input.trim();
        input = input.toLowerCase();

        return input;
    }
}



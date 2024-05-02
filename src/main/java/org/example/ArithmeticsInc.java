package org.example;

/*
Компания Arithmetics Inc. разрабатывает программное обеспечение для работы с бесконечными арифметическими прогрессиями. Необходимо разработать структуру данных, которая будет хранить арифметические прогрессии и поддерживать следующие операции:

Операция первого типа позволяет добавить новую арифметическую прогрессию в структуру.
Операция второго типа позволяет удалить заданную арифметическую прогрессию из структуры.
Операция третьего типа находит арифметическую прогрессию с минимальным первым элементом и возвращает найденный элемент, предварительно заменив стартовый элемент в прогрессии на следующей в ней. Если таких прогрессий несколько, то обрабатывается прогрессия, у которой минимальный идентификатор.


Формат ввода
На вход подается одно целое положительное число q (1≤q≤10^5) — количество операций.

Далее на вход подаются q строк в следующем формате:
Если это операция первого типа, то на вход подаются четыре числа 1, a1, d, id(0≤∣a1∣,∣d∣≤10^9, 1≤id≤10^9) — первый элемент и разность добавляемой прогрессии, а также ее идентификатор.

Если это операция второго типа, то на вход подаются два числа 2, id — идентификатор прогрессии, которую необходимо удалить.

Если это операция третьего типа, то на вход подается одно число 3. В этот момент хотя бы одна прогрессия будет находиться в структуре.

Гарантируется, что все id арифметических прогрессий различны. Удаляемая прогрессия, гарантированно находится в структуре данных.


Формат вывода
Выведите ответы на каждый запрос третьего типа по одному в строке.


Пример 1:
Ввод:
15
1 3 -4 1
1 -5 4 3
1 -2 10 2
3
3
2 3
3
3
2 2
1 -5 4 4
3
2 1
3
3
3
Вывод:
-5
-2
3
-1
-5
-5
-1
3
 */

import java.util.HashMap;
import java.util.Scanner;

class ArithProgression {
    int firstElement;
    int difference;
    int id;

    public ArithProgression(int firstElement, int difference, int id) {
        this.firstElement = firstElement;
        this.difference = difference;
        this.id = id;
    }
}

public class ArithmeticsInc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int q = scanner.nextInt(); // количество операций
        scanner.nextLine(); // переходим на следующую строку

        HashMap<Integer, ArithProgression> progressions = new HashMap<>();
        int[] currentFirstElements = new int[q + 1]; // массив текущих первых элементов

        for (int i = 0; i < q; i++) {
            String[] input = scanner.nextLine().split(" ");
            int operation = Integer.parseInt(input[0]);

            if (operation == 1) {
                int a1 = Integer.parseInt(input[1]);
                int d = Integer.parseInt(input[2]);
                int id = Integer.parseInt(input[3]);
                progressions.put(id, new ArithProgression(a1, d, id));
                if (a1 < currentFirstElements[1] || i == 0)
                    currentFirstElements[1] = a1;
            } else if (operation == 2) {
                int id = Integer.parseInt(input[1]);
                // Удаляем прогрессию с указанным id
                progressions.remove(id);
            } else if (operation == 3) {
                // Извлекаем минимальный первый элемент
                System.out.println(currentFirstElements[1]);
                // Обновляем текущий первый элемент
                int nextFirstElement = Integer.MAX_VALUE;
                for (int element : progressions.keySet()) {
                    if (element < nextFirstElement)
                        nextFirstElement = element;
                }
                currentFirstElements[1] = nextFirstElement + progressions.get(nextFirstElement).difference;
            }
        }
    }
}



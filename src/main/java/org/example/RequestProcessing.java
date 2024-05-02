package org.example;

/*

    На производство внедрили новую систему обработки заявок.
    Оператор может принять новую заявку с k-м приоритетом или удалить заявку с k-м приоритетом из системы.

    Важной особенностью данной системы является возможность быстро ответить на вопрос, какая задача на данный момент является i-й по важности относительно приоритетов заявок.
    У заявки с наибольшим приоритетом i=1.

    Напишите программу на языке Java, которая будет выполнять запросы оператора.

    Формат ввода
    На первой строчке находится число n (1≤n≤10^5) — количество заявок на обслуживание.

    Далее на n строчках идёт последовательность обработки заявок:
    * 1 k — в системе появляется новая заявка c приоритетом k (∣k∣≤10^9);
    * 0 i — оператора просят назвать приоритет i-й по важности заявки в системе;
    * −1 k — заявка с приоритетом k завершена и удалена из системы.

    Формат вывода
    Для каждой заявки типа "0 i" выведите приоритет i-й по важности заявки в системе.

    Пример:
    Ввод:
    11
    1 5
    1 3
    1 7
    0 1
    0 2
    0 3
    -1 5
    1 10
    0 1
    0 2
    0 3

    Вывод:
    7
    5
    3
    10
    7
    3

    Примечание:
    В примере первые три команды добавляют заявки с приоритетами 5, 3 и 7.
    Следующие три команды запрашивают первую, вторую и третью по приоритету заявки, это будут заявки с приоритетами 7, 5 и 3 соответственно. Дальше удаляется заявка с приоритетом 5 и добавляется заявка с приоритетом 10. Последние три команды снова запрашивают первую, вторую и третью по приоритету заявки, но теперь это будут заявки с приоритетами 10, 7 и 3.

 */
import java.util.Scanner;

public class RequestProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        final int MAX_PRIORITY = 1_000_000_000; // Максимальный приоритет
        final int OFFSET = 1_000_000_000; // Смещение для хранения приоритетов отрицательных чисел
        int[] priorities = new int[MAX_PRIORITY * 2 + 1];

        for (int i = 0; i < n; i++) {
            int type = scanner.nextInt();
            if (type == 1) {
                int priority = scanner.nextInt();
                priorities[priority + OFFSET]++;
            } else if (type == 0) {
                int importance = scanner.nextInt();
                int count = 0, req = 0;
                for (int j = MAX_PRIORITY * 2; j >= 0; j--) {
                    count += priorities[j];
                    if (count >= importance) {
                        req = j - OFFSET;
                        break;
                    }
                }
                System.out.println(req);
            } else if (type == -1) {
                int priority = scanner.nextInt();
                if (priorities[priority + OFFSET] > 0) {
                    priorities[priority + OFFSET]--;
                }
            }
        }
    }
}

/*
    Ни одно решение не прошло по TL (time limit)
    ДРУГИЕ РЕШЕНИЯ:

    import java.util.Collections;
    import java.util.PriorityQueue;
    import java.util.Scanner;

    public class RequestProcessing {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            PriorityQueue<Integer> requests = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                int type = scanner.nextInt();
                if (type == 1) {
                    int priority = scanner.nextInt();
                    requests.add(priority);
                } else if (type == 0) {
                    int importance = scanner.nextInt();
                    PriorityQueue<Integer> copy = new PriorityQueue<>(requests);
                    int count = 0, req = 0;
                    while (!copy.isEmpty() && count < importance) {
                        req = copy.poll();
                        count++;
                    }
                    System.out.println(req);
                } else if (type == -1) {
                    int priority = scanner.nextInt();
                    requests.remove(priority);
                }
            }
        }
    }

    ===================================================================================================================

    import java.util.Collections;
    import java.util.Map;
    import java.util.Scanner;
    import java.util.TreeMap;

    public class RequestProcessing {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            TreeMap<Integer, Integer> requests = new TreeMap<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                int type = scanner.nextInt();
                if (type == 1) {
                    int priority = scanner.nextInt();
                    requests.put(priority, requests.getOrDefault(priority, 0) + 1);
                } else if (type == 0) {
                    int importance = scanner.nextInt();
                    int count = 0, req = 0;
                    for (Map.Entry<Integer, Integer> entry : requests.entrySet()) {
                        count += entry.getValue();
                        if (count >= importance) {
                            req = entry.getKey();
                            break;
                        }
                    }
                    System.out.println(req);
                } else if (type == -1) {
                    int priority = scanner.nextInt();
                    if (requests.containsKey(priority)) {
                        int count = requests.get(priority);
                        if (count == 1) {
                            requests.remove(priority);
                        } else {
                            requests.put(priority, count - 1);
                        }
                    }
                }
            }
        }
    }

    ===================================================================================================================

    import java.util.*;

    public class RequestProcessing {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            TreeMap<Integer, Integer> requests = new TreeMap<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                int type = scanner.nextInt();
                if (type == 1) {
                    int priority = scanner.nextInt();
                    requests.put(priority, requests.getOrDefault(priority, 0) + 1);
                } else if (type == 0) {
                    int importance = scanner.nextInt();
                    int count = 0, req = 0;
                    for (Map.Entry<Integer, Integer> entry : requests.entrySet()) {
                        count += entry.getValue();
                        if (count >= importance) {
                            req = entry.getKey();
                            break;
                        }
                    }
                    System.out.println(req);
                } else if (type == -1) {
                    int priority = scanner.nextInt();
                    if (requests.containsKey(priority)) {
                        int count = requests.get(priority);
                        if (count == 1) {
                            requests.remove(priority);
                        } else {
                            requests.put(priority, count - 1);
                        }
                    }
                }
            }
        }
    }
 */
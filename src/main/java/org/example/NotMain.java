package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int M = m;
        int N = n;

        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(List.of(new Integer[M+2])));
        for (int i = 0; i < n; i++) {
            List<Integer> tmp = new ArrayList<>(m + 2);
            tmp.add(0);
            for (int j = 0; j < m; j++) {
                tmp.add(scanner.nextInt());
            }
            tmp.add(0);
            input.add(tmp);
        }
        input.add(new ArrayList<>(List.of(new Integer[M+2])));

        n = N;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                input.get(i).set(j, input.get(i).get(j) + Math.max(input.get(i).get(j - 1), input.get(i - 1).get(j)));
            }
        }

        System.out.println(input.get(N).get(M));

        while (!(N == 1 && M == 1)) {
            if (!(N == n && M == m)) {
                if (M <= 1) {
                    System.out.print(" D");
                    N--;
                } else if (N <= 1) {
                    System.out.print(" R");
                    M--;
                } else if (input.get(N - 1).get(M) > input.get(N).get(M - 1)) {
                    System.out.print(" D");
                    N--;
                } else {
                    System.out.print(" R");
                    M--;
                }
            } else {
                if (input.get(N - 1).get(M) > input.get(N).get(M - 1)) {
                    System.out.print("D");
                    N--;
                } else {
                    System.out.print("R");
                    M--;
                }
            }
        }
        System.out.println();
    }
}

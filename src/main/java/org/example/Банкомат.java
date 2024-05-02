package org.example;

/*
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */

import java.util.HashMap;
import java.util.Map;

public class Банкомат {
    private Map<Integer, Integer> availableNotes; // Хранит количество доступных купюр каждого номинала

    public Банкомат() {
        availableNotes = new HashMap<>();
    }

    public static void main(String[] args) {
        Банкомат atm = new Банкомат();
        atm.initializeNotes(10, 10, 10, 10, 10); // Пример инициализации банкомата

        // Пример выдачи суммы
        atm.withdraw(5500);
    }

    // Инициализация набора купюр в банкомате
    public void initializeNotes(int note50, int note100, int note500, int note1000, int note5000) {
        availableNotes.put(50, note50);
        availableNotes.put(100, note100);
        availableNotes.put(500, note500);
        availableNotes.put(1000, note1000);
        availableNotes.put(5000, note5000);
    }

    // Метод для выдачи суммы с учетом доступных купюр в банкомате
    public void withdraw(int amount) {
        Map<Integer, Integer> withdrawalResult = new HashMap<>();

        int remainingAmount = amount;
        for (int note : new int[]{5000, 1000, 500, 100, 50}) {
            int availableCount = availableNotes.getOrDefault(note, 0);
            int notesToWithdraw = Math.min(remainingAmount / note, availableCount);
            if (notesToWithdraw > 0) {
                withdrawalResult.put(note, notesToWithdraw);
                remainingAmount -= notesToWithdraw * note;
            }
        }

        if (remainingAmount == 0) {
            // Выдача купюр
            for (int note : withdrawalResult.keySet()) {
                int count = withdrawalResult.get(note);
                availableNotes.put(note, availableNotes.get(note) - count);
                System.out.println("Выдано " + count + " купюр номиналом " + note + "₽");
            }
        } else {
            System.out.println("Невозможно выдать указанную сумму");
        }
    }
}


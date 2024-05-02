package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Есть набор целых чисел.
 * Реализовать итератор, возвращающий только чётные (по значению) числа из него.
 */
public class EvenIterator implements Iterator<Integer> {

    public EvenIterator(List<Integer> numbers) {
        iterator = numbers.iterator();
    }

    private Iterator<Integer> iterator;
    private Integer nextNumber;

    public boolean hasNext() {

        if (nextNumber == null) {
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next % 2 == 0) {
                    nextNumber = next;
                    return true;
                }
            }
        } else
            return true;

        return false;
    }

    public Integer next() {
        if (!hasNext()) {
            throw new RuntimeException();
        }

        Integer copy = nextNumber;
        nextNumber = null;
        return copy;
    }


    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        EvenIterator evenIterator = new EvenIterator(numbers);

        while (evenIterator.hasNext()) {
            System.out.println(evenIterator.next());
        }
    }

}
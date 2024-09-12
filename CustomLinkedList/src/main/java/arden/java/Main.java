package arden.java;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList<Integer> customLinkedList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(new CustomLinkedList<>(),
                        (list, element) -> {
                            list.add(element);
                            return list;
                        },
                        (list1, list2) -> {
                            list1.addAll(list2.toList());
                            return list1;
                        });

        customLinkedList.display();
    }
}
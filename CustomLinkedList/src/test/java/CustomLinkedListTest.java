import arden.java.CustomLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class CustomLinkedListTest {
    CustomLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }

    @Test
    @DisplayName("Adding and getting elements")
    public void testAddAndGet() {
        list.add(10);
        list.add(20);

        assertAll("Check elements",
                () -> assertThat(list.get(0)).isEqualTo(10),
                () -> assertThat(list.get(1)).isEqualTo(20)
        );
    }

    @Test
    @DisplayName("Remove elements")
    public void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(20);

        assertAll("Check removing",
                () -> assertThat(list.get(0)).isEqualTo(10),
                () -> assertThat(list.get(1)).isEqualTo(30),
                () -> assertThat(list.size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("Contain elements test")
    public void testContains() {
        list.add(10);
        list.add(20);

        assertAll("Check containing",
                () -> assertThat(list.contains(20)).isTrue(),
                () -> assertThat(list.contains(30)).isFalse());
    }

    @Test
    @DisplayName("addAll elements test")
    public void testAddAll() {
        list.addAll(List.of(1, 3, 5));

        assertAll("Check containing",
                () -> assertThat(list.contains(1)).isTrue(),
                () -> assertThat(list.contains(3)).isTrue(),
                () -> assertThat(list.contains(5)).isTrue());
    }

    @Test
    @DisplayName("Stream reduce test")
    public void testStreamReduce() {
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

        assertAll("Check stream reduce",
                () -> assertThat(customLinkedList.size()).isEqualTo(10),
                () -> assertThat(customLinkedList.get(0)).isEqualTo(1));
    }
}

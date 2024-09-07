import arden.java.CustomLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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

        assertThat(list.get(0)).isEqualTo(10);
        assertThat(list.get(1)).isEqualTo(20);
    }

    @Test
    @DisplayName("Remove elements")
    public void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(20);

        assertThat(list.get(0)).isEqualTo(10);
        assertThat(list.get(1)).isEqualTo(30);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Contain elements test")
    public void testContains() {
        list.add(10);
        list.add(20);

        assertThat(list.contains(20)).isTrue();
        assertThat(list.contains(30)).isFalse();
    }

    @Test
    @DisplayName("addAll elements test")
    public void testAddAll() {
        list.addAll(List.of(1, 3, 5));

        assertThat(list.contains(1)).isTrue();
        assertThat(list.contains(3)).isTrue();
        assertThat(list.contains(5)).isTrue();
    }
}

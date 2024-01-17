package org.example;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayIteratorTest {
    @Test
    public void givenEmptyArray_whenCheckHasNext_thenFalse() {
        ArrayIterator<Object> iterator = new ArrayIterator<>(new Object[0]);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void givenEmptyArray_whenCallNext_thenThrowException() {
        ArrayIterator<Object> iterator = new ArrayIterator<>(new Object[0]);
        assertThrows(NoSuchElementException.class, iterator::next);
    }


    @Test
    public void givenArrayWithElements_whenIterate_thenCorrectElementsReturned() {
        Integer[] numbers = {1, 2, 3};
        ArrayIterator<Integer> iterator = new ArrayIterator<>(numbers);

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void givenArrayWithOneElement_whenCallNextTwice_thenThrowException() {
        Integer[] numbers = {1};
        ArrayIterator<Integer> iterator = new ArrayIterator<>(numbers);
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }


}

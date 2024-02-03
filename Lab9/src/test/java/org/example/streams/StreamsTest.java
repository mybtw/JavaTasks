package org.example.streams;

import org.example.person.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamsTest {

    @Test
    public void testFilter() {
        List<Person> originalList = Arrays.asList(new Person("Alice", 22), new Person("Bob", 19));
        List<Person> expected = Collections.singletonList(new Person("Alice", 22));
        Streams<Person> stream = Streams.of(originalList);
        List<Person> filtered = stream.filter(p -> p.getAge() > 20).collection;

        assertEquals(expected, filtered);
        assertEquals(2, originalList.size());
    }

    @Test
    public void testTransform() {
        List<Person> originalList = Arrays.asList(new Person("Alice", 22), new Person("Bob", 19));
        Streams<Person> stream = Streams.of(originalList);
        List<Person> transformed = stream.transform(p -> new Person(p.getName(), p.getAge() + 10)).collection;

        assertEquals("Alice", transformed.get(0).getName());
        assertEquals(32, transformed.get(0).getAge());
        assertEquals(2, originalList.size());
    }

    @Test
    public void testToMap() {
        List<Person> originalList = Arrays.asList(new Person("Alice", 22), new Person("Bob", 19));
        Streams<Person> stream = Streams.of(originalList);
        Map<String, Person> map = stream.toMap(Person::getName, p -> p);

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Alice"));
        assertTrue(map.containsKey("Bob"));
        assertEquals(22, map.get("Alice").getAge());
        assertEquals(19, map.get("Bob").getAge());
        assertEquals(2, originalList.size());
    }
}
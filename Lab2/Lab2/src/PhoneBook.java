import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> phoneBook = new HashMap<>();


    public void add(String name, String phoneNumber) {
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
    }

    public Set<String> get(String surname) {
        return phoneBook.getOrDefault(surname, new HashSet<>());
    }
}

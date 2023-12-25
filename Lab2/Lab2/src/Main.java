import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        task1();

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ilia", "+123456789");
        phoneBook.add("Petrov", "+987654321");
        phoneBook.add("Ilia", "+111222333");
        phoneBook.add("Ilia", "+62316541654161954");
        phoneBook.add("Ilia", "+62316541654161954");

        var iliaNumbers = phoneBook.get("Ilia");
        System.out.println("Ilia's phone numbers: " + iliaNumbers);

        var petrovNumbers = phoneBook.get("Petrov");
        System.out.println("Petrov's phone numbers: " + petrovNumbers);

        var nonameNumbers = phoneBook.get("Noname");
        System.out.println("Noname's phone numbers: " + nonameNumbers);
    }

    public static void task1() {
        var strArr = new String[] {"Ilia", "Ilia", "Vlad", "Maria", "Maria", "Maria", "Egor", "Max", "Vitaliy", "Elena", "Oleg", "Andrey", "Pavel", "Nika"};
        var map = new HashMap<String, Integer>();
        for (var str : strArr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        System.out.println("Уникальные слова: ");
        map.keySet().forEach(key -> System.out.println("Word : " + key));
        System.out.println();
        map.forEach((key, value) -> System.out.println("Word : " + key + ", counter: " + value));
    }
}

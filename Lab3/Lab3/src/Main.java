import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        int count = map.getCount(5);  // 2
        System.out.println(count);
        count = map.getCount(6);  // 1
        System.out.println(count);
        count = map.getCount(10); // 3
        System.out.println(count);

        // addAll
        List<Integer> sourceList = Arrays.asList(1, 2, 3);
        List<Integer> destinationList = new ArrayList<>();
        CollectionUtils.addAll(sourceList, destinationList);
        System.out.println(destinationList.equals(sourceList));

        // newArrayList
        List<String> stringList = CollectionUtils.newArrayList();
        System.out.println(stringList.isEmpty());

        // indexOf
        int index = CollectionUtils.indexOf(sourceList, 2);
        System.out.println(index == 1);

        // limit
        List<Integer> limitedList = CollectionUtils.limit(sourceList, 2);
        System.out.println(limitedList.size() == 2);

        // add
        CollectionUtils.add(destinationList, 4);
        System.out.println(destinationList.contains(4));

        // removeAll
        CollectionUtils.removeAll(destinationList, sourceList);
        System.out.println(!destinationList.containsAll(sourceList));

        // Test for containsAll
        boolean containsAll = CollectionUtils.containsAll(destinationList, Arrays.asList(4));
        System.out.println(containsAll);

        // Test for containsAny
        boolean containsAny = CollectionUtils.containsAny(sourceList, Arrays.asList(4, 5));
        System.out.println(!containsAny);

        List<Integer> rangedList = CollectionUtils.range(sourceList, 1, 2);
        System.out.println(rangedList.size() == 2);

        Comparator<Integer> comparator = Integer::compare;
        rangedList = CollectionUtils.range(sourceList, 1, 3, comparator);
        System.out.println(rangedList.size() == 3);

    }
}


import java.lang.constant.Constable;

public abstract class Parent {
    private String name;
    static {
        System.out.println("Parent:static 1");
    }
    static {
        System.out.println("Parent:static 2");
    }

    {
        System.out.println("Parent:instance 1");
    }

    {
        System.out.println("Parent:instance 2");
    }
    public Parent() {
        System.out.println("Parent:constructor");
    }

    public Parent(String name) {
        this.name = name;
        System.out.println("Parent:name-constructor");
    }
}

package collections_Framework.framework_List;

import java.util.*;

public class Cat {
    private final String name;
    private final int age;
    private final List<String> color;

    //создаем конструктор:
    public Cat(String name, int age, List<String> color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override   //нажали alt+insert -> toString
    //переопределили поведение метода toString
    // (для того, чтобы в классе Main на печать вышли все параметры объекта (System.out.println(barsik);)
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color=" + color +
                '}';
    }

}



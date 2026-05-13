// ArrayList - хранение данных на основе массива

package collections_framework.framework_list;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> color = new ArrayList<>();    //создается мутабельный ArrayList\
        // у него нет понятия "длина"
        color.add("pink");
        color.add("blue");
        color.add("white");

        //добавление в ArrayList в конец списка
        color.add("brown");
        color.add("black");

        //добавление по индексу в конкретное место, сдвигая остальные вправо
        color.add(1, "ginger");

        //при удалении все элементы справа должны сдвинуться на одну позицию влево

        // удаление по названию элемента = по заданному предикату (removeIf)
        color.removeIf(c-> c.equals("pink"));

        //удаление с конца
        color.remove(color.size() - 1);

        Cat barsik = new Cat("Barsik", 2, color);

        //создание массива
        String[] catArray = new String[] {"pink","blue", "white"};

        //создание цикла for
        for (int i = 0; i < catArray.length; i++) {
            System.out.println(catArray[i]);           //применяется, когда нам нужен конкретный индекс
        }


       System.out.println(barsik);

    }


}

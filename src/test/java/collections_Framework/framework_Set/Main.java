package collections_Framework.framework_Set;

import collections_Framework.framework_Map.Auto;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //создаем Объекты авто
        Auto mazda = new Auto("Mazda 6", 2020);
        Auto audi = new Auto("Audi A4", 2022);
        Auto nissan = new Auto("Nissan GT-R", 2021);
        Auto subaru = new Auto("Subaru Impreza", 2023);

        //создаем Set (множество) объектов
        Set<Auto> autoSet = new HashSet<>();

        // добавление элементов
        autoSet.add(mazda);
        autoSet.add(audi);
        autoSet.add(nissan);
        autoSet.add(subaru);

        //пробуем добавить дубликат
        autoSet.add(mazda);

        //проверяем наличие объекта
        System.out.println("Присутствует в списке audi? " + autoSet.contains(audi));

        //удаление объекта
        autoSet.remove(nissan);

        //удаление старше 2022г (т.е. удаляем все авто, год выпуска которых меньше 2022)
        autoSet.removeIf(auto -> auto.getAge() < 2022);

        //Выведем все оставшиеся авто
        System.out.println("Список всех авто в Set:");

        Iterator<Auto> iterator = autoSet.iterator();  //получаем итератор (спец.объект, который перебирает коллекцию)
        while (iterator.hasNext()) {              //пока в сете есть следующий элемент
            Auto a = iterator.next();            //берем этот элемент
            System.out.println(a);              //печатаем его
        }
    }
}

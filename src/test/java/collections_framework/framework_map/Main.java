//Map позволяет иметь константное время доступа к объекту по ключу

package collections_framework.framework_map;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //создаем Объекты авто
        Auto mazda = new Auto("Mazda 6", 2020);
        Auto audi = new Auto("Audi A4", 2022);
        Auto nissan = new Auto("Nissan GT-R", 2021);
        Auto subaru = new Auto("Subaru Impreza", 2023);

        Map<String, Auto> autos = new HashMap<>();
        autos.put("234234", mazda);    //вносим/кладем данные в Map:  Id клиента + марка авто
        autos.put("34344234", audi);
        autos.put("6466", nissan);


        //добавление пары ключ-значение
        autos.put("76767", subaru);

        //изменение - перезаписывает ключ, если такой уже есть
        autos.put("6466", audi);
        System.out.println(autos.get("6466"));

        //удаление всей записи по Id
        autos.remove("6466");

        //"безопасное" удаление - при совпадении ключ+значения
        autos.remove("234234", mazda);

        //массовое удаление - по условию
        //все авто старше 2021
        autos.entrySet().removeIf(entry ->entry.getValue().getAge() < 2021);  //был создан getter в Auto


        //итерирование происходит по одному из трех методов:
        //1-ый метод: .entry.Set - возвращает набор пар ключ-значение
        //проходимся циклом по всей Map-е
        for (Map.Entry<String, Auto> entry : autos.entrySet()) {
//            entry.getKey();       //наш Id клиента
//            entry.getValue();     //наша марка авто
            System.out.println("Id" + " " + entry.getKey() + " " + "марка" + entry.getValue());
        }

        //2-ой метод .keySet - позволяет проитерироваться по ключам
        //возвращает HashSet
        for (String key : autos.keySet()) {

        }

        //3-ий - итерация по значениям
        for (Auto value : autos.values()) {

        }

    }
}




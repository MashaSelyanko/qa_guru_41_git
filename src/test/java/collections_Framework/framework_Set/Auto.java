package collections_Framework.framework_Set;

import java.util.*;

public class Auto {

        private final String name;
        private final int age;

        public int getAge() {
            return age;    //создание метода (Getter) для того, чтобы в Main сработал метод массового удаления
        }

        //создаем конструктор:
        public Auto(String name, int age) {
            this.name = name;
            this.age = age;
        }

        //нажали alt+insert -> toString
        //переопределили поведение метода toString

        @Override
        public String toString() {
            return "Auto{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
//переопределяем методы quails, hashCode для сравнения двух объектов (корректная работа Set)
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return age == auto.age && Objects.equals(name, auto.name);
    }

    // метод для генерации хеш-кода (для работы HashSet/HashMap)
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}


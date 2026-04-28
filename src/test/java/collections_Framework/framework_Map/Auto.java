package collections_Framework.framework_Map;

public class Auto {

    private final String name;
    private final int age;
    //private final List<String> autoCompany;

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
}



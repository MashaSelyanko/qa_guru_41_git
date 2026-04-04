package selyanko.masha;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

public static void main(String[] args) {
    int a = 100;
    int b = 200;
    double c = 2.5;
    double d = 2.55;
    byte f = 127;
    byte g = 99;
    int max = Integer.MAX_VALUE;

    System.out.println("a+b= " + (a+b));
    System.out.println("b-a= " + (b-a));
    //System.out.println("b/a= " + (b/a));
    System.out.println("a*b= " + (a*b));

    System.out.println("a/c= " + (a/c));
    System.out.println("a/d= " + (a%d));

    if (a == b) {
        System.out.println("покупаем");
    } else if (a>c) {
        System.out.println("продаем");
    } else {
        System.out.println("держим");
    }

    System.out.println("f*g= " + (f*g));
    System.out.println("переполнение " + ++max);

}
}
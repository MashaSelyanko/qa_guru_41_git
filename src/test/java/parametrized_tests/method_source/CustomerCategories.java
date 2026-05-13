package parametrized_tests.method_source;
import java.util.List;

public enum CustomerCategories {
    PHYSICAL("Физические лица",
            List.of("О бюро", "Новости", "Займы", "Журнал", "Вопросы и ответы", "Контакты"),
            true, null, ".headertop__link a"),

    LEGAL("Юридические лица",
            List.of("О бюро", "Новости", "Займы", "Журнал", "Вопросы и ответы", "Контакты"),
            false, null, ".headertop__link a"),

    CORPORATE("Корпоративные клиенты",
            List.of("Главная", "О бюро", "Контакты"),
            true, "Подключиться к НБКИ", ".headertop__link a");

    public final String typeName;
    public final List<String> expectedButtons;
    public final boolean hasLoginButton;
    public final String extraButton;
    public final String selectCategory; //поле для проверки активного типа клиента

    CustomerCategories(String typeName,
                       List<String> expectedButtons,
                       boolean hasLoginButton,
                       String extraButton,
                       String selectCategory) {
        this.typeName = typeName;
        this.expectedButtons = expectedButtons;
        this.hasLoginButton = hasLoginButton;
        this.extraButton = extraButton;
        this.selectCategory = selectCategory;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
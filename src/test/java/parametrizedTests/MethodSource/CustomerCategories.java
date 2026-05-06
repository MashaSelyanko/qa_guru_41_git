package parametrizedTests.MethodSource;

import java.util.List;

public enum CustomerCategories {
    PHYSICAL("Физические лица",
            List.of("О бюро", "Новости", "Займы", "Журнал", "Вопросы и ответы", "Контакты"),
    true,null
            ),

    LEGAL("Юридические лица",
          List.of("О бюро", "Новости","Займы","Журнал","Вопросы и ответы","Контакты"),
    false,null
            ),

    CORPORATE("Корпоративные клиенты",
              List.of("Главная", "О бюро","Контакты"),
        true,"Подключиться к НБКИ"
                );

    public final String typeName;
    public final List<String> expectedButtons;
    public final boolean hasLoginButton;
    public final String extraButton;

    CustomerCategories(String typeName, List<String> expectedButtons, boolean hasLoginButton, String extraButton) {
        this.typeName = typeName;
        this.expectedButtons = expectedButtons;
        this.hasLoginButton = hasLoginButton;
        this.extraButton = extraButton;
    }
}






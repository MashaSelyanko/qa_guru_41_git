package testdata;

import java.util.List;

public enum CustomerCategories {

    PHYSICAL("Частным клиентам",
            List.of("Вклады", "Карты", "Кредиты", "Ипотека", "Премиум", "Пенсионерам"),
            "div.css-1ndprbh a.chakra-link"),

    LEGAL("Бизнесу",
            List.of("Расчетный счет", "Кредиты", "Карты", "Депозиты", "Зарплатный проект", "Прием платежей"),
            "div.css-1ndprbh a.chakra-link"),

    FEA("ВЭД",
            List.of("Преимущества ВЭД", "ВЭД360"),
            "div.css-1ndprbh a.chakra-link"),

    MARKETS("Финансовые рынки",
            List.of("Частным клиентам", "Бизнесу", "Финансовым институтам"),
            "div.css-1ndprbh a.chakra-link"),

    INVESTORS("Инвесторам",
            List.of("Отчетность и презентации", "Информация для акционеров", "Акции Банка", "Корпоративное управление"),
            "div.css-1ndprbh a.chakra-link");

    public final String typeName;
    public final List<String> expectedButtons;
    public final String selectCategory;

    CustomerCategories(String typeName, List<String> expectedButtons, String selectCategory) {
        this.typeName = typeName;
        this.expectedButtons = expectedButtons;
        this.selectCategory = selectCategory;
    }

    @Override
    public String toString() {
        return typeName;

    }
}

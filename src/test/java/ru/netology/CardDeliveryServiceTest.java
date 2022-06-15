package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SetValueOptions;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class CardDeliveryServiceTest {
    private static Faker faker;

    @BeforeAll
    static void setUp() {
        faker = new Faker(new Locale("ru"));
    }

    @Test
    public void shouldCompleteDeliveryForm() {
        DeliveryApplication info = DataGenerator.Registration.generateInfo("ru");

        Configuration.headless = true;
        open("http://localhost:9999");

        $x("//*/span[@data-test-id=\"city\"]//input").setValue(info.getCity());
        $x("//*/span[@data-test-id=\"date\"]//input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*/span[@data-test-id=\"date\"]//input").setValue(info.getDate());
        $x("//*/span[@data-test-id=\"name\"]//input").setValue(info.getName());
        $x("//*/span[@data-test-id=\"phone\"]//input").setValue(info.getPhone());
        $x("//*/label[@data-test-id=\"agreement\"]").click();
        $(withText("Запланировать")).click();

        $x("//*/div[@class=\"notification__content\"]").should(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + info.getDate()));
    }
}

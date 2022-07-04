package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CardDeliveryServiceTest {
    private DeliveryApplication info;

    private CardDeliveryServiceTest () {
        this.info = DataGenerator.Registration.generateInfo("ru");
    }

    @Test
    public void shouldCompleteDeliveryForm() {
        String date1 = DataGenerator.Registration.generateDate();
        String date2 = DataGenerator.Registration.generateDate();

        Configuration.headless = true;
        open("http://localhost:9999");

        $x("//*/span[@data-test-id=\"city\"]//input").setValue(info.getCity());
        $x("//*/span[@data-test-id=\"date\"]//input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*/span[@data-test-id=\"date\"]//input").setValue(date1);
        $x("//*/span[@data-test-id=\"name\"]//input").setValue(info.getName());
        $x("//*/span[@data-test-id=\"phone\"]//input").setValue(info.getPhone());
        $x("//*/label[@data-test-id=\"agreement\"]").click();
        $(withText("Запланировать")).click();

        $x("//*/div[@class=\"notification__content\"]").should(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date1));

        open("http://localhost:9999");

        $x("//*/span[@data-test-id=\"city\"]//input").setValue(info.getCity());
        $x("//*/span[@data-test-id=\"date\"]//input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*/span[@data-test-id=\"date\"]//input").setValue(date2);
        $x("//*/span[@data-test-id=\"name\"]//input").setValue(info.getName());
        $x("//*/span[@data-test-id=\"phone\"]//input").setValue(info.getPhone());
        $x("//*/label[@data-test-id=\"agreement\"]").click();
        $(withText("Запланировать")).click();

        $x("//*/div[@data-test-id=\"replan-notification\"]")
                .should(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $x("//*/div[@data-test-id=\"replan-notification\"]//*/button").click();
        $x("//*/div[@data-test-id=\"success-notification\"]").should(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date2));

    }
}

package ru.netology;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@UtilityClass
public class DataGenerator {
    @UtilityClass
    public static class Registration {
        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public DeliveryApplication generateInfo(String locale) {
            Faker faker = new Faker(new Locale("ru"));

            int days = faker.number().numberBetween(3, 365);
            String city = ListOfCities.getRandomElement();

            return new DeliveryApplication(
                days,
                city,
                generateDate(days),
                faker.name().fullName().replace("ё", "е").replace("Ё", "Е"),
                faker.phoneNumber().phoneNumber()
            );
        }
    }
}

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
        private static final Faker faker = new Faker(new Locale("ru"));

        public static String generateDate() {
            int days = faker.number().numberBetween(3, 365);
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public DeliveryApplication generateInfo(String locale) {

            String city = ListOfCities.getRandomElement();

            return new DeliveryApplication(
                city,
                faker.name().fullName().replace("ё", "е").replace("Ё", "Е"),
                faker.phoneNumber().phoneNumber()
            );
        }
    }
}

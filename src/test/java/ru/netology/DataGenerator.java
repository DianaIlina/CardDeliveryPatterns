package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    public static class Registration {
        public static String generateDate(int days) {
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        public static DeliveryApplication generateInfo(String Locale) {
            Faker faker = new Faker(new Locale("ru"));
            int days = faker.number().numberBetween(3, 365);

            return new DeliveryApplication(
                days,
                faker.address().cityName(),
                generateDate(days),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
            );
        }
    }
}

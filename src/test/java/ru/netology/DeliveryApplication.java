package ru.netology;

import lombok.Data;

@Data
public class DeliveryApplication {
    private final int days;
    private final String city;
    private final String date;
    private final String name;
    private final String phone;
}

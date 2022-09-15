package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Integer id;
    private String name;
}

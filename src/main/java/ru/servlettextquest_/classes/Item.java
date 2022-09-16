package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item /*extends AdditionalCharacteristics*/ {
    private Integer id;
    private String name;
    private Integer strength;
    private Integer dexterity;
    private Integer life;

}

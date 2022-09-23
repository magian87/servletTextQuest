package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdditionalCharacteristics {
    private Integer strength;
    private Integer dexterity;
    private Integer life;
}

package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Door {
    private Integer roomId;
}

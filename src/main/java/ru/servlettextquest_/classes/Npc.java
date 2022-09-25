package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Npc {
    private Integer id;
    private String name;
    private Integer startQuestionId;

}

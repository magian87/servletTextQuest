package ru.servlettextquest_.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Question {
    private Integer id;
    private String text;
    private List<Answer> answers;


    @Data
    @Builder
    @AllArgsConstructor
    public static class Answer{
        private String text;
        private Integer nextQuestion;
        private Integer questId;
    }
}

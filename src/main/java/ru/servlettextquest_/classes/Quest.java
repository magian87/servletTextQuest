package ru.servlettextquest_.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class Quest {
    private Integer id;
    private String text;

    private Predicate<User>  isFinished;

    public boolean isFinished(User user) {
        return  isFinished.test(user);
    }


}

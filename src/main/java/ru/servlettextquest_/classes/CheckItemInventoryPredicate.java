package ru.servlettextquest_.classes;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.Predicate;

@NoArgsConstructor
@Setter
public class CheckItemInventoryPredicate implements Predicate<User> {
    private Integer neededItemId;

    public CheckItemInventoryPredicate(Integer neededItemId) {
        this.neededItemId = neededItemId;
    }

    @Override
    public boolean test(User user) {
        return user.getItems().contains(neededItemId);
    }
}

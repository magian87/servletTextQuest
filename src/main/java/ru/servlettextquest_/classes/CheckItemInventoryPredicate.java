package ru.servlettextquest_.classes;

import java.util.function.Predicate;

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

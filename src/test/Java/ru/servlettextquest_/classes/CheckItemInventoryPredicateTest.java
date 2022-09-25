package ru.servlettextquest_.classes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckItemInventoryPredicateTest {
    @Spy
    private CheckItemInventoryPredicate checkItemInventoryPredicate;
    @Spy
    private User user;

    @Test
    void test() {
        checkItemInventoryPredicate.setNeededItemId(1);
        user.addItem(1);
        assertTrue(checkItemInventoryPredicate.test(user));

    }

}

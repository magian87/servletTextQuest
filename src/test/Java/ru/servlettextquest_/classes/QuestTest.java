package ru.servlettextquest_.classes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class QuestTest {

    @Spy
    private User user;

    @Spy
    private Quest quest;

    @Mock
    private CheckItemInventoryPredicate checkItemInventoryPredicate;

    @Test
    void testIsFinish_WhenUserIncludeItem_ShouldTrue() {
        assertTrue(false);


        /*quest.setId(1);
        quest.setText("111");

        user.addItem(1);

        when(quest.isFinished(user)).thenReturn(user.)



        quest.isFinished(user);
        assertTrue(new CheckItemInventoryPredicate(1).test(user));*/
    }

}

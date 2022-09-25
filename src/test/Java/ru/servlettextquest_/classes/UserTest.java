package ru.servlettextquest_.classes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.argThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Spy
    private User user;

    @Test
    void test_WhenAddQuest_ShouldVerifyAdd() throws ServletException, IOException {
        user.addQuest(1);
        verify(user).addQuest(1);
        assertEquals(1,user.getQuests().size());
    }

    @Test
    void test_WhenAddItem_ShouldVerifyAdd() throws ServletException, IOException {
        user.addItem(1);
        verify(user).addItem(1);
        assertEquals(1,user.getItems().size());
    }

    @Test
    void test_WhenGetUserQuest_ShouldCloneQuests() throws ServletException, IOException {
         assertEquals(user.getUserQuests(), user.getUserQuests());
    }
}

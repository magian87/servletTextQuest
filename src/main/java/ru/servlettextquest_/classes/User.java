package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String username;
    private Integer currentRoomId;

    private List<Integer> quests = new ArrayList<>();
    private List<Integer> items = new ArrayList<>();


}

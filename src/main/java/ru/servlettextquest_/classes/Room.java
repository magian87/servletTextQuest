package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Room {
    private Integer id;
    private String name;

    @Builder.Default
    private List<Door> doors = new ArrayList<>();
    @Builder.Default
    private List<Integer> npcs = new ArrayList<>();
    @Builder.Default
    private List<Integer> items = new ArrayList<>();

     public void addDoor(Door door){
        doors.add(door);
    }

}

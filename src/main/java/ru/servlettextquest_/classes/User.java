package ru.servlettextquest_.classes;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User  {
    private String username;
    private Integer currentRoomId;

    //Эти же три поля есть и в Item, без ламбука знаю как сделать, с ним нет, наследованиме AdditionCharacteristic проходит
    //но builder не работает на доп поля
    private Integer strength;
    private Integer dexterity;
    private Integer life;

    private List<Integer> quests = new ArrayList<>();
    private List<Integer> items = new ArrayList<>();

    public void addQuest(Integer questId){
        quests.add(questId);
    }
    public void addItem(Integer itemId){
        items.add(itemId);
    }
}

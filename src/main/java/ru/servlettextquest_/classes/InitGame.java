package ru.servlettextquest_.classes;

import java.util.ArrayList;
import java.util.List;

public class InitGame {
    public List<Room> receiveRooms() {
        List<Room> rooms = new ArrayList<>();

        int idRoom = 1;

        Room startRoom = Room.builder()
                .id(idRoom++)
                .name("StartRoom")
                .build();
        rooms.add(startRoom);

        Room garden = Room.builder()
                .id(idRoom++)
                .name("Garden")
                .npcs(List.of(1))
                .items(List.of(1))
                .build();
        rooms.add(garden);

        Room hotel = Room.builder()
                .id(idRoom++)
                .name("Hotel")
                .build();
        rooms.add(hotel);

        Room bathroom = Room.builder()
                .id(idRoom++)
                .name("Bathroom")
                .build();
        rooms.add(bathroom);

        Room dungeon = Room.builder()
                .id(idRoom++)
                .name("Dungeon")
                .build();
        rooms.add(dungeon);

        Room mines = Room.builder()
                .id(idRoom++)
                .name("Mines")
                .build();
        rooms.add(mines);

        Room hospital = Room.builder()
                .id(idRoom++)
                .name("Hospital")
                .build();
        rooms.add(hospital);

        Room university = Room.builder()
                .id(idRoom++)
                .name("Hotel")
                .build();
        rooms.add(university);

        startRoom.addDoor(Door.builder().roomId(garden.getId()).build());

        garden.addDoor(Door.builder().roomId(hotel.getId()).build());
        garden.addDoor(Door.builder().roomId(bathroom.getId()).build());

        hotel.addDoor(Door.builder().roomId(dungeon.getId()).build());
        hotel.addDoor(Door.builder().roomId(garden.getId()).build());

        bathroom.addDoor(Door.builder().roomId(garden.getId()).build());
        bathroom.addDoor(Door.builder().roomId(dungeon.getId()).build());

        dungeon.addDoor(Door.builder().roomId(hotel.getId()).build());
        dungeon.addDoor(Door.builder().roomId(bathroom.getId()).build());
        dungeon.addDoor(Door.builder().roomId(mines.getId()).build());

        mines.addDoor(Door.builder().roomId(dungeon.getId()).build());

        hospital.addDoor(Door.builder().roomId(dungeon.getId()).build());
        hospital.addDoor(Door.builder().roomId(university.getId()).build());

        university.addDoor(Door.builder().roomId(hospital.getId()).build());
        return rooms;
    }

    public List<Npc> receiveNpcs() {
        List<Npc> npcs = new ArrayList<>();

        int idNpc = 1;

        Npc npc = Npc.builder()
                .id(idNpc++)
                .name("DorianGrey")
                .startQuestionId(1)
                .build();

        npcs.add(npc);
        return npcs;
    }

    public List<Question> receiveQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(
                Question.builder()
                        .id(1)
                        .text("Привет, поболтаем?")
                        .answers(List.of(
                                Question.Answer.builder()
                                        .text("Да, давай!")
                                        .nextQuestion(2)
                                        .build(),
                                Question.Answer.builder()
                                        .text("Нет")
                                        .build(),
                                Question.Answer.builder()
                                        .text("Иди лесом!")
                                        .nextQuestion(3)
                                        .build()
                        ))
                        .build()
        );

        questions.add(
                Question.builder()
                        .id(2)
                        .text("Ищем золотой ключик?")
                        .answers(List.of(
                                Question.Answer.builder()
                                        .text("Нет")
                                        .build(),
                                Question.Answer.builder()
                                        .text("Да")
                                        .questId(1)
                                        .build()
                        ))
                        .build()
        );

        questions.add(
                Question.builder()
                        .id(3)
                        .text("Чего хомим?")
                        .answers(List.of(
                                Question.Answer.builder()
                                        .text("Так просто надо")
                                        .build()
                        ))
                        .build()
        );


        return questions;
    }

    public List<Quest> receiveQuests(){
        List<Quest> quests = new ArrayList<>();

        quests.add(Quest.builder()
                .id(1)
                .text("Золотой ключик")
                .isFinished(new CheckItemInventoryPredicate(1))
                .build());

        return quests;
    }

    public List<Item> receiveItems(){
        List<Item> items = new ArrayList<>();

        items.add(Item.builder().id(1).name("Золотой ключик").build());

        return items;
    }
}

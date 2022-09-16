package ru.servlettextquest_.classes;

import java.util.ArrayList;
import java.util.List;

public class InitGame {
    public List<Room> receiveRooms() {
        List<Room> rooms = new ArrayList<>();

        int idRoom = 1;

        Room startRoom = Room.builder()
                .id(idRoom++)
                .name("Начальная комната")
                .build();
        rooms.add(startRoom);

        Room garden = Room.builder()
                .id(idRoom++)
                .name("Сад")
                .npcs(List.of(1))
                .items(List.of(1,4))
                .build();
        rooms.add(garden);

        Room hotel = Room.builder()
                .id(idRoom++)
                .name("Отель")
                .items(List.of(2))
                .build();
        rooms.add(hotel);

        Room bathroom = Room.builder()
                .id(idRoom++)
                .name("Ванна")
                .items(List.of(7))
                .build();
        rooms.add(bathroom);

        Room dungeon = Room.builder()
                .id(idRoom++)
                .name("Темница")
                .items(List.of(3,9))
                .build();
        rooms.add(dungeon);

        Room mines = Room.builder()
                .id(idRoom++)
                .name("Шахты")
                .items(List.of(5))
                .build();
        rooms.add(mines);

        Room hospital = Room.builder()
                .id(idRoom++)
                .name("Госпиталь")
                .items(List.of(8,6))
                .build();
        rooms.add(hospital);

        Room university = Room.builder()
                .id(idRoom++)
                .name("Университет")
                .items(List.of(10))
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
                .name("Мартин Иден")
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
                        .text("Привет?")
                        .answers(List.of(
                                Question.Answer.builder()
                                        .text("Здорово приятель!")
                                        .nextQuestion(2)
                                        .build(),
                                Question.Answer.builder()
                                        .text("Я тороплюсь, пока!")
                                        .build()
                        ))
                        .build()
        );

        questions.add(
                Question.builder()
                        .id(2)
                        .text("Давай я тебе дам квест?")
                        .answers(List.of(
                                Question.Answer.builder()
                                        .text("Да")
                                        .nextQuestion(3)
                                        .build(),
                                Question.Answer.builder()
                                        .text("Нет")
                                        .build()

                        ))
                        .build()
        );

        questions.add(
                Question.builder()
                        .id(3)
                        .text("Выбирай что ищешь")
                        .answers(List.of(
                                Question.Answer.builder()
                                        .text("Парализатор")
                                        .questId(1)
                                        .build(),
                                Question.Answer.builder()
                                        .text("Замораживатель")
                                        .questId(2)
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
                .text("Парализатор")
                .isFinished(new CheckItemInventoryPredicate(2))
                .build());
        quests.add(Quest.builder()
                .id(2)
                .text("Замораживатель")
                .isFinished(new CheckItemInventoryPredicate(3))
                .build());

        return quests;
    }

    public List<Item> receiveItems(){
        List<Item> items = new ArrayList<>();

        //items.add(Item.builder().id(1).name("Золотой ключик").build());
        items.add(Item.builder().id(1).name("Световой меч").strength(70).build());
        items.add(Item.builder().id(2).name("Парализатор").strength(50).build());
        items.add(Item.builder().id(3).name("Замораживатель").strength(100).build());


        items.add(Item.builder().id(4).name("Кольчуга").dexterity(70).build());
        items.add(Item.builder().id(5).name("Щит").dexterity(40).build());
        items.add(Item.builder().id(6).name("Бронежилет").dexterity(100).build());

        items.add(Item.builder().id(7).name("Лайфдар").life(50).build());
        items.add(Item.builder().id(8).name("Жизнедар").life(70).build());
        items.add(Item.builder().id(9).name("Витадар").life(100).build());
        items.add(Item.builder().id(10).name("Хайтдар").life(100).build());

        return items;
    }
}

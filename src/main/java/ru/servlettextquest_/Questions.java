package ru.servlettextquest_;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.servlettextquest_.settings.QuestionsSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Questions {
    private final String jsonStr = "{\n" +
            "\n" +
            "  \"questionSettings\": [\n" +
            "    {\n" +
            "      \"num\":1,\n" +
            "      \"question\": \"Принять вызов НЛО?\",\n" +
            "\t  \"acceptText\" :  \"Принять вызов\",\n" +
            "\t  \"accept\" : 2,\n" +
            "\t  \"rejectText\" :  \"Отклонить вызов\",\n" +
            "\t  \"reject\" : -1,\n" +
            "      \"commentCurrentStep\" : \"Ты потерял память.\"\t  \n" +
            "    },\n" +
            "\t\n" +
            "\t{\n" +
            "      \"num\":-1,\n" +
            "      \"question\": \"\",\n" +
            "\t  \"accept\" : 0,\n" +
            "\t  \"acceptText\" :  \"\",\n" +
            "\t  \"reject\" : 0,\t  \n" +
            "\t  \"rejectText\" :  \"\",\n" +
            "\t  \"commentCurrentStep\" : \"Ты отклонил вызов. Поражение\"  \t  \n" +
            "    },\n" +
            "\t\n" +
            "\t{\n" +
            "      \"num\":2,\n" +
            "      \"question\": \"Поднимаешься на мостик к капитану?\",\n" +
            "\t  \"acceptText\" :  \"Подняться на мостик\",\n" +
            "\t  \"accept\" : 3,\n" +
            "\t  \"rejectText\" :  \"Отказаться подниматься на мостик\",\n" +
            "\t  \"reject\" : -2,\n" +
            "      \"commentCurrentStep\" : \"Ты принял вызов.\"\t  \n" +
            "    },\n" +
            "\t\n" +
            "\t{\n" +
            "      \"num\":-2,\n" +
            "      \"question\": \"\",\n" +
            "\t  \"acceptText\" :  \"\",\n" +
            "\t  \"accept\" : 0,\n" +
            "\t  \"rejectText\" :  \"\",\n" +
            "\t  \"reject\" : 0,\n" +
            "      \"commentCurrentStep\" : \"Ты не пошел на переговоры. Поражение\"\t  \n" +
            "    },\n" +
            "\t\n" +
            "\t{\n" +
            "      \"num\":3,\n" +
            "      \"question\": \"Ты кто?\",\n" +
            "\t  \"acceptText\" :  \"Рассказать правду о себе\",\n" +
            "\t  \"accept\" : 4,\n" +
            "\t  \"rejectText\" :  \"Солгать о себе\",\n" +
            "\t  \"reject\" : -3,\n" +
            "      \"commentCurrentStep\" : \"Ты поднялся на мостик.\"\t  \n" +
            "    },\n" +
            "\t\n" +
            "\t{\n" +
            "      \"num\":-3,\n" +
            "      \"question\": \"\",\n" +
            "\t  \"acceptText\" :  \"\",\n" +
            "\t  \"accept\" : 0,\n" +
            "\t  \"rejectText\" :  \"\",\n" +
            "\t  \"reject\" : 0,\n" +
            "      \"commentCurrentStep\" : \"Твоя ложь была раскрыта. Поражение\"\t  \n" +
            "    },\n" +
            "\t\n" +
            "\t{\n" +
            "      \"num\":4,\n" +
            "      \"question\": \"\",\n" +
            "\t  \"acceptText\" :  \"\",\n" +
            "\t  \"accept\" : 0,\n" +
            "\t  \"rejectText\" :  \"\",\n" +
            "\t  \"reject\" : 0,\n" +
            "      \"commentCurrentStep\" : \"Тебя вернули домой. Победа.\"\t  \n" +
            "    }\t\n" +
            "  ]\n" +
            "}\n" +
            "\n";

   private final QuestionsSettings questionsSettings;

    public Questions() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            questionsSettings = objectMapper.readValue(jsonStr, QuestionsSettings.class);

            //questionsSettings = objectMapper.readValue(
            //        Files.newBufferedReader(Path.of("D:\\Java\\Study\\projects\\JR\\Others\\servletTextQuest\\src\\main\\resources\\settings.json")), QuestionsSettings.class);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public QuestionsSettings getQuestionsSettings() {
        return questionsSettings;
    }
}

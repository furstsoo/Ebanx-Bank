package com.ebanx.bank.util;

import com.ebanx.bank.entity.Account;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileUtil {

    Gson gson = new Gson();

    public static Account readFile(Integer accountId) throws IOException {
        var jsonText = String.join("", Files.readAllLines(
                Paths.get("src/main/resources/json/account" + accountId + ".json")));

        return gson.fromJson(jsonText, Account.class);
    }

    public static Boolean findFile(Integer accountId) {
        return Files.exists(Path.of("src/main/resources/json/account" + accountId + ".json"));
    }

    public static void createFile(Integer accountId, String json) throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/json/account" + accountId + ".json");
        writer.write(json);
        writer.close();

    }
}

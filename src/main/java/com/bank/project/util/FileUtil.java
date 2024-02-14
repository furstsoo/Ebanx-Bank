package com.bank.project.util;

import com.bank.project.entity.Account;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@UtilityClass
public class FileUtil {

    Gson gson = new Gson();

    public static Account readFile(String accountId) throws IOException {
        var jsonText = String.join("", Files.readAllLines(
                Paths.get("src/main/resources/json/account" + accountId + ".json")));

        return gson.fromJson(jsonText, Account.class);
    }
    public static Boolean findFile(String accountId) {
        return Files.exists(Path.of("src/main/resources/json/account" + accountId + ".json"));
    }

    public static void updateAndCreateFile(String accountId, Account account) throws IOException {
        String json = gson.toJson(account);

        FileWriter writer = new FileWriter("src/main/resources/json/account" + accountId + ".json");
        writer.write(json);
        writer.close();

    }
}

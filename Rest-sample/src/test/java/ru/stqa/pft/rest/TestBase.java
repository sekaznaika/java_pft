package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {
    boolean isIssueOpen(int issueId){
        if (!status(issueId).equals("Resolved")) {
            return true;
        } else return false;
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public String status(int issue_id) {
        String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/" + issue_id + ".json")).asString();
        JsonElement parsed = new JsonParser().parse(json);
        String status = parsed.getAsJsonObject()
                .get("issues").getAsJsonArray().get(0).getAsJsonObject()
                .get("state_name").getAsString();
        return status;
    }
}
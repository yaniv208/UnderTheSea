package com.hit.underthesea.score;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ScoreHandler {
    @SuppressLint("StaticFieldLeak")
    private static ScoreHandler instance;
    private JSONArray jsonArray;
    private Context context;

    private ScoreHandler() {

    }

    private JSONArray getArray() {
        if (jsonArray == null) {
            jsonArray = new JSONArray();
        }
        return jsonArray;
    }

    public static ScoreHandler getInstance() {
        if (instance == null) {
            instance = new ScoreHandler();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void addScore(Score score) {
        getArray().put(score.toJson());
    }

    public ArrayList<Score> fetchScores(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("GameData", Context.MODE_PRIVATE);
        try {
            jsonArray = new JSONArray(sharedPreferences.getString("Scores", ""));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

}
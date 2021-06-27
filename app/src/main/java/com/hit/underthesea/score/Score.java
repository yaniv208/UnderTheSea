package com.hit.underthesea.score;

import org.json.JSONException;
import org.json.JSONObject;

public class Score {
    private String name;
    private String level;
    private int score;

    public Score(String name, String level, int score) {
        this.name = name;
        this.level = level;
        this.score = score;
    }
    public Score(JSONObject object) {
        this.name = object.optString("name");
        this.level = object.optString("level");
        this.score = object.optInt("score");
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("name", this.name);
            obj.put("level", this.level);
            obj.put("score", this.score);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

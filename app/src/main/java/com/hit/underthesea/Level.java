package com.hit.underthesea;

public class Level {
    private int stoneAmount;
    private int foodAmount;
    private int fishpic;
    private int foodpic;
    private int stonepic;
    private int maxSpeedFood, minSpeedFood, maxSpeedStone,minSpeedStone;
    private String numlevel;
    private int highScore;

    public Level(int stoneAmount, int foodAmount, int fishpic, int foodpic, int stonepic, int maxSpeedFood, int minSpeedFood, int maxSpeedStone, int minSpeedStone,String numlevel, int highScore) {
        this.stoneAmount = stoneAmount;
        this.foodAmount = foodAmount;
        this.fishpic = fishpic;
        this.foodpic = foodpic;
        this.stonepic = stonepic;
        this.maxSpeedFood = maxSpeedFood;
        this.minSpeedFood = minSpeedFood;
        this.maxSpeedStone = maxSpeedStone;
        this.minSpeedStone = minSpeedStone;
        this.numlevel=numlevel;
        this.highScore=highScore;
    }

    public Level(Level level) {
        this.stoneAmount = level.stoneAmount;
        this.foodAmount = level.foodAmount;
        this.fishpic = level.fishpic;
        this.foodpic = level.foodpic;
        this.stonepic = level.stonepic;
        this.maxSpeedFood = level.maxSpeedFood;
        this.minSpeedFood = level.minSpeedFood;
        this.maxSpeedStone = level.maxSpeedStone;
        this.minSpeedStone = level.minSpeedStone;
        this.numlevel = level.numlevel;
        this.highScore=level.highScore;
    }

    public int getStoneAmount() {
        return stoneAmount;
    }

    public void setStoneAmount(int stoneAmount) {
        this.stoneAmount = stoneAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFishpic() {
        return fishpic;
    }

    public void setFishpic(int fishpic) {
        this.fishpic = fishpic;
    }

    public int getFoodpic() {
        return foodpic;
    }

    public void setFoodpic(int foodpic) {
        this.foodpic = foodpic;
    }

    public int getStonepic() {
        return stonepic;
    }

    public void setStonepic(int stonepic) {
        this.stonepic = stonepic;
    }

    public int getMaxSpeedFood() {
        return maxSpeedFood;
    }

    public void setMaxSpeedFood(int maxSpeedFood) {
        this.maxSpeedFood = maxSpeedFood;
    }

    public int getMinSpeedFood() { return minSpeedFood; }

    public int getHighScore() { return highScore; }

    public void setHighScore(int highScore) { this.highScore = highScore; }

    public String getNumlevel() { return numlevel; }

    public void setNumlevel(String numlevel) { this.numlevel = numlevel; }

    public void setMinSpeedFood(int minSpeedFood) {
        this.minSpeedFood = minSpeedFood;
    }

    public int getMaxSpeedStone() {
        return maxSpeedStone;
    }

    public void setMaxSpeedStone(int maxSpeedStone) {
        this.maxSpeedStone = maxSpeedStone;
    }

    public int getMinSpeedStone() {
        return minSpeedStone;
    }

    public void setMinSpeedStone(int minSpeedStone) {
        this.minSpeedStone = minSpeedStone;
    }
}

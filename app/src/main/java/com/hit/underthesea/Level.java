package com.hit.underthesea;

public class Level {
    private int stoneAmount;
    private int foodAmount;
    private int fishPic;
    private int foodPic;
    private int stonePic;
    private int maxSpeedFood, minSpeedFood, maxSpeedStone,minSpeedStone;
    private int highScore;
    private String numLevel;

    public Level(int stoneAmount, int foodAmount, int fishPic, int foodPic, int stonePic, int maxSpeedFood, int minSpeedFood, int maxSpeedStone, int minSpeedStone, String numLevel, int highScore) {
        this.stoneAmount = stoneAmount;
        this.foodAmount = foodAmount;
        this.fishPic = fishPic;
        this.foodPic = foodPic;
        this.stonePic = stonePic;
        this.maxSpeedFood = maxSpeedFood;
        this.minSpeedFood = minSpeedFood;
        this.maxSpeedStone = maxSpeedStone;
        this.minSpeedStone = minSpeedStone;
        this.numLevel = numLevel;
        this.highScore=highScore;
    }

    public Level(Level level) {
        this.stoneAmount = level.stoneAmount;
        this.foodAmount = level.foodAmount;
        this.fishPic = level.fishPic;
        this.foodPic = level.foodPic;
        this.stonePic = level.stonePic;
        this.maxSpeedFood = level.maxSpeedFood;
        this.minSpeedFood = level.minSpeedFood;
        this.maxSpeedStone = level.maxSpeedStone;
        this.minSpeedStone = level.minSpeedStone;
        this.numLevel = level.numLevel;
        this.highScore=level.highScore;
    }

    public int getStoneAmount() {
        return stoneAmount;
    }

//    public void setStoneAmount(int stoneAmount) {
//        this.stoneAmount = stoneAmount;
//    }

    public int getFoodAmount() {
        return foodAmount;
    }

//    public void setFoodAmount(int foodAmount) {
//        this.foodAmount = foodAmount;
//    }

    public int getFishPic() {
        return fishPic;
    }

    public void setFishPic(int fishPic) {
        this.fishPic = fishPic;
    }

    public int getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(int foodPic) {
        this.foodPic = foodPic;
    }

    public int getStonePic() {
        return stonePic;
    }

//    public void setStonePic(int stonePic) {
//        this.stonePic = stonePic;
//    }
//
//    public int getMaxSpeedFood() {
//        return maxSpeedFood;
//    }
//
//    public void setMaxSpeedFood(int maxSpeedFood) {
//        this.maxSpeedFood = maxSpeedFood;
//    }
//
//    public int getMinSpeedFood() { return minSpeedFood; }

    public int getHighScore() { return highScore; }

//    public void setHighScore(int highScore) { this.highScore = highScore; }

    public String getNumLevel() { return numLevel; }

//    public void setNumLevel(String numLevel) { this.numLevel = numLevel; }
//
//    public void setMinSpeedFood(int minSpeedFood) {
//        this.minSpeedFood = minSpeedFood;
//    }

    public int getMaxSpeedStone() {
        return maxSpeedStone;
    }

//    public void setMaxSpeedStone(int maxSpeedStone) {
//        this.maxSpeedStone = maxSpeedStone;
//    }

    public int getMinSpeedStone() {
        return minSpeedStone;
    }

//    public void setMinSpeedStone(int minSpeedStone) {
//        this.minSpeedStone = minSpeedStone;
//    }
}

package com.example.pantry;

public class FoodData {

    private String itemName;
    private String itemDescription;
    private String itemDuration;
    private String itemImage;
    private String key;
    private String recipeKey;

    public FoodData() {

    }

    public FoodData(String itemName, String itemDescription, String itemDuration, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemDuration = itemDuration;
        this.itemImage = itemImage;
    }

    public FoodData(String itemName, String itemDescription, String itemDuration, String itemImage, String key,String recipeKey){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemDuration = itemDuration;
        this.itemImage = itemImage;
        this.key = key;
        this.recipeKey = recipeKey;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemDuration() {
        return itemDuration;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRecipeKey() {
        return recipeKey;
    }

    public void setRecipeKey(String recipeKey) {
        this.recipeKey = recipeKey;
    }

}

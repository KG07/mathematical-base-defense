package com.mathematicalbasedefense.mathematicalbasedefense.game.entities;

import java.awt.*;

public class Enemy {

    public int xPos, yPos, width, height, rotation, speed,  attack, health, enemyNumber;
    public double valueInPoints;
    public Color color;
    public String name, requestedValue;


    public Enemy(int xPos, int yPos, int width, int height, int rotation, int speed, String requestedValue, double valueInPoints, int health, int attack, int enemyNumber, Color color, String name){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.speed = speed;
        this.requestedValue = requestedValue;
        this.valueInPoints = valueInPoints;
        this.attack = attack;
        this.health = health;
        this.enemyNumber = enemyNumber;
        this.color = color;
        this.name = name;
    }

    public void move(int speed){
        this.xPos -= speed;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRotation(){
        return rotation;
    }

    public int getSpeed() {
        return speed;
    }

    public String getRequestedValue() {
        return requestedValue;
    }

    public double getValueInPoints() {
        return valueInPoints;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getEnemyNumber() {
        return enemyNumber;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}

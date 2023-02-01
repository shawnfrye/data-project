/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gov.faa.sortby;

import java.awt.Color;

/**
 *
 * @author sfrye
 */
final class Person {

    //attributes of final class
    protected final String name;
    protected final int date;
    protected final String company;
    protected final Color color;

    //Constructor
    public Person(String name, int date, Color color, String company) {

        this.name = name;
        this.date = date;
        this.color = color;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public String getCompany() {
        return company;
    }

    public Color getColor() {
        return color;
    }

}

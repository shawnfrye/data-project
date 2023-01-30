/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gov.faa.sortbycolor;

import java.awt.Color;

/**
 *
 * @author sfrye
 */
final class Person {
    
    //attributes of final class
    
    private final String name;
    private final int date;
    private final String company;
    private final Color color;
    
    //Constructor
    public Person(String name, int date, Color color, String company) {
        
        this.name = name;
        this.date = date;
        this.color = color;
        this.company = company;
}
    
}

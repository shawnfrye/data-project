/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gov.faa.sortby;

import static gov.faa.sortby.LogToFile.log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author sfrye
 */
public class SortBy {
    
    ArrayList<Person> alPerson = new ArrayList<Person>();

    public static void main(String[] args) {
        GUI gui = new GUI();
        log("info", "THIS IS ONLY A TEST");
        gui.setVisible(true);
        
        
        try{
            readTextFile();
            
        } catch (FileNotFoundException e) {
            log("fine", "File Not Found Exception");
        }
        
        
    }
    
    protected static void readTextFile() throws FileNotFoundException {
        String inputFile = GUI.inputFileField.getText();
        inputFile = "/vnx1/home/sfrye/test-data.txt";
        File file = new File(inputFile);
        
       Scanner input = new Scanner(new File(inputFile));        
       
       while(input.hasNextLine()) {
           String dateRecord = input.nextLine();
           String[] dateSplit = dateRecord.split(":");   
           dateRecord = dateSplit[1];
           String nameRecord = input.nextLine();
           String[] nameSplit = nameRecord.split(":");
           nameRecord = nameSplit[1];
           String companyRecord = input.nextLine();
           String[] companySplit = companyRecord.split(":");
           companyRecord = companySplit[1];
           String colorRecord = input.nextLine();
           String[] colorSplit = colorRecord.split(":");
           colorRecord = colorSplit[1];
           String emptyRecord = input.nextLine();
           
           //Person person = new Person();
           
       }
             
       
    

}

    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

class LogToFile {

    protected static final Logger logger=Logger.getLogger("MYLOG");
   
    /**
     * log Method 
     * enable to log all exceptions to a file and display user message on demand
     * @param ex
     * @param level
     * @param msg 
     */
    public static void log(String level, String msg){

        FileHandler fh = null;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        try {
            fh = new FileHandler("/vnx1/home/sfrye/Repos/SortBy/data-project/sFryeProject/SortBy/" + timeStamp + "__LOG.xml",true);
            logger.addHandler(fh);
            switch (level) {
                case "severe":
                    logger.log(Level.SEVERE, msg);
                    if(!msg.equals(""))
                        JOptionPane.showMessageDialog(null,msg,
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "warning":
                    logger.log(Level.WARNING, msg);
                    if(!msg.equals(""))
                        JOptionPane.showMessageDialog(null,msg,
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    break;
                case "info":
                    logger.log(Level.INFO, msg);
                    break;
                case "config":
                    logger.log(Level.CONFIG, msg);
                    break;
                case "fine":
                    logger.log(Level.FINE, msg);
                    break;
                case "finer":
                    logger.log(Level.FINER, msg);
                    break;
                case "finest":
                    logger.log(Level.FINEST, msg);
                    break;
                default:
                    logger.log(Level.CONFIG, msg);
                    break;
            }
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package gov.faa.sortby;

import static gov.faa.sortby.LogToFile.log;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.beryx.awt.color.ColorFactory;

/**
 *
 * @author sfrye
 */
public class SortBy {

    static ArrayList<Person> alPerson = new ArrayList<Person>();

    public static void main(String[] args) {
        GUI gui = new GUI();

        gui.setVisible(true);

    }

    // ran when sort button is clicked
    //reads input file, sorts, outputs xml
    public static void sortAndCreate() {

        try {
            readTextFile(GUI.inputFileField.getText());

        } catch (FileNotFoundException e) {
            log("warning", "File Not Found!");
        }

        sortPersonArrayList(alPerson);
        createXmlDocument(GUI.outputFileField.getText());
    }

    //reads input txt file
    protected static void readTextFile(String inputFile) throws FileNotFoundException {

        Scanner input = new Scanner(new File(inputFile));
        // while txt file has remaining txt lines
        // create person objects
        while (input.hasNextLine()) {

            //read in and split strings where colon is
            String dateRecord = input.nextLine();
            if (dateRecord.startsWith("Date"));
            {

                String[] dateSplit = dateRecord.split(":");
                dateRecord = dateSplit[1];
                dateRecord = dateRecord.replaceAll("[,.]", "");
                int iDateRecord = Integer.parseInt(dateRecord);
                String nameRecord = input.nextLine();

                String[] nameSplit = nameRecord.split(":");
                nameRecord = nameSplit[1];
                String companyRecord = input.nextLine();
                String[] companySplit = companyRecord.split(":");
                companyRecord = companySplit[1];
                String colorRecord = input.nextLine();
                String[] colorSplit = colorRecord.split(":");
                colorRecord = colorSplit[1];
                if (input.hasNextLine()) {
                    String emptyRecord = input.nextLine();
                }

                Color newColor = ColorFactory.valueOf(colorRecord);

                Person person = new Person(nameRecord, iDateRecord, newColor, companyRecord);
                alPerson.add(person);

            }

        }

    }

    //sort person arraylist based on selected parameter in GUI
    static void sortPersonArrayList(ArrayList<Person> list) {

        if (GUI.dateBubble.isSelected()) {
            Collections.sort(
                    list,
                    (person1, person2) -> person1.getDate()
                    - person2.getDate());
        } else if (GUI.nameBubble.isSelected()) {
            NameComparator comparator = new NameComparator();
            Collections.sort(list, comparator);
        } else if (GUI.colorBubble.isSelected()) {
            Collections.sort(
                    list,
                    (person1, person2) -> person1.getColor().getRGB()
                    - person2.getColor().getRGB());

        } else if (GUI.companyBubble.isSelected()) {
            CompanyComparator comparator = new CompanyComparator();
            Collections.sort(list, comparator);
        } else {
            log("warning", "Choose a Sort By: parameter");
        }

    }
    // create xml file

    private static void createXmlDocument(String fileInfo) {

        XMLOutputFactory factory = XMLOutputFactory.newFactory();

        FileOutputStream fos;
        XMLStreamWriter writer = null;

        try {

            fos = new FileOutputStream(fileInfo);
            writer = factory.createXMLStreamWriter(fos, "UTF-8");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (XMLStreamException e) {

            e.printStackTrace();
        }

        writeToDocument(writer);

    }

    private static void writeToDocument(XMLStreamWriter writer) {

        try {

            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("Persons");
            writer.writeCharacters("\n");

            for (Person person : alPerson) {

                writer.writeCharacters("\t");
                writer.writeStartElement("Person");
                writer.writeAttribute("Name", String.valueOf(person.name));

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("date");
                writer.writeCharacters(String.valueOf(person.date));
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("company");
                writer.writeCharacters(String.valueOf(person.company));
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("color");
                writer.writeCharacters(String.valueOf(person.color));
                writer.writeEndElement();

                writer.writeCharacters("\n\t");
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();

        } catch (XMLStreamException e) {

            e.printStackTrace();
        }
    }

}

class NameComparator implements Comparator<Person> {

    public int compare(Person obj1, Person obj2) {
        return obj1.getName().compareTo(obj2.getName());
    }

}

class CompanyComparator implements Comparator<Person> {

    public int compare(Person obj1, Person obj2) {
        return obj1.getCompany().compareTo(obj2.getCompany());
    }

}

class LogToFile {

    protected static final Logger logger = Logger.getLogger("MYLOG");

    /**
     * log Method enable to log all exceptions to a file and display user
     * message on demand
     *
     * @param ex
     * @param level
     * @param msg
     */
    public static void log(String level, String msg) {

        FileHandler fh = null;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        try {
            fh = new FileHandler("/vnx1/home/sfrye/Repos/SortBy/data-project/sFryeProject/SortBy/" + timeStamp + "__LOG.xml", true);
            logger.addHandler(fh);
            switch (level) {
                case "severe":
                    logger.log(Level.SEVERE, msg);
                    if (!msg.equals("")) {
                        JOptionPane.showMessageDialog(null, msg,
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "warning":
                    logger.log(Level.WARNING, msg);
                    if (!msg.equals("")) {
                        JOptionPane.showMessageDialog(null, msg,
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
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
        } finally {
            if (fh != null) {
                fh.close();
            }
        }

    }
}


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

class ID implements Comparable<ID>{

    int id;

    public ID(String inputID)
    {
        this.id = Integer.parseInt(inputID);
    }


    public int getID() {
        return id;
    }

    @Override
    public int compareTo(ID o) {
        return Integer.compare(getID(), o.getID());
    }

    public String toString()
    {
        return (id + "\n");
    }
}

public class CE203_1903165_Ass1 extends JFrame
{
    private ID theID;           // The ID is initialized

    // After, we create an Array List to store the IDs
    private  final ArrayList<ID> listID = new ArrayList<>();


    // We create the input and output
    private final JTextField inputTextField;  // user input
    private final JTextArea outputTextArea;   // system output


    // Now, we create the JTextFields.
    // User will input the following values:
    private JTextField redTextField;        //  red value
    private JTextField greenTextField;      // green value
    private JTextField blueTextField;       // blue value

    // Then, we create the constructor
    CE203_1903165_Ass1()
    {
        setSize(400,400);                       // we set the size of the program
        setTitle("Assignment");                             // we choose a title for it
        setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Enter ID:");
        inputTextField = new JTextField(6);             // JTextField for user input
        outputTextArea = new JTextArea(15, 20);    // JTextArea for System Output
        outputTextArea.setEditable(false);

        // We are going to create the JButtons for the requirements:
        JButton addButton = new JButton("ADD");             // JButton for adding
        JButton displayButton = new JButton("DISPLAY");     // JButton for displaying
        JButton deleteButton = new JButton("DELETE");       // JButton for deleting
        JButton sortButton = new JButton("SORT");           // JButton for sorting
        JButton clearButton = new JButton("CLEAR");         // JButton for clearing
//        I have added an extra button for a beautiful grid
        JButton extraButton = new JButton("CLOSE");         // JButton for extra feature

        // Now, we are going to add the Action Listeners

        // Action Listener for the "ADD" button
        // Will add a new 6 digit unique ID
        addButton.addActionListener(event -> {
            String inputID = inputTextField.getText();

            try {
                // To set the length of ID to 6 digits
                if(String.valueOf(inputID).length() != 6)
                {
                    outputTextArea.setText("The ID must contain only 6 digits!");
                }
                else
                {
                    theID = new ID(inputID);
                    // check for same ID
                    if(replace(theID)){
                        outputTextArea.setText("ID " + theID + "already exists");
                    }
                    else {

                        // add the ID to the list if its length is 6 digits and it's unique
                        addIDMethod(theID);
                        outputTextArea.setText(theID + "has been added.");
                    }

                }
            }
            catch (NumberFormatException exception)
            {
                outputTextArea.setText("Enter a valid ID");
            }
        });

        // Action Listener for "DISPLAY" button
        // Display all the IDs stored in the list
        displayButton.addActionListener(event -> {

            int blue, red, green;

            try {
                red = Integer.parseInt(redTextField.getText());
                green = Integer.parseInt(greenTextField.getText());
                blue = Integer.parseInt(blueTextField.getText());

                if( blue >= 0 && red >= 0 && green >=0 &&
                        blue <= 255 && red <= 255 && green <= 255
                ) {
                    outputTextArea.setText(printList());
                    outputTextArea.setForeground(new Color(red, green, blue));
                    outputTextArea.repaint();
                }
                else{
                    outputTextArea.setForeground(new Color(0,0,0));
                    outputTextArea.setText("Please enter a number between 0 and 255");
                }
            }
            catch (NumberFormatException exception)
            {
                outputTextArea.setForeground(new Color(0, 0, 0));
                outputTextArea.setText("Please enter a value between 0 and 255");
            }
        });

        // Action Listener for "SORT" button
        // Sort the list of IDs in ascending order and
        // display the contents of the list
        sortButton.addActionListener(event -> {
            Collections.sort(listID);
            outputTextArea.setText("Sorted list: " + "\n" + printList());
        });

        // Action Listener for "CLOSE" button
        // This extraButton is going to close the program once it's clicked
        extraButton.addActionListener(event -> System.exit(0));

        // Action Listener for "CLEAR" button
        // Will clear the list
        clearButton.addActionListener(event -> {
            listID.clear();
            outputTextArea.setText("List has been cleared");
        });

        // Action Listener for "DELETE" button
        deleteButton.addActionListener(event -> {
            String inputID = inputTextField.getText();

            try {
                if(String.valueOf(inputID).length() != 6)
                {
                    outputTextArea.setText("Enter a 6 digit ID");
                }
                else {
                    theID = new ID(inputID);
                    listID.removeIf(id -> id.compareTo(theID) == 0);
                    outputTextArea.setText( theID + "has been removed from the list");

                }
            }
            catch (NumberFormatException exception)
            {
                outputTextArea.setText("Please enter a 6 digit ID");
            }
        });


        // We add some JLabels for the color names
        JLabel redLabel = new JLabel("Red:");
        JLabel greenLabel = new JLabel("Green:");
        JLabel blueLabel = new JLabel("Blue");

        // Then, some JTextFields to enter RGB values
        redTextField = new JTextField(3);       // JTextField for Red value
        greenTextField = new JTextField(3);     // JTextField for Green value
        blueTextField = new JTextField(3);      // JTextField for Blue value

        //Instantiate the panels to store the components
        JPanel idPanel = new JPanel();          // Panel for storing JTextField
        JPanel buttonsPanel = new JPanel();     // Panel to store JButtons
        JPanel outputPanel = new JPanel();      // Panel to store JTextArea
        JPanel rgbPanel = new JPanel();         // Panel to store RGB JTextFields
        JPanel extraPanel = new JPanel();       // Panel to store idPanel, rgbPanel and buttonsPanel

        //After, we add buttons to the buttonsPanel
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(sortButton);
        buttonsPanel.add(displayButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(extraButton);
        buttonsPanel.setLayout(new GridLayout(0, 3));

        //Now we add text field with label for user input
        idPanel.add(idLabel);
        idPanel.add(inputTextField);
        //adds output text area
        outputPanel.add(outputTextArea);

        // text field for RGB values
        rgbPanel.add(redLabel);
        rgbPanel.add(greenLabel);
        rgbPanel.add(blueLabel);

        rgbPanel.add(redTextField);
        rgbPanel.add(greenTextField);
        rgbPanel.add(blueTextField);

        rgbPanel.setLayout(new GridLayout(2, 3));

        extraPanel.add(idPanel);
        extraPanel.add(rgbPanel);
        extraPanel.add(buttonsPanel);
        extraPanel.setLayout(new BoxLayout(extraPanel, BoxLayout.PAGE_AXIS));

        //Now we add all the panels to the JFrame
        // extraPanel is used to add idPanel, rgbPanel and buttonsPanel to the SOUTH of JFrame
        add(outputPanel, BorderLayout.CENTER);
        add(extraPanel, BorderLayout.SOUTH);
    }

    public void addIDMethod(ID inputID)
    {
        listID.add(inputID);
    }

    public String printList()
    {
        StringBuilder line = new StringBuilder();

        for (ID currentID:
                listID) {

            line.append(currentID.toString());

        }
        return line.toString();
    }

    public boolean replace(ID input)
    {
        for (ID id : listID) {
            if (id.compareTo(input) == 0) {
                return true;
            }
        }
        return false;
    }

    // Main method
    public static void main(String[] args) {

        CE203_1903165_Ass1 frame = new CE203_1903165_Ass1();                // Instantiate the constructor
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);                // Used for stopping the program
        frame.setResizable(false);                                          // The program won't be resizable
        frame.setVisible(true);                                             // The program is visible

    }
}
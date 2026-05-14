import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OnlineReservationSystem extends JFrame implements ActionListener {

    // LOGIN COMPONENTS
    JLabel userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    // RESERVATION COMPONENTS
    JLabel nameLabel, trainNoLabel, trainNameLabel,
            classLabel, dateLabel, sourceLabel, destinationLabel;

    JTextField nameField, trainNoField, trainNameField,
            dateField, sourceField, destinationField;

    JComboBox<String> classBox;

    JButton reserveButton, openCancelButton;

    // DATA STORAGE
    static ArrayList<Ticket> tickets = new ArrayList<>();
    static int pnrCounter = 1001;

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new OnlineReservationSystem());
    }

    // CONSTRUCTOR
    OnlineReservationSystem() {

        showLoginForm();
        setVisible(true);
    }

    // LOGIN FORM
    void showLoginForm() {

        setTitle("Online Reservation System - Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 60, 100, 30);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(150, 60, 150, 30);
        add(userField);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 110, 100, 30);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(150, 110, 150, 30);
        add(passField);

        loginButton = new JButton("Login");
        loginButton.setBounds(140, 180, 100, 35);
        loginButton.addActionListener(this);
        add(loginButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // RESERVATION FORM
    void showReservationForm() {

        getContentPane().removeAll();

        setTitle("Reservation Form");
        setSize(550, 550);
        setLayout(null);
        setLocationRelativeTo(null);

        nameLabel = new JLabel("Passenger Name:");
        nameLabel.setBounds(50, 30, 150, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(220, 30, 200, 25);
        add(nameField);

        trainNoLabel = new JLabel("Train Number:");
        trainNoLabel.setBounds(50, 70, 150, 25);
        add(trainNoLabel);

        trainNoField = new JTextField();
        trainNoField.setBounds(220, 70, 200, 25);
        add(trainNoField);

        trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(50, 110, 150, 25);
        add(trainNameLabel);

        trainNameField = new JTextField();
        trainNameField.setBounds(220, 110, 200, 25);
        add(trainNameField);

        classLabel = new JLabel("Class Type:");
        classLabel.setBounds(50, 150, 150, 25);
        add(classLabel);

        String classes[] = {"Sleeper", "AC", "First Class"};

        classBox = new JComboBox<>(classes);
        classBox.setBounds(220, 150, 200, 25);
        add(classBox);

        dateLabel = new JLabel("Journey Date:");
        dateLabel.setBounds(50, 190, 150, 25);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(220, 190, 200, 25);
        add(dateField);

        sourceLabel = new JLabel("From:");
        sourceLabel.setBounds(50, 230, 150, 25);
        add(sourceLabel);

        sourceField = new JTextField();
        sourceField.setBounds(220, 230, 200, 25);
        add(sourceField);

        destinationLabel = new JLabel("To:");
        destinationLabel.setBounds(50, 270, 150, 25);
        add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(220, 270, 200, 25);
        add(destinationField);

        reserveButton = new JButton("Reserve Ticket");
        reserveButton.setBounds(80, 350, 150, 40);
        reserveButton.addActionListener(this);
        add(reserveButton);

        openCancelButton = new JButton("Cancel Ticket");
        openCancelButton.setBounds(260, 350, 150, 40);
        openCancelButton.addActionListener(this);
        add(openCancelButton);

        revalidate();
        repaint();
    }

    // ACTION EVENTS
    public void actionPerformed(ActionEvent e) {

        // LOGIN BUTTON
        if (e.getSource() == loginButton) {

            String username = userField.getText();
            String password = String.valueOf(passField.getPassword());

            if (username.equals("admin") && password.equals("1234")) {

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                showReservationForm();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password");
            }
        }

        // RESERVE BUTTON
        if (e.getSource() == reserveButton) {

            String name = nameField.getText().trim();
            String trainNo = trainNoField.getText().trim();
            String trainName = trainNameField.getText().trim();
            String classType = String.valueOf(classBox.getSelectedItem());
            String date = dateField.getText().trim();
            String source = sourceField.getText().trim();
            String destination = destinationField.getText().trim();

            // VALIDATION
            if (name.isEmpty() || trainNo.isEmpty() ||
                    trainName.isEmpty() || date.isEmpty() ||
                    source.isEmpty() || destination.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please Fill All Fields");

                return;
            }

            int pnr = pnrCounter++;

            Ticket ticket = new Ticket(
                    pnr,
                    name,
                    trainNo,
                    trainName,
                    classType,
                    date,
                    source,
                    destination
            );

            tickets.add(ticket);

            JOptionPane.showMessageDialog(this,
                    "Reservation Successful\nPNR Number: " + pnr);

            clearFields();
        }

        // OPEN CANCELLATION FORM
        if (e.getSource() == openCancelButton) {

            new CancellationFrame();
        }
    }

    // CLEAR INPUT FIELDS
    void clearFields() {

        nameField.setText("");
        trainNoField.setText("");
        trainNameField.setText("");
        dateField.setText("");
        sourceField.setText("");
        destinationField.setText("");
    }

    // TICKET CLASS
    static class Ticket {

        int pnr;
        String passengerName;
        String trainNo;
        String trainName;
        String classType;
        String date;
        String source;
        String destination;

        Ticket(int pnr,
               String passengerName,
               String trainNo,
               String trainName,
               String classType,
               String date,
               String source,
               String destination) {

            this.pnr = pnr;
            this.passengerName = passengerName;
            this.trainNo = trainNo;
            this.trainName = trainName;
            this.classType = classType;
            this.date = date;
            this.source = source;
            this.destination = destination;
        }

        public String toString() {

            return "PNR Number : " + pnr +
                    "\nPassenger Name : " + passengerName +
                    "\nTrain Number : " + trainNo +
                    "\nTrain Name : " + trainName +
                    "\nClass Type : " + classType +
                    "\nJourney Date : " + date +
                    "\nFrom : " + source +
                    "\nTo : " + destination;
        }
    }

    // CANCELLATION WINDOW
    class CancellationFrame extends JFrame implements ActionListener {

        JLabel pnrLabel;
        JTextField pnrField;

        JButton searchButton, cancelButton;

        JTextArea resultArea;

        Ticket foundTicket = null;

        CancellationFrame() {

            setTitle("Cancellation Form");
            setSize(500, 450);
            setLayout(null);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            pnrLabel = new JLabel("Enter PNR Number:");
            pnrLabel.setBounds(30, 30, 150, 30);
            add(pnrLabel);

            pnrField = new JTextField();
            pnrField.setBounds(180, 30, 180, 30);
            add(pnrField);

            searchButton = new JButton("Search");
            searchButton.setBounds(90, 90, 100, 35);
            searchButton.addActionListener(this);
            add(searchButton);

            cancelButton = new JButton("Cancel Ticket");
            cancelButton.setBounds(220, 90, 140, 35);
            cancelButton.addActionListener(this);
            add(cancelButton);

            resultArea = new JTextArea();
            resultArea.setBounds(50, 150, 370, 200);
            resultArea.setEditable(false);
            add(resultArea);

            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {

            int pnr;

            try {

                pnr = Integer.parseInt(pnrField.getText());

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Enter Valid PNR Number");

                return;
            }

            // SEARCH BUTTON
            if (e.getSource() == searchButton) {

                foundTicket = null;

                for (Ticket t : tickets) {

                    if (t.pnr == pnr) {

                        foundTicket = t;

                        resultArea.setText(t.toString());

                        break;
                    }
                }

                if (foundTicket == null) {

                    resultArea.setText("Ticket Not Found");
                }
            }

            // CANCEL BUTTON
            if (e.getSource() == cancelButton) {

                if (foundTicket != null) {

                    tickets.remove(foundTicket);

                    JOptionPane.showMessageDialog(this,
                            "Ticket Cancelled Successfully");

                    foundTicket = null;

                    resultArea.setText("");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Search Ticket First");
                }
            }
        }
    }
}
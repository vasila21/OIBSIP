import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {

    // TITLE LABELS
    JLabel titleLabel;
    JLabel instructionLabel;

    // INPUT LABELS
    JLabel guessLabel;
    JLabel attemptLabel;
    JLabel scoreLabel;
    JLabel roundLabel;

    // TEXT FIELD
    JTextField guessField;

    // BUTTONS
    JButton guessButton;
    JButton newGameButton;
    JButton exitButton;

    // GAME VARIABLES
    int randomNumber;
    int attempts;
    int maxAttempts = 10;

    int score = 0;
    int round = 1;

    Random random = new Random();

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new NumberGuessingGame();
        });
    }

    // CONSTRUCTOR
    NumberGuessingGame() {

        // FRAME SETTINGS
        setTitle("Number Guessing Game");
        setSize(550, 450);
        setLayout(null);
        setLocationRelativeTo(null);

        // TITLE LABEL
        titleLabel = new JLabel("NUMBER GUESSING GAME");
        titleLabel.setBounds(150, 20, 300, 30);
        add(titleLabel);

        // INSTRUCTION LABEL
        instructionLabel = new JLabel(
                "Guess a number between 1 and 100");
        instructionLabel.setBounds(140, 60, 300, 25);
        add(instructionLabel);

        // GUESS LABEL
        guessLabel = new JLabel("Enter Your Guess:");
        guessLabel.setBounds(80, 120, 150, 30);
        add(guessLabel);

        // TEXT FIELD
        guessField = new JTextField();
        guessField.setBounds(240, 120, 150, 30);
        add(guessField);

        // GUESS BUTTON
        guessButton = new JButton("Guess");
        guessButton.setBounds(70, 200, 120, 40);
        guessButton.addActionListener(this);
        add(guessButton);

        // NEW GAME BUTTON
        newGameButton = new JButton("New Game");
        newGameButton.setBounds(210, 200, 120, 40);
        newGameButton.addActionListener(this);
        add(newGameButton);

        // EXIT BUTTON
        exitButton = new JButton("Exit");
        exitButton.setBounds(350, 200, 120, 40);
        exitButton.addActionListener(this);
        add(exitButton);

        // ATTEMPT LABEL
        attemptLabel = new JLabel(
                "Attempts Left : " + maxAttempts);
        attemptLabel.setBounds(80, 300, 180, 30);
        add(attemptLabel);

        // SCORE LABEL
        scoreLabel = new JLabel(
                "Score : " + score);
        scoreLabel.setBounds(250, 300, 150, 30);
        add(scoreLabel);

        // ROUND LABEL
        roundLabel = new JLabel(
                "Round : " + round);
        roundLabel.setBounds(380, 300, 120, 30);
        add(roundLabel);

        // GENERATE RANDOM NUMBER
        generateRandomNumber();

        // FRAME CLOSE
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // FRAME VISIBLE
        setVisible(true);
    }

    // RANDOM NUMBER METHOD
    void generateRandomNumber() {

        randomNumber = random.nextInt(100) + 1;

        attempts = 0;
    }

    // CLEAR FIELD METHOD
    void clearField() {

        guessField.setText("");
    }

    // UPDATE LABELS METHOD
    void updateLabels() {

        int attemptsLeft = maxAttempts - attempts;

        attemptLabel.setText(
                "Attempts Left : " + attemptsLeft);

        scoreLabel.setText(
                "Score : " + score);

        roundLabel.setText(
                "Round : " + round);
    }

    // ACTION METHOD
    public void actionPerformed(ActionEvent e) {

        // GUESS BUTTON
        if (e.getSource() == guessButton) {

            int userGuess;

            // VALIDATION
            try {

                userGuess = Integer.parseInt(
                        guessField.getText());

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please Enter Valid Number");

                clearField();

                return;
            }

            // RANGE CHECK
            if (userGuess < 1 || userGuess > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Number Between 1 and 100");

                clearField();

                return;
            }

            // INCREMENT ATTEMPTS
            attempts++;

            // CORRECT GUESS
            if (userGuess == randomNumber) {

                int points;

                points = (maxAttempts - attempts + 1) * 10;

                score = score + points;

                JOptionPane.showMessageDialog(
                        this,
                        "Correct Guess!\n" +
                        "You Won The Round!\n" +
                        "Points Earned : " + points);

                round++;

                generateRandomNumber();

                updateLabels();

                clearField();

                return;
            }

            // HIGHER NUMBER
            if (userGuess > randomNumber) {

                JOptionPane.showMessageDialog(
                        this,
                        "Too High!");

            }

            // LOWER NUMBER
            else {

                JOptionPane.showMessageDialog(
                        this,
                        "Too Low!");
            }

            // UPDATE LABELS
            updateLabels();

            // GAME OVER
            if (attempts >= maxAttempts) {

                JOptionPane.showMessageDialog(
                        this,
                        "Game Over!\n" +
                        "Correct Number Was : "
                                + randomNumber);

                round++;

                generateRandomNumber();

                updateLabels();
            }

            clearField();
        }

        // NEW GAME BUTTON
        if (e.getSource() == newGameButton) {

            generateRandomNumber();

            attempts = 0;

            updateLabels();

            clearField();

            JOptionPane.showMessageDialog(
                    this,
                    "New Game Started!");
        }

        // EXIT BUTTON
        if (e.getSource() == exitButton) {

            int choice;

            choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do You Want To Exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            // YES OPTION
            if (choice == JOptionPane.YES_OPTION) {

                System.exit(0);
            }
        }
    }
}
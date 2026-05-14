import javax.swing.*;
import java.awt.event.*;

public class OnlineExamination extends JFrame implements ActionListener {

    // LOGIN
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    // EXAM UI
    JLabel questionLabel, timerLabel, scoreLabel;

    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup group;

    JButton nextButton, submitButton, updateButton, logoutButton;

    // DATA
    String user = "admin";
    String pass = "1234";

    String questions[] = {
            "which language is platform independent?",
            "Which company developed Java?",
            "Which keyword is used for Inheritance?",
            "Which package is used for GUI?",
            "Which method is the starting point of java?"
    };

    String options[][] = {
            {"C", "Java", "C++", "python"},
            {"Microsoft", "Sun Microsystem", "Google", "Apple"},
            {"extends", "import", "class", "void"},
            {"java.io", "javax.swing", "util", "sql"},
            {"start()", "main()", "run()", "init()"}
    };

    int answers[] = {2, 2, 1, 2, 2};

    int userAns[] = new int[5];

    int index = 0;
    int score = 0;
    int time = 60;

    Timer timer;

    boolean examFinished = false;

    public static void main(String[] args) {
        new OnlineExamination();
    }

    OnlineExamination() {
        loginPage();
    }

    // LOGIN PAGE
    void loginPage() {

        setTitle("Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);   
        setResizable(false);

        JLabel u = new JLabel("Username:");
        u.setBounds(50, 50, 100, 30);
        add(u);

        userField = new JTextField();
        userField.setBounds(150, 50, 150, 30);
        add(userField);

        JLabel p = new JLabel("Password:");
        p.setBounds(50, 100, 100, 30);
        add(p);

        passField = new JPasswordField();
        passField.setBounds(150, 100, 150, 30);
        add(passField);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 170, 100, 40);
        loginButton.addActionListener(this);
        add(loginButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EXAM PAGE
    void examPage() {

        getContentPane().removeAll();

        setTitle("Online Exam");
        setSize(650, 450);
        setLayout(null);
        setLocationRelativeTo(null);  
        setResizable(false);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 500, 30);
        add(questionLabel);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        opt1.setBounds(80, 80, 200, 30);
        opt2.setBounds(80, 120, 200, 30);
        opt3.setBounds(80, 160, 200, 30);
        opt4.setBounds(80, 200, 200, 30);

        group = new ButtonGroup();
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        add(opt1);
        add(opt2);
        add(opt3);
        add(opt4);

        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");
        updateButton = new JButton("Update Profile");
        logoutButton = new JButton("Logout");

        nextButton.setBounds(50, 280, 100, 40);
        submitButton.setBounds(170, 280, 100, 40);
        updateButton.setBounds(290, 280, 150, 40);
        logoutButton.setBounds(460, 280, 100, 40);

        add(nextButton);
        add(submitButton);
        add(updateButton);
        add(logoutButton);

        nextButton.addActionListener(this);
        submitButton.addActionListener(this);
        updateButton.addActionListener(this);
        logoutButton.addActionListener(this);

        timerLabel = new JLabel("Time: " + time);
        timerLabel.setBounds(500, 20, 120, 30);
        add(timerLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(500, 50, 120, 30);
        add(scoreLabel);

        loadQuestion();

        timer = new Timer(1000, e -> {
            time--;
            timerLabel.setText("Time: " + time);

            if (time == 0 && !examFinished) {
                autoSubmit();
            }
        });

        timer.start();

        revalidate();
        repaint();

        setVisible(true);
    }

    void loadQuestion() {
        questionLabel.setText(questions[index]);
        opt1.setText(options[index][0]);
        opt2.setText(options[index][1]);
        opt3.setText(options[index][2]);
        opt4.setText(options[index][3]);
        group.clearSelection();
    }

    void saveAnswer() {
        if (opt1.isSelected()) userAns[index] = 1;
        else if (opt2.isSelected()) userAns[index] = 2;
        else if (opt3.isSelected()) userAns[index] = 3;
        else if (opt4.isSelected()) userAns[index] = 4;
    }

    void calculateScore() {
        score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (userAns[i] == answers[i]) {
                score++;
            }
        }
        scoreLabel.setText("Score: " + score);
    }

    void finishExam() {

        examFinished = true;

        saveAnswer();
        calculateScore();

        timer.stop();

        JOptionPane.showMessageDialog(this,
                "Exam Finished!\nScore: " + score);

        // LOCK UI AFTER EXAM
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);

        nextButton.setEnabled(false);
        submitButton.setEnabled(false);
    }

    void autoSubmit() {
        JOptionPane.showMessageDialog(this,
                "Time Over! Auto Submitting Exam");
        finishExam();
    }

    public void actionPerformed(ActionEvent e) {

        // LOGIN
        if (e.getSource() == loginButton) {

            String u = userField.getText();
            String p = String.valueOf(passField.getPassword());

            if (u.equals(user) && p.equals(pass)) {
                JOptionPane.showMessageDialog(this, "Login Success");
                examPage();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        }

        // NEXT
        if (e.getSource() == nextButton && !examFinished) {

            saveAnswer();

            index++;

            if (index < questions.length) {
                loadQuestion();
            } else {
                JOptionPane.showMessageDialog(this, "Last Question");
                index--;
            }
        }

        // SUBMIT
        if (e.getSource() == submitButton) {
            finishExam();
        }

        // UPDATE PROFILE
        if (e.getSource() == updateButton) {

            JTextField newUser = new JTextField();
            JPasswordField newPass = new JPasswordField();

            Object msg[] = {
                    "New Username:", newUser,
                    "New Password:", newPass
            };

            int opt = JOptionPane.showConfirmDialog(
                    this, msg, "Update Profile",
                    JOptionPane.OK_CANCEL_OPTION);

            if (opt == JOptionPane.OK_OPTION) {
                user = newUser.getText();
                pass = String.valueOf(newPass.getPassword());

                JOptionPane.showMessageDialog(this,
                        "Profile Updated Successfully");
            }
        }

        // LOGOUT
        if (e.getSource() == logoutButton) {

            int c = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if (c == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
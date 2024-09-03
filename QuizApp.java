import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QuizApp extends JFrame implements ActionListener {
    private String[] questions = {
        "What is the capital of France?",
        "What is 5 + 3?",
        "What is the largest planet in our solar system?"
    };

    private String[][] options = {
        {"Paris", "London", "Berlin", "Madrid"},
        {"5", "8", "10", "15"},
        {"Earth", "Jupiter", "Mars", "Venus"}
    };

    private String[] correctAnswers = {"Paris", "8", "Jupiter"};

    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons = new JRadioButton[4];
    private ButtonGroup optionsGroup;
    private JButton nextButton;

    public QuizApp() {
        // Setting up the GUI components
        questionLabel = new JLabel();
        optionsGroup = new ButtonGroup();
        nextButton = new JButton("Next");

        setLayout(new GridLayout(6, 1));
        add(questionLabel);

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            add(optionButtons[i]);
        }

        add(nextButton);
        nextButton.addActionListener(this);

        // Initialize the first question
        loadQuestion();

        // JFrame settings
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            String[] currentOptions = options[currentQuestionIndex];
            for (int i = 0; i < currentOptions.length; i++) {
                optionButtons[i].setText(currentOptions[i]);
            }
        } else {
            // Quiz is over, show score
            JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score + "/" + questions.length);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // Check the selected answer
            for (JRadioButton optionButton : optionButtons) {
                if (optionButton.isSelected()) {
                    if (optionButton.getText().equals(correctAnswers[currentQuestionIndex])) {
                        score++;
                    }
                    break;
                }
            }

            // Move to the next question
            currentQuestionIndex++;
            optionsGroup.clearSelection();
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}

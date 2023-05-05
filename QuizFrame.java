package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizFrame extends JFrame {

    private JTextField answerField;
    private JLabel questionLabel;
    private JButton submitButton;
    JLabel resultLabel;

    private List<Question> questions;
    private int currentQuestion;
    private int correctAnswers;

    
    public QuizFrame() {
        initQuestions();
        initUI();
    }

    private void initQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", "Paris"));
        questions.add(new Question("What is the capital of Germany?", "Berlin"));
        questions.add(new Question("What is the capital of Italy?", "Rome"));
    }

    private void initUI() {
        answerField = new JTextField(20);
        questionLabel = new JLabel();
        submitButton = new JButton("Submit");
        resultLabel = new JLabel();

        setLayout(new FlowLayout());

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = answerField.getText();
                if (questions.get(currentQuestion).isCorrect(answer)) {
                    correctAnswers++;
                    resultLabel.setText("Correct!");
                } else {
                    resultLabel.setText("Incorrect. The correct answer is: " + questions.get(currentQuestion).getAnswer());
                }
                currentQuestion = (currentQuestion + 1) % questions.size();
                questionLabel.setText(questions.get(currentQuestion).getQuestion());
                answerField.setText("");
            }
        });

        add(questionLabel);
        add(answerField);
        add(submitButton);
        add(resultLabel);

        currentQuestion = new Random().nextInt(questions.size());
        questionLabel.setText(questions.get(currentQuestion).getQuestion());

        setTitle("Quiz");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuizFrame ex = new QuizFrame();
                ex.setVisible(true);
            }
        });
    }

    class Question {
        private String question;
        private String answer;

        public Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public boolean isCorrect(String answer) {
            return this.answer.equalsIgnoreCase(answer);
        }
    }
    }
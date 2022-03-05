import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI {
    private static int generatedNumber;
    private static JFrame frame;
    private static int num_guesses = 0;
    private static final int INITIAL_SCORE = 1000;
    private static int maxScore = INITIAL_SCORE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Guess The Number");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setResizable(true);
                displayGame();
                frame.setVisible(true);
            }
        });
    }


    public static void displayGame(){
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JButton playAgain = new JButton("Play Again");
        playAgain.setForeground(Color.MAGENTA);
        JButton endGame = new JButton("Give up !");
        endGame.setForeground(Color.ORANGE);
        JButton enter = new JButton("Guess");
        JTextField numberField = new JTextField(10);
        JLabel enterNumber = new JLabel("Enter a number between 0 and 100");
        enterNumber.setForeground(Color.RED);
        JLabel bestScoreLabel = new JLabel("Best Score");
        bestScoreLabel.setForeground(Color.GREEN);
        JLabel bestScore = new JLabel("1000");
        bestScore.setForeground(Color.GREEN);
        JLabel guesses = new JLabel("Guesses");
        guesses.setForeground(Color.cyan);
        JLabel attemptCount = new JLabel("0");
        attemptCount.setForeground(Color.cyan);
        Random r = new Random();
        generatedNumber = r.nextInt(101);
        System.out.println(generatedNumber);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num_guesses++;
                attemptCount.setText(String.valueOf(num_guesses));
                frame.repaint();
                frame.setVisible(true);
                int numberEntered = Integer.parseInt(numberField.getText());
                if (numberEntered == generatedNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations !!! You guessed the right number.");
                    if (num_guesses < maxScore) {
                        maxScore = num_guesses;
                        bestScore.setText(String.valueOf(maxScore));
                    }
                    num_guesses = 0;
                    attemptCount.setText("0");
                    generatedNumber = r.nextInt(101);
                    frame.repaint();
                    frame.setVisible(true);
                } else if (numberEntered < generatedNumber) {
                    JOptionPane.showMessageDialog(null, "Try a larger number !!!");
                } else if (numberEntered > generatedNumber) {
                    JOptionPane.showMessageDialog(null, "Try a smaller number !!!");
                }
            }
        });
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatedNumber = r.nextInt(101);
                num_guesses = 0;
                attemptCount.setText("0");
                frame.repaint();
                frame.setVisible(true);
            }
        });
        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Better luck next time. The number was " + generatedNumber);
                generatedNumber = r.nextInt(101);
            }
        });
        centerPanel.add(enterNumber);
        centerPanel.add(numberField);
        centerPanel.add(enter);
        northPanel.add(bestScoreLabel);
        northPanel.add(bestScore);
        northPanel.add(guesses);
        northPanel.add(attemptCount);
        southPanel.add(playAgain);
        southPanel.add(endGame);
        frame.add(centerPanel);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.repaint();
        frame.setVisible(true);
    }


}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ISpyGameNoConsole {
    private JFrame frame;
    private JTextArea hintArea;
    private JTextField guessField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private int hintIndex = 0;

    String object = "apple";
    String[] hints = {
            "I spy with my little eye, something that is red.",
            "It is round and can be found in a kitchen.",
            "You can eat it, and it's often associated with teachers."
    };

    public ISpyGameNoConsole() {
        frame = new JFrame("I Spy Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        //hint area at the top
        hintArea = new JTextArea("Welcome to the 'I Spy' game  \n" + hints[hintIndex]);
        hintArea.setEditable(false);
        hintArea.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(hintArea, BorderLayout.NORTH);

        //input panel with guess field and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        guessField = new JTextField(20);
        submitButton = new JButton("Submit Guess");
        inputPanel.add(guessField);
        inputPanel.add(submitButton);

        frame.add(inputPanel, BorderLayout.CENTER);

        //feedback label at the bottom
        feedbackLabel = new JLabel("Enter your guess and press 'Submit Guess'.");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(feedbackLabel, BorderLayout.SOUTH);

        //button action
        submitButton.addActionListener(new SubmitButtonListener());

        //show frame
        frame.setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guess = guessField.getText().trim().toLowerCase();

            if (guess.equals(object)) {
                feedbackLabel.setText("Correct! The object was '" + object + "'.");
                submitButton.setEnabled(false); //disable button after winning
            } else {
                hintIndex ++;
                if (hintIndex < hints.length) {
                    hintArea.setText(hintArea.getText() + "\n" + hints[hintIndex]);
                    feedbackLabel.setText("Wrong guess. Try again!");
                } else {
                    feedbackLabel.setText("Out of hints! The object was '" + object + "'.");
                    submitButton.setEnabled(false); // Disable button after losing
                }
            }

            guessField.setText(""); // Clear the input field
        }
    }

    public static void main(String[] args) {
        //System.out.println("Hello world!");
        SwingUtilities.invokeLater(ISpyGameNoConsole::new);
    }
}
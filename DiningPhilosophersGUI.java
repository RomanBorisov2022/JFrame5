import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiningPhilosophersGUI extends JFrame {
    private Philosopher[] philosophers;
    private Fork[] forks;
    private JButton startButton;
    private JTextArea resultTextArea;



    public DiningPhilosophersGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dining Philosophers");
        setSize(400, 300);
        setLayout(new BorderLayout());

        startButton = new JButton("Start Dining");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDiningPhilosophers();
            }
        });

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
    }

    private void startDiningPhilosophers() {
        int numPhilosophers = 5;

        forks = new Fork[numPhilosophers];
        philosophers = new Philosopher[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers], resultTextArea);
            philosophers[i].start();
        }
    }
}


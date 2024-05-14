import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JButton submitButton;
    private JButton clearButton;

    private JButton original;
    private JFrame enclosingFrame;
    private BufferedImage goomba;

    public WelcomePanel(JFrame frame) {
        enclosingFrame = frame;
        try {
            goomba = ImageIO.read(new File("src/goomba.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textField = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        original = new JButton("Original");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(submitButton);
        add(clearButton);
        add(original);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        original.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString("Please enter your name:", 50, 30);
        g.drawImage(goomba, 200, 50, null);
        textField.setLocation(50, 50);
        submitButton.setLocation(20, 100);
        original.setLocation(100, 100);
        clearButton.setLocation(180, 100);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            if (buttonText.equals("Submit")) {
                String playerName = textField.getText();
                MainFrame f = new MainFrame(playerName);
                enclosingFrame.setVisible(false);
            } else if(buttonText.equals("Original")){
                String playerName = "Mario";
                MainFrame f = new MainFrame(playerName);
                enclosingFrame.setVisible(false);
            }
            else{
                textField.setText("");
            }
        }
    }
}

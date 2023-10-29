import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;

public class UserInterface extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    private JTextArea textArea;
    private Color initialGreenHue;

    public UserInterface() {
        // Create menu bar and menu
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        
        // Create menu items
        menuItem1 = new JMenuItem("Date and Time");
        menuItem2 = new JMenuItem("Save to log.txt");
        menuItem3 = new JMenuItem("Change Background Color");
        menuItem4 = new JMenuItem("Exit");
        
        // Add action listeners
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);
        
        // Add items to menu
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        
        // Add menu bar to frame
        setJMenuBar(menuBar);
        
        // Create and add text area
        textArea = new JTextArea();
        add(textArea);
        
        // Set frame 
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1) {
            textArea.setText(LocalDateTime.now().toString());
        } else if (e.getSource() == menuItem2) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == menuItem3) {
            if (initialGreenHue == null) {
                float hue = (float) Math.random();
                initialGreenHue = Color.getHSBColor(hue, 1.0f, 0.5f);
            }
            textArea.setBackground(initialGreenHue);
        } else if (e.getSource() == menuItem4) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new UserInterface();
    }
}

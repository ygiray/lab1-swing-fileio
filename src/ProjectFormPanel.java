import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectFormPanel extends JPanel{

       public ProjectFormPanel() {

           setLayout(new BorderLayout());

           JPanel formContainer = new JPanel();
           formContainer.setLayout(new GridLayout(3, 2, 10, 10));


           JLabel nameLabel = new JLabel("Project Name: ");
           JTextField nameField = new JTextField();

           JLabel devLabel = new JLabel("Developer: ");
           JTextField devField = new JTextField();

           JLabel typeLabel = new JLabel("Project Type: ");
           String[] types = {"Web App", "Mobile App", "Desktop App"};

           JComboBox<String> typeBox = new JComboBox<>(types);


           formContainer.add(nameLabel);
           formContainer.add(nameField);
           formContainer.add(devLabel);
           formContainer.add(devField);
           formContainer.add(typeLabel);
           formContainer.add(typeBox);


           JPanel buttonPanel = new JPanel();
           JButton saveButton = new JButton("Save");
           JButton clearButton = new JButton("Clear");


           buttonPanel.add(saveButton);
           buttonPanel.add(clearButton);


           add(formContainer, BorderLayout.CENTER);
           add(buttonPanel, BorderLayout.SOUTH);


           saveButton.addActionListener(new ActionListener() {
               @Override

               public void actionPerformed(ActionEvent e) {
                   String name = nameField.getText().trim();
                   String developer = devField.getText().trim();
                   String type = (String) typeBox.getSelectedItem();


                   if (name.isEmpty() || developer.isEmpty()) {
                       JOptionPane.showMessageDialog(null, "Error: Please fill the all required fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
                       return;
                   }

                   try (FileWriter writer = new FileWriter("projects.txt", true)) {
                       writer.write(name + ", " + developer + ", " + type + "\n");


                       JOptionPane.showMessageDialog(null,
                               "Project saved successfully!",
                               "Success",
                               JOptionPane.INFORMATION_MESSAGE);

                   } catch (IOException ex) {
                       System.out.println("Dosya hatası: " + ex.getMessage());
                   }
               }
           });

           clearButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   nameField.setText("");
                   devField.setText("");
                   typeBox.setSelectedIndex(0);
               }

           });

       }
}


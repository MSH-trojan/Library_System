package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Library_System extends JFrame {

    private JLabel l1;
    private JPanel p1, dynamicPanel;
    private CardLayout dynamicCard;
    private JButton bAdmin, bUser;

    public Library_System() {
        super("LibraCore");

        // --- Top-level layout ---
        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // --- Always-on label ---
        l1 = new JLabel("Good Morning, welcome to MoeLib!");
        l1.setFont(new Font("Arial", Font.BOLD, 18));
        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p1.add(l1);

        // --- Dynamic panel below label ---
        dynamicCard = new CardLayout();
        dynamicPanel = new JPanel(dynamicCard);
        dynamicPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Welcome Button Panel ---
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        bAdmin = new JButton("Admin Login");
        bUser = new JButton("User Login");

        bAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        bUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createVerticalStrut(20));
        welcomePanel.add(bAdmin);
        welcomePanel.add(Box.createVerticalStrut(10));
        welcomePanel.add(bUser);

        // --- Admin Login Panel ---
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField adminUser = new JTextField();
        JButton btnLogoutAdmin = new JButton("🚪 Logout");
        JPasswordField adminPass = new JPasswordField();
        JButton toggleAdminPass = new JButton("👁");
        JPanel adminPassPanel = new JPanel(new BorderLayout());
        adminPassPanel.add(adminPass, BorderLayout.CENTER);
        adminPassPanel.add(toggleAdminPass, BorderLayout.EAST);
        toggleAdminPass.addActionListener(e -> {
            if (adminPass.getEchoChar() == '\u0000') {
                adminPass.setEchoChar('•'); // Hide password
            } else {
                adminPass.setEchoChar((char) 0); // Show password
            }
        });

        
        JButton adminBack = new JButton("← Back");
        
        

        JButton adminLoginBtn = new JButton("Login");
        adminLoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = adminUser.getText();
                String password = new String(adminPass.getPassword());

                try {
                    Connection conn = DBConnection.getConnection();
                    String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        dynamicCard.show(dynamicPanel, "adminMenu");
                        
                     // Keep it here for now — later we’ll route to admin dashboard



                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials.");
                    }

                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel registerLabelAdmin = new JLabel("Not registered? Click below:");
        JButton registerAdminBtn = new JButton("Register");
        
       
        adminPanel.add(new JLabel("Username:"));
        adminPanel.add(adminUser);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(new JLabel("Password:"));
        adminPanel.add(adminPassPanel);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(adminLoginBtn);
        adminPanel.add(Box.createVerticalStrut(10));
        adminPanel.add(adminBack);
        adminPanel.add(Box.createVerticalStrut(20));
        adminPanel.add(registerLabelAdmin);
        adminPanel.add(registerAdminBtn);
        adminPanel.add(Box.createVerticalStrut(10));
        

        JPanel addBookPanel = new JPanel();
        addBookPanel.setLayout(new BoxLayout(addBookPanel, BoxLayout.Y_AXIS));
        addBookPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField isbnField = new JTextField();
        JButton addBookBtn = new JButton("Add Book");
        JButton backToAdminBtn = new JButton("← Back to Admin");

        addBookPanel.add(new JLabel("Title:"));
        addBookPanel.add(titleField);
        addBookPanel.add(Box.createVerticalStrut(10));

        addBookPanel.add(new JLabel("Author:"));
        addBookPanel.add(authorField);
        addBookPanel.add(Box.createVerticalStrut(10));

        addBookPanel.add(new JLabel("Genre:"));
        addBookPanel.add(genreField);
        addBookPanel.add(Box.createVerticalStrut(10));

        addBookPanel.add(new JLabel("Year:"));
        addBookPanel.add(yearField);
        addBookPanel.add(Box.createVerticalStrut(10));

        addBookPanel.add(new JLabel("ISBN:"));
        addBookPanel.add(isbnField);
        addBookPanel.add(Box.createVerticalStrut(20));

        addBookPanel.add(addBookBtn);
        addBookPanel.add(Box.createVerticalStrut(10));
        addBookPanel.add(backToAdminBtn);
        dynamicPanel.add(addBookPanel, "addBook");

        // --- User Login Panel ---
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField userEmail = new JTextField();
        JPasswordField userPass = new JPasswordField();
        JButton toggleUserPass = new JButton("👁");
        JPanel userPassPanel = new JPanel(new BorderLayout());
        userPassPanel.add(userPass, BorderLayout.CENTER);
        userPassPanel.add(toggleUserPass, BorderLayout.EAST);

        toggleUserPass.addActionListener(e -> {
            if (userPass.getEchoChar() == '\u0000') {
                userPass.setEchoChar('•');
            } else {
                userPass.setEchoChar((char) 0);
            }
        });
        JButton userBack = new JButton("← Back");
        JButton userLoginBtn = new JButton("Login");
        JLabel registerLabelUser = new JLabel("Not registered? Click below:");
        JButton registerUserBtn = new JButton("Register");
        registerUserBtn.addActionListener(e -> dynamicCard.show(dynamicPanel, "userRegister"));

        userPanel.add(new JLabel("Username:"));
        userPanel.add(userEmail);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(new JLabel("Password:"));
        userPanel.add(userPassPanel);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(userLoginBtn);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(userBack);
        userPanel.add(Box.createVerticalStrut(20));
        userPanel.add(registerLabelUser);
        userPanel.add(registerUserBtn);


        // --- Add all subpanels to dynamic card layout ---
        dynamicPanel.add(welcomePanel, "welcome");
        dynamicPanel.add(adminPanel, "adminLogin");
        dynamicPanel.add(userPanel, "userLogin");
     // --- User Registration Panel ---
        JPanel userRegisterPanel = new JPanel();
        userRegisterPanel.setLayout(new BoxLayout(userRegisterPanel, BoxLayout.Y_AXIS));
        userRegisterPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField usernameRegField = new JTextField();
        JPasswordField passwordRegField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        JButton registerConfirmBtn = new JButton("Register");
        JButton registerBackBtn = new JButton("← Back");

        registerConfirmBtn.addActionListener(e -> {
            String password = new String(passwordRegField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
                return;
            }

            // TODO: Add DB insert logic here
            JOptionPane.showMessageDialog(null, "Registration successful!");
        });

        registerBackBtn.addActionListener(e -> dynamicCard.show(dynamicPanel, "userLogin"));

        userRegisterPanel.add(new JLabel("First Name:")); userRegisterPanel.add(firstNameField);
        userRegisterPanel.add(new JLabel("Last Name:")); userRegisterPanel.add(lastNameField);
        userRegisterPanel.add(new JLabel("Username:")); userRegisterPanel.add(usernameRegField);
        userRegisterPanel.add(new JLabel("Password:")); userRegisterPanel.add(passwordRegField);
        userRegisterPanel.add(new JLabel("Confirm Password:")); userRegisterPanel.add(confirmPasswordField);
        userRegisterPanel.add(new JLabel("Email:")); userRegisterPanel.add(emailField);
        userRegisterPanel.add(new JLabel("Phone (+1 123-456-7890):")); userRegisterPanel.add(phoneField);
        userRegisterPanel.add(Box.createVerticalStrut(10));
        userRegisterPanel.add(registerConfirmBtn);
        userRegisterPanel.add(Box.createVerticalStrut(5));
        userRegisterPanel.add(registerBackBtn);

        dynamicPanel.add(userRegisterPanel, "userRegister");

        JPanel adminMenuPanel = new JPanel();
        adminMenuPanel.setLayout(new BoxLayout(adminMenuPanel, BoxLayout.Y_AXIS));
        adminMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel adminTitle = new JLabel("Admin Dashboard Menu");
        adminTitle.setFont(new Font("Arial", Font.BOLD, 18));
        adminTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Define the 5 action buttons
        JButton btnManageBooks = new JButton("📚 Manage Books");
        JButton btnManageMembers = new JButton("👥 Manage Members");
        JButton btnViewBorrowed = new JButton("📖 View Borrowed");
        JButton btnViewDonations = new JButton("🎁 View Donations");
        

        // Align buttons to center
        btnManageBooks.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnManageMembers.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnViewBorrowed.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnViewDonations.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogoutAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        adminMenuPanel.add(adminTitle);
        adminMenuPanel.add(Box.createVerticalStrut(20));
        adminMenuPanel.add(btnManageBooks);
        adminMenuPanel.add(Box.createVerticalStrut(10));
        adminMenuPanel.add(btnManageMembers);
        adminMenuPanel.add(Box.createVerticalStrut(10));
        adminMenuPanel.add(btnViewBorrowed);
        adminMenuPanel.add(Box.createVerticalStrut(10));
        adminMenuPanel.add(btnViewDonations);
        adminMenuPanel.add(Box.createVerticalStrut(10));
        adminMenuPanel.add(btnLogoutAdmin);


        dynamicPanel.add(adminMenuPanel, "adminMenu");
        btnLogoutAdmin.addActionListener(e -> {
            // Optional: reset login fields here
            // adminUser.setText(""); 
            // adminPass.setText("");

            dynamicCard.show(dynamicPanel, "welcome"); // Go back to welcome screen
        });

        p1.add(dynamicPanel); // Add dynamic part to the main panel

        add(p1);

        // --- Action Listeners to switch cards ---
        bAdmin.addActionListener(e -> dynamicCard.show(dynamicPanel, "adminLogin"));
        bUser.addActionListener(e -> dynamicCard.show(dynamicPanel, "userLogin"));
        adminBack.addActionListener(e -> dynamicCard.show(dynamicPanel, "welcome"));
        userBack.addActionListener(e -> dynamicCard.show(dynamicPanel, "welcome"));
        addBookBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.getConnection();
                String query = "INSERT INTO books (title, author, genre, year, isbn, status) VALUES (?, ?, ?, ?, ?, 'available')";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, titleField.getText());
                pst.setString(2, authorField.getText());
                pst.setString(3, genreField.getText());
                pst.setInt(4, Integer.parseInt(yearField.getText()));
                pst.setString(5, isbnField.getText());
                if (titleField.getText().isEmpty() || authorField.getText().isEmpty() ||
                	    genreField.getText().isEmpty() || yearField.getText().isEmpty() || isbnField.getText().isEmpty()) {
                	    JOptionPane.showMessageDialog(null, "All fields are required.");
                	    return;
                	}
                	try {
                	    Integer.parseInt(yearField.getText());
                	} catch (NumberFormatException ez) {
                	    JOptionPane.showMessageDialog(null, "Year must be a valid number.");
                	    return;
                	}


                pst.executeUpdate();
                conn.close();

                JOptionPane.showMessageDialog(null, "Book added successfully!");
                titleField.setText(""); authorField.setText(""); genreField.setText("");
                yearField.setText(""); isbnField.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Could not add book.");
            }
        });
        backToAdminBtn.addActionListener(e -> dynamicCard.show(dynamicPanel, "adminLogin"));


        // --- Final JFrame settings ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
        dynamicCard.show(dynamicPanel, "welcome");
        setVisible(true);
    }

    public static void main(String[] args) {
        new Library_System();
    }
}




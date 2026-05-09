import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SchoolGUI {
	private School school;
	private JFrame inputFrame;
	private JFrame resultFrame;
	private JComboBox<String> roleBox;
	private JTextField nameField;
	private JTextField idField;
	private JTextArea resultArea;
	
	public SchoolGUI(School school) {
		this.school = school;
		buildInputFrame();
		buildResultFrame();
	}
	
	public void show() {
		inputFrame.setVisible(true);
		resultFrame.setVisible(true);
	}
	
	private void buildInputFrame() {
		inputFrame = new JFrame("School User Input");
		inputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		inputFrame.setSize(360, 230);
		inputFrame.setLocation(150, 150);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(8, 8, 8, 8);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		DefaultComboBoxModel<String> roles = new DefaultComboBoxModel<>();
		roles.addElement("Student");
		roles.addElement("Teacher");
		roles.addElement("Admin");
		roles.addElement("Employee");
		roleBox = new JComboBox<>(roles);
		nameField = new JTextField(16);
		idField = new JTextField(16);
		JButton loginButton = new JButton("Log in");
		JButton savedInputButton = new JButton("Read saved input");
		
		addInputRow(panel, constraints, 0, "Role:", roleBox);
		addInputRow(panel, constraints, 1, "Name:", nameField);
		addInputRow(panel, constraints, 2, "ID:", idField);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(loginButton, constraints);
		
		constraints.gridx = 1;
		panel.add(savedInputButton, constraints);
		
		loginButton.addActionListener(new LoginButtonHandler());
		savedInputButton.addActionListener(new SavedInputButtonHandler());
		
		inputFrame.add(panel);
	}
	
	private void buildResultFrame() {
		resultFrame = new JFrame("School Results");
		resultFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		resultFrame.setSize(520, 320);
		resultFrame.setLocation(540, 150);
		
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setWrapStyleWord(true);
		
		resultFrame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
	}
	
	private void addInputRow(JPanel panel, GridBagConstraints constraints, int row, String label, java.awt.Component input) {
		constraints.gridx = 0;
		constraints.gridy = row;
		panel.add(new JLabel(label), constraints);
		
		constraints.gridx = 1;
		panel.add(input, constraints);
	}
	
	private void handleLogin() {
		String role = (String) roleBox.getSelectedItem();
		String name = nameField.getText().trim();
		String id = idField.getText().trim();
		
		try {
			if (name.isEmpty() || id.isEmpty()) {
				throw new IllegalArgumentException("Name and ID are required.");
			}
			
			school.saveUserInput(role, name, id);
			Person person = school.searchPersonById(id);
			
			if (person != null && person.getName().equalsIgnoreCase(name) && matchesRole(person, role)) {
				person.incrementLoginCount();
				school.saveLoginCount();
				showResult("Login successful.\n\n" + person + "\n" + person.getRoleInfo());
			}
			else {
				showResult("The name, ID, or role is incorrect.");
			}
		}
		catch(IllegalArgumentException e) {
			showResult(e.getMessage());
		}
		catch(IOException e) {
			showResult("Could not save user input: " + e.getMessage());
		}
	}
	
	private boolean matchesRole(Person person, String role) {
		if ("Student".equals(role)) {
			return person instanceof Student;
		}
		
		if ("Teacher".equals(role)) {
			return person instanceof Teacher;
		}
		
		if ("Admin".equals(role)) {
			return person instanceof AdminStaff;
		}
		
		return person instanceof Employee && !(person instanceof Teacher) && !(person instanceof AdminStaff);
	}
	
	private void showResult(String message) {
		resultArea.setText(message);
		resultFrame.setVisible(true);
	}
	
	private class LoginButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			handleLogin();
		}
	}
	
	private class SavedInputButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			showResult(school.readSavedUserInput());
		}
	}
}

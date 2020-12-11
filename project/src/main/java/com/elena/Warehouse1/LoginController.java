package com.elena.Warehouse1;

import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");

	private static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	@FXML
    private AnchorPane pane_login;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ComboBox type;

    @FXML
    private Button btn_login;
    
    @FXML
    private Button btn_signup;
    

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField txt_username_up;

    @FXML
    private TextField txt_password_up;

    @FXML
    private TextField email_up;

    @FXML
    private ComboBox type_up;

	@FXML
    private TextField number_up;
    
    private static Users userSave = new Users();
    
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public Users getUserSave() {
		return this.userSave;
	}

	public void setUserSave(Users userSave) {
		this.userSave = userSave;
	}
	
    public void loginPaneShow() {
    	pane_login.setVisible(true);
    	pane_signup.setVisible(false);
    }
    
    public void signupPaneShow() {
    	pane_login.setVisible(false);
    	pane_signup.setVisible(true);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		type_up.getItems().addAll("Admin");
		type.getItems().addAll("Admin", "Operator");
		

		
	}
	
	@FXML
	private void Login(ActionEvent event) throws Exception{
		conn = MySqlConnect.ConnectDb();
		String sql = "Select * from users where user_name = ? and ppassword = ? and role_id = ?";
		String usernameSave = txt_username.getText();
		UsersTable userTable = new UsersTable();
		this.userSave = userTable.getUser(usernameSave);
		
		try {
			
			pst =  conn.prepareStatement(sql);
			if(!txt_username.getText().isEmpty()) {
				logger.info("Entering username");
			}
			
			if(!txt_password.getText().isEmpty()) {
				logger.info("Entering password");
			}
			
			pst.setString(1, txt_username.getText());
			pst.setString(2, txt_password.getText());
			if(type.getValue().toString().equals("Admin")) {
			pst.setInt(3, 1);	
			} else {
			pst.setInt(3, 2);		
			}
			rs = pst.executeQuery();
		
	
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Username And Password Are Correct!");
				
				if(type.getValue().toString().equals("Admin")) {
					btn_login.getScene().getWindow().hide();
					AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("Administrator.fxml"));
					Stage mainStage = new Stage();
					Scene scene = new Scene(root);
					mainStage.setScene(scene);
					mainStage.show();
						logger.info("Login like Admin");
						
				} else if(type.getValue().toString().equals("Operator")) {
					btn_login.getScene().getWindow().hide();
					AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("Operator.fxml"));
					Stage mainStage = new Stage();
					Scene scene = new Scene(root);
					mainStage.setScene(scene);
					mainStage.show();
					logger.info("Login like Operator");
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Type !");
					logger.error("Invalid Type ");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Username, Password Or Type !");
				logger.error("Invalid Username, Password Or Type ");
			}
			
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Fill in all fields");
			logger.error("Null pointer exception ");
        }catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void addAdministrator(ActionEvent event) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		conn = MySqlConnect.ConnectDb();
		
			try {
				et = em.getTransaction();
				et.begin();
				String username = txt_username_up.getText();
				String password = txt_password_up.getText();
				String email = email_up.getText();
				String phoneNumber = number_up.getText();
				RoleTable roleTable = new RoleTable();
				
				Role role = roleTable.getRole(1);
				
				Users user = new Users();
				user.setName(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setPhoneNumber(phoneNumber);
				user.setRole(role);
				
				em.persist(user);
				et.commit();
				
				JOptionPane.showMessageDialog(null, "Saved!");
				logger.info("New Operator created ");
		
        }catch(Exception e) {
        	JOptionPane.showMessageDialog(null, "Fill in all fields");
			logger.error("Not all fields are filled ");
		}
	}
	}
	
	



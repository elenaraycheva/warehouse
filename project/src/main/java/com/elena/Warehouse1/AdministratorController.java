package com.elena.Warehouse1;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class AdministratorController implements Initializable{
	
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
	    @FXML
	    private TextField name_cash;
	
	    @FXML
	    private TextField money_cash;

	 	@FXML
	    private AnchorPane pane_create_op;

	    @FXML
	    private TextField email_op;

	    @FXML
	    private TextField username_op;

	    @FXML
	    private TextField number_op;

	    @FXML
	    private TextField firstname_op;

	    @FXML
	    private TextField lastname_op;

	    @FXML
	    private PasswordField password_op;

	    @FXML
	    private Button btn_create_op;

	    @FXML
	    private TextField firstname_sup;

	    @FXML
	    private TextField lastname_sup;

	    @FXML
	    private TextField number_sup;

	    @FXML
	    private TextField email_sup;

	    @FXML
	    private TextField company_sup;

	    @FXML
	    private TextField address_sup;
	    
	    @FXML
	    private TextField firstname_client;

	    @FXML
	    private TextField address_client;

	    @FXML
	    private TextField lastname_client;

	    @FXML
	    private TextField number_client;

	    @FXML
	    private TextField email_client;

	    @FXML
	    private TextField company_client;
	    
	    @FXML
	    private AnchorPane pane_create_sup;

	    @FXML
	    private AnchorPane pane_create_client;

	    @FXML
	    private AnchorPane pane_create_cash;
	    
	    @FXML
	    private AnchorPane pane_create_nomenclature;

	    @FXML
	    private TextField create_brand;

	    @FXML
	    private TextField create_type;

	    @FXML
	    private TextField create_role;


	public void CreateOperatorPaneShow() {
		pane_create_op.setVisible(true);
		pane_create_sup.setVisible(false);
		pane_create_client.setVisible(false);
		pane_create_cash.setVisible(false);
		pane_create_nomenclature.setVisible(false);
    }
	
	public void CreateSupplierPaneShow() {
		pane_create_op.setVisible(false);
		pane_create_sup.setVisible(true);
		pane_create_client.setVisible(false);
		pane_create_cash.setVisible(false);
		pane_create_nomenclature.setVisible(false);
    }
	
	public void CreateClientPaneShow() {
		pane_create_op.setVisible(false);
		pane_create_sup.setVisible(false);
		pane_create_client.setVisible(true);
		pane_create_cash.setVisible(false);
		pane_create_nomenclature.setVisible(false);
    }
	
	public void CreateCashRegisterPaneShow() {
		pane_create_op.setVisible(false);
		pane_create_sup.setVisible(false);
		pane_create_client.setVisible(false);
		pane_create_cash.setVisible(true);
		pane_create_nomenclature.setVisible(false);
    }
	
	public void CreateNomenclaturePaneShow() {
		pane_create_op.setVisible(false);
		pane_create_sup.setVisible(false);
		pane_create_client.setVisible(false);
		pane_create_cash.setVisible(false);
		pane_create_nomenclature.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void CreateOperator() {
		conn = MySqlConnect.ConnectDb();

			try {	
				String username = username_op.getText();
				String password = password_op.getText();
				String firstName = firstname_op.getText();
				String lastName = lastname_op.getText();
				String email = email_op.getText();
				String phoneNumber = number_op.getText();
				RoleTable roleTable = new RoleTable();
				Role role = roleTable.getRole(2);
				
				Users user = new Users();
				user.setName(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setPhoneNumber(phoneNumber);
				user.setRole(role);

				UsersTable usertable = new UsersTable();
				
				usertable.addUser(user);
				
				Operators operator = new Operators();
				operator.setFirstName(firstName);
				operator.setLastName(lastName);
				operator.setEmail(email);
				operator.setPhoneNumber(phoneNumber);
				operator.setUser(user);
				
				OperatorsTable operatorTable = new OperatorsTable();
				operatorTable.addOperator(operator);
				
				JOptionPane.showMessageDialog(null, "Saved!");		
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
	}
	
	
	public void CreateSupplier() {
		conn = MySqlConnect.ConnectDb();
		
			try {	
				String firstName = firstname_sup.getText();
				String lastName = lastname_sup.getText();
				String phoneNumber = number_sup.getText();
				String email = email_sup.getText();
				String company = company_sup.getText();
				String address = address_sup.getText();
	
				Supplier supplier = new Supplier();
				supplier.setFirstName(firstName);
				supplier.setLastName(lastName);
				supplier.setPhoneNumber(phoneNumber);
				supplier.setEmail(email);
				supplier.setCompany(company);
				supplier.setAddress(address);
				
				SupplierTable supplierTable = new SupplierTable();
				supplierTable.addSupplier(supplier);
				
				JOptionPane.showMessageDialog(null, "Saved!");	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void CreateClient() {
		conn = MySqlConnect.ConnectDb();
		
			try {
				
				String firstName = firstname_client.getText();
				String lastName = lastname_client.getText();
				String phoneNumber = number_client.getText();
				String email = email_client.getText();
				String company = company_client.getText();
				String address = address_client.getText();
	
				Clients client = new Clients();
				client.setFirstName(firstName);
				client.setLastName(lastName);
				client.setPhoneNumber(phoneNumber);
				client.setEmail(email);
				client.setCompany(company);
				client.setAddress(address);
				
				ClientsTable clientTable = new ClientsTable();
				clientTable.addClient(client);
				
				JOptionPane.showMessageDialog(null, "Saved!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void CreateCashRegister() {
		conn = MySqlConnect.ConnectDb();
		
			try {
				
				String name = name_cash.getText();
				double money = Double.parseDouble(money_cash.getText());
	
				CashRegister cashRegister = new CashRegister();
				cashRegister.setName(name);
				cashRegister.setMoney(money);
				
				CashRegisterTable cashRegisterTable = new CashRegisterTable();
				cashRegisterTable.addCashRegister(cashRegister);
				
				JOptionPane.showMessageDialog(null, "Saved!");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void CreateRole() {
		conn = MySqlConnect.ConnectDb();
		
			try {
				
				String role = create_role.getText();
				
				
				RoleTable roleTable = new RoleTable();
				roleTable.addRole(role);
				
				JOptionPane.showMessageDialog(null, "Saved!");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void CreateType() {
		conn = MySqlConnect.ConnectDb();
		
			try {
				
				String type = create_type.getText();
				
				
				TypeTable typeTable = new TypeTable();
				Type typeOb = new Type();
				typeOb.setType(type);
				typeTable.addType(typeOb);
				
				JOptionPane.showMessageDialog(null, "Saved!");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void CreateBrand() {
		conn = MySqlConnect.ConnectDb();
		
			try {
				
				String brand = create_brand.getText();
				
				Brands brandOb = new Brands();
				brandOb.setBrand(brand);
				
				BrandsTable brandTable = new BrandsTable();
				brandTable.addBrand(brandOb);
				
				JOptionPane.showMessageDialog(null, "Saved!");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
}

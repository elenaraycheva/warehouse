package com.elena.Warehouse1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TableProfitController  implements Initializable {

	  @FXML
	    private AnchorPane profit;

	    @FXML
	    private TableView<ProfitModel> table_profit;

	    @FXML
	    private TableColumn<ProfitModel, Double> col_income;

	    @FXML
	    private TableColumn<ProfitModel, Double> col_expence;

	    @FXML
	    private TableColumn<ProfitModel, Double> col_profit;
	    
	    @FXML
	    private TableColumn<ProfitModel, String> col_cash_register;

	    @FXML
	    private DatePicker from_date;

	    @FXML
	    private DatePicker to_date;
	    
	    ObservableList<ProfitModel> obList = FXCollections.observableArrayList();

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
		
		public void ShowRecords() {
			table_profit.getItems().clear();
			Connection conn = MySqlConnect.ConnectDb();
			LocalDate fromDate = from_date.getValue();
			LocalDate toDate = to_date.getValue();
			
			try {
				String sql1 = "select * from cash_register";
				Statement statement1 = conn.createStatement();
				ResultSet rs1 = statement1.executeQuery(sql1);
				
				double moneyInvoice = 0;
				double moneyDeliveryNote = 0;
				double moneyProfit = 0;
			
				while(rs1.next()) {
					//get Cash Register
					int id_cashRegister = rs1.getInt("id");
					CashRegisterTable cashRegisterTable = new CashRegisterTable();
					CashRegister cashRegister = cashRegisterTable.getCashRegister(id_cashRegister);
					
					//get list of invoices for period of time
					String sql2 = "select * from invoice where ddate >='" + fromDate + "' and ddate <= '" + toDate + "'";
					Statement statement2 = conn.createStatement();
					ResultSet rs2 = statement2.executeQuery(sql2);
					
					while(rs2.next()) {
						moneyInvoice += rs2.getDouble("total_price");
					}
					
					//get list of invoices for period of time
					String sql3 = "select * from delivery_notes where ddate >='" + fromDate + "' and ddate <= '" + toDate + "'";
					Statement statement3 = conn.createStatement();
					ResultSet rs3 = statement3.executeQuery(sql3);
					
					while(rs3.next()) {
						moneyDeliveryNote += rs3.getDouble("total_price");
					}
					
					moneyProfit = Math.round((moneyInvoice - moneyDeliveryNote)* 100.0) / 100.0;
					obList.add(new ProfitModel(Math.round(moneyInvoice* 100.0) / 100.0,
							Math.round(moneyDeliveryNote* 100.0) / 100.0, moneyProfit, cashRegister.getName()));
				}
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			
			
			col_income.setCellValueFactory(new PropertyValueFactory<>("income"));
			col_expence.setCellValueFactory(new PropertyValueFactory<>("expence"));
			col_profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
			col_cash_register.setCellValueFactory(new PropertyValueFactory<>("cashRegister"));
			table_profit.setItems(obList);
		}
}

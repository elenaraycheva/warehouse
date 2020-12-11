package com.elena.Warehouse1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

public class TableOperatorController implements Initializable{

	@FXML
    private AnchorPane operator;

    @FXML
    private TableView<OperatorModel> operator_table;

    @FXML
    private TableColumn<OperatorModel, String > col_first_name;

    @FXML
    private TableColumn<OperatorModel, String> col_last_name;

    @FXML
    private TableColumn<OperatorModel, String> col_date;

    @FXML
    private TableColumn<OperatorModel, String> col_part;

    @FXML
    private DatePicker from_date;

    @FXML
    private DatePicker to_date;    

    ObservableList<OperatorModel> obList = FXCollections.observableArrayList();
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void ShowRecords() {
		operator_table.getItems().clear();
		Connection conn = MySqlConnect.ConnectDb();
		LocalDate fromDate = from_date.getValue();
		LocalDate toDate = to_date.getValue();
		try {
			String sql = "select * from invoice where ddate >='" + fromDate + "' and ddate <= '" + toDate + "';";
			Statement statement1 = conn.createStatement();
			ResultSet rs = statement1.executeQuery(sql);
				
			while(rs.next()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String date = dateFormat.format(rs.getDate("ddate"));	
				
				int id_operator = rs.getInt("operator_id");
				OperatorsTable operatorTable = new OperatorsTable();
				Operators operator = operatorTable.getOperator(id_operator);
		
				obList.add(new OperatorModel(operator.getFirstName(), operator.getLastName(),date, "invoice"));	
		}
			
		String sql1 = "select * from delivery_notes where ddate >='" + fromDate + "' and ddate <= '" + toDate + "'";
		Statement statement2 = conn.createStatement();
		 rs = statement2.executeQuery(sql1);
		
		while(rs.next()) {
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");  
			String date1 = dateFormat1.format(rs.getDate("ddate"));
					
			int id_operator1 = rs.getInt("operator_id");
			OperatorsTable operatorTable1 = new OperatorsTable();
			Operators operator1 = operatorTable1.getOperator(id_operator1);
				
			obList.add(new OperatorModel(operator1.getFirstName(), operator1.getLastName(), date1, "delivery note"));
		}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_part.setCellValueFactory(new PropertyValueFactory<>("part"));
		operator_table.setItems(obList);
	}
}

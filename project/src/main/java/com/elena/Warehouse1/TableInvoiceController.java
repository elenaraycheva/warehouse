package com.elena.Warehouse1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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

public class TableInvoiceController implements Initializable{
	@FXML
    private AnchorPane invoice;

    @FXML
    private TableView<InvoiceModel> table_invoice;

    @FXML
    private TableColumn<InvoiceModel, String> col_date;

    @FXML
    private TableColumn<InvoiceModel, String> col_client;

    @FXML
    private TableColumn<InvoiceModel, String> col_operator;

    @FXML
    private DatePicker from_date;

    @FXML
    private DatePicker to_date;
    
    ObservableList<InvoiceModel> obList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void ShowRecords() {
		table_invoice.getItems().clear();
		Connection conn = MySqlConnect.ConnectDb();
		LocalDate fromDate = from_date.getValue();
		LocalDate toDate = to_date.getValue();
		try {
			String sql = "select * from invoice where ddate >='" + fromDate + "' and ddate <= '" + toDate + "'";
		ResultSet rs = conn.createStatement().executeQuery(sql);
		
		while(rs.next()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String date = dateFormat.format(rs.getDate("ddate"));
			int id_client = rs.getInt("clients_id");
			ClientsTable clientTable = new ClientsTable();
			Clients client = clientTable.getClient(id_client);
			System.out.println(client.getFirstName());
			
			int id_operator = rs.getInt("operator_id");
			OperatorsTable operatorTable = new OperatorsTable();
			Operators operator = operatorTable.getOperator(id_operator);
			System.out.println(operator.getFirstName());
			
			obList.add(new InvoiceModel(date, client.getFirstName(), operator.getFirstName()));
		}
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_client.setCellValueFactory(new PropertyValueFactory<>("client"));
		col_operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
		table_invoice.setItems(obList);

	}


}

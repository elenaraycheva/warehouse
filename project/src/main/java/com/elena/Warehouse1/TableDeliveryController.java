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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TableDeliveryController implements Initializable{


	@FXML
	private AnchorPane delivery;

    @FXML
    private TableView<DeliveryNoteModel> table_delivery;

    @FXML
    private TableColumn<DeliveryNoteModel, String> col_date;

    @FXML
    private TableColumn<DeliveryNoteModel, String> col_supplier;

    @FXML
    private TableColumn<DeliveryNoteModel, String> col_operator;
    
    @FXML
    private DatePicker from_date;

    @FXML
    private DatePicker to_date;
    		
    
    ObservableList<DeliveryNoteModel> obList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	
	public void ShowRecords() {
		table_delivery.getItems().clear();
		Connection conn = MySqlConnect.ConnectDb();
		LocalDate fromDate = from_date.getValue();
		LocalDate toDate = to_date.getValue();
		try {
			String sql = "select * from delivery_notes where ddate >='" + fromDate + "' and ddate <= '" + toDate + "'";
		ResultSet rs = conn.createStatement().executeQuery(sql);
			
		while(rs.next()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String date = dateFormat.format(rs.getDate("ddate"));
			int id_supplier = rs.getInt("supplier_id");
			SupplierTable suptable = new SupplierTable();
			Supplier supplier = suptable.getSupplier(id_supplier);
			
			int id_operator = rs.getInt("operator_id");
			OperatorsTable operatorTable = new OperatorsTable();
			Operators operator = operatorTable.getOperator(id_operator);
			
			obList.add(new DeliveryNoteModel(date, supplier.getFirstName(), operator.getFirstName()));
		}
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
		col_operator.setCellValueFactory(new PropertyValueFactory<>("operator"));
		table_delivery.setItems(obList);		
	}
	
	
	

}


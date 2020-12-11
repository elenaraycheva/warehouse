package com.elena.Warehouse1;


import java.net.URL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OperatorController implements Initializable{

	double newMoney = 0;
	double newMoney1 = 0;
	Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    List<InvoiceItems> invoiceItems = new ArrayList();
    List<DeliveryNoteItems> deliveryNoteItems = new ArrayList();
    
    List<Double> invoiceItemsAmount = new ArrayList();
    List<Double> deliveryNoteItemsAmount = new ArrayList();
    private static double cashRegisterValue = 0;
    
    @FXML
    private Button btn_create_delivery_note;
    
    @FXML
    private AnchorPane invoice_pane;
    
    @FXML
    private AnchorPane new_stock_pane;
    
    @FXML
    private TextField name_stock;

    @FXML
    private TextField amount_stock;

    @FXML
    private TextField type_stock;

    @FXML
    private TextField brand_stock;
    
    @FXML
    private TextField price_for_buying;
    
    @FXML
    private TextField phone_client_invoice;

    @FXML
    private TextField Stock_name_invoice;

    @FXML
    private TextField Brand_Invoice;

    @FXML
    private TextField amount_invoice;
    
    @FXML
    private TextField cash_register_invoice;

    @FXML
    private AnchorPane delivery_note_pane;

    @FXML
    private TextField phone_number_supplier;

    @FXML
    private TextField Stock_name_delivery_note;

    @FXML
    private TextField Brand_delivery_note;

    @FXML
    private TextField amount_delivery_note;

    @FXML
    private TextField cash_register_delivery_note;
    
    @FXML
    private AnchorPane search_pane;

    @FXML
    private AnchorPane search_stock_pane;

    @FXML
    private AnchorPane search_cash_register_pane;
    
    @FXML
    private Label stock_name_label;
    
    @FXML
    private Label brand_stock_label;

    @FXML
    private Label type_stock_label;

    @FXML
    private Label price_stock_label;

    @FXML
    private Label amount_stock_label;
    
    @FXML
    private TextField search_stock_name;

    @FXML
    private TextField search_stock_brand;
    
    @FXML
    private TextField search_cash_register_name;

    @FXML
    private Label name_cash_register_label;

    @FXML
    private Label money_cash_register_label;
    
    @FXML
    private AnchorPane reference_pane;
    
    @FXML
    private Button btn_delivery;
    
	public void CreateNewStockPaneShow() {
		new_stock_pane.setVisible(true);
		invoice_pane.setVisible(false);
		delivery_note_pane.setVisible(false);
		search_pane.setVisible(false);
		search_stock_pane.setVisible(false);
		search_cash_register_pane.setVisible(false);
		reference_pane.setVisible(false);
    }
	
	public void CreateInvoicePaneShow() {
		new_stock_pane.setVisible(false);
		invoice_pane.setVisible(true);
		delivery_note_pane.setVisible(false);
		search_pane.setVisible(false);
		search_stock_pane.setVisible(false);
		search_cash_register_pane.setVisible(false);
		reference_pane.setVisible(false);

    }
	
	public void CreateDeliveryNotePaneShow() {
		new_stock_pane.setVisible(false);
		invoice_pane.setVisible(false);
		delivery_note_pane.setVisible(true);
		search_pane.setVisible(false);
		search_stock_pane.setVisible(false);
		search_cash_register_pane.setVisible(false);
		reference_pane.setVisible(false);
	}
	
	public void SearchPaneShow() {
		new_stock_pane.setVisible(false);
		invoice_pane.setVisible(false);
		delivery_note_pane.setVisible(false);
		search_pane.setVisible(true);
		search_stock_pane.setVisible(false);
		search_cash_register_pane.setVisible(false);
		reference_pane.setVisible(false);
	}
	
	public void SearchStockPaneShow() {
		new_stock_pane.setVisible(false);
		invoice_pane.setVisible(false);
		delivery_note_pane.setVisible(false);
		search_pane.setVisible(true);
		search_stock_pane.setVisible(true);
		search_cash_register_pane.setVisible(false);
		reference_pane.setVisible(false);
	}
	
	public void SearchCashRegisterPaneShow() {
		new_stock_pane.setVisible(false);
		invoice_pane.setVisible(false);
		delivery_note_pane.setVisible(false);
		search_pane.setVisible(true);
		search_stock_pane.setVisible(false);
		search_cash_register_pane.setVisible(true);
		reference_pane.setVisible(false);
	}
	
	public void ReferencePaneShow() {
		new_stock_pane.setVisible(false);
		invoice_pane.setVisible(false);
		delivery_note_pane.setVisible(false);
		search_pane.setVisible(false);
		search_stock_pane.setVisible(false);
		search_cash_register_pane.setVisible(true);
		reference_pane.setVisible(true);
	}
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	
	
	public void CreateStock() {
		conn = MySqlConnect.ConnectDb();
		try {
			String name = name_stock.getText();
			double amount = Double.parseDouble(amount_stock.getText());
			String stockBrand = brand_stock.getText();
			String stockType = type_stock.getText();
			double priceForBuying = Double.parseDouble(price_for_buying.getText());
			
			Brands brand = new Brands();
			BrandsTable tableBrand = new BrandsTable();
			if(tableBrand.brandExists(stockBrand)) {
				brand = tableBrand.getBrand(stockBrand);	
			}else {
				brand.setBrand(stockBrand);
				tableBrand.addBrand(brand);
			}	
			Type type = new Type();
			TypeTable tableType = new TypeTable();
			
			if(tableType.typeExists(stockType)) {
				type = tableType.getType(stockType);	
			}else {
				type.setType(stockType);
				tableType.addType(type);
			}			
			Stocks stock = new Stocks();
			stock.setName(name);
			stock.setAmount(amount);
			stock.setBrand(brand);
			stock.setType(type);
			stock.setPriceForBuying(priceForBuying);
			StocksTable stockTable = new StocksTable();
			stockTable.addStock(stock);			
			JOptionPane.showMessageDialog(null, "Saved!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void AddInvoiceItems() {
		//get Stock
		String stockName = Stock_name_invoice.getText();
		String brandName = Brand_Invoice.getText();
		
		BrandsTable brandsTable = new BrandsTable();
		Brands brand = brandsTable.getBrand(brandName);
		
		StocksTable stockTable = new StocksTable();
		Stocks stock = stockTable.getStock(stockName, brand);
		
		//get Amount
		double amount = Double.parseDouble(amount_invoice.getText());
		if(amount > stock.getAmount()) {
			JOptionPane.showMessageDialog(null, "Not enough amount of " + stock.getName());
		} else {
			if(stock.getAmount() - amount < 10) {
				JOptionPane.showMessageDialog(null, "There are less then 10 entities of " + stock.getName());
			}
			invoiceItemsAmount.add(amount);
			
			InvoiceItems invoiceItem = new InvoiceItems();
			invoiceItem.setStock(stock);
			invoiceItem.setAmountDelivery(amount);
			
			this.invoiceItems.add(invoiceItem);
			JOptionPane.showMessageDialog(null, "Added!");
		}
	}
	
	
	public void AddDeliveryNoteItems() {
		//get Stock
		String stockName = Stock_name_delivery_note.getText();
		String brandName = Brand_delivery_note.getText();
		
		BrandsTable brandsTable = new BrandsTable();
		Brands brand = brandsTable.getBrand(brandName);
		
		StocksTable stockTable = new StocksTable();
		Stocks stock = stockTable.getStock(stockName, brand);
		
		//get Amount
		double amount = Double.parseDouble(amount_delivery_note.getText());
		deliveryNoteItemsAmount.add(amount);
		
		DeliveryNoteItems deliveryNoteItem = new DeliveryNoteItems();
		deliveryNoteItem.setStock(stock);
		deliveryNoteItem.setAmountDelivery(amount);
		
		this.deliveryNoteItems.add(deliveryNoteItem);
		JOptionPane.showMessageDialog(null, "Added!");
	}
	
	
	public void CreateDeliveryNote() {
		
		LoginController login = new LoginController();
		Users user =login.getUserSave();
	
		OperatorsTable operatorsTable = new OperatorsTable();
		Operators operator = operatorsTable.getOperatorByUser(user); 
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now); // take date
		
		String supplierPhoneNumber = phone_number_supplier.getText();
		SupplierTable supplierTable = new SupplierTable();
		Supplier supplier = supplierTable.getSupplier(supplierPhoneNumber); 
		
		//Create delivery note
		DeliveryNotes deliveryNote = new DeliveryNotes();
		deliveryNote.setOperator(operator);
		deliveryNote.setSupplier(supplier);
		deliveryNote.setDate(date);
		
		String cashRegisterName = cash_register_delivery_note.getText();
		CashRegisterTable cashRegisterTable = new CashRegisterTable();
		CashRegister cashRegister =cashRegisterTable.getCashRegister(cashRegisterName);
		
		for(int i = 0; i < deliveryNoteItems.size(); i++) {   
			deliveryNoteItems.get(i).setDeliveryNote(deliveryNote);
		    newMoney1 += (deliveryNoteItems.get(i).getStock().getPriceForBuying() *
		    		deliveryNoteItems.get(i).getAmountDelivery());
		} 
		if(cashRegister.getMoney() - newMoney1 < 0) {
			JOptionPane.showMessageDialog(null, "Not enough money in this cash register");
		} else {
			if(cashRegister.getMoney() - newMoney1 < 100) {
				JOptionPane.showMessageDialog(null, "Only " 
			+Math.round((cashRegister.getMoney() - newMoney1) * 100.0) / 100.0 + " in this cash register");
			}
		deliveryNote.setTotalPrice(newMoney1);
		deliveryNote.setDeliveryNoteItems(deliveryNoteItems);
		
		DeliveryNotesTable deliveryNotesTable = new DeliveryNotesTable();
		deliveryNotesTable.addDeliveryNote(deliveryNote);
		
		// add money to the cash register
		CashRegister cashRegisterNew = new CashRegister();
		cashRegisterNew.setMoney(cashRegister.getMoney() - newMoney1);
		cashRegisterNew.setName(cashRegisterName);
		
		cashRegisterTable.updateCashRegister(cashRegister.getId(), cashRegisterNew);
		 cashRegisterValue = cashRegister.getMoney();
		for(int i = 0; i < deliveryNoteItems.size(); i++) {   // update stocks with new amounts
			Stocks stock = deliveryNoteItems.get(i).getStock();
			Stocks newStock = new Stocks();
			newStock.setName(deliveryNoteItems.get(i).getStock().getName());
			newStock.setBrand(deliveryNoteItems.get(i).getStock().getBrand());
			newStock.setType(deliveryNoteItems.get(i).getStock().getType());
			newStock.setPriceForBuying(deliveryNoteItems.get(i).getStock().getPriceForBuying());
			newStock.setAmount(deliveryNoteItemsAmount.get(i) + stock.getAmount() );
			
			StocksTable stocksTable = new StocksTable();
			stocksTable.updateStock(stock.getId(), newStock);
		} 
		JOptionPane.showMessageDialog(null, Math.round(newMoney1 * 100.0) / 100.0 
				+ " are withdrawn from " + cashRegister.getName());
		}
	}
	
	
public void CreateInvoice() {
	    
		
		LoginController login = new LoginController();
		Users user =login.getUserSave();
	
		OperatorsTable operatorsTable = new OperatorsTable();
		Operators operator = operatorsTable.getOperatorByUser(user); // find operator for invoice
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now); // take date
		
		String clientPhoneNumber = phone_client_invoice.getText();
		ClientsTable clientsTable = new ClientsTable();
		Clients client = clientsTable.getClient(clientPhoneNumber);
		
		//Create invoice
		Invoice invoice = new Invoice();
		invoice.setOperator(operator);
		invoice.setClient(client);
		invoice.setDate(date);
		
		for(int i = 0; i < invoiceItems.size(); i++) {   
		    invoiceItems.get(i).setInvoice(invoice);
		    newMoney += ((invoiceItems.get(i).getStock().getPriceForBuying() + 
		    		0.2*invoiceItems.get(i).getStock().getPriceForBuying())
		    		* invoiceItems.get(i).getAmountDelivery());
		}
		invoice.setTotalPrice(newMoney);
		invoice.setInvoiceItems(invoiceItems);
		
		InvoiceTable invoiceTable = new InvoiceTable();
		invoiceTable.addInvoice(invoice);
		
		// add money to the cash register
		String cashRegisterName = cash_register_invoice.getText();
		
		CashRegisterTable cashRegisterTable = new CashRegisterTable();
		CashRegister cashRegister =cashRegisterTable.getCashRegister(cashRegisterName);
		
		CashRegister cashRegisterNew = new CashRegister();
		cashRegisterNew.setMoney(cashRegister.getMoney() + newMoney);
		cashRegisterNew.setName(cashRegisterName);
		
		cashRegisterTable.updateCashRegister(cashRegister.getId(), cashRegisterNew);
		
		for(int i = 0; i < invoiceItems.size(); i++) {   // update stocks with new amounts
			Stocks stock = invoiceItems.get(i).getStock();
			Stocks newStock = new Stocks();
			newStock.setName(invoiceItems.get(i).getStock().getName());
			newStock.setBrand(invoiceItems.get(i).getStock().getBrand());
			newStock.setType(invoiceItems.get(i).getStock().getType());
			newStock.setPriceForBuying(invoiceItems.get(i).getStock().getPriceForBuying());
			newStock.setAmount(stock.getAmount() - invoiceItemsAmount.get(i));
			
			StocksTable stocksTable = new StocksTable();
			stocksTable.updateStock(stock.getId(), newStock);
		    
		} 
		
		JOptionPane.showMessageDialog(null, Math.round(newMoney * 100.0) / 100.0 + " are stashed in " + cashRegister.getName());
	

	}



	public void SearchStock() {
		String stockName = search_stock_name.getText();
		String brandName = search_stock_brand.getText();
		
		BrandsTable brandsTable = new BrandsTable();
		Brands brand = brandsTable.getBrand(brandName);
		
		
		
		StocksTable stockTable = new StocksTable();
		Stocks stock = stockTable.getStock(stockName, brand);
		
		BrandsTable brandTable = new BrandsTable();
		Brands brandOb = brandTable.getBrand(stock.getBrand().getId());
		
		TypeTable typeTable = new TypeTable();
		Type typeOb = typeTable.getType(stock.getType().getId());
		
		
		stock_name_label.setText(stock.getName());
		brand_stock_label.setText(brandOb.getBrand());
		type_stock_label.setText(typeOb.getType());
		price_stock_label.setText(String.valueOf(stock.getPriceForBuying()));
		amount_stock_label.setText(String.valueOf(stock.getAmount()));

	
	}
	
	public void SearchCashRegister() {
		String cashRegisterName = search_cash_register_name.getText();
		
		CashRegisterTable cashRegisterTable = new CashRegisterTable();
		CashRegister cashRegister = cashRegisterTable.getCashRegister(cashRegisterName);

		
		name_cash_register_label.setText(cashRegister.getName());
		money_cash_register_label.setText(String.valueOf(cashRegister.getMoney()));

	
	}
	
	public void ShowTableDelivery() {
		try {
			AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("TableDNote.fxml"));
			Stage mainStage = new Stage();
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void ShowTableInvoice() {
		try {
			AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("TableInvoice.fxml"));
			Stage mainStage = new Stage();
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void ShowTableOperator() {
		try {
			AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("TableOperator.fxml"));
			Stage mainStage = new Stage();
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void ShowTableProfit() {
		try {
			AnchorPane root =(AnchorPane) FXMLLoader.load(getClass().getResource("TableProfit.fxml"));
			Stage mainStage = new Stage();
			Scene scene = new Scene(root);
			mainStage.setScene(scene);
			mainStage.show();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}


package com.example.demo;

import database.DatabaseManager;
import handlers.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class DemoApplication {
	private static Handler handler;
	private static DatabaseManager dbm;

	public static void main(String[] args) {
		dbm = new DatabaseManager();
		handler = new Handler(dbm);
		if (!dbm.databaseExists())
			dbm.createDatabase();
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/registerItem")
	public ResponseEntity<String> registerItem(
			@RequestParam String name,
			@RequestParam int quantity
	) {
		try {
			handler.registerNewItem(name,quantity);
		} catch (SQLException e) {
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("Item registered");
		return ResponseEntity.ok("Datos recibidos");
	}

	@GetMapping("/deleteItem")
	public ResponseEntity<String> deleteItem(
			@RequestParam String name
	) {
		try {
			System.out.println("Trying to delete item");
			handler.deleteItem(name);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("Item deleted");
		return ResponseEntity.ok("Datos recibidos");
	}

	@GetMapping("/getAllItems")
	public List<String[]> getAllItems() {
		List<String[]> toReturn = null;
		try {
			toReturn = dbm.getAllItems();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("All items retrived "+toReturn);
		return toReturn;
	}

	@GetMapping("/registerPayment")
	public ResponseEntity<String> registerPayment(
			@RequestParam String name,
			@RequestParam int quantity
	) {
		try {
			handler.registerPayment(name,quantity);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("Payment registered");
		return ResponseEntity.ok("Datos recibidos");
	}

	@GetMapping("/deletePayment")
	public ResponseEntity<String> deletePayment(
			@RequestParam String cause,
			@RequestParam Date date
	) {
		try {
			System.out.println("Trying to delete item");
			handler.deletePayment(cause,date);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("Payment deleted");
		return ResponseEntity.ok("Datos recibidos");
	}

	@GetMapping("/getAllPayments")
	public List<String[]> getAllPayments() {
		List<String[]> toReturn = null;
		try {
			toReturn = dbm.getAllPayments();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("All payments retrived "+toReturn.toString());
		return toReturn;
	}

	@GetMapping("/registerIncome")
	public ResponseEntity<String> registerIncome(
			@RequestParam String name,
			@RequestParam int quantity
	) {
		try {
			handler.registerIncome(name,quantity);
		} catch (SQLException e) {
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("Income registered");
		return ResponseEntity.ok("Datos recibidos");
	}

	@GetMapping("/deleteIncome")
	public ResponseEntity<String> deleteIncome(
			@RequestParam String cause,
			@RequestParam Date date
	) {
		try {
			System.out.println("Trying to delete item");
			handler.deleteIncome(cause,date);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("Income deleted");
		return ResponseEntity.ok("Datos recibidos");
	}

	@GetMapping("/getAllIncomes")
	public List<String[]> getAllIncomes() {
		List<String[]> toReturn = null;
		try {
			toReturn = dbm.getAllIncomes();
		} catch (SQLException e) {
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		System.out.println("All payments retrived "+toReturn.toString());
		return toReturn;
	}


}

package test;


import java.util.Scanner;

import localStorageImpl.LocalStorageImpl;
import storage.Permissions;
import storage.Storage;
import storage.StorageManager;
import storage.User;

public class Test {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Kreiranje SuperAdmina..");
		System.out.println("Unesite username: ");
		String username = sc.next();
		System.out.println("Unesite sifru: ");
		String password = sc.next();
		User user = new User(username,password);
		user.getPrivileges().put(Permissions.delete, true);
		user.getPrivileges().put(Permissions.record, true);
		user.getPrivileges().put(Permissions.download, true);
		user.getPrivileges().put(Permissions.preview, true);
		user.getPrivileges().put(Permissions.create, true);
		System.out.println("Uspesno ste kreirali admina. Admin ima sve privilegije!");
		try {
			
			Class.forName("googledriveapi.GoogleDriveImpl");
			Storage storage = StorageManager.getStorage();
			storage.preview();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}

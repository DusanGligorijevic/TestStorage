package test;


import java.util.Scanner;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;

import localStorageImpl.LocalStorageImpl;
import storage.Permissions;
import storage.Storage;
import storage.StorageManager;
import storage.User;

public class Test {
	
	public static void main(String[] args) {
		try {	
			//Class.forName("localStorageImpl.LocalStorageImpl");
			Class.forName("localStorageImpl.LocalStorageImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		Storage storage = StorageManager.getStorage();
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
		System.out.println("Uspesno ste kreirali SuperAdmina. SuperAdmin ima sve privilegije!");
		System.out.println("Unesite putanju do skladista.");
		String path =sc.next();
		storage.initialise(user,path);
		while(true) {
			String input = sc.nextLine();
			String[] params = input.split("\\s+");
			switch(params[0]) {
			case "create":
					switch(params[1]) {
					case "files": 
							if(storage.check(params[2], params[3]))
							storage.createFiles(params[2], params[3], Integer.parseInt(params[4]));
							break;
					case "file":
							if(storage.check(params[2], params[3]))
							storage.createFile(params[2], params[3]);
							break;
					case "folders":
							if(storage.check(params[2], params[3]))
							storage.createFolders(params[2], params[3], params[4]);
							break;
					case "folder":
							if(storage.check(params[2], params[3]))
							storage.createFolder(params[2], params[3]);
							break;
					case "user":
						if(storage.getConnectedUser().getPrivileges().get(Permissions.create)) {
						User u = storage.getConnectedUser().createUser();
						storage.addUser(u);
						}else
							System.out.println("Ulogovani korisnik nema dozvolu za kreiranje drugih korisnika.");
						break;
					}
					break;
			case "disconnect":
					storage.disconnect(storage.getConnectedUser());
					break;
			case "connect":
					storage.connect(params[1],params[2]);
					break;
			case "delete":
					storage.delete(params[1]);
					break;
			case "transfer":
					storage.transfer(params[1], params[2], params[3]);
					break;
			case "download":
					storage.download(params[1]);
					break;
			case "preview":
					switch(params[1]) {
					case "dir":
						storage.previewDir(params[2]);
						break;
					case "all":
						storage.previewAll(params[2]);
						break;
					case "ext":
						storage.previewExt(params[2], params[3]);
						break;
					}
					break;
			
			}
			
			if(params[0].contentEquals("0"))
				break;
			
		}
		
	}

}

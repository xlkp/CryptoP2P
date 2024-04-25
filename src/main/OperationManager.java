package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import resources.Menus;

public class OperationManager {
	private ArrayList<User> allUsers = new ArrayList<>();
	private ArrayList<TransactionCrypto> cryptoTransactions = new ArrayList<>();
	private ArrayList<TransactionCurrency> currencyTransactions = new ArrayList<>();
	static Scanner input = new Scanner(System.in);

	// user options
	public void createUser() {
		User user = new User();
		System.out.print("What's your name: ");
		user.setUsername(input.nextLine());
		System.out.println("Set your password");
		user.setPassword(input.nextLine());
		allUsers.add(user);
		createWallets(user);
	}

	public void createWallets(User user) {
		Coin[] allCoins = Coin.values();
		user.userWallets = new Wallet[allCoins.length];

		for (int i = 0; i < allCoins.length; i++) {
			user.userWallets[i] = new Wallet(user, allCoins[i]);
			user.userWallets[i].setWealth(10.0);
		}
	}

	public void seeUsers() {
		if (allUsers.isEmpty()) {
			System.out.println("No users created yet");
			System.out.println("Let's create one!!!");
			createUser();
		} else {
			for (User user : allUsers) {
				System.out.println(user);
			}
		}
	}

	public void deleteUser() {
		Iterator<User> iteratorUser = allUsers.iterator();
		boolean userDeleted = false;
		if (allUsers.isEmpty()) {
			System.out.println("there are no users created, go and create one");
		} else {
			System.out.println("So... you want to delete someone huh?");
			System.out.println("Then if his password and username are correct");
			System.out.println("I will delete that user, but it's permanentely deleted remeber");

			System.out.print("USERNAME: ");
			String name = input.nextLine();
			System.out.println("PASSWORD: ");
			String pass = input.nextLine();

			while (iteratorUser.hasNext()) {
				User user = iteratorUser.next();
				if (user.getUsername().equals(name) && user.getPassword().equals(pass)) {
					iteratorUser.remove();
					System.out.println("user " + user + " has been deleted");
					userDeleted = true;
					System.out.println("USER TABLE UPDATED");
					for (User user1 : allUsers) {
						System.out.println(user1);
					}
					break;
				}
			}
			if (!userDeleted) {
				System.out.println("user not found or password not correct");
			}
		}
	}

	// transaction options
	public void seeTransactions() {
		if (cryptoTransactions.isEmpty() && currencyTransactions.isEmpty()) {
			System.out.println("NOT A SINGLE TRANSACTION CREATED");
		} else if (cryptoTransactions.isEmpty()) {
			for (TransactionCurrency currency : currencyTransactions) {
				System.out.println(currency);
			}
		} else if (currencyTransactions.isEmpty()) {
			for (TransactionCrypto crypto : cryptoTransactions) {
				System.out.println(crypto);
			}
		}

	}

	public User[] userTransactionChecker() {
		User[] users = new User[2];
		// remitent check
		boolean remitentFound = false;
		System.out.println("Who is the remitent?");
		String remitentName = input.nextLine();

		for (User user : allUsers) {
			if (user.getUsername().equalsIgnoreCase(remitentName)) {
				remitentFound = true;
				users[0] = user;
				break;
			}
		}

		if (remitentFound) {
			System.out.println("Remitent found! :)");
		} else {
			System.out.println("Remitent not found! :(");
			return null;
		}

		// destinatary check
		boolean destinataryFound = false;
		System.out.println("Who is the destinatary?");
		String destinataryName = input.nextLine();

		for (User user : allUsers) {
			if (user.getUsername().equalsIgnoreCase(destinataryName)) {
				destinataryFound = true;
				users[1] = user;
				break;
			}
		}

		if (destinataryFound) {
			System.out.println("Destinatary found! :)");
			//
		} else {
			System.out.println("Destinatary not found! :(");
			return null;
		}
		return users;
	}

	public void cryptoTransaction() {
		TransactionCrypto cryptoTrans = new TransactionCrypto();
		User[] containerUsers = userTransactionChecker();
		if (containerUsers != null) {
			User remitent = containerUsers[0];
			User destinatary = containerUsers[1];
		}

	}

	public void currencyTransaction() {
		TransactionCurrency currencyTrans = new TransactionCurrency();
		User[] containerUsers = userTransactionChecker();
		if (containerUsers != null) {
			User remitent = containerUsers[0];
			User destinatary = containerUsers[1];

		}

	}

	public void deleteTransaction() {

	}

	// wallet options
	public void walletUser() {

	}

	public void walletWealth() {

	}
}

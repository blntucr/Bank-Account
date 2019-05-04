import java.util.Date;
import java.util.ArrayList;
public class Account {
	
	ArrayList<Transaction> tList = new ArrayList<Transaction>();
	private int id;
	private static double balance;
	private double annualInterestRate;
	private Date dateCreated = new Date();
	private String name;
	
	Account(){
		name = null;
		id = 0;
		balance = 0;
		annualInterestRate = 0;
	}
	
	Account(String name, int id, double balance){
		this.name = name;
		this.id = id;
		Account.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public static void setBalance(double balance) {
		Account.balance = balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMonthlyInterestRate() {
		return annualInterestRate / 12;
	}
	
	public double getMonthlyInterest() {
		return (balance * getMonthlyInterestRate());
	}
	
	public void withdraw(double amount) {
		Transaction t = new Transaction('w', amount, "Rent", getBalance());
		t.transaction(amount);
		tList.add(t);
	}
	
	public void deposit(double amount) {
		Transaction t = new Transaction('d', amount, "sent", getBalance());
		t.transaction(amount);
		tList.add(t);
	}
	
	@Override
	public String toString() {
		return "Name:"+name+"\nAccount id: "+id+"\nBalance: "+balance+"\nAnnual Interest Rate: "
				+annualInterestRate+"\nDate: "+dateCreated+"\n";
	}
}

class CheckingAccount extends Account{
	
	final private double limit;
	double balance;
	
	CheckingAccount(String name, int id, double balance){
		super(name, id, balance);
		limit = super.getBalance() * 2;
		this.balance = balance;
		
	}

	public double getLimit() {
		return limit;
	}
	
	@Override
	public void withdraw(double amount) {
		Transaction t = new Transaction('w', amount, "Rent", super.getBalance());
		t.transaction(amount);
		tList.add(t);
	}
	
	@Override
	public void deposit(double amount) {
		Transaction t = new Transaction('d', amount, "Sent", super.getBalance());
		t.transaction(amount);
		tList.add(t);
	}
	
	@Override
	public String toString() {
		return "Checking Account of "+super.getId()+"\nLimit: "+limit
				+" Balance: "+super.getBalance()+"\n";
	}
	
}

class SavingsAccount extends Account{
	private double balance;
	final private double limit;
	
	SavingsAccount(double balance){
		limit = 2 * super.getBalance();
		this.balance = balance;
	}
	
	@Override
	public double getBalance() {
		return balance;
	}
	
	public double getLimit() {
		return limit;
	}
	
	public String widthdraw(double amount) {
		if(balance >= amount) {
			balance -= amount;
			return String.valueOf(balance);
		}
		else
			return "Limit Exceeded";
	}
}		

class Transaction extends Account{
	Date DoT;
	char type;
	String description;
	double amount, newBalance;
	
	Transaction(char type, double amount, String description, double balance){
		super.setBalance(balance);
		this.type = type;
		this.amount = amount;
		this.description = description;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDoT() {
		return DoT;
	}
	
	public void transaction(double amount) {
		if(type == 'w' || type == 'W') { 
			super.setBalance(super.getBalance()-amount);
			newBalance = super.getBalance();
		}
		else if(type == 'd' || type == 'D') {
			super.setBalance(super.getBalance()+amount);
			newBalance = super.getBalance();
		}
		else
			System.out.print("\nPlease enter a valid character\n");
	}
	
	@Override
	public String toString() {
		if((type == 'w' || type == 'W') && super.getBalance() >= 0) {
			return amount+" TL withdrawal transaction complete\n"+"Balance: "
					+newBalance+" "+new Date()+"\n\n";
		}
		else if(type == 'd' || type == 'D')
			return amount+" TL deposit transaction complete\n"+"Balance: "
			+newBalance+" "+new Date()+"\n\n";
		else
			return "Insufficient funds";
			}
	
	public String TransactionHistory() {
		if(type == 'w' || type == 'W')
			return amount+" TL withrawal transaction complete\n"+" "+new Date()+
					"\n\n";
		else if(type == 'd' || type == 'D')
			return amount+" TL deposit transaction complete\n"+" "+new Date()+
					"\n\n";
		else
			return "No transaction history available";
	}
}

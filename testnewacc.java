
public class testnewacc {
	public static void main(String[] args) {
		Account acc = new Account("George", 12345, 10000.0);
		acc.deposit(1000);
		Transaction t0 = acc.tList.get(0);
		System.out.println(t0.toString());
		acc.deposit(1000);
		Transaction t1 = acc.tList.get(1);
		System.out.println(t1.toString());
		
		acc.withdraw(10000);
		Transaction t3 = acc.tList.get(2);
		System.out.print(t3.toString()+"\n\n");
		System.out.print("Transaction History: "+acc.tList.toString());
		Account acc1 = new Account("Hande Ucar", 54321, 5000);
		CheckingAccount c1 = new CheckingAccount(acc1.getName(), acc1.getId(), acc1.getBalance());
		System.out.println(c1.toString());
		c1.withdraw(500);
		System.out.print(c1.toString());
		c1.withdraw(500);
		System.out.print(c1.toString()+"\n"+c1.tList.toString());

	}
}

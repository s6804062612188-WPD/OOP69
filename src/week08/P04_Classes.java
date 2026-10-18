package week08;

abstract class Employee{
	private final String firstname;
	private final String lastname;
	private final String id;
	public Employee(String firstname, String lastname, String id) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.id=id;
	}
	public abstract double earning();
	public abstract double bonus(int year);

	public String getFirstname() { return firstname; }
	public String getLastname() { return lastname; }
	public String getId() { return id; }
}

class SalariedEmployee extends Employee {
	public double salary;
	public SalariedEmployee(String firstname, String lastname, String id, double sal) {
		super(firstname, lastname, id);
		this.salary = sal;
	}

	@Override
	public double earning() {
		return salary*0.95;
	}

	@Override
	public double bonus(int year) {
		return salary* ((year > 5)? 12:6) ;
	}
}
class ComEmployee extends Employee {
	public double grossSale;
	public double ComRate;
	public ComEmployee(String firstname,String lastname,String id, double sales, double percent) {
		super(firstname, lastname, id);
		this.grossSale = sales;
		this.ComRate = percent;
	}

	@Override
	public double earning() {
		double commission = grossSale * ComRate;
		return grossSale + commission;
	}

	@Override
	public double bonus(int year) {
		return grossSale* ((year > 5)? 6:3) ;
	}
}
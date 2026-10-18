package week07;

class Address {
	protected String street;
	protected String city;

	public Address() {
		this.street = "";
		this.city = "";
	}

	public Address(String street, String city) {
		this.street = street;
		this.city = city;
	}

	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getAddressInfo() { return street + ", " + city; }
}

class Employee {
	protected int id;
	protected String name;
	protected double salary;
	protected Address address;

	public Employee() {
		this.id = 0;
		this.name = "";
		this.salary = 0.0;
		this.address = new Address();
	}

	public String getDetails() {
		return "Employee ID: " + id
			+ "\nName: " + name
			+ "\nSalary: " + salary
			+ "\nAddress: " + address.getAddressInfo();
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public double getSalary() { return salary; }
	public void setSalary(double salary) { this.salary = salary; }
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }
}

class Manager extends Employee {
	protected String parkingNo;

	public Manager() {
		super();
		this.parkingNo = "";
	}

	public String getParkingNo() { return parkingNo; }
	public void setParkingNo(String parkingNo) { this.parkingNo = parkingNo; }

	@Override
	public String getDetails() {
		return super.getDetails() + "\nParking No: " + parkingNo;
	}
}

public class P05_EmployeeManager {
	public static void main(String[] args) {
		Employee emp = new Manager();

		emp.setId(1001);
		emp.setName("John Doe");
		emp.setSalary(12345.67);

		Address address = new Address(
			"123 Somewhere Street",
			"Someplace"
		);
		emp.setAddress(address);
		
		( (Manager) emp ).setParkingNo("P-101");

		System.out.println(emp.getDetails());
	}
}

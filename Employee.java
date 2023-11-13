import java.util.ArrayList;

class Employee 
 {
        private int emp_ID;
        private String empFirst, empLast, address, city, email, empMiddle, state;
        private int zip, phone;
        public double hourlyRate;
        private ArrayList<PayPeriod> payPeriods;

        // Getters
        public int getEmp_ID() { return emp_ID; }
        public String getEmpFirst() { return empFirst; }
        public String getEmpMiddle() { return empMiddle; }
        public String getEmpLast() { return empLast; }
        public String getAddress() { return address; }
        public String getCity() { return city; }
        public String getState() { return state; }
        public int getZip() { return zip; }
        public int getPhone() { return phone; }
        public String getEmail() { return email; }
        public double getHourlyRate() { return hourlyRate; }
        public ArrayList<PayPeriod> getPayPeriods() { return payPeriods; }

        // Constructor
        public Employee()
        {
            this.emp_ID = 0;
            this.empFirst = "";
            this.empLast = "";
            this.empMiddle = "";
            this.address = "";
            this.city = "";
            this.state = "";
            this.zip = 0;
            this.phone = 0;
            this.email = "";
            this.hourlyRate = 0;
            this.payPeriods = new ArrayList<>();
        }

    public Employee(double hourlyRate) 
    {
        this.hourlyRate = hourlyRate;
    }

    // Setters
    public void setEmp_ID(int emp_ID) { this.emp_ID = emp_ID; }
    public void setEmpFirst(String empFirst) 
                { this.empFirst = empFirst; }
    public void setEmpMiddle(String empMiddle) 
                { this.empMiddle = empMiddle; }
    public void setEmpLast(String empLast) { this.empLast = empLast; }
    public void setAddress(String address) { this.address = address; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZip(int zip) { this.zip = zip; }
    public void setPhone(int phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setHourlyRate(double hourlyRate) 
                { this.hourlyRate = hourlyRate; }
    public void setPayPeriods(ArrayList<PayPeriod> payPeriods) 
                { this.payPeriods = payPeriods; }

	public String getTotalCompensationDescription()
	{
		return "Hourly Compensation: $" + hourlyRate;
	}

    public IPrinter getPrinter() 
    {
        return new EmployeePrinter();
    }

    public String Serialize()
    {
        return "\nEMPLOYEE\nEmployee ID : " + getEmp_ID() + "\nName : " + 
               getEmpFirst() + "," + getEmpMiddle() + "," + getEmpLast() + 
               "\nAddress : " + getAddress() + "," + getCity() + "," + 
               getState() + "," + getZip() + "\nPhone :" + getPhone() 
               + "\nEmail : " + getEmail() + "\nHourly Rate : $" + 
               getHourlyRate() + "/hr";
    }
                
 }

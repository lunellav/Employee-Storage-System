class PayPeriod 
{
    Employee payEmp = new Employee ();

    private int payPeriodId = 1;
    private int employeeId = payEmp.getEmp_ID(); // To match with the ID
    private String startDate = "1/1/23";
    private String endDate = "1/1/24";
    private double numberOfHours;

    // Getters
    public int getEmployeeId() { return employeeId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public double getNumberOfHours() { return numberOfHours; }

    // Setters
    public void setEmployeeId(int employeeId) 
                { this.employeeId = employeeId; }
    public void setStartDate(String startDate) 
                { this.startDate = startDate; }
    public void setEndDate(String endDate) 
                { this.endDate = endDate; }
    public void setNumberOfHours(double numberOfHours) 
                { this.numberOfHours = numberOfHours; }

    public String Serialize(Employee employee) 
    {
        StringBuilder serializedData = new StringBuilder();

        serializedData.append(employee.getEmp_ID()).append(',').append(getNumberOfHours());

        return serializedData.toString();
    }
                
}

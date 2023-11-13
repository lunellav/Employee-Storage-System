class EmployeeData 
{
    private Employee employee;
    private PayPeriod payPeriod;

    public EmployeeData(Employee employee, PayPeriod payPeriod) 
    {
        this.employee = employee;
        this.payPeriod = payPeriod;
    }

    public Employee getEmployee() 
    {
        return employee;
    }

    public PayPeriod getPayPeriod() 
    {
        return payPeriod;
    }
}

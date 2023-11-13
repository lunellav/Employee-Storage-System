class PayrollManager 
{
    private double hours, pay;

    public PayrollManager(Employee employee, PayPeriod payPeriod) 
    {
        this.hours = payPeriod.getNumberOfHours();
        this.pay = employee.getHourlyRate();
    }

    public double calculateRegularPay() 
    {
        double regular;

        if (hours > 40) 
        {
            regular = 40 * pay;
        } 
        else 
        {
            regular = hours * pay;
        }	
    
        return regular;

    }

    public double calculateOvertimePay() 
    {
        double overtime;
        double overpay;

        if (hours > 40) 
        {
            overtime = (hours - 40) * 1.5;
            overpay = overtime * pay;
            return overpay;
        } 	
        else 
        {
            return 0.0; // No overtime pay if less or equal to 40 hours
        }
    }

    public double calculateGrossPay() 
    {
        double regular = calculateRegularPay();
        double overtime = calculateOvertimePay();
        return regular + overtime;
    }

    public void printPaystub(Employee employee, PayPeriod payPeriod, 
                            TaxPayment taxPayment) 
    {
        IPrinter printer = employee.getPrinter();
        printer.printEmployee(employee, payPeriod, taxPayment);
    }

    public void printEmployeeList(Employee employee, PayPeriod payPeriod)
    {
        System.out.printf("%d %s %s %s\n\n", 
                        employee.getEmp_ID(), employee.getEmpFirst(), 
                        employee.getEmpMiddle(), employee.getEmpLast());
    }

}

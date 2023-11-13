import java.util.Date;

public class EmployeePrinter implements IPrinter
{
    @Override

    public void printEmployee(Employee employee, PayPeriod payPeriod, 
                            TaxPayment taxPayment) 
    {
        PayrollManager payroll2 = new PayrollManager(employee, payPeriod);

        double regular = payroll2.calculateRegularPay();
        double overtime = payroll2.calculateOvertimePay();
        double grossTotal = payroll2.calculateGrossPay();
        Date currentDate = new Date();

        System.out.println("\nEmployee ID: " + employee.getEmp_ID());
        System.out.printf("Name: %s %s %s\n", 
                        employee.getEmpFirst(), employee.getEmpMiddle(),
                        employee.getEmpLast());
        System.out.printf("Address : %s, %s, %s, %s\n", 
                        employee.getAddress(), employee.getCity(), 
                        employee.getZip(), employee.getState());
        System.out.println("Date : " + currentDate);
        System.out.println("Phone : " + employee.getPhone());
        System.out.println("Email : " + employee.getEmail());
        System.out.println("Hours Worked: " + payPeriod.getNumberOfHours());
        
        System.out.println(employee.getTotalCompensationDescription());
        
        if (payPeriod.getNumberOfHours() > 40) 
        {
            System.out.printf("Regular Pay: $%.2f%n", regular);
            System.out.printf("Overtime Pay: $%.2f%n", overtime);
            System.out.printf("Gross Total: $%.2f%n", grossTotal);
        } 	
        else 
        {
            System.out.printf("Total: $%.2f%n", regular);
        }

        System.out.printf("Federal Tax : $%.2f%n", taxPayment.federalTax);
        System.out.printf("State Tax: $%.2f%n", taxPayment.stateTax);
        System.out.printf("FICA Tax: $%.2f%n", taxPayment.ficaTax);
        System.out.printf("Net Total: $%.2f%n\n", taxPayment.netPay);
    }	
}

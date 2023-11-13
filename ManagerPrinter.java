import java.util.Date;

public class ManagerPrinter implements IPrinter 
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

        if (employee instanceof Manager) 
        {
            Manager manager1 = (Manager) employee;

            System.out.printf("Name: %s %s %s\n", manager1.getEmpFirst(), 
                              manager1.getEmpMiddle(), manager1.getEmpLast());
            System.out.println("\nEmployee ID: " + manager1.getEmp_ID());
            System.out.printf("Name: %s %s %s\n", 
                    manager1.getEmpFirst(), manager1.getEmpMiddle(),
                    manager1.getEmpLast());
            System.out.printf("Address : %s, %s, %s, %s\n", 
                    manager1.getAddress(), manager1.getCity(), 
                    manager1.getZip(), manager1.getState());
            System.out.println("Date : " + currentDate);
            System.out.println("Phone : " + manager1.getPhone());
            System.out.println("Email : " + manager1.getEmail());
            System.out.println("Hours Worked: " + payPeriod.getNumberOfHours());

            System.out.println(manager1.getTotalCompensationDescription());

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
}

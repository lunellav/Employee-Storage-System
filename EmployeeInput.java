import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class EmployeeInput 
{
    private ArrayList<EmployeeData> storeEmployeeData;
    HashMap<Integer, Double> importedEmployees = new HashMap<>();

    public EmployeeInput() 
    {
        storeEmployeeData = new ArrayList<>();
    }

    public void enterEmployeeData() 
    {
        String YN;
        String empFirst, empLast, address, city, email, empMiddle, state;
        int zip, phone;
        double hours, pay;
        Scanner input = new Scanner(System.in);
        //if i close the input, it causes an error????

        do 
        {
            System.out.println("Enter Employee's first name: ");
            empFirst = input.nextLine();

            System.out.println("Enter Employee's middle initial: ");
            empMiddle = input.nextLine();

            System.out.println("Enter Employee's last name: ");
            empLast = input.nextLine();

            System.out.println("Enter Employee's Email: ");	
            email = input.nextLine();

            System.out.println("Enter Employee's Address: ");
            address = input.nextLine();

            System.out.println("Enter Employee's City: ");	
            city = input.nextLine();

            System.out.println("Enter Employee's State: ");	
            state = input.nextLine();

            System.out.println("Enter Employee's Zip Code: ");
            zip = input.nextInt();

            System.out.println("Enter Employee's Phone: ");
            phone = input.nextInt();

            System.out.println("Enter # of hours worked this week: ");
            hours = input.nextDouble();

            System.out.println("Enter hourly rate of pay: ");
            pay = input.nextDouble();

            System.out.println("Is the Employee a manager?");
            input.nextLine();
            String isManager = input.nextLine().toLowerCase();

            Employee emp;
            double bonus = 0;

            if (isManager.equalsIgnoreCase("y")) 
            {
                System.out.println("What is the Manager's yearly bonus?");
                bonus = input.nextDouble();
                emp = new Manager(pay, bonus);
                ((Manager) emp).setBonus(bonus);
                input.nextLine();
            }
            else 
            {
                emp = new Employee();
            }

            emp.setEmp_ID(storeEmployeeData.size() + 1);
            emp.setEmpFirst(empFirst);
            emp.setEmpLast(empLast);
            emp.setEmpMiddle(empMiddle);
            emp.setAddress(address);
            emp.setCity(city);
            emp.setState(state);
            emp.setZip(zip);
            emp.setPhone(phone);
            emp.setEmail(email);
            emp.setHourlyRate(pay);

            PayPeriod pay1 = new PayPeriod();
            pay1.setNumberOfHours(hours);

            EmployeeData employeeData = new EmployeeData(emp, pay1);
            storeEmployeeData.add(employeeData);

            System.out.println("Would you like to enter another employee? (Y/N)");
            YN = input.nextLine();

        } while (YN.equalsIgnoreCase("Y"));

        //input.close(); // Close the scanner
    }

public void processEmployeeData() 
{
    String employeeFilePath = "Employee Data.txt";
    File oldEmployeeFile = new File(employeeFilePath); 

    if (oldEmployeeFile.exists())
    {
        if (oldEmployeeFile.delete()) 
        {
            System.out.println("Old employee file deleted successfully.");
        } 
        else 
        {
            System.out.println("Failed to delete the old employee file.");
        }
    }

    for (EmployeeData employeeData : storeEmployeeData) //arraylist to cycle through the data
    {
        Employee emp = employeeData.getEmployee();
        PayPeriod pay1 = employeeData.getPayPeriod();

        int employeeId = emp.getEmp_ID();
        double hoursWorked = pay1.getNumberOfHours();

        // Add the employee to the HashMap
        importedEmployees.put(employeeId, hoursWorked);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(employeeFilePath, true)))
        {
            String serializedEmployee = emp.Serialize();

            writer.write(serializedEmployee + "\n");
            
            
            System.out.println("Employee file updated successfully!\n");

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

public void payrollSummary()
{
    try 
    {
        BufferedReader reader = new BufferedReader(new FileReader("Payroll Data.txt"));
        String line;
        int importedCount = 0;
        double totalHours = 0.0;

        while ((line = reader.readLine()) != null) 
        {
            String[] parts = line.split(",");
            if (parts.length == 2) 
            {
                int employeeId = Integer.parseInt(parts[0].trim());
                double hoursWorked = Double.parseDouble(parts[1].trim());

                if (importedEmployees.containsKey(employeeId)) 
                {
                    importedCount++;
                    totalHours += hoursWorked;
                } 
                else 
                {
                    System.out.println("Error: Employee with ID " + employeeId 
                                        + " not found in the system.");
                }
            }
        }

        System.out.println("Summary:");
        System.out.println("# of employees imported: " + importedCount);
        System.out.println("Total hours: " + totalHours);

        reader.close();
    } 
    catch (IOException e) 
    {
        System.out.println("Error: Unable to read the payroll file.");
    }
}

public void printPayroll()
{
    String payrollFilePath = "Payroll Data.txt";
    File oldPayrollFile = new File(payrollFilePath); 

    if (oldPayrollFile.exists())
    {
        if (oldPayrollFile.delete()) 
        {
            System.out.println("Old payroll file deleted successfully.");
        } 
        else 
        {
            System.out.println("Failed to delete the old payroll file.");
        }
    }
    
    if (storeEmployeeData.isEmpty())
    {
        System.out.println("No payroll data available. Please fill it out first.");
    }
    else
    {
        for (EmployeeData employeeData : storeEmployeeData) //arraylist to cycle through the data
        {
            Employee emp = employeeData.getEmployee();
            PayPeriod pay1 = employeeData.getPayPeriod();

            PayrollManager payroll = new PayrollManager(emp, pay1);
            double grossPay = payroll.calculateGrossPay();

            TaxManager taxMan1 = new TaxManager();
            TaxPayment taxPay1 = taxMan1.ComputeTaxPayment(grossPay, emp);

            payroll.printPaystub(emp, pay1, taxPay1); // Prints Employee info

            try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(payrollFilePath, true)))
            {
                String serializedPayroll = pay1.Serialize(emp);

                writer1.write(serializedPayroll + "\n");
                
                System.out.println("Payroll file updated successfully!\n");
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }

        }
    }
}

public void summaryReport()
{
    Employee emp = new Employee();
    PayPeriod pay1 = new PayPeriod();

    EmployeeData employeeData = new EmployeeData(emp, pay1);

    PayrollManager payroll = new PayrollManager(emp, pay1);
    double grossPay = payroll.calculateGrossPay();

    TaxManager taxMan1 = new TaxManager();
    TaxPayment taxPay1 = taxMan1.ComputeTaxPayment(grossPay, emp);

    double totalGrossPay = 0.0;
    double totalHours = 0.0;
    double totalOvertime = 0.0;
    double totalRegular = 0.0;
    double totalfedTax = 0.0;
    double totalstateTax = 0.0;
    double totalFICAtax = 0.0; 
    double highestPay = Double.MIN_VALUE;
    double lowestPay = Double.MAX_VALUE;
    double highestHours = Double.MIN_VALUE;
    double lowestHours = Double.MAX_VALUE;
    Employee highestHoursEmployee = null;
    Employee lowestHoursEmployee = null; 

    double hour = pay1.getNumberOfHours();
    double overtime = payroll.calculateOvertimePay();
    double regular = payroll.calculateRegularPay();
    double fedTax = taxPay1.federalTax;
    double stateTax = taxPay1.stateTax;
    double FICATax = taxPay1.ficaTax; 

    totalGrossPay += grossPay;
    totalHours += hour;
    totalOvertime += overtime;
    totalRegular += regular;
    totalfedTax += fedTax;
    totalstateTax += stateTax;
    totalFICAtax += FICATax; 

    if (grossPay > highestPay)
    {
        highestPay = grossPay; //update highest pay
    }
    
    if (grossPay < lowestPay) 
    {
        lowestPay = grossPay; //update lowest pay
    }

    if (hour > highestHours) 
    {
        highestHours = hour; //update highest hours
        highestHoursEmployee = employeeData.getEmployee();
    }

    if (hour < lowestHours) 
    {
        lowestHours = hour; //update lowest hours
        lowestHoursEmployee = employeeData.getEmployee();
    }		
        
    double overtimePercent = (totalOvertime/totalGrossPay)*100;

    System.out.println("SUMMARY REPORT : \n");
    System.out.printf("Employees Entered: %d\n", storeEmployeeData.size());
    System.out.printf("Total Gross Pay: $%.2f%n",totalGrossPay);
    System.out.printf("Highest Gross Pay: $%.2f%n",highestPay);
    System.out.printf("Lowest Gross Pay: $%.2f%n",lowestPay);
    System.out.printf("Employee with Highest Number of Hours: %s %s %s\n",highestHoursEmployee.getEmpFirst(), 
            highestHoursEmployee.getEmpMiddle(), highestHoursEmployee.getEmpLast());
    System.out.printf("Employee with Lowest Number of Hours: %s %s %s\n",lowestHoursEmployee.getEmpFirst(), 
            lowestHoursEmployee.getEmpMiddle(), lowestHoursEmployee.getEmpLast());
    System.out.printf("Total Hours: %.2f%n", totalHours);
    System.out.printf("Total Overtime Hours: %.2f%n", totalOvertime);
    System.out.printf("Total Overtime Paid: %.2f%n", totalRegular);
    System.out.printf("Overtime %% of total gross: %.2f%%%n", overtimePercent);
    System.out.printf("Total Federal Taxes: $%.2f%n", totalfedTax);
    System.out.printf("Total State Taxes: $%.2f%n", totalstateTax);
    System.out.printf("Total FICA: $%.2f%n", totalFICAtax);
}

public void printList()
{
     System.out.println("Employee List :\nID Name");

    for (EmployeeData employeeData : storeEmployeeData)
    {
        Employee emp = employeeData.getEmployee();
        PayPeriod pay1 = employeeData.getPayPeriod();
        PayrollManager payroll = new PayrollManager(emp, pay1);

        payroll.printEmployeeList(emp, pay1);
    }
}


}


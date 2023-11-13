/* Name : Vera Valenti
 * I certify that this is my own work and where appropriate an extension of
 * the starter code provided for the assignment
 */

import java.util.Scanner;

public class Salary
{
    public static void main(String[] args) 	
    {
        Scanner menuInput = new Scanner(System.in);
        int mainOption = 0, firstOption = 0, secondOption = 0;
        EmployeeInput empInput = new EmployeeInput();

        while (mainOption != 4) 
        {
            System.out.println("Menu :");
            System.out.println("1. Employee Management");
            System.out.println("2. Payroll Management");
            System.out.println("3. Utilities");
            System.out.println("4. Exit");
            System.out.print("Option (enter the number): ");
            mainOption = menuInput.nextInt();

            switch (mainOption)
            {
                case 1:
                do
                {
                    System.out.println("\n1. Add Employees");
                    System.out.println("2. List All Employees");
                    System.out.println("3. Return to main menu");
                    System.out.print("Option (enter the number): ");
                    firstOption = menuInput.nextInt();

                    switch (firstOption)
                    {
                        case 1:
                        empInput.enterEmployeeData();
                        empInput.processEmployeeData();
                        break;

                        case 2:
                        empInput.printList();
                        break;

                        case 3:
                        System.out.println("Returning to Main Menu\n");
                        break;

                        default:
                        System.out.println("Invalid option. Please try again.\n");
                    }
                } while (firstOption != 3);

                break;

                case 2:
                do
                {
                    System.out.println("\n1. Import Payroll File");
                    System.out.println("2. Print Paystubs");
                    System.out.println("3. Return to main menu");
                    System.out.print("Option (enter the number): ");
                    secondOption = menuInput.nextInt();

                    switch (secondOption)
                    {
                        case 1:
                        empInput.payrollSummary();
                        break;

                        case 2:
                        empInput.printPayroll();
                        break;

                        case 3:
                        System.out.println("Returning to Main Menu.\n");
                        break;

                        default:
                        System.out.println("Invalid option. Please try again.\n");
                    }
                } while (secondOption != 3);

                break;

                case 3:
                //utilities
                break;

                case 4:
                System.out.println("Exiting the program");
                break;

                default:
                System.out.println("Invalid option. Please try again.");
                break;
            }
        }

        menuInput.close();
    }
}

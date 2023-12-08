package com.day3;

import java.util.Random;

/*
 * @Desc : Employee wage Calculator class
 * @Methods : computeEmployeeWage, checkAttendance, calculateDailyWage
 */

class CompanyEmpWage {
    private final String companyName;
    private final int wagePerHour;
    private final int fullDayHours;
    private final int partTimeHours;
    private final int totalWorkingDays;
    private final int maxTotalWorkingHours;

    // Instance variables to store total wage and total working hours for each company
    private int totalWage = 0;
    private int totalWorkingHours = 0;

    //@desc: Constructor to initialize company-specific details
    public CompanyEmpWage(String companyName, int wagePerHour, int fullDayHours,
                          int partTimeHours, int totalWorkingDays, int maxTotalWorkingHours) {
        this.companyName = companyName;
        this.wagePerHour = wagePerHour;
        this.fullDayHours = fullDayHours;
        this.partTimeHours = partTimeHours;
        this.totalWorkingDays = totalWorkingDays;
        this.maxTotalWorkingHours = maxTotalWorkingHours;
    }

    /*
     * @Desc : compute employee wage for the company
     */
    public void computeEmployeeWage() {
        System.out.println("Welcome to " + companyName + " Employee Wage Computation Program");

        int daysWorked = 0;

        while (daysWorked < totalWorkingDays && totalWorkingHours < maxTotalWorkingHours) {
            int workingHours = checkAttendance();

            if (workingHours > 0) {
                System.out.println("Day " + (daysWorked + 1) + ": Employee is Present");

                int dailyWage = calculateDailyWage(workingHours);
                System.out.println("Daily Employee Wage: " + dailyWage);

                totalWage += dailyWage;
                totalWorkingHours += workingHours;

                displayTypeOfWork(workingHours);
            } else {
                System.out.println("Day " + (daysWorked + 1) + ": Employee is Absent");
            }

            daysWorked++;

            System.out.println();
        }

        System.out.println("Total Wage for the Month at " + companyName + ": " + totalWage);
        System.out.println("Total Working Hours at " + companyName + ": " + totalWorkingHours);
        System.out.println();
    }

    /*
     * @Desc : check employee attendance and return the number of working hours
     * @returns: 0 for Absent, fullDayHours for Full Time, partTimeHours for Part Time
     */
    private int checkAttendance() {
        Random random = new Random();
        int attendance = random.nextInt(3);

        switch (attendance) {
            case 0:
                return 0; // Absent
            case 1:
                return fullDayHours; // Full Time
            case 2:
                return partTimeHours; // Part Time
            default:
                return 0; // Absent (default case)
        }
    }

    /*
     * @Desc : calculate daily wage
     * @Params: hoursWorked
     * @returns: daily wage
     */
    private int calculateDailyWage(int hoursWorked) {
        return wagePerHour * hoursWorked;
    }

    /*
     * @Desc : display the type of work based on the number of working hours
     * @Params: hoursWorked
     */
    private void displayTypeOfWork(int hoursWorked) {
        switch (hoursWorked) {
            case 8:
                System.out.println("Type of Work: Full Time");
                break;
            case 4:
                System.out.println("Type of Work: Part Time");
                break;
        }
    }

    //@desc: Getter method to retrieve the total wage for reporting
    public int getTotalWage() {
        return totalWage;
    }

    //@desc: Getter method to retrieve the total wage for reporting
    public String getCompanyName() {
        return companyName;
    }
}
public class EmployeeWageComputation {
    private final int numCompanies;
    private final CompanyEmpWage[] companyEmpWages;

    // @desc: Constructor to initialize the EmpWageBuilder with the number of companies
    public EmployeeWageComputation(int numCompanies) {
        this.numCompanies = numCompanies;
        this.companyEmpWages = new CompanyEmpWage[numCompanies];
    }

    // @desc: Method to add a company with specific details to the array
    public void addCompany(String companyName, int wagePerHour, int fullDayHours,
                           int partTimeHours, int totalWorkingDays, int maxTotalWorkingHours, int index) {
        companyEmpWages[index] = new CompanyEmpWage(companyName, wagePerHour, fullDayHours,
                partTimeHours, totalWorkingDays, maxTotalWorkingHours);
    }

    // @desc: Method to compute employee wage for all companies
    public void computeEmployeeWages() {
        for (int i = 0; i < numCompanies; i++) {
            companyEmpWages[i].computeEmployeeWage();
        }
    }

    //  @desc: Method to display the total wage for each company
    public void displayTotalWages() {
        for (int i = 0; i < numCompanies; i++) {
            System.out.println("Total Wage for the Month at " + companyEmpWages[i].getCompanyName() +
                    ": " + companyEmpWages[i].getTotalWage());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Create an EmpWageBuilder with the number of companies
        EmployeeWageComputation empWageBuilder = new EmployeeWageComputation(2);

        // Add companies with specific details
        empWageBuilder.addCompany("Company A", 25, 8, 4, 20, 100, 0);
        empWageBuilder.addCompany("Company B", 22, 8, 4, 25, 120, 1);

        // Compute and display employee wages for all companies
        empWageBuilder.computeEmployeeWages();
        empWageBuilder.displayTotalWages();
    }
}
class Manager extends Employee
{
    private double bonus;

    public Manager(double hourlyRate, double bonus)
    {
        super(hourlyRate);
        this.bonus = bonus;
    }

    public void setBonus(double bonus) 
    {
        this.bonus = bonus;
    }

    public double getBonus() 
    {
        return bonus;
    }

    @Override //to 'redefine' this particular string
    public String getTotalCompensationDescription()
    {
        return super.getTotalCompensationDescription() + "\nManager Bonus: $" + getBonus();
    }
    
    @Override
    public IPrinter getPrinter() 
    {
        return new ManagerPrinter();
    }

    @Override
    public String Serialize()
    {
        return "\nMANAGER " + super.Serialize() + "\nBonus : " + bonus;
    }
}

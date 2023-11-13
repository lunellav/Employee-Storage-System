class TaxManager
{
    double federalRate = 0.0;
    double stateRate = 0.0;
    double ficaRate = 0.0;

    public double incrementalFedTax (double grossPay)
    {
    double annualPay = grossPay*52;
    double totalFedTax = 0.0;
    
    double[] payBrackets = {50000, 25000, 10000, 0};
    double[] taxRates = {0.25, 0.20, 0.15, 0.10};

    for (int i = 0; i < payBrackets.length; i++)
    {	
    if (annualPay <= 0)
    {
    break;
    }

    double currentBracket = payBrackets[i];	
    double currentRate = taxRates[i];
    
    if (annualPay > currentBracket)
    {
    totalFedTax += (currentBracket * currentRate)/52;
    annualPay -= currentBracket;
    }
    else
    {
    totalFedTax += (annualPay * currentRate)/52;
    break;
    }

    }

    return totalFedTax;
    }

    public TaxPayment ComputeTaxPayment (double grossPay, Employee employee)
    {
    char state = employee.getState().charAt(0);

        federalRate = incrementalFedTax(grossPay);

        if (Character.toString(state).equalsIgnoreCase("I"))
    {
    stateRate = 0.05*grossPay;
    }
    else
    {
    stateRate = 0.03*grossPay;
    }

        double ficaRate = 0.0765*grossPay;

        double netPay = grossPay - (federalRate + stateRate + ficaRate);

        TaxPayment taxPayment = new TaxPayment(federalRate, stateRate, 
                                            ficaRate, netPay);

        return taxPayment;
    }
}

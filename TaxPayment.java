class TaxPayment
{
    //instance variable
    double federalTax, stateTax, ficaTax, netPay;

    public TaxPayment(double federalTax, double stateTax, double ficaTax, 
                    double netPay)
    {
        //assign value of constructor parameter federalTax to instance 
        //variable federalTax
        this.federalTax = federalTax; 
        this.stateTax = stateTax;
        this.ficaTax = ficaTax;
        this.netPay = netPay;
    }
}

package big_talk.chapter25.mediator1;

class UnitedNationsSecurityCouncil extends UnitedNations{
    private USA countryUSA;
    private Iraq countryIraq;

    public void setUSA(USA value) {
        this.countryUSA = value; 
    }

    public void setIraq(Iraq value) {
        this.countryIraq = value; 
    }

    public void declare(String message, Country country)
    {
        if (country == this.countryUSA) {
            this.countryIraq.getMessage(message);
        }
        else if (country == this.countryIraq) {
            this.countryUSA.getMessage(message);
        }
    }
}


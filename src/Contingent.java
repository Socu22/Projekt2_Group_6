public class Contingent {
    private double amount;
    private double debt;

    public Contingent(Member m){
        if (m.getAge()<18){
            amount = 1000;
        } else if (m.getAge()>60) {
            amount = 1600*0.75;
        }else {
            amount = 1600;
        }
        debt = amount;
    }

    public double getAmount(){
        return amount;
    }

    void addDebt(){
        debt += amount;
    }

    void payDebt(double paidAmount){
        debt -= paidAmount;
    }

    public String toString(){
        return "\tKontingent årligt: " + amount + "\n\tGæld: " + debt;
    }
}

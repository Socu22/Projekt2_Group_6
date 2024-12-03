import java.time.LocalDate;

public class Contingent {
    private double amount;
    //private double debt;
    private boolean isPaid;
    private Member member;

    public Contingent(Member m){
        this.member=m;
        if (m.getAge()<18){
            amount = 1000;
        } else if (m.getAge()>60) {
            amount = 1600*0.75;
        }else {
            amount = 1600;
        }

        if(m instanceof PassiveMember){
            amount = 500;
        }
        isPaid = false;
    }

    public double getAmount(){
        return amount;
    }

    public boolean getDebt() {
        return isPaid;
    }

    void addDebt(){
        isPaid = false;
    }

    void payDebt(){
        isPaid = true;
    }

    String paidString(){
        if (isPaid == true){
            return "har betalt";
        }
        else{
            return "har ikke betalt";
        }
    }


    public String toString(){
        return "\tKontingent årligt: " + amount + "\n\tbetalt:" + paidString();
    }


}

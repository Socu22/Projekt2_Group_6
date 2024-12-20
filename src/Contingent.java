
public class Contingent {
    private double amount;      //Price of membership
    private boolean isPaid;     //Current payment status
    private Member m;

    public Contingent(Member m){
        this.m=m;

        //Check age of member to set price
        contingentLogic();

        isPaid = false;         //Set payment status to unpaid
    }
    private void contingentLogic(){
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

    }

    public boolean getDebt() {  //Returns payment status
        return isPaid;
    }

    void addDebt(){             //Sets dept to unpaid
        isPaid = false;
    }

    void payDebt(){             //Sets debt to paid
        isPaid = true;
    }

    void setContingentToCSV(boolean isPaid){    //Sets the payments status from the database
        this.isPaid = isPaid;
    }

    String paidString(){        //Returns payment status as a string
        if (isPaid){
            return "har betalt";
        }
        else{
            return "har ikke betalt";
        }
    }

    public String toString(){
        return "\tKontingent årligt: " + amount + "\n\tStatus: " + paidString();
    }
}

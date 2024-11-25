public class Interface_Master {
    public static int input=-1;
    public static final Interface_Admin startAdminInterface= new Interface_Admin();
    public void start(){
        System.out.println("Project Started");
        input=1;
        switch (input){
            case 1:
                //Interface Admin
                startAdminInterface();
            case 2:
                //Interface User

        }
    }
    private void startAdminInterface(){
        startAdminInterface.start_interface_Admin();

    }
}

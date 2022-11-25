public class Bus extends Vehicle implements PublicTransport{
    private static int busIndex;
    private String status = "운행중";
    private final int MAXPASSENGER = 30; //최대 승객수
    private String destination;
    private int defaultCost = 1000;
    private int totalPassenger;
    private int totalCost;
    


    Bus(String destination){
        number = busIndex++;
        start(destination);
    }

    @Override
    public int calculateCost() {
        this.totalCost = totalPassenger * defaultCost;
        return totalCost;
    }

    @Override
    public void end() {
        status = "차고지행";
        setPrePassenger(0);
        setTotalCost(0);
    }

    @Override
    public void start(String destination) {
        setStatus("운행중");
        setdestination(destination);
    }
    
    @Override
    public void useOil(int oil){
        this.oil -= oil;
        if(this.oil <10){
            System.out.println("주유가 필요하다.");
            setStatus("차고지행");
        }
    };

    @Override
    public void fillOil(int oil){
        this.oil += oil;
    }

    @Override
    public boolean addPassenger(int Passenger){
        if(prePassenger + Passenger > MAXPASSENGER){
            System.out.println("최대 승객 초과");
            return false;
        }
        if(status != "운행중"){
            System.out.println("운행 중이 아닙니다.");
            return false;
        }

        prePassenger += Passenger;
        totalPassenger += Passenger;
        calculateCost();
        return true;
    };

    
    public int getMAXPASSENGER() {
        return this.MAXPASSENGER;
    }
    public String getStatus() {
        return status;
    }
    public String getDestination() {
        return destination;
    }
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public int getTotalCost() {
        return totalCost;
    }

    @Override
    public int getDefaultCost() {
        return this.defaultCost;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
        
    }

    @Override
    public void setdestination(String destination) {
        this.destination = destination;
    }



    
    
}

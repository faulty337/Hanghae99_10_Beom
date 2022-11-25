
public class Taxi extends Vehicle implements PublicTransport{
    static int taxiIndex;
    private final int MAXPASSENGER = 4; //최대 승객수
    private String destination;
    private int defaultDistance = 1;
    private int destinationDistance;
    private int defaultCost = 3000;
    private int cost = 1000;
    private String status = "일반";
    private int preCost;
    private int totalCost;
    

    enum status{
        일반,
        운행중
    }
    Taxi(){
        number = taxiIndex++;
    }

    @Override
    public int calculateCost() {
        this.preCost = defaultCost + (destinationDistance - defaultDistance)*cost;
        return preCost;
    }

    

    @Override
    public void end() { //종료(요금 결제)
        this.totalCost += this.preCost;
        setPrePassenger(0);
        setStatus("일반");
        setDestinationDistance(0);
        
    }



    @Override
    public void start(String destination) {
        if(oil <10){
            System.out.println("주유가 필요합니다.");
            return;
        }
        
    }

    public void start(String destination, int passenget, int distance) {
        if(oil <10){
            System.out.println("주유가 필요합니다.");
            return;
        }
        if(!addPassenger(passenget))return;
        
        setStatus("운행중");
        setdestination(destination);
        setDestinationDistance(distance);
        calculateCost();
    }
    

    public boolean addPassenger(int Passenger){
        
        if(this.prePassenger + Passenger > MAXPASSENGER){
            System.out.println("최대 승객 초과");
            return false;
        }
        
        if(!status.equals("일반")){
            System.out.println("탑승 불가");
            return false;
        }

        this.prePassenger += Passenger;
        return true;
    };
    
    @Override
    public void useOil(int oil) {
        this.oil -= oil;
        if(oil <10){
            System.out.println("주유 필요");
            if(oil <= 0){
                this.status = "운행 불가";
            }
        }
    }

    public int getMAXPASSENGER() {
        return MAXPASSENGER;
    }
    public String getDestination() {
        return this.destination;
    }
    public int getDestinationDistance() {
        return this.destinationDistance;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setDestinationDistance(int distance){
        this.destinationDistance = distance;
    }

    
    @Override
    public int getTotalCost() {
        return this.totalCost;
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

    public static int getTaxiIndex() {
        return taxiIndex;
    }

    public static void setTaxiIndex(int taxiIndex) {
        Taxi.taxiIndex = taxiIndex;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDefaultDistance() {
        return defaultDistance;
    }

    public void setDefaultDistance(int defaultDistance) {
        this.defaultDistance = defaultDistance;
    }

    public void setDefaultCost(int defaultCost) {
        this.defaultCost = defaultCost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPreCost() {
        return preCost;
    }

    public void setPreCost(int preCost) {
        this.preCost = preCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}

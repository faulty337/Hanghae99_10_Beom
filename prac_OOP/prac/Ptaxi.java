package prac;
class Ptaxi{
    static int taxiIndex;
    final int MAXPASSENGER = 4;
    int prePassenger;
    int taxiNum;
    int oil = 100;
    int speed = 0;
    String destination;
    int defaultDistance = 1;
    int destinationDistance;
    int defaultCost = 3000;
    int cost = 1000;
    String status = "일반";

    Ptaxi(){
        this.taxiNum = taxiIndex++; 
        if(oil < 10){
            System.out.println("주유가 필요하다");
        }else{
            status = "운행";
        }
    }
    public void set(){

    }

    //승객 탑승
    public void addPassenger(){
        if(prePassenger >= MAXPASSENGER){
            System.out.println("만석입니다.");
            return;
        }
        if(!status.equals("운행")){
            System.out.println("현재 운행중이 아닙니다.");
            return;
        }
        prePassenger++;
    }

    //속도 변경
    public void setSpeed(int speed){
        if(this.oil < 10){
            System.out.println("주유량을 확인해주세요.");
            return;
        }
        this.speed += speed;
    }

    
    // public void useFuel(int oil){
    //     this.oil -= oil;
    //     if(this.oil <= 0){
    //         this.status = "차고지행";
    //     }
    // }

    // public void end(){
    //     this.status = "차고지행";
    // }

    public String toString(String ...filed){
        return "주유량 = " + oil + "\n" 
            + "주유량 = " + oil + "\n" 
            + "상태 = " + status;
    }

}
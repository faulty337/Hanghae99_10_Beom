package prac;
class Pbus{
    static int busIndex;
    final int MAXPASSENGER = 30;
    int prePassenger = 0;
    int cost = 1000;
    int busNum;
    int oil = 100;
    int speed = 0;
    String status;

    Pbus(){
        this.busNum = busIndex++;
        if(oil < 10){
            System.out.println("주유가 필요하다");
        }else{
            status = "운행";
        }
    }

    // public void setStatus(String status){
    //     this.status = status;
    // }

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
    public void setSpeed(int speed){
        if(this.oil < 10){
            System.out.println("주유량을 확인해주세요.");
            return;
        }
        this.speed += speed;
    }

    public void useFuel(int oil){
        this.oil -= oil;
        if(this.oil <= 0){
            this.status = "차고지행";
        }
    }

    public void end(){
        this.status = "차고지행";
    }

    public String toString(String ...filed){
        return "주유량 = " + oil + "\n" 
            + "주유량 = " + oil + "\n" 
            + "상태 = " + status;
    }

}
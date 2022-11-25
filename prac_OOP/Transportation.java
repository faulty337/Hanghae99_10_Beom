public class Transportation {
    //교통수단

    protected int number; //번호
    protected int oil = 100; //주유량
    protected int speed = 0; //속도
    protected int MAXPASSENGER; //최대 승객수
    protected int prePassenger; //현재 승객 수
    
    
    // 승객 탑승
    public boolean addPassenger(int Passenger){
        if(prePassenger + Passenger > MAXPASSENGER){
            System.out.println("최대 승객 초과");
            return false;
        }
        prePassenger+= Passenger;
        return true;
    };

    // 승객 하차
    public void outPassenger(int Passenger){
        if(prePassenger - Passenger <= 0){
            System.out.println("내릴 승객이 없습니다.");
        }else{
            prePassenger-= Passenger;
        }
    };


    //주유
    public void fillOil(int oil){
        this.oil += oil;
    }
    public int getOil() {
        return oil;
    }

    //연료 사용
    public void useOil(int oil){
        this.oil -= oil;
        if(oil <10){
            System.out.println("주유필요");
        }
    };

    public void setPrePassenger(int prePassenger) {
        this.prePassenger = prePassenger;
    }

    public void setSpeed(int speed){
        this.speed += speed;
    }
    public int getSpeed() {
        return speed;
    }
    public int getPrePassenger() {
        return prePassenger;
    }
    public int getNumber() {
        return number;
    }
}

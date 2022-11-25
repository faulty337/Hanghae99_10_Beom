public class TestBus {
    public static void main(String[] args) {

        //버스 2대 생성
        Bus bus1 = new Bus("종점1");
        Bus bus2 = new Bus("종점2");

        //출력
        //static변수 이용 번호 다르게
        System.out.println("bus1 의 번호 : " + bus1.getNumber());
        System.out.println("bus2 의 번호 : " + bus2.getNumber());

        //승객 +2
        bus1.addPassenger(2);

        //출력
        System.out.println("탑승 승객 수 = " + bus1.getPrePassenger());
        System.out.println("잔여 승객 수 = " + (bus1.getMAXPASSENGER()-bus1.getPrePassenger()));
        System.out.println("요금 확인 = " + bus1.getTotalCost());

        //주유량 -50
        bus1.useOil(50);

        //출력
        System.out.println("주유량 = " + bus1.getOil());

        //상태 변경
        bus1.end();

        //주유량 -10
        bus1.fillOil(10);

        //출력
        System.out.println("상태 = " + bus1.getStatus());
        System.out.println("주유량 = " + bus1.getOil());

        //상태 변경
        bus1.setStatus("운행중");

        //승객 +45
        bus1.addPassenger(45);
        //알럿(승객 초과)

        //승객 +5
        bus1.addPassenger(5);

        //출력
        System.out.println("탑승 승객 수 = " + bus1.getPrePassenger());
        System.out.println("잔여 승객 수 = " + (bus1.getMAXPASSENGER() - bus1.getPrePassenger()));
        System.out.println("요금 = " + bus1.getTotalCost());

        //주유량 -55
        bus1.useOil(55);
        //알럿(주유 필요)

        System.out.println("주유량 = " + bus1.getOil());
        System.out.println("상태 = " + bus1.getStatus());

        
        
    }
}

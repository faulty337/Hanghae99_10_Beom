public class TestTaxi {
    public static void main(String[] args) {
        //택시2대 생성
        Taxi taxi1 = new Taxi();
        Taxi taxi2 = new Taxi();

        //출력
        //static 이용 번호 다르게
        System.out.println("taxi1의 번호 = "+taxi1.getNumber());
        System.out.println("taxi2의 번호 = "+taxi2.getNumber());
        System.out.println("주유량 = " + taxi1.getOil());
        System.out.println("상태 = " + taxi1.getStatus());

        //운행 시작
        taxi1.start("서울역", 2, 2);

        //출력
        System.out.println("탑승 승객 수 = " + taxi1.getPrePassenger());
        System.out.println("잔여 승객 수 = " + (taxi1.getMAXPASSENGER() - taxi1.getPrePassenger()));
        System.out.println("기본 요금 확인 = " + taxi1.getDefaultCost());
        System.out.println("목적지 = " + taxi1.getDestination());
        System.out.println("목적지까지 거리 = " + taxi1.getDestinationDistance() + "km");
        System.out.println("지불할 요금 = " + taxi1.calculateCost());
        System.out.println("상태 = " + taxi1.getStatus());

        //주유량 -80
        taxi1.useOil(80);

        //요금 결제(종료)
        taxi1.end();

        System.out.println("주유량 = "+taxi1.getOil());
        System.out.println("누적 요금 = " + taxi1.getTotalCost());

        taxi1.addPassenger(5);

        //운행 시작
        taxi1.start("구로디지털단지역", 3, 12);

        //출력
        System.out.println("탑승 승객 수 = " + taxi1.getPrePassenger());
        System.out.println("잔여 승객 수 = "+(taxi1.getMAXPASSENGER() - taxi1.getPrePassenger()));
        System.out.println("기본요금 확인 = "+taxi1.getDefaultCost());
        System.out.println("목적지 = " + taxi1.getDestination());
        System.out.println("목적지까지 거리 = " + taxi1.getDestinationDistance() + "km");
        System.out.println("지불할 요금 = " + taxi1.calculateCost());

        //주유량 -20
        taxi1.useOil(20);

        //요금 결제(종료)
        taxi1.end();

        //출력
        System.out.println("주유량 = " + taxi1.getOil());
        System.out.println("상태 = " + taxi1.getStatus());
        System.out.println("누적 요금 = " + taxi1.getTotalCost());
        

    }
}

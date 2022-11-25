public interface PublicTransport {
    //대중 교통 인터페이스
    //대중교통이기 위한 필수적인 기능 들이 필요 ex) 노선에 따른 목적지, 운행 상태, 요금

    void start(String destination); //운행 시작

    void end(); //운행 종료

    void setStatus(String status); //상태 설정

    int calculateCost(); //요금 계산

    int getTotalCost(); //총 요금

    int getDefaultCost(); //기본 요금

    void setdestination(String destination); //목적지 설정(대중교통은 정해진 노선과 목적지가 정해져 있다고 한다.)
}

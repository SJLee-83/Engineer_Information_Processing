public class Main{
    // 3개의 요소를 갖는 문자형 배열 x를 Main 클래스 어디에서든 사용할 수 있는 전역 변수로 선언
    static String[] x = new String[3];
    static void func(String[] x, int y){
        for(int i = 1; i < y; i++){
            if(x[i-1].equals(x[i])){
                System.out.print("O");
            }
            else{
                System.out.print("N");
            }
        }
        // String z : 문자열 x의 각 요소가 할당될 변수
        // x : 문자열 변수가 배열이며, 3개의 요소를 가지므로 각 요소를 z에 할당하면서 출력문을 3번 수행
        for (String z:x){
            System.out.print(z);
        }
    }
    // 모든 Java 프로그램은 반드시 main() 메소드에서 시작
    public static void main(String[] args){
        x[0] = "A"; // ["A", , ]
        x[1] = "A"; // ["A","A" , ]
        // new 키워드로 새로운 메모리 영역 확보
        x[2] = new String("A"); 
        // ["A","A" , ]
        // ["A", , ]

        // 인수로 배열의 이름을 지정하면 배열의 시작 주소가 인수로 전달
        func(x, 3);
    }
}
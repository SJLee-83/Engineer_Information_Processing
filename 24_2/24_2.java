public class Test{
    public static void check(int[] x, int[] y){
        if(x == y) System.out.print("O");
        else System.out.print("N");
    }
}

public static void main(String[] args){
    int a[] = new int[] {1, 2, 3, 4};
    int b[] = new int[] {1, 2, 3, 4};
    int c[] = new int[] {1, 2, 3};
    check(a, b);
    check(b, c);
    check(a, c);
}

// ## == 연산자의 동작 원리: 주소 비교 vs. 내용 비교
// 기본 자료형 (Primitive Types: int, char, double 등)
// == 연산자는 변수에 저장된 실제 값을 비교합니다.
// int x = 10; int y = 10; → x == y는 true입니다.

// 참조 자료형 (Reference Types: 배열, String, 모든 클래스 객체 등)
// == 연산자는 변수에 저장된 **메모리 주소(참조값)**를 비교합니다.

// 비유: 똑같은 가구와 구조를 가진 쌍둥이 집 두 채가 있다고 상상해 보세요. 🏡

// 내용물 (Value): 두 집의 내부 모습은 완전히 똑같습니다.

// 주소 (Reference): 하지만 'A동 101호'와 'B동 202호'라는 주소는 명백히 다릅니다.
// == 연산자는 "두 집의 내부 모습이 같니?"가 아니라 "두 집이 같은 주소에 있는 바로 그 집이니?"라고 묻는 것과 같습니다.

// 💡 참고: 배열의 내용물을 비교하려면?
// 배열의 실제 내용이 같은지 비교하고 싶다면 java.util.Arrays.equals(a, b) 메서드를 사용해야 합니다. 이 경우 Arrays.equals(a, b)는 true를 반환합니다.
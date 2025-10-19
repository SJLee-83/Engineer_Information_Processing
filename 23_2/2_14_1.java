public class Test {
    public static void main(String[] args) {
        String str1 = "Programming";
        String str2 = "Programming";
        String str3 = new String("Programming");
        System.out.println(str1==st2);
        System.out.println(str1==st3);
        System.out.println(str1.eqauals(str3));
        System.out.println(str2.eqauals(str3));
    }
}

// ## 코드 실행 과정
// 이 결과는 자바(Java)가 문자열(String)을 메모리에서 처리하는 방식, 특히 == 연산자와 .equals() 메서드의 근본적인 차이 때문에 발생합니다.

// ### 1. 메모리 저장 방식: 문자열 상수 풀 vs. 힙
//     문자열 상수 풀 (String Constant Pool) String str1 = "Programming"; 처럼 **따옴표("")**로 문자열을 생성하면, 자바는 이 문자열을 **"문자열 상수 풀"**이라는 특별한 메모리 영역에 저장합니다. String str2 = "Programming"; 처럼 동일한 내용의 문자열을 다시 생성하려고 하면, 자바는 새로 만들지 않고 상수 풀에 이미 존재하는 "Programming"의 주소를 재사용합니다.

//     힙 (Heap) 메모리 String str3 = new String("Programming"); 처럼 new 키워드를 사용하면, 자바는 문자열 상수 풀을 확인하지 않고 무조건 "Heap" 메모리 영역에 새로운 객체를 생성합니다.

// ### 2. main 메서드 실행
// String str1 = "Programming";

//     문자열 상수 풀에 "Programming"이 없으므로 새로 생성합니다. str1은 이 주소를 가리킵니다.

// String str2 = "Programming";

//     문자열 상수 풀에 "Programming"이 이미 존재하므로, str2는 str1과 완전히 동일한 주소를 가리키게 됩니다.

// String str3 = new String("Programming");

//     new 키워드 때문에 힙(Heap) 메모리에 "Programming"이라는 내용을 가진 완전히 새로운 객체가 생성됩니다. str3는 이 새 객체의 주소를 가리킵니다.

//         ※ 현재 상태:

//         str1과 str2는 상수 풀의 같은 주소를 가리킵니다.

//         str3는 힙 메모리의 다른 주소를 가리킵니다.

// ## printf 출력 분석
// ### System.out.println(str1 == str2);
//     == 연산자는 두 변수가 가리키는 메모리 주소가 같은지 비교합니다.

//     str1과 str2는 문자열 상수 풀의 동일한 주소를 가리키므로 true입니다.

//     결과: true

// ### System.out.println(str1 == str3);
//     == 연산자가 주소를 비교합니다.

//     str1(상수 풀 주소)과 str3(힙 메모리 주소)는 서로 다른 주소를 가리키므로 false입니다.

//     결과: false

// ### System.out.println(str1.equals(str3));
//     .equals() 메서드는 주소가 다르더라도, 두 객체 안의 **실제 내용(문자열)**이 같은지 비교합니다.

//     str1의 내용 "Programming"과 str3의 내용 "Programming"은 동일합니다.

//     결과: true

// ### System.out.println(str2.equals(str3));
//     .equals() 메서드가 내용을 비교합니다.

//     str2의 내용 "Programming"과 str3의 내용 "Programming"은 동일합니다.

//     결과: true
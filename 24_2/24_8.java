public class Test {
    public static String rf(String str, int index, boolean[] seen) {
        if(index < 0) return "";
        char c = str.chatAt(index); // charAt(7) = 7 위치에 있는 요소 -> c = 'd'
        String result = rf(str, index-1, seen);
        if(!seen[c]) {
            seen[c] = true;
            return c + result // a -> ba -> cba -> dcba / result + c : a -> ab -> abc -> abcd
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "abacabcd";
        int len = str.length();
        boolean[] seen = new boolean[256]; // 256개의 요소를 가진 논리형 배열 -> [false, false, ... , false]
        System.out.print(rf(str, len-1, seen));
    }
}

// ## 1. seen[c]의 동작 원리 (문자의 정수 변환)
// ### 이게 왜 되나요? (Java)
// 자바에서 char 타입은 내부적으로 숫자로 처리됩니다. 
// boolean[] seen 배열의 인덱스(index)로 char 타입 변수 c를 사용하면, 컴파일러는 c의 아스키(ASCII) 코드 값(정수)으로 자동 형 변환하여 사용합니다.

// 예를 들어, 문자 'a'는 아스키 코드 97에 해당하므로, seen['a']는 실제로는 seen[97]에 접근하는 것과 같습니다. 이 배열은 문자 출현 여부를 기록하는 체크리스트 역할을 합니다.

// ### C, C++, Python에서는 어떻게 작동되나요?
//     C/C++: Java와 거의 동일하게 작동합니다. C/C++에서 char는 '문자'라기보다 '1바이트 크기의 정수'라는 개념이 더 강합니다. 따라서 seen[c]와 같은 코드는 매우 자연스럽게 사용됩니다.

//     Python: 다르게 작동합니다. 파이썬은 타입에 매우 엄격해서 문자를 바로 리스트의 인덱스로 사용할 수 없습니다. 
//     반드시 ord()라는 내장 함수를 사용해 문자를 아스키 코드(정수)로 명시적으로 변환해주어야 합니다.

    // # Python의 경우
    // c = 'a'
    // # seen[c] -> TypeError 발생
    // seen[ord(c)] # ord('a')는 97을 반환. 이렇게 사용해야 함

// ### 쉽게 기억하는 아스키 코드 💡
// 전체 표를 외울 필요는 전혀 없습니다. 기준점 몇 개만 기억하면 나머지는 유추할 수 있습니다.

//     '0' = 48

//     'A' = 65

//     'a' = 97

// 규칙:

//     숫자('0' ~ '9'), 대문자('A' ~ 'Z'), 소문자('a' ~ 'z')는 각각 순서대로 연속된 값을 가집니다. ('B'는 66, 'c'는 99)

//     같은 알파벳의 대문자와 소문자는 항상 32만큼 차이 납니다. ('a' - 'A' = 32) 이것이 가장 유용한 팁입니다.
interface Number {
    int sum(int[] a, boolean odd);
}

public class Test {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        OENumber OE = new OENumber();
        System.out.print(OE.sum(a, true) + ", " + OE.sum(a, false));
    }
}

class OENumber implements Number {
    public int sum(int[] a, boolean odd) {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            if((odd && a[i] % 2 != 0) || (!odd && a[i] % 2 == 0)) { // java에서는 true, false가 1, 0으로 해석되지 않음
                result += a[i];
            }
        }
        return result;
    }
}

// ## 핵심 개념 설명
// 인터페이스 (Interface)와 구현 (implements) 📜
// **인터페이스(Number)**는 특정 클래스가 어떤 메서드를 반드시 포함해야 하는지를 명시하는 설계도 또는 계약서입니다. 인터페이스 자체는 실제 코드를 가지지 않고, 메서드의 이름과 형태만 정의합니다.

// **구현(implements)**은 어떤 클래스(OENumber)가 특정 인터페이스(Number)를 채택하여 그 계약을 이행하겠다고 선언하는 것입니다. 
// OENumber 클래스는 Number 인터페이스를 implements 했기 때문에, 인터페이스에 정의된 sum 메서드를 반드시 코드로 구현해야 할 의무가 있습니다.

// 클래스 (Class)와 객체 (Object) 🏢
// **클래스(OENumber)**는 객체를 만들기 위한 '설계도'이고, **객체(OE)**는 그 설계도를 바탕으로 메모리에 실제로 만들어진 '실체'입니다. new 키워드를 통해 클래스로부터 객체를 생성할 수 있습니다.

// Boolean 플래그를 이용한 조건부 로직 🚩
// sum 메서드는 boolean odd라는 변수를 **플래그(flag)**로 사용하여 메서드의 동작을 제어합니다. 
// odd 값이 true일 때는 홀수를 더하고, false일 때는 짝수를 더하도록 하나의 메서드 안에서 두 가지 동작을 수행하게 만든 것입니다. 이는 코드의 재사용성을 높이는 효과적인 방법입니다.

// 결론적으로, Java만 boolean과 숫자를 엄격하게 분리할 뿐, C, C++, Python은 모두 산술 연산에서 true를 1로 취급합니다. -> C, C++, Python : True + 6 = 7
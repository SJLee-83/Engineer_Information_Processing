class Printer{
    void print(Integer a){
        System.out.print("A" + a);
    }
    void print(Object a){
        System.out.print("B" + a);
    }
    void print(Number a){
        System.out.print("C" + a);
    }
}

public class Main{
    public static void main(String[] args){
        new Collection<>(0).print(); // 제너릭
    }
    public static class Collection<T>{
        T value;
        public Collection(T t){
            value = t;
        }
        public void print(){
            new Printer().print(value);
        }
    }
}

// ### 1. 메서드 오버로딩 (Method Overloading) ⚖️
// 메서드 오버로딩은 하나의 클래스 내에서 같은 이름을 가진 메서드를 여러 개 정의하는 것을 말합니다. 단, 각 메서드의 매개변수 타입, 개수, 순서 중 하나 이상이 달라야 합니다. 
// 호출 시에는 전달되는 인자의 타입과 가장 일치하는 메서드가 선택됩니다.

// ### 2. 제네릭 (Generics) 📜
// 제네릭은 클래스나 메서드를 작성할 때 타입을 미리 지정하지 않고, 사용하는 시점에 지정할 수 있게 하는 기능입니다. 
// Collection<T>에서 T는 "타입 매개변수"로, 어떤 타입이든 들어올 수 있는 자리 표시자 역할을 합니다. 코드에서는 new Collection<>(0)을 통해 T가 Integer로 결정되었습니다.

    // ### 1. 타입 이레이저(Type Erasure)란?
    // 제네릭(<T>)은 자바 컴파일 시점에만 존재하는 문법적 장치입니다. 코드가 컴파일되고 나면, 하위 버전 호환성을 위해 제네릭 타입 정보가 사라집니다(erased). 
    // 이때 T와 같은 타입 매개변수는 그 경계(bound)로 변환되는데, 특별히 지정하지 않으면 최상위 클래스인 Object로 변환됩니다.
    
    // ### 2. 메서드 오버로딩 결정 시점
    // 메서드 오버로딩은 프로그램을 실행하는 시점(런타임)이 아니라, 코드를 컴파일하는 시점(컴파일 타임)에 어떤 메서드를 호출할지 결정됩니다.
    // 컴파일러는 value의 타입을 Object로 인식하고 있기 때문에, Printer 클래스의 세 print 메서드 중에서 Object 타입을 받을 수 있는 가장 적합한 메서드를 찾습니다.

    // print(Integer a): Object는 Integer가 아닐 수 있으므로 탈락

    // print(Number a): Object는 Number가 아닐 수 있으므로 탈락

    // print(Object a): Object 타입을 정확히 받을 수 있으므로 선택

    // 따라서 컴파일러는 이 호출을 print(Object a) 메서드에 연결(바인딩)합니다.

    // ### 3. 최종 실행
    // 1. main에서 new Collection<Integer>(0) 객체를 만듭니다. value에는 실제 Integer 객체 0이 들어갑니다.

    // 2. .print()를 호출하면, 컴파일 시점에 이미 결정된 print(Object a) 메서드가 실행됩니다.

    // 3. Integer 객체 0이 Object 타입으로 전달되어 System.out.print("B" + a);가 실행됩니다.

    // 4. 최종 결과는 B0가 됩니다.

// ### 3. 클래스 상속 구조와 오토박싱 (Inheritance & Autoboxing) 🎁
// 이 코드의 오버로딩을 이해하려면 클래스의 상속 관계를 알아야 합니다.
// Object ← Number ← Integer
// (Object가 최상위, Integer가 가장 구체적인 자식 클래스입니다.)

// 오토박싱은 원시 데이터 타입(primitive type, 예: int)을 해당하는 래퍼 클래스(wrapper class, 예: Integer) 객체로 자동으로 변환해주는 기능입니다. 
// 이 기능 덕분에 0이라는 int 값이 Integer 객체로 취급될 수 있었습니다.



// ## 컴파일 시점 (Compile Time): 설계도 검토하기 📜
// 컴파일 시점은 우리가 작성한 소스 코드(.java 파일)를 컴퓨터가 이해할 수 있는 기계어(.class 파일)로 번역하는 단계입니다.

// 이 단계에서는 실제 건물을 짓기 전에 설계도를 꼼꼼히 검토하는 것과 같습니다. 컴파일러(번역가)가 코드에 다음과 같은 오류는 없는지 확인합니다.

// 문법 오류: 자바 문법에 맞게 코드를 작성했는가? (철자가 틀리거나 세미콜론;을 빠뜨린 경우)

// 타입 오류: int 타입 변수에 문자열을 넣는 등 타입이 맞지 않는 경우

// 메서드 결정: 이전 질문처럼, 오버로딩된 메서드 중 어떤 것을 호출할지 결정

// 이 단계에서 오류가 발견되면 프로그램은 아예 만들어지지(번역되지) 않습니다.

// ## 실행 시점 (Runtime): 건물 짓기 🏗️
// 실행 시점은 컴파일이 완료된 파일(.class 파일)을 실제로 실행하여 프로그램이 동작하는 단계입니다.

// 설계도 검토가 끝나고, 이제 그 설계도를 바탕으로 실제 건물을 짓는 과정과 같습니다. 이 단계에서는 다음과 같은 일들이 벌어집니다.

// 메모리 할당: 변수나 객체를 저장할 메모리 공간이 할당됩니다.

// 사용자 입력: 사용자가 값을 입력하거나 버튼을 클릭합니다.

// 로직 수행: 코드에 작성된 대로 계산, 데이터 처리 등이 실제로 일어납니다.

// 이 단계에서는 설계도(문법)는 완벽했지만, 실제 건물을 짓는 과정에서 발생하는 문제들(예: NullPointerException, 메모리 부족 등)이 에러로 나타날 수 있습니다.
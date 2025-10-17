class Person {
    private String name;
    public Person(String val) {
        name = val;
    }
    public static String get() {
        return name;
    }
    public void print() {
        System.out.println(name);
    }
}

public class Test {
    public static void main(String[] args) {
        Person obj = new Person("Kim");
        obj.print();
    }
}

// 코드 실행 이전의 코드 컴파일 과정에서 오류가 발생하기 때문에 main 메서드가 내려가지 않음

// ## 오류가 발생하는 이유: Static과 Non-Static의 충돌
// 결론부터 말하면, static 메서드 안에서는 static이 아닌 변수(인스턴스 변수)를 직접 사용할 수 없기 때문입니다.

//     static 메서드 (get): static 키워드가 붙은 멤버는 클래스에 소속됩니다. 객체를 만들지 않아도 Person.get()처럼 바로 호출할 수 있으며, 프로그램 전체에 단 하나만 존재하는 '공용' 기능입니다.

//     인스턴스 변수 (name): static이 붙지 않은 멤버 변수는 **객체(인스턴스)**에 소속됩니다. 
//     new Person("Kim"), new Person("Lee")처럼 객체를 만들 때마다 각 객체 내부에 자신만의 name 공간이 따로 생성됩니다.

// 오류의 원인: static 메서드인 get()은 어떤 특정 객체에 소속된 것이 아닙니다. 
// 그런데 이 메서드가 객체마다 따로 존재하는 name 변수를 반환하려고 하니, "수많은 Person 객체들 중 어떤 객체의 name을 돌려줘야 할지" 알 수 없는 모순에 빠집니다.

// 자바 컴파일러는 이 논리적 모순을 문법 오류로 간주하여 프로그램을 아예 만들어주지 않습니다.

// 비유: 대학교 강의실과 학생 개인 사물함 🏫
//     static 메서드 (get): 모든 학생이 함께 사용하는 공용 강의실과 같습니다.

//     인스턴스 변수 (name): 학생 개개인에게 할당된 사물함과 같습니다.

//     오류 상황: 공용 강의실에 "사물함에 있는 물건 가져와!"라는 지시가 내려온 것과 같습니다. 어떤 학생의 사물함을 열어야 할지 알 수 없으므로 이 지시는 수행될 수 없습니다.

// ### 해결 방법 1: 변수(name)를 static으로 만들기
// name을 모든 Person 객체가 공유하는 하나의 값으로 만들고 싶을 때 사용합니다.

//     설명: name 변수 앞에 static을 붙여 get() 메서드와 마찬가지로 클래스에 소속된 공용 변수로 만듭니다. 이렇게 하면 static 메서드가 static 변수에 접근하는 것이므로 아무런 문제가 없습니다.

//     단점: 모든 Person 객체의 name이 똑같아집니다. new Person("Kim")을 하든 new Person("Lee")를 하든, 마지막에 설정된 값 하나만 남게 되어 "개인"의 이름으로는 적합하지 않습니다.

// class Person {
//     // name을 static으로 변경
//     private static String name;

//     public Person(String val) {
//         // this 대신 클래스 이름을 사용하여 static 변수임을 명확히 할 수 있음
//         Person.name = val;
//     }

//     public static String get() {
//         // 이제 static 메서드가 static 변수에 접근하므로 OK!
//         return name;
//     }
//     // ... print() 메서드는 동일
// }

// ### 해결 방법 2: 메서드(get)를 static이 아니게 만들기 (가장 일반적인 해결책)
// 각 Person 객체가 **자신만의 고유한 name**을 가지게 하고 싶을 때 사용합니다. 이것이 훨씬 더 자연스럽고 일반적인 방법입니다.

//     설명: get() 메서드에서 static 키워드를 제거하여, name 변수와 마찬가지로 객체마다 따로 존재하는 인스턴스 메서드로 만듭니다.

//     특징: 이제 get() 메서드는 특정 객체를 통해서만 호출할 수 있습니다(예: obj.get()). get()은 자신을 호출한 객체(obj)의 name을 반환하게 됩니다.

// class Person {
//     private String name;

//     public Person(String val) {
//         name = val;
//     }

//     // get()에서 static 제거
//     public String get() {
//         // 이제 인스턴스 메서드가 인스턴스 변수에 접근하므로 OK!
//         return name;
//     }

//     public void print() {
//         System.out.println(name);
//     }
// }

// // main 메서드에서 사용할 때
// public static void main(String[] args) {
//     Person obj = new Person("Kim");
//     String nameFromGet = obj.get(); // 객체를 통해 호출해야 함
//     System.out.println(nameFromGet); // "Kim" 출력
// }
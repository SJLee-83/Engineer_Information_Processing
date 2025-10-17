class Parent {
    int x = y;

    Parent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x*y;
    }
}

class Child extends Parent {
    int x;

    Child(int x) {
        super(x+1, x);
        this.x = x;
    }

    int getX(int n) {
        return super.getX() + n;
    }
}

public class Main {
    public static void main(String[] args) {
        Parent parent = new Child(10); // parent 변수는 Parent 타입으로 선언되지만, Child 객체의 실체를 가리키고 있음. -> 자바의 다형성
        System.out.println(parent.getX());
    }
}

// ## 코드 실행 과정 상세 분석
//     (Line 5) main 메서드가 프로그램을 시작합니다.

//     (Line 6) Parent parent = new Child(10);

//         new Child(10): Child 클래스의 객체를 생성하기 위해, 10이라는 값을 가지고 Child의 생성자를 호출합니다.

//         Parent parent = ...: 생성된 Child 객체를 Parent 타입의 참조 변수 parent에 할당합니다. (다형성)

//     (Line 3) Child(int x) 생성자가 x = 10인 상태로 호출됩니다.

//         생성자의 가장 첫 줄인 super(x+1, x);가 먼저 실행됩니다.

//         x+1은 11, x는 10이므로, 부모 클래스인 Parent의 생성자를 super(11, 10)으로 호출합니다.

//     (Line 1) Parent(int x, int y) 생성자가 x = 11, y = 10인 상태로 호출됩니다.

//         this.x = x;: Parent 객체의 인스턴스 변수 x에 11을 저장합니다.

//         this.y = y;: Parent 객체의 인스턴스 변수 y에 10을 저장합니다.

//         Parent 생성자 실행이 완료되고, 다시 Child 생성자로 돌아갑니다.

//     (Line 3, 이어서) this.x = x;

//         super() 호출이 끝난 후, Child 생성자의 나머지 코드가 실행됩니다.

//         this.x는 Child 객체 자신의 인스턴스 변수 x를 의미하며, 여기에 생성자 매개변수로 받은 10을 저장합니다.

//         ※ 이 시점에 객체 상태:

//         parent가 가리키는 Child 객체는 내부에 Parent 부분과 Child 부분을 모두 가집니다.

//         Parent 부분의 변수: x = 11, y = 10

//         Child 부분의 변수: x = 10

//     (Line 7) System.out.println(parent.getX());

//         parent.getX() 메서드를 호출합니다.

//         메서드 호출 규칙: 자바는 변수의 타입(Parent)이 아닌, **실제 생성된 객체의 타입(Child)**부터 메서드를 찾습니다.

//         Child 클래스에 getX()라는 이름의 메서드가 있는지 확인합니다. getX(int n)은 있지만, 매개변수가 없는 getX()는 없습니다.

//         따라서, 부모 클래스인 Parent로 올라가서 getX() 메서드를 찾습니다.

//     (Line 2) Parent 클래스의 getX() 메서드가 최종적으로 실행됩니다.

//         return x*y; 코드가 실행됩니다.

//         이때 사용되는 x와 y는 getX() 메서드가 속한 Parent 클래스의 인스턴스 변수입니다.

//         따라서 11 * 10이 계산되어 **110**이 반환됩니다. 이 값이 최종적으로 화면에 출력됩니다.

// ### super - "나의 부모" 👨‍👩‍👧
// super는 부모 클래스의 멤버를 가리킵니다.

//     용도 1: 부모 클래스의 생성자 호출

//         Child 생성자의 super(x+1, x);처럼, 자식 클래스 생성자의 반드시 첫 줄에서 부모 클래스의 생성자를 호출하기 위해 사용됩니다. 
//         자식 객체를 만들기 전에 부모 객체를 먼저 완성해야 하기 때문입니다.

//     용도 2: 부모 클래스의 멤버 접근

//         Child 클래스의 getX(int n) 메서드에서 return super.getX() + n;은, 만약 Child 클래스에도 getX()가 있어 메서드가 오버라이딩되었더라도, 
//         **"나의 getX()가 아닌, 부모 클래스의 원본 getX()"**를 명시적으로 호출하기 위해 사용됩니다.


// parent.getX()가 작동하는 이유는 컴파일러가 코드를 검사하는 시점과 프로그램이 실행되는 시점의 역할이 다르기 때문입니다.

// ## 1. 컴파일 시점: "이 리모컨에 버튼이 있는가?" (문법 검사)
// 컴파일러는 코드를 번역할 때, 변수 parent가 Parent 타입으로 선언된 것만 봅니다.

//     컴파일러는 "parent는 Parent 타입의 리모컨이구나. Parent 클래스 설계도에 getX()라는 버튼이 있는지 확인해 봐야지." 라고 생각합니다.

//     Parent 클래스에 getX() 메서드가 정의되어 있으므로, 컴파일러는 문법적으로 올바르다고 판단하고 코드를 통과시킵니다.

// 여기서 중요한 점은, 컴파일러는 parent가 실제로 Child 객체를 가리키고 있다는 사실에는 아직 관심이 없습니다. 오직 **선언된 타입(Parent)**에 그 메서드가 존재하는지만 검사합니다.

// ## 2. 실행 시점: "어떤 객체의 버튼을 눌러야 하는가?" (실제 동작)
// 프로그램이 실행될 때, JVM(자바 가상 머신)은 parent.getX()를 실행합니다.

//     JVM은 "parent 변수가 실제로 가리키고 있는 객체는 Child 타입이구나." 라는 것을 인지합니다.

//     JVM은 가장 먼저 실제 객체인 Child 클래스에서 getX() 메서드가 있는지 찾아봅니다. (이를 '메서드 오버라이딩'이라고 합니다.)

//     이 코드에서는 Child 클래스에 getX()가 없으므로, JVM은 부모 클래스인 Parent로 올라가서 getX()를 찾아 실행합니다.

// 만약 Child 클래스에도 getX() 메서드가 재정의되어 있었다면, JVM은 부모의 것은 무시하고 Child의 getX()를 실행했을 것입니다.


// 오버로딩 (Overloading) ⚖️
// 오버로딩은 하나의 클래스 안에서 같은 이름의 메서드를 여러 개 정의하는 것입니다. 마치 하나의 단어에 여러 뜻이 있는 것처럼, 같은 이름의 메서드가 다양한 상황에 대응할 수 있게 해줍니다.

//     핵심 규칙: 메서드 이름은 같지만 매개변수의 타입, 개수, 순서가 달라야 합니다.

//     목적: 같은 기능을 하지만 처리해야 할 데이터의 종류가 다를 때, 메서드 이름을 통일하여 코드의 가독성과 편의성을 높입니다.

//     비유: 더하기라는 똑같은 이름의 계산기 버튼이 정수용(add(int a, int b)), 실수용(add(double a, double b)), 세 개의 숫자용(add(int a, int b, int c))으로 나뉘어 있는 것과 같습니다. 사용자는 상황에 맞는 버튼을 누르기만 하면 됩니다.

// class Calculator {
//     // int 두 개를 더하는 메서드
//     int add(int a, int b) {
//         return a + b;
//     }
//     // double 두 개를 더하는 메서드
//     double add(double a, double b) {
//         return a + b;
//     }
// }

// 오버라이딩 (Overriding) 🎤
// 오버라이딩은 부모 클래스로부터 상속받은 메서드의 내용을 자식 클래스에서 자신만의 방식으로 재정의하는 것입니다.

//     핵심 규칙: 메서드의 이름, 매개변수, 반환 타입이 부모 클래스의 것과 완전히 동일해야 합니다.

//     목적: 부모에게 물려받은 기능의 기본 골격은 유지하되, 자식 클래스의 특성에 맞게 동작을 바꾸기 위함입니다.

//     비유: 동물 클래스에 소리내기()라는 기능이 있지만, 이를 상속받은 강아지는 "멍멍" 짖도록, 고양이는 "야옹" 울도록 각자에게 맞게 소리내기() 기능을 재정의하는 것과 같습니다.

// class Animal {
//     void makeSound() {
//         System.out.println("동물이 소리를 냅니다.");
//     }
// }

// class Dog extends Animal {
//     @Override // 오버라이딩했다는 것을 명시
//     void makeSound() {
//         System.out.println("멍멍!");
//     }
// }
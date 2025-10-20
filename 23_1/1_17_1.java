abstract class Vehicle {
    String name;
    abstract public String getName(String val);
    public String getName() {
        return "Vehicle name : " + name;
    }
}

class Car extends Vehicle {
    private String name;
    public Car(String val) {
        name = super.name = val;
    }
    public String getName(String val) {
        return "Car name : " + val;
    }
    public String getName(byte[] val) {
        return "Car name : " + val;
    }
}

public class Test {
    public static void main(String[] args) {
        Vehicle obj = new Car("Spark");
        System.out.print(obj.getName());
    }
}

// ## 1. abstract 클래스 (추상 클래스)란?
// **abstract 클래스(추상 클래스)**는 미완성된 설계도 📜와 같습니다.

//     객체 생성 불가: 미완성 설계도이므로, new Vehicle()처럼 직접 객체(인스턴스)를 만들 수 없습니다.

//     상속(extends) 강제: 반드시 다른 클래스가 이 추상 클래스를 상속받아 완성해야만 사용할 수 있습니다.

//     추상 메서드 포함: abstract public String getName(String val);처럼 몸통({...})이 없는 추상 메서드를 가질 수 있습니다.

//        추상 메서드는 "이 설계도를 상속받는 클래스는 반드시 이 기능을 구현해야 한다"라고 강제하는 역할을 합니다. (Car 클래스가 getName(String val)를 구현한 이유입니다.)

//     일반 메서드 포함: public String getName() { ... }처럼 완성된 일반 메서드도 가질 수 있습니다. 이 메서드들은 자식 클래스에 그대로 상속됩니다.

// ## 2. private name과 name = super.name = val;
//     질문: Car 클래스 내부에서 name 값을 선언하는 거니까 name이 private으로 선언되었어도 name에 "Spark"를 저장하는 데 상관 없나요?

// 네, 전혀 상관없습니다. private이 막는 것은 외부 클래스에서의 접근입니다.

//     private 🔒: "이 변수는 오직 이 클래스 내부에서만 사용할 수 있다"는 의미입니다.

//     name = super.name = val; 코드는 Car 클래스의 생성자, 즉 Car 클래스 내부에 있습니다.

//     클래스 자기 자신은 자신의 private 멤버에 얼마든지 자유롭게 접근하여 값을 읽고 수정할 수 있습니다.

// 만약 Test 클래스의 main 메서드에서 obj.name = "Test"와 같이 외부에서 접근하려고 했다면, private이기 때문에 컴파일 에러가 발생했을 것입니다.
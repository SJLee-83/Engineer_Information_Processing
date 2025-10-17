class Connection {
    private static Connection _inst = null;
    private int count = 0;
    public static Connection get() {
        if(_inst == null) {
            _inst = new Connection();
            return _inst;
        }
        return _inst;
    }
    public void count() { count++; } 
    public int getCount() { return count; }
}
public class Test {
    public static void main(String[] args) {
        Connection conn1 = Connection.get();
        conn1.count();
        Connection conn2 = Connection.get();
        conn2.count();
        Connection conn3 = Connection.get();
        conn3.count();
        conn1.count();
        System.out.print(conn1.getCount());
    }
}

// ## 코드 실행 과정
// 1. Connection conn1 = Connection.get();:

//     Connection.get() 메서드가 처음 호출됩니다.

//     _inst는 null이므로, new Connection()을 통해 새로운 Connection 객체가 힙(Heap) 메모리에 생성되고, 이 객체는 static 변수 _inst에 저장됩니다.

//     conn1은 이 객체를 가리키게 됩니다.

// 2. conn1.count();: conn1이 가리키는 객체의 count 변수가 1 증가합니다. (count = 1)

// 3. Connection conn2 = Connection.get();:

//     Connection.get() 메서드가 다시 호출됩니다.

//     이제 _inst는 null이 아니므로, if문은 건너뛰고 기존에 만들어졌던 _inst 객체를 그대로 반환합니다.

//     conn2는 conn1과 완전히 동일한 객체를 가리키게 됩니다.

// 4. conn2.count();: conn2가 가리키는 객체(결국 conn1과 같은 객체)의 count가 1 증가합니다. (count = 2)

// 5. Connection conn3 = Connection.get();:

//     마찬가지로 기존의 _inst 객체가 반환됩니다. conn3도 같은 객체를 가리킵니다.

// 6. conn3.count();: 같은 객체의 count가 1 증가합니다. (count = 3)

// 7. conn1.count();: 다시 conn1을 통해 같은 객체의 count가 1 증가합니다. (count = 4)

// 8. System.out.print(conn1.getCount());: conn1이 가리키는 객체의 getCount()를 호출하여 최종 count 값인 4를 출력합니다.


// ## 메모리 영역 (Static vs. Heap)
// 자바 프로그램이 실행될 때 데이터는 목적에 따라 다른 메모리 영역에 저장됩니다.

//     Static 영역 (메서드 영역) 🏛️

//         클래스 자체의 정보가 저장되는 공간입니다.

//         static 키워드가 붙은 변수나 메서드는 이 영역에 저장됩니다.

//         프로그램이 시작될 때 메모리에 올라가서 끝날 때까지 사라지지 않으며, 클래스당 단 하나만 생성되어 모든 객체가 공유합니다.

//         이 코드에서: _inst 변수, get() 메서드가 Static 영역에 속합니다.

//     Heap 영역 🏗️

//         객체(인스턴스)가 실제로 생성되는 공간입니다. new 키워드로 생성된 모든 것은 이곳에 저장됩니다.

//         여러 객체가 생성될 수 있으며, 가비지 컬렉터(Garbage Collector)에 의해 관리됩니다.

//         이 코드에서: new Connection()으로 만들어진 단 하나의 Connection 객체가 Heap 영역에 존재합니다. 이 객체는 자신의 인스턴스 변수인 count를 가지고 있습니다.

//     💡 Stack 영역: main 메서드 내의 지역 변수인 conn1, conn2, conn3는 스택(Stack) 영역에 저장됩니다. 
//     이 변수들은 객체 자체가 아니라, Heap 영역에 있는 실제 객체를 가리키는 **'리모컨'(참조값)**과 같습니다.


// ## 접근 제어자 (private vs. public)
// 접근 제어자는 클래스, 변수, 메서드에 대한 접근 권한을 설정하는 키워드입니다.

//     private (비공개) 🔒

//         오직 해당 클래스 내부에서만 접근할 수 있습니다. 외부에서는 보이지도 않고 사용할 수도 없습니다.

//         목적: 클래스의 중요한 데이터나 내부 로직을 외부로부터 보호하고 숨기기 위함입니다 (캡슐화).

//         이 코드에서: private static Connection _inst는 외부에서 _inst를 마음대로 바꾸지 못하게 막고, 
//         private int count는 외부에서 conn1.count = 100과 같이 값을 멋대로 조작하지 못하게 막습니다.

//         # class 내의 private으로 선언된 변수를 클래스 외부에서 직접 전근하여 바꾸는 것은 불가능
//             // 이런 코드는 private 접근 제어자 때문에 컴파일 에러가 발생합니다.
//             conn1.count = 100;      // X - 외부에서 직접 수정 불가
//             conn1._inst = null;     // X - 외부에서 직접 수정 불가

//         # 대신 class 외부에서 메서드로 변수에 접근하면 수정이 가능하며, 클래스 내부에 저장된 private 변수들의 값 자체가 영구적으로 변경되어 저장됨
//             ## _inst 변수의 변경 과정
//             저장 위치: Static 영역 (클래스에 속한 단 하나의 변수)

//             초기 상태: _inst = null;

//             변경 시점: main에서 Connection.get()이 처음 호출되는 순간

//             변경 내용: _inst는 null에서 Heap 메모리에 생성된 Connection 객체의 주소로 값이 영구적으로 바뀝니다.

//             이후: 프로그램이 끝날 때까지 _inst는 계속해서 그 객체의 주소를 가리키고 있게 됩니다.

//         ## count 변수의 변경 과정
//             저장 위치: Heap 영역 (단 하나 생성된 Connection 객체 내부)

//             초기 상태: count = 0;

//             변경 시점: count() 메서드가 호출될 때마다

//             변경 내용: conn1.count()를 하든 conn2.count()를 하든, 결국 모두 똑같은 객체 내부의 count 변수를 찾아가 그 값을 1씩 계속 증가시킵니다. 0 → 1 → 2 → 3 → 4로 값이 누적되어 변경됩니다.

//     public (공개) 🌐

//         어디서든 (어떤 다른 클래스에서든) 자유롭게 접근할 수 있습니다.

//         목적: 클래스가 외부에 제공하는 공식적인 기능(인터페이스)을 정의합니다.

//         이 코드에서: public static Connection get()은 외부에서 이 클래스의 유일한 객체를 얻어갈 수 있는 유일한 통로를 제공하고, 
//         public void count()는 외부에서 카운트를 올릴 수 있도록 허용된 기능을 제공합니다.   
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
        System.out.print(conn1.getCount());
    }
}

// 알겠습니다. 이 코드가 **개발자 컴퓨터에서 실행 파일로 변환되고, 실제 실행되어 결과가 나오기까지**의 모든 과정을 순서대로 설명해 드릴게요.

// ### ## 1단계: 컴파일 (Compile Time) 🧑‍💻

// (프로그램 실행 전, 개발자가 코드를 `.java` 파일로 저장하고 '번역'을 시도하는 단계)

// 1.  **번역 시도**: 개발자가 `javac Test.java` (또는 이클립스/VSCode의 '저장') 명령을 내립니다.
// 2.  **문법 검사**: 컴파일러가 `Test.java`와 `Connection.java`의 모든 코드를 읽으며 문법 오류가 없는지 검사합니다.
//     * `static` 메서드(`get`)가 `static` 변수(`_inst`)에 접근하는 것을 확인합니다. → **(정상)**
//     * `static` 메서드(`get`)가 `static`이 아닌 변수(`count`)에 직접 접근하지 않는 것을 확인합니다. → **(정상)**
//     * `count()` 메서드가 `static`이 아닌 변수(`count`)에 접근하는 것을 확인합니다. → **(정상)**
//     * `main`에서 `Connection.get()`을 호출하고 `conn1.count()`를 호출하는 등 문법적 오류가 없는지 확인합니다.
// 3.  **번역 완료**: 문법 오류가 없으므로, 컴파일러는 `Connection.class`와 `Test.class`라는 **바이트코드(기계어) 파일**을 생성합니다.

// **이 단계에서는 어떤 변수에도 값이 저장되거나 실행되지 않습니다. 그저 '번역'만 끝난 상태입니다.**

// ---
// ### ## 2단계: 실행 (Runtime) 🏃

// (사용자가 `java Test` 명령으로 번역된 `.class` 파일을 '실행'하는 순간)

// 1.  **JVM 시작 및 클래스 로딩 (준비)**
//     * 자바 가상 머신(JVM)이 시작됩니다.
//     * JVM은 `Test.class`와 `Connection.class` 파일을 메모리로 불러옵니다.
//     * **`Connection` 클래스의 `static` 멤버들을 메모리의 'Static 영역'에 준비시킵니다.**
//         * `private static Connection _inst = null;` → `_inst`라는 변수 공간이 **`null`** 값으로 초기화됩니다.
//         * `public static Connection get() { ... }` → `get` 메서드의 **코드**가 올라갑니다.
//     * (`static`이 아닌 `private int count = 0;`는 "앞으로 `new`로 객체를 만들면 그 객체 안에 `0`으로 만들어라"는 **설계도**로서만 준비됩니다.)

// 2.  **`main` 메서드 실행 (시작)**
//     * `main` 스레드(일꾼)가 `main` 메서드의 첫 줄을 실행합니다.
//     * `Connection conn1 = Connection.get();` → `Connection` 클래스의 `static` 영역에 있는 `get()` 메서드를 **호출**합니다.

// 3.  **`get()` 첫 번째 호출**
//     * `get()` 메서드가 실행됩니다.
//     * `if(_inst == null)` → Static 영역의 `_inst`를 보니 `null`이 맞습니다. (True)
//     * `_inst = new Connection();` → **`new` 명령이 실행됩니다!**
//         * JVM이 **Heap 영역**에 `Connection` 객체를 위한 새 공간을 만듭니다.
//         * JVM이 `Connection` 설계도를 보고, 그 객체 **내부**에 `private int count = 0;` 코드를 실행하여 `count` 변수를 `0`으로 만듭니다.
//         * 이 새 객체의 주소(예: "100번지")가 `_inst` 변수에 저장됩니다. (`_inst`는 `null`에서 "100번지"가 됨)
//     * `return _inst;` → "100번지" 주소가 반환됩니다.
//     * `conn1` 변수에 "100번지"가 저장됩니다.

// 4.  **`conn1.count();` 실행**
//     * `conn1`("100번지")을 찾아가, 그 객체 내부의 `count()` 메서드를 호출합니다.
//     * `count++` → "100번지" 객체 내부의 `count`가 0에서 **1**이 됩니다.

// 5.  **`get()` 두 번째 호출**
//     * `Connection conn2 = Connection.get();` → `get()` 메서드가 다시 호출됩니다.
//     * `if(_inst == null)` → Static 영역의 `_inst`를 보니 "100번지"입니다. `null`이 아닙니다. (False)
//     * `if`문을 건너뛰고 `return _inst;`가 실행됩니다.
//     * "100번지" 주소가 반환됩니다.
//     * `conn2` 변수에 "100번지"가 저장됩니다.

// 6.  **`conn2.count();` 실행**
//     * `conn2`("100번지")를 찾아가, 그 객체 내부의 `count()` 메서드를 호출합니다.
//     * `count++` → "100번지" 객체 내부의 `count`가 1에서 **2**가 됩니다.

// 7.  **`get()` 세 번째 호출**
//     * `Connection conn3 = Connection.get();` → `get()` 메서드가 또 호출됩니다.
//     * `if(_inst == null)` → `_inst`는 여전히 "100번지"입니다. (False)
//     * `return _inst;` → "100번지" 주소가 반환됩니다.
//     * `conn3` 변수에 "100번지"가 저장됩니다.

// 8.  **`conn3.count();` 실행**
//     * `conn3`("100번지")를 찾아가, 그 객체 내부의 `count()` 메서드를 호출합니다.
//     * `count++` → "100번지" 객체 내부의 `count`가 2에서 **3**이 됩니다.

// 9.  **최종 출력**
//     * `System.out.print(conn1.getCount());`
//     * `conn1`("100번지")을 찾아가, 그 객체 내부의 `getCount()` 메서드를 호출합니다.
//     * `getCount()`는 "100번지" 객체 내부의 `count` 값(현재 **3**)을 반환합니다.
//     * 화면에 **3**이 출력됩니다.

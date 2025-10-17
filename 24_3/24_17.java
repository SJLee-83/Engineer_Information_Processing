public class Main{
    public static void main(String[] args){
        int sum = 0;
        try{
            func();
        }
        catch (NullPointerException e){
            sum = sum + 1;
        }
        catch (Exception e){
            sum = sum + 10;
        }
        finally{
            sum = sum + 100;
        }
        System.out.print(sum);
    }

    static void func() throws Exception{ // throw : 강제로 예외를 강제로 발생시키는 키워드
        throw new NullPointerException(); 
    }
}

// NullPointerException은 자바에서 프로그래밍 오류로 인해 발생하는 대표적인 런타임 예외 클래스입니다. 더 정확히는, **검사하지 않는 예외(Unchecked Exception)**의 일종입니다.

// ## NullPointerException의 정체: Unchecked Runtime Exception
// NullPointerException 클래스의 특징을 이해하기 위한 핵심 키워드는 두 가지입니다.

// ### 1. 런타임 예외 (Runtime Exception) 💣
// 자바의 예외는 크게 두 종류로 나뉩니다.

    // Checked Exception: 컴파일러가 코드에서 예외 처리(try-catch 등)를 했는지 반드시 검사하는 예외입니다. 
    // 파일이 존재하지 않거나(FileNotFoundException), 네트워크 연결이 끊기는 등 예측 가능하고 복구할 여지가 있는 외부 요인에 의해 발생합니다.

    // Unchecked Exception (Runtime Exception): 컴파일러가 예외 처리를 검사하지 않는 예외입니다. 
    // NullPointerException처럼 주로 개발자의 논리적인 실수로 인해 프로그램 실행 중에(Runtime) 발생하는 예외들이 여기에 속합니다.

// NullPointerException은 개발자가 null이 될 수 있는 변수를 미리 확인하지 않은 논리적 버그이기 때문에 Unchecked Exception으로 분류됩니다.

// ### 2. null 참조의 의미
// NullPointerException이 발생하는 근본적인 이유는 null 값을 가진 참조 변수를 사용하기 때문입니다.

    // 참조 변수: 객체가 저장된 메모리 주소를 가리키는 리모컨과 같습니다.

    // null 값: 리모컨이 어떤 TV도 가리키고 있지 않은 상태(텅 빈 상태)입니다.

// 이 텅 빈 리모컨의 전원 버튼을 누르거나 채널을 바꾸려고 시도하면 아무 일도 할 수 없는 것처럼, null인 변수의 메서드나 필드에 접근하려고 하면 자바는 NullPointerException을 발생시킵니다.
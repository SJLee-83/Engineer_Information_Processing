Class Test {
    public static void main(String args[]) {
        cond obj = new cond(3);
        obj.a = 5;
        int b = obj.func();
        System.out.print(obj.a + b);
    }
}

class cond {
    int a;
    public cond(int a) {
        this.a = a; // obj.a = 3 this : 현재의 실행중인 메소드가 속한 클래스를 가리키는 예약어로 cond 클래스의 객체 변수 obj의 생성자로 호출되었으므로, obj.a와 같은 의미
    }
    public int func() {
        int b = 1;
        for(int i = 1; i < a; i++)
            b += a * i;
        return a + b;
    }
}
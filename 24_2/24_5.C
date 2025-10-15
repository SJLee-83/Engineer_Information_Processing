#include <stdio.h>
void swap(int a, int b) {
    int t = a;
    a = b;
    b = t;
}

int main() {
    int a = 11;
    int b = 19;
    swap(a, b);
    switch (a) {
    case 1:
        b += 1;
    case 11:
        b += 2;
    default:
        b += 3;
        break;
    }
    printf("%d", a-b);
}

// ## 1. swap 함수가 동작하지 않는 이유와 해결 방법
// ### 이유: 값에 의한 호출 (Call by Value)
// 질문하신 대로 swap 함수를 호출해도 main 함수의 변수 a, b에는 아무런 영향이 없습니다. 그 이유는 C언어의 함수 호출 방식이 **값에 의한 호출(Call by Value)**이기 때문입니다.

// swap(a, b)를 호출할 때 main 함수의 a(값: 11)와 b(값: 19)가 swap 함수로 복사됩니다. swap 함수는 원본이 아닌 복사본을 가지고 값을 교환합니다. 
// 함수 실행이 끝나면 이 복사본들은 사라지고, main 함수의 원본 a, b는 그대로 남아있게 됩니다. 📠

// 비유: 원본 서류를 두고 복사본을 다른 사람에게 전달한 것과 같습니다. 다른 사람이 복사본에 아무리 낙서를 해도 원본 서류는 깨끗하게 유지됩니다.

// ### 해결 방법: 참조에 의한 호출 (Call by Reference)
// main 함수의 변수 값을 직접 바꾸려면, 변수의 값 대신 변수가 저장된 메모리 주소를 함수에 넘겨주어야 합니다. 
// 이를 **참조에 의한 호출(Call by Reference)**이라고 하며, C언어에서는 **포인터(Pointer)**를 사용합니다.

// swap 함수 수정: 주소를 받을 수 있도록 매개변수를 포인터(*)로 바꿉니다.

// void swap(int* a, int* b) { // int* : int형 변수의 주소를 받겠다는 의미
//     int t = *a;  // *a : a 주소에 저장된 실제 값
//     *a = *b;
//     *b = t;
// }
// main 함수 수정: swap 함수를 호출할 때 변수의 주소를 넘겨줍니다.

// // main 함수 내부
// swap(&a, &b); // &a : a 변수의 메모리 주소

// ## 2. printf와 print의 차이점
// printf (Print Formatted)
// printf는 "print formatted(형식을 갖추어 출력한다)"의 약자입니다. C언어의 표준 입출력 라이브러리(stdio.h)에 포함된 표준 함수입니다. 
// %d(정수), %s(문자열), %f(실수) 같은 **서식 지정자(format specifier)**를 사용해 다양한 형식으로 값을 출력할 수 있는 강력한 기능입니다.

// print
// C언어의 표준 라이브러리에는 print라는 이름의 함수가 없습니다. 만약 C 코드에서 print() 함수가 사용되었다면, 그것은 프로그래머가 직접 만든 사용자 정의 함수입니다. 
// Python이나 Java(System.out.print) 같은 다른 프로그래밍 언어에는 print 함수가 기본적으로 존재하기 때문에 혼동이 생길 수 있습니다.
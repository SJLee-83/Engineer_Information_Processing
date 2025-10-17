#include <stdio.h>

main() {
    char* p = "KOREA"; // 'K'의 메모리 주소 저장하는 포인터
    printf("1. %s\n", p); // 'K'부터 \0까지 문자열 출력
    printf("2. %s\n", p + 1); // 'O'부터 \0까지 문자열 출력
    printf("3. %c\n", *p); // 시작주소에 있는 문자 출력
    printf("4. %c\n", *(p + 3)); // p + 3 위치에 있는 문자 출력
    printf("5. %c\n", *p + 4); // 시작주소 문자 : K -> 75 -> 75 + 4 = 79 = O
}
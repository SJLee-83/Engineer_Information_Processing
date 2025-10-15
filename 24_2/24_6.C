#include <stdio.h>
void func(char *d, char *s) { // d = t|e|s|t|s|t|r|i|n|g|\0 , s = f|i|r|s|t|\0
    while (*s) {
        *d = *s; // t->f, e->i, s->r, t->s, s->t
        d++;
        s++;
    }
    *d ='\0'; // d = f|i|r|s|t|\0
}

int main() {
    char* str1 = "first"; // str1은 포인트 변수
    char str2[50] = "teststring"; // str2는 배열
    int result = 0;
    // 인수를 포인터 변수나 배열의 이름으로 지정하면 포인터 변수나 배열의 시작 주소가 인수로 전달
    func(str2, str1);
    for (int i = 0; str2[i] != '\0'; i++) {
        result += i; // 0+1+2+3+4=10
    }
    printf("%d\n", result);
    return 0;
}
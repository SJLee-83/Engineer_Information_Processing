#include <stdio.h>
main() {
    int n[] = { 73, 95, 82 };
    int sum = 0;
    for (int i = 0; i < 3; i++)
        sum += n[i];
    switch (sum/30) {
        case 10:
        case 9: printf("A");
        case 8: printf("B");
        case 7: 
        case 6: printf("C");
        default: printf("D");
    }
}

// ## switch 문 실행 (폴스루 발생!)
// switch 조건 평가

//     switch 문 안의 sum/30이 계산됩니다.

//     C언어에서 정수끼리 나누면 결과도 정수입니다. 250 / 30은 8.333...이지만 소수점 이하는 버려져서 최종 값은 8이 됩니다.

// switch(8) 실행

//     switch는 8과 일치하는 case를 찾아 그 위치로 점프합니다.

//     case 10:, case 9: : 건너뜁니다.

//     case 8:: 8과 일치하므로 여기로 이동합니다. printf("B");가 실행되어 화면에 B가 출력됩니다.

//     break;가 없습니다! 따라서 switch 문을 빠져나가지 않고, 아래에 있는 모든 코드를 멈추지 않고 계속 실행합니다.

// 폴스루(Fall-through)

//     case 8: 실행 후, case 7:과 case 6: 라벨을 그냥 지나쳐서 printf("C"); 코드를 만납니다. 화면에 C가 출력됩니다. (현재까지 출력: BC)

//     여전히 break;가 없습니다! 코드는 또 아래로 흘러내려가 default: 라벨을 지나칩니다.

//     printf("D"); 코드를 만납니다. 화면에 D가 출력됩니다. (현재까지 출력: BCD)

//     switch 문의 맨 끝 }에 도달했으므로 밖으로 나옵니다.
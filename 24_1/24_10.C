# include <stdio.h>
struct Node {
    int value;
    struct Node* next;
};

void func (struct Node* node){
    while(node != NULL && node -> next != NULL){
            // 1 = &n3 && 3 = &n2
        int t = node -> value; // t = n1.value = 1
        node -> value = node -> next -> value; // n1.value = n3.value = 3
        node -> next -> value = t; // n3.value = 1
        node = node -> next -> next; // node = &n2
    }
}

int main() {
    struct Node n1 = {1, NULL}; // n1.value = 1
    struct Node n2 = {2, NULL}; // n2.value = 2
    struct Node n3 = {3, NULL}; // n3.value = 3
    n1.next = &n3; // 1 -> 3
    n3.next = &n2; // 3 -> 2
    func(&n1);
    struct Node* current = &n1;
    while(current != NULL){
        printf("%d", current -> value);
        current = current -> next;
    }
    return 0;
}
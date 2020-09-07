#include <iostream>
#include <cmath>
#include <chrono>
using namespace std;


long double factorsList[100];
unsigned long listIndex = 0;
long double numbersList[100];
unsigned long long number;




bool getNumber() {
    unsigned long long numberValue;
    cout << "Enter your number: ";
    cin >> numberValue;
    if (numberValue == 0) {
        return false;
    }
    number = numberValue;
    return true;
}


void factorize() {
    numbersList[listIndex] = number;
    long double root = sqrt(number)+1;
    long double i = 2;
    for (i; i <= root; i++) {
        if (fmod(number, i) == 0) {
            number /= i;
            factorsList[listIndex] = i;
            listIndex ++;
            numbersList[listIndex] = number;
            factorize();
            return;
        }
    }
    factorsList[listIndex] = number;
    listIndex ++;
    numbersList[listIndex] = 1;
}

void print() {
    for (int i=0; listIndex >= i; i++) {
        cout << fixed << "\t" << (unsigned long long) numbersList[i] << "\t\t|\t\t" << (unsigned long long) factorsList[i] << endl;
    }
}


void flush() {
    number = 0;
    for (int i=0; i <= listIndex; i++) {
        numbersList[i] = 0;
        factorsList[i] = 0;
    }
    listIndex = 0;
}


int main() {
    while (true) {
        flush();
        if (!getNumber()) {
            break;
        }
        auto start = chrono::high_resolution_clock::now();
        factorize();
        auto stop = chrono::high_resolution_clock::now();
        auto duration = chrono::duration_cast<chrono::microseconds>(stop - start);
        print();
        cout << endl << "Took " << duration.count() << " micros" << endl;
    }

    return 0;
}

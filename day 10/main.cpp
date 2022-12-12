#include <bits/stdc++.h>
#include <regex>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct reg
{
    int val;
    int cycle;
};

void check_signal(int &sum, reg &x){
    if((x.cycle == 20) || (x.cycle == 60) || (x.cycle == 100) || (x.cycle == 140) || (x.cycle == 180) || (x.cycle == 220)){
        sum = sum + x.val*x.cycle;
    }
}

void handle_addx(int n, reg &x){


}



int main(int argc, char const *argv[])
{
    string line;
    ifstream in("input.txt");
    reg x ={1,0};
    int signal = 0;
    while(getline(in, line)){
        string inst = line.substr(0,4);
        // check_signal(signal, x);
        if(!inst.compare("noop")){
            x.cycle++;
            check_signal(signal, x);
        }else{
            x.cycle++;
            check_signal(signal, x);
            x.cycle++;
            check_signal(signal, x);
            x.val = x.val + stoi(line.substr(5, line.length()-5));
        }
        // check_signal(signal, x);
    }
    cout << signal << "\n";
    return 0;
}

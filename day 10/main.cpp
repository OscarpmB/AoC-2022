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

void draw_sprite(reg &x){
    int mid = x.val;
    int crt = (x.cycle -1);
    char c;
    if(crt % 40 == 0 && crt != 0){
        cout << "\n";
    }
    switch (abs(mid-(crt%40))%40)    
    {
    case -1:
        c = '#';
        break;
    case 0:
        c = '#';
        break;
    case 1:
        c = '#';
        break;
    default:
        c = '.';
        break;
    }
    cout << c;
}

void check_signal(int &sum, reg &x){
    draw_sprite(x);
    if((x.cycle == 20) || (x.cycle == 60) || (x.cycle == 100) || (x.cycle == 140) || (x.cycle == 180) || (x.cycle == 220)){
        sum = sum + x.val*x.cycle;
    }
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

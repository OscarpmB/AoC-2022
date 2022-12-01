#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[]){
    string line;
    // ifstream in("input.in");
    ifstream in("input.in");
    // string command;

    int max_calories = 0;
    int pre_max = 0;
    
    // Start reading lines
    while(getline(in, line)){
        if(!line.compare("")){
            if(pre_max <max_calories){
                pre_max = max_calories;
            }
            max_calories = 0;
        }
        else{
            max_calories = max_calories + stoi(line);
        }
           
    }
    cout << pre_max << endl;
    /* code */
    return 0;
}

#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[]){
    string line;
    ifstream in("input.in");
    // ifstream in("input2.in");
    // string command;

    int max_calories = 0;
    int pre1 = 0;
    int pre2 = 0;
    int pre3 = 0;
    
    // Start reading lines
    while(getline(in, line)){
        if(!line.compare("")){
            cout<< "max_c=" << max_calories << endl;
            if(max_calories > pre1){
                pre3 = pre2;
                pre2 = pre1;
                pre1 = max_calories;
            }else if((pre1 > max_calories)&& (max_calories > pre2)){
                pre3 = pre2;
                pre2 = max_calories; 
            }else if((pre2 > max_calories)&& (max_calories > pre3)){
                pre3 = max_calories;
            }
            max_calories = 0;
        }
        else{
            max_calories = max_calories + stoi(line);
        }
           
    }
    int tot = pre1 + pre2 + pre3;
    cout << tot << endl;
    /* code */
    return 0;
}

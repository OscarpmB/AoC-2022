#include <bits/stdc++.h>
#include <regex>
#include <stack>
using namespace std;

void part1(){
    ifstream reader("test.in");
    vector<string> start;
    string line;
    int num_of_stacks = 0;
    while(getline(reader, line)){
    
        if(!line.compare("")){
            for(auto i: line){
                num_of_stacks += 1;
            }
            break;
        }else {
            start.push_back(line);
        }
    }
    vector<stack<string>> stacks(num_of_stacks);
    for(int i = start.size()-1; 0 <= i; --i){
        line = start[i];
        int j = 0;
        string crate;
        
        while(j < line.length()){
            crate = line.substr(j+1, 1);
            if(crate.compare(" ") || crate.compare("")){
                stacks[j/4].push(crate);
            }
            j +=4;
        }
    }
    string command;
    int from;
    int crates = 0;
    int to;
    while(getline(reader, command)){
        int c=0;
        for(auto x: command){
            if(c == 1){
                crates = x;
            }else if(c == 3){
                from = x -1;
            }else if ( c == 5){
                to = x;
            }
        }
    }

    reader.close(); 
    
}

void part2(){
    ifstream reader("input.in");
    

    reader.close();
}


int main(int argc, char const *argv[]){


    part1();
    // part2();

    return 0;
}

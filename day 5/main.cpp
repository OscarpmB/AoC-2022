#include <bits/stdc++.h>
#include <regex>
#include <stack>
using namespace std;

void print_stacks( vector<stack<char>> &stacks){
    for (auto it = stacks.begin(); it < stacks.end(); it++){
        int count = (*it).size();
        for(int i = 0; i < count; i++){
            cout << (*it).top() << " ";
            (*it).pop();
        }
        cout << "\n";
    }
}

void split(string str, char delimiter, string* words){
    // string words[6];
    int startpos = 0;
    int added_words = 0;
    for(int i = 0; i <= str.length(); ++i){
        if((str[i] == delimiter) || i == str.length()){
            char x = str[i];
            words[added_words] = str.substr(startpos, i - startpos);
            startpos = i+1;
            added_words = added_words +1;
        }
    }
}

void part1(){
    ifstream reader("input.in");
    stack<string> start;
    string line;
    int num_of_stacks = 0;
    while(getline(reader, line)){
    
        if(!line.compare("")){
            line = start.top();
            start.pop();
            for(char i: line){
                if(i != ' '){
                    num_of_stacks += 1;
                    // cout << "i: " << i << endl;
                }   
            }
            break;
        }else {
            start.push(line);
        }
    }
    vector<stack<char>> stacks(num_of_stacks);
    int hight = start.size();
    for(int i = 0; i < hight; ++i){
        string crate;
        line = start.top();
        start.pop();
        int j = 0;
        for(char c : line){
            if((64 < c) && (c < 91)){ // ensure c is a character
                stacks[j/4].push(c);
            }
            j = j + 1; // ensure j is the index of where crate starts in input data
        }
    }
    /*  Start reading commands*/
    string command;
    int from;
    int crates = 0;
    int to;
    
    stack<char> temp;
    while(getline(reader, command)){
        string words[6];
        split(command, ' ', words);
        crates = stoi(words[1]);
        from = stoi(words[3]);
        to = stoi(words[5]);
        while (crates > 0){

            temp.push(stacks[from-1].top());
            stacks[from-1].pop();
            crates -=1;
        }
        while ( temp.size() > 0){
            stacks[to-1].push(temp.top());
            temp.pop();
        }
    }
    
    for(int i = 0; i < stacks.size(); ++i){
        cout << stacks[i].top();
    }
    cout << endl;
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

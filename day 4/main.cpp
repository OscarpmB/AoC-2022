#include <bits/stdc++.h>
using namespace std;

void part1(){
    ifstream reader("input.in");
    string line;
    string first;
    string second;
    int overlaps = 0;
    while (getline(reader, line)){
        int i = line.find(",");
        first = line.substr(0,i);
        second = line.substr(i+1,line.length());
        // Start finding the range 
        int x1 = stoi(first.substr(0, first.find("-")));
        int x2 = stoi(first.substr(first.find("-")+1, first.length()));
        int y1 = stoi(second.substr(0, second.find("-")));
        int y2 = stoi(second.substr(second.find("-")+1, second.length()));
        // cout << x1 << "-" << x2 << " " << y1 << "-" << y2 << endl;
        // check for overlap
        if(((y1 <= x1) && (x2 <= y2)) || ((x1 <= y1) && (y2 <= x2))){ //Check if X is in Y
            overlaps += 1;
        }

    }
    cout << "part1: " << overlaps << endl;
    reader.close();
    
}

void part2(){
    ifstream reader("input.in");
    string line;
    string first;
    string second;
    int overlaps = 0;
    while (getline(reader, line)){
        int i = line.find(",");
        first = line.substr(0,i);
        second = line.substr(i+1,line.length());
        // Start finding the range 
        int x1 = stoi(first.substr(0, first.find("-")));
        int x2 = stoi(first.substr(first.find("-")+1, first.length()));
        int y1 = stoi(second.substr(0, second.find("-")));
        int y2 = stoi(second.substr(second.find("-")+1, second.length()));
        // cout << x1 << "-" << x2 << " " << y1 << "-" << y2 << endl;
        // check for overlap
        if((y1 <= x2) && (x1 <= y2)){ //Check if X is in Y or Y in X
            overlaps += 1;
        }

    }
    cout << "part2: " << overlaps << endl;
    reader.close();
}


int main(int argc, char const *argv[]){


    part1();
    part2();

    return 0;
}

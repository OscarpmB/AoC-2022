#include <bits/stdc++.h>
using namespace std;
int main(int argc, char const *argv[]){
    string line;
    ifstream in("input.in");
    // ifstream in("sample.in");
    // string command;

    int score=0;
    
    // Start reading lines
    while(getline(in, line)){
        string opponent = line.substr(0,1);
        string player = line.substr(2,3);
        // cout << opponent << " " << player << endl;
        if(!opponent.compare("A")){ // rock
            if(!player.compare("Y")){ // win
                score = score + 2 + 6;
            }else if( !player.compare("X")){ // draw
                score = score + 1 + 3;
            }if(!player.compare("Z")){
                score = score + 3;
            }
        }else if(!opponent.compare("B")){ // Paper
            if(!player.compare("Z")){ // win
                score = score + 3 + 6;
            }else if( !player.compare("Y")){ // draw
                score = score + 2 + 3;
            }if(!player.compare("X")){
                score = score + 1;
            }
        }else if(!opponent.compare("C")){ // sissors
            if(!player.compare("X")){ // win
                score = score + 1 + 6;
            }else if( !player.compare("Z")){ // draw
                score = score + 3 + 3;
            }if(!player.compare("Y")){
                score = score + 2;
            }
        }
    }
    cout << score << endl;
    /* code */
    return 0;
}

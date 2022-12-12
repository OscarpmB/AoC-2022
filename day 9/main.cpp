#include <bits/stdc++.h>
#include <regex>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class End {
    public:
        int x;
        int y;
        vector<pair<int, int>> visited;
        End(){
            this->x = 0;
            this->y = 0;
            update_pos(0,0);
        }
        
        void update_pos(int dx, int dy){
            this->x = this->x + dx;
            this->y = this->y + dy;
            pair<int,int> tmp(this->x,this->y);
            if(find(this->visited.begin(), this->visited.end(), tmp) == this->visited.end()){
                this->visited.push_back(tmp);
            }
        }
        void print_visited(){
            for(int i = 0; i < this->visited.size(); ++i){
                cout << "(" << this->visited[i].first << "," << this->visited[i].second << ") "; 
            }
            cout << "\n";
        }
};

void updateHead(string cmd, End &head){
    if(!cmd.compare("R")){
        head.update_pos(1,0);
    }else if(!cmd.compare("L")){
        head.update_pos(-1,0);
    }else if(!cmd.compare("U")){
        head.update_pos(0,1);
    }else if(!cmd.compare("D")){
        head.update_pos(0,-1);
    }
}

void updateTail(End head, End &tail){
    int dx = head.x - tail.x;
    int dy = head.y - tail.y;
    // check for diagonal move
    int diagonal = dx*dy; // zero only if tail is on same vertical or horizontal line
    if(diagonal && (abs(dx) >1 || abs(dy) > 1)){
        /* diagonal > 0 -> head is in first or third quadrant of tail */
        if(diagonal > 0){
            if((dx > 0) && (dy>0)){
                tail.update_pos(1,1);
            }else{
                tail.update_pos(-1,-1);
            }
        }else{
            if((dx>0)&&(dy<0)){
                tail.update_pos(1,-1);
            }else if((dx<0)&&(dy>0)){
                tail.update_pos(-1,1);
            }
        }
    }else{
        switch(dx){
        case 2:
            tail.update_pos(1,0);
            break;
        case -2:
            tail.update_pos(-1,0);
            break;
         }
        switch (dy)
        {
        case 2:
            tail.update_pos(0,1);
            break;
        
        case -2:
            tail.update_pos(0,-1);
            break;
        }
    }
}

int main(int argc, char const *argv[])
{
    string line;
    ifstream in("test.txt");
    End head;
    End tail;
    while(getline(in, line)){
        string cmd = line.substr(0,1);
        int n = stoi(line.substr(2,1));
        for(int i = 0; i < n ; ++i){
            updateHead(cmd, head);
            updateTail(head, tail);
            /* Todo fix so ut jumps diagonaly when needed*/
        }
    }
    int k = tail.visited.size();
    tail.print_visited();
    cout << k << endl;
    return 0;
}

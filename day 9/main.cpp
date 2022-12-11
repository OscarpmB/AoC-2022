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
        }
        
        void update_pos(int dx, int dy){
            this->x = this->x + dx;
            this->y = this->y + dy;
            pair<int,int> tmp(this->x,this->y);
            if(find(this->visited.begin(), this->visited.end(), tmp) == this->visited.end()){
                this->visited.push_back(tmp);
            }
        }
        // int visited_positions(){
        //     int i = 0;
        //     for(auto it = this->visited.begin(); it < this->visited.end(); ++it){
        //         i = i +1;
        //     }
        //     return i;
        // }
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
    cout << k << endl;
    return 0;
}

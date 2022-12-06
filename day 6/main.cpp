#include <bits/stdc++.h>
#include <regex>
using namespace std;

bool is_unique_seq(char *buf, int lenght){
    for(int i = 0; i < lenght-1; ++i){
        if(buf[i] == 0){ return false;}
        for(int j = i+1; j < lenght; ++j){
            // cout << "Checking: " << buf[i] << "==" << buf[j] << endl;
            if(buf[i] == buf[j]){
                // cout << "returning false\n";
                return false;
            }
        }
    }
    return true;
}

void print_seq(char *seq){
    for(int i = 0; i < 4; ++i){
        cout << seq[i];
    }
    cout << "\n";
}

void make_room(char *seq, int lenght){
    for(int i = 0; i < lenght-1; ++i){
        seq[i] = seq[i+1];
    }
}

void part1(){
    ifstream stream("input.txt");
    stream.seekg(0, stream.end);
    int size = stream.tellg();
    stream.seekg(0, stream.beg);

    char buf[size];
    stream.read(buf, size);
    char seq[4] = "";
    char message[14] = "";
    int index = -1;
    int message_i = -1;
    bool found_seq = true;
    bool found_message = true;
    for(int i = 0; i < size; ++i){
        make_room(seq, 4);
        make_room(message, 14);
        // seq[0] = seq[1];
        // seq[1] = seq[2];
        // seq[2] = seq[3];
        seq[3] = buf[i];
        message[13] = buf[i];
        if(is_unique_seq(seq, 4) && found_seq){
            print_seq(seq);
            index = i+1; // remember where message begins
            found_seq = false;
        }
        if(is_unique_seq(message, 14) && found_message){
            message_i = i +1;
            found_message = false;
        }
        
    }
    cout << index << endl;
    cout << message_i << endl;
    stream.close();
}



int main(int argc, char const *argv[]){


    part1();
    // part2();

    return 0;
}

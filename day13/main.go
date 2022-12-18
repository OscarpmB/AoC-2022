package main

import (
	"bufio"
	"fmt"
	"os"
)
func compare(left string, right string){
	for i := 0 ; i < len(left); i++{
		fmt.Printf("%c\n",left[i])
	}
}

func main() {

	readFile, err := os.Open("data.txt")

	if err != nil {
		fmt.Println(err)
	}
	fileScanner := bufio.NewScanner(readFile)
	fileScanner.Split(bufio.ScanLines)

	

	for fileScanner.Scan() {
		left := fileScanner.Text()
		right := fileScanner.Text()
		compare(left,right)
	}

	readFile.Close()
}
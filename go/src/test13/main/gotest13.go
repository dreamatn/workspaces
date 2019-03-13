package main
import (
	"fmt"
)

func main(){
	var a int = 1 >> 2
	var b int = -1 >> 2
	fmt.Printf("a = %d, b = %d\n", a, b)
	fmt.Println(2&3)
	fmt.Println(2|3)

}
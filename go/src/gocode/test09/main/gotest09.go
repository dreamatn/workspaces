package main
import (
	"fmt"
	"strconv"
)

func main(){
	var str4 string = "1023"
	var n3 int64 = 11
	n3, _ = strconv.ParseInt(str4, 10, 64)
	fmt.Printf("n3 type %T n3 = %v\n",n3 ,n3)
}
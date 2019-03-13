package main
import (
	"fmt"
	"math"
)

func main(){
	var a float64 = 3
	var b float64 = 100
	var c float64 = 6
	m := b * b - 4 * a * c
	if (m > 0) {
		x1 := (-b + math.Sqrt(m))/(2 * a)
		x2 := (+b + math.Sqrt(m))/(2 * a)
		fmt.Printf("x1 = %v, x2 = %v\n", x1, x2)
	} else if (m == 0) {
		x1 := (-b + math.Sqrt(m))/(2 * a)
		fmt.Printf("x1 = %v\n", x1)
	} else {
		fmt.Println("无解....")
	}
}
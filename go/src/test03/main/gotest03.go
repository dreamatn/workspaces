package main
import (
	"fmt"
)

// 演示go中小数类型的使用
func main()  {
	var price float32 = 89.21
	fmt.Println("price", price)
	var num1 float32 = -0.000089
	var num2 float64 = 789656.09
	var num3 float32 = -123.0000901
	var num4 float64 = -123.0000901
	fmt.Println("num1", num1, "num2", num2)
	fmt.Println("num3", num3, "num4", num4)
}
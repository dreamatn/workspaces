package main
import (
	"fmt"
)

// 转换中如果将int64 --> int8(-128-127),编译不会报错
// 只是转换结果按照溢出处理，和我们希望结果可能不一样

func main(){
	// var num1 int64 = 999999
	// var num2 int8 = int8(num1)
	// fmt.Println("num2 =",num2)
	var num1 int = 99
	// var num2 float64 = 23.456
	// var b bool = true
	// var mychar byte = 'h'
	var str string
	str = fmt.Sprintf("%d",num1)
	fmt.Printf("str type %T str=%q\n",str,str)
	fmt.Println("str 的地址：", &str)

}
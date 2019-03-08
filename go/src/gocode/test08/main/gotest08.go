package main
import (
	"fmt"
)

// 演示golang中指针类型
func main(){
	// 基本数据类型在内存布局
	var i int = 10
	// i 的地址是什么，&i
	fmt.Println("i地址是：", &i)

	// 下面的 var ptr *int = &i
	// 1. ptr 是一个指针变量
	// 2. ptr 的类型 *int
	// 3. ptr 本身的值是 &i
	var ptr *int = &i
	fmt.Printf("ptr=%v\n", ptr)
	fmt.Printf("ptr=%v\n", &ptr)
	fmt.Printf("ptr=%v\n", *ptr)
}
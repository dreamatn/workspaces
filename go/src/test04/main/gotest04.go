package main
import (
	"fmt"
	"unsafe"
)
func main(){
	var c1 byte = 'c'
	var c2 byte = '0'
	
	//当我们输出时，实际输出的是该字符对应的码值
	fmt.Println("c1",c1)
	fmt.Println("c2",c2)
	//如果我们希望输出对应字符，需要格式化输出
	fmt.Printf("c1=%c c2=%c\n",c1,c2)
	var c3 int = 22958
	fmt.Printf("c3=%c\n", c3)
	fmt.Println("c2 占用的空间 = ", unsafe.Sizeof(c2))

}
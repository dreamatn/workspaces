package main
import (
	"fmt"
	// 为了使用utils.go 文件的变量和函数，我们需要先引用该model
	"../model"
)

func main(){
	// 变量在同一作用域不能重名
	// 我们使用utils.go 的HeroName 包名.标志符
	fmt.Println(model.HeroName)
	var a int = 10
	a++
	fmt.Println(a)
	a--
	fmt.Println(a)
}
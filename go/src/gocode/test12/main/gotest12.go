package main
import (
	"fmt"
)

func main(){
	var name string
	var age byte
	var sal float32
	var ispass bool
	fmt.Println("请输入你的名字：")
	fmt.Scanln(&name)
	fmt.Println("请输入你的年龄：")
	fmt.Scanln(&age)
	fmt.Println("请输入你的薪水：")
	fmt.Scanln(&sal)
	fmt.Println("是否通过考试：")
	fmt.Scanln(&ispass)
	fmt.Printf("姓名是 %v，年龄是 %v，薪水是 %v，是否通过考试 %v", name, age, sal, ispass)

}
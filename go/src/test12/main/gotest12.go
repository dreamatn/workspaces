package main
import (
	"fmt"
)

func main(){

	// 方法1
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
	
	// 方法2
	fmt.Println("请输入姓名，年龄，薪水，是否通过考试，使用空格隔开")
	fmt.Scanf("%s %d %f %t",&name, &age, &sal, &ispass)
	fmt.Printf("姓名是 %v，年龄是 %v，薪水是 %v，是否通过考试 %v", name, age, sal, ispass)

}
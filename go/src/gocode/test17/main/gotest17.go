package main
import (
	"fmt"
)

func main(){
	var score float32
	fmt.Println("请输入成绩：")
	fmt.Scanln(&score)

	switch int(score / 60 ){
		case 1:
			fmt.Println("及格")
		case 0:
			fmt.Println("不及格")
		default:
			fmt.Println("输入有误")
	}

}
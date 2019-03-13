package main
import (
	"fmt"
)

//判断输入的年份为闰年或者平年
func main (){
	var year int 
	fmt.Println("Enter a year:")
	fmt.Scanln(&year)
	
	if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
		fmt.Printf("%d 是闰年", year)
	} else {
		fmt.Printf("%d 是平年", year)
	}

}
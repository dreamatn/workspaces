package simplemath

import "math"

//Sqrt 计算输入数字平方根
func Sqrt(i int) int {
	v := math.Sqrt(float64(i))
	return int(v)
}

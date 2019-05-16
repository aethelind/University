/* Aethelind Rose Racic
 * 7686783
 *
 * CSI2120 Winter 2018
 * Assignment Three
 * Question Three*/
package main

import (
	"fmt"
	"math"
	"math/rand"
)

func RandomArray(len int) []float32 {
	array := make([]float32, len)
	for i := range array {
		array[i] = rand.Float32()
	}
	return array
}

// Implementation
func Process(a []float32, out chan float32) {
	// call AbsDiff on the two subarrays
	absRes := AbsDiff(a[:len(a)/2], a[len(a)/2:])

	// sum elements of the resulting array
	var sum float32 = 0
	for i := range absRes {
		sum += absRes[i]
	}

	// send sum to channel out
	out <- sum
}

// using AbsDiff from Q1
func AbsDiff(sliceA, sliceB []float32) (res []float32) {
	res = make([]float32, len(sliceA), len(sliceA))
	for i, _ := range sliceA {
		res[i] = float32(math.Abs(float64(sliceA[i]) - float64(sliceB[i])))
	}
	return res
}

func main() {
	rand.Seed(100) // use this seed value

	out := make(chan float32)
	defer close(out)

	for i := 0; i < 1000; i++ {
		a := RandomArray(2 * (50 + rand.Intn(50)))
		go Process(a, out)
	}

	// add together the sums from out
	var sum float32 = 0
	for i := 0; i < 1000; i++ {
		sum += <-out
	}

	fmt.Println(sum)
}

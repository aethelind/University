/* Aethelind Rose Racic
 * 7686783
 *
 * CSI2120 Winter 2018
 * Assignment Three
 * Question One*/

package main

import (
	"bufio"
	"errors"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func getSlice(txt string) (f []float32, err error) {
	// process input text
	txt = strings.TrimSuffix(txt, "\n")
	txt = strings.TrimSuffix(txt, "\r")
	txt = strings.TrimSpace(txt)

	txtSplit := strings.Split(txt, " ")
	txtFloat := make([]float32, len(txtSplit))

	// attempt to parsefloat each element from input
	for i, val := range txtSplit {
		if x, err := strconv.ParseFloat(val, 32); err != nil {
			return nil, err
		} else {
			txtFloat[i] = float32(x)
		}
	}
	return txtFloat, nil
}

/* AbsDiff uses strat to choose which strategy to apply. */
func AbsDiff(sliceA, sliceB []float32, strat int) (res []float32, err error) {
	switch strat {
	case 0: // slices must be same length
		if len(sliceA) == len(sliceB) {
			res := make([]float32, len(sliceA), len(sliceA))
			for i, _ := range sliceA {
				res[i] = float32(math.Abs(float64(sliceA[i]) - float64(sliceB[i])))
			}
			return res, nil
		} else {
			err := errors.New("Slices are not the same length.")
			return nil, err
		}

	case 1: // ignore extras of longer list
		if len(sliceA) < len(sliceB) {
			res := make([]float32, len(sliceA), len(sliceA))
			for i, _ := range sliceA {
				res[i] = float32(math.Abs(float64(sliceA[i]) - float64(sliceB[i])))
			}
			return res, nil
		} else {
			res := make([]float32, len(sliceB), len(sliceB))
			for i, _ := range sliceB {
				res[i] = float32(math.Abs(float64(sliceA[i]) - float64(sliceB[i])))
			}
			return res, nil
		}

	case -1: // assume missing elements have value of 0
		if len(sliceA) > len(sliceB) {
			res := make([]float32, len(sliceA), len(sliceA))
			for i, _ := range sliceB {
				res[i] = float32(math.Abs(float64(sliceA[i]) - float64(sliceB[i])))
			}
			for i := len(sliceB); i < len(sliceA); i++ {
				res[i] = sliceA[i]
			}
			return res, nil
		} else {
			res := make([]float32, len(sliceB), len(sliceB))
			for i, _ := range sliceA {
				res[i] = float32(math.Abs(float64(sliceA[i]) - float64(sliceB[i])))
			}
			for i := len(sliceA); i < len(sliceB); i++ {
				res[i] = sliceB[i]
			}
			return res, nil
		}

	default:
		err := errors.New("Invalid strategy choice.")
		return nil, err
	}
	return nil, nil
}

func main() {
	// hard-coded previous slice
	prev := []float32{3.2, -6.77, 42, -0.9}

	for flag := " "; flag != "q"; {
		fmt.Println("Previous slice:", prev)
		fmt.Println("Enter new slice of floats: ")

		// collect input
		reader := bufio.NewReader(os.Stdin)
		txt, _ := reader.ReadString('\n')

		// get float slice
		if curr, err1 := getSlice(txt); err1 != nil {
			fmt.Println("Input not float values. Try again.")
		} else {
			// get user strategy choice
			fmt.Println("Which AbsDiff strategy?")
			fmt.Println("\t-1. assume zeros")
			fmt.Println("\t 0. same length")
			fmt.Println("\t 1. ignore extras")

			var strat string
			fmt.Scanln(&strat)
			strategy, err2 := strconv.Atoi(strat)

			if err2 != nil || strategy > 1 || strategy < -1 {
				fmt.Println("Input not a valid strategy. Trying strategy 0.")
				strategy = 0
			}

			// call AbsDiff on current and previous float slice with chosen strategy
			res, err3 := AbsDiff(curr, prev, strategy)
			prev = curr

			if err3 != nil {
				fmt.Println(err3)
			} else {
				fmt.Println("Result:", res)
			}
		}
		fmt.Println("q to quit, anything else to continue.")
		fmt.Scanln(&flag)
	}
}

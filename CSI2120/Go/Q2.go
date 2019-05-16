/* Aethelind Rose Racic
 * 7686783
 *
 * CSI2120 Winter 2018
 * Assignment Three
 * Question Two*/

package main

import (
	"fmt"
)

// Part One
type Bread struct {
	name        string
	ingredients map[string]Item
	weight      float32 // in kilograms
	baking      Baking
}
type Baking struct {
	bakeTime    int
	coolTime    int
	temperature int
}
type Item struct {
	weight int // in grams
}

// Part Two
type Baker interface {
	shoppingList(map[string]Item) (map[string]Item, map[string]Item)
	printBakeInstructions()
	printBreadInfo()
}

// Part Three
func NewBread() *Bread {
	// default ingredient map
	brIngredients := map[string]Item{
		"whole wheat flour": {500},
		"yeast":             {25},
		"salt":              {25},
		"sugar":             {50},
		"butter":            {50},
		"water":             {350},
	}

	// sum ingredients for total weight
	var weightSum float32 = 0
	for _, v := range brIngredients {
		weightSum += float32(v.weight)
	}
	weightSum /= 1000.0 // convert g to kg

	return &Bread{
		name:        "Whole Wheat",
		ingredients: brIngredients,
		baking: Baking{
			bakeTime:    120,
			coolTime:    60,
			temperature: 180,
		},
		weight: weightSum,
	}
}

func NewBreadVariation(newName string, addIngredients, removeIngredients map[string]Item) *Bread {
	// create default ingredient map
	brIngredients := map[string]Item{
		"whole wheat flour": {500},
		"yeast":             {25},
		"salt":              {25},
		"sugar":             {50},
		"butter":            {50},
		"water":             {350},
	}

	// add and remove ingredients as necessary
	if addIngredients != nil {
		for k, v := range addIngredients {
			if i, ok := brIngredients[k]; ok {
				// if item already on list, increase the weight.
				tmp := i.weight + v.weight
				brIngredients[k] = Item{tmp}
			} else {
				// if item not on list yet, add it.
				brIngredients[k] = v
			}
		}
	}
	if removeIngredients != nil {
		for k, v := range removeIngredients {
			if i, ok := brIngredients[k]; ok {
				// if item on the list, find new decreased weight.
				tmp := i.weight - v.weight
				if tmp > 0 {
					// if there is still some weight of item left, keep it on list with new weight
					brIngredients[k] = Item{tmp}
				} else {
					// if there is no weight of item left, delete it from list
					delete(brIngredients, k)
				}
			} // if item is not on list, do nothing.
		}
	}

	// calculate weight of ingredients
	var weightSum float32 = 0
	for _, v := range brIngredients {
		weightSum += float32(v.weight)
	}
	weightSum /= 1000.0 // to convert g to kg

	// create bread with new information
	return &Bread{
		name:        newName,
		ingredients: brIngredients,
		baking: Baking{
			bakeTime:    120,
			coolTime:    60,
			temperature: 180,
		},
		weight: weightSum,
	}
}

// Part Four
func (br *Bread) printBakeInstructions() {
	fmt.Println("Bake", br.name, "at", br.baking.temperature, "Celsius for", br.baking.bakeTime, "minutes and let cool for", br.baking.coolTime, "minutes")
}

func (br *Bread) printBreadInfo() {
	fmt.Print(br.name, " bread:\n\t")
	fmt.Println(br.ingredients)
	fmt.Println("\tWeight", br.weight, "kg")
}

func (br *Bread) shoppingList(avail map[string]Item) (missing, leftover map[string]Item) {
	missing = make(map[string]Item)
	leftover = make(map[string]Item)
	for k, v := range avail {
		// add everything in available into leftover.
		// only the ingredients from the recipe will be changed
		leftover[k] = Item{v.weight}
	}

	// iterate through each ingredient required for bread
	for k, v := range br.ingredients {
		if i, ok := avail[k]; ok { // if this ingredient is in avail..
			tmp := i.weight - v.weight
			if tmp < 0 { // if more weight is needed for bread than is avail,
				//add difference to shopping list
				tmp = -1 * tmp
				missing[k] = Item{tmp}
			} else { // there is enough ingredient avail for recipe
				// change leftover to reflect difference
				leftover[k] = Item{tmp}
			}
		} else { // the ingredient is not in avail at all, so add full weight to missing
			missing[k] = Item{v.weight}
		}
	}

	// missing contains ingredients not in available, and ingredients in avail we need more of
	// leftover contains all ingredients that were available before making bread, minus whats in the recipe
	return missing, leftover
}

// Part Five
func main() {
	//construct two breads in a slice of Baker,
	//one standard whole wheat and one sesame
	bakerSlice := []Baker{NewBread(),
		NewBreadVariation("Sesame",
			map[string]Item{"sesame": {50}, "white flour": {200}},
			map[string]Item{"whole wheat flour": {250}})}

	bakerSlice[0].printBreadInfo()
	fmt.Println()
	bakerSlice[1].printBreadInfo()
	fmt.Println()

	// assuming we currently have..
	pantry := map[string]Item{
		"whole wheat flour": {5000},
		"salt":              {500},
		"sugar":             {1000},
	}
	fmt.Print("Pantry:\n\t", pantry, "\n\n")

	// obtain shopping list
	list1 := make(map[string]Item)
	list1, pantry = bakerSlice[1].shoppingList(pantry)

	list2 := make(map[string]Item)
	list2, pantry = bakerSlice[0].shoppingList(pantry)

	for k, v := range list2 {
		if i, ok := list1[k]; ok {
			i.weight += v.weight
		} else {
			list1[k] = Item{v.weight}
		}
	}

	fmt.Print("Shopping list:\n\t", list1, "\n\n")

	// print baking instructions
	bakerSlice[0].printBakeInstructions()
	bakerSlice[1].printBakeInstructions()
}

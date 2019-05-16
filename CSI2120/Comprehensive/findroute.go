/*
* Aethelind Racic
* 7686783
* Assignment Four
 */

package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	inFileName := "wading-pools-go.txt"
	outFileName := "go-solution.txt"
	numGoRoutines := 5

	solution := findRoute(inFileName, numGoRoutines)

	fmt.Println(saveRoute(solution, outFileName))
}

/* MAIN FUNCTIONS */
func findRoute(fileName string, num int) (route []Edge) {
	tree := readPools(fileName)

	// calculate the division of work. the logic behind this:
	// if there are N routines, we must split the tree (len(tree)) into N pieces (/N)
	// the root of the tree won't be visited when finding parents in makeTree,
	// so it is ignored. (len(tree)-1)
	// therefore, the work is divided into pieces of length (len(tree)-1)/num
	div := (len(tree) - 1) / num

	out := make(chan bool)
	defer close(out)

	// run num go routines
	// the slice of nodes is divided into N pieces and sent to makeTree
	// in there, each node in the slice gets compared against the previous nodes
	// and then inserted as a child of the closest node
	for i := 0; i < num; i++ {
		go makeTree(tree[div*i+1:div*(i+1)+1], tree[:div*(i+1)+1], out)
	}

	// make sure the tree is completed
	for i := 0; i < num; i++ {
		<-out
	}

	// traverse the tree in pre-order and return the resulting Edges.
	return traverse(tree)
}

// Saves the slice of Edges containing the solution in a file
func saveRoute(route []Edge, fileName string) bool {
	file, e := os.Create(fileName)
	if e != nil {
		fmt.Println(e)
	}
	defer file.Close()

	for _, r := range route {
		fmt.Fprintln(file, r.name, r.distance)
	}

	return true
}

/* STRUCTS */
// Pool holds its name and coordinates in degrees and radians
type Pool struct {
	name string
	ltD  float64
	lnD  float64
	ltR  float64
	lnR  float64
}

// A Node holds a Pool and its children
type Node struct {
	data     Pool
	children []*Node
}

// Edge holds the name of a pool and the current distance travelled
type Edge struct {
	name     string
	distance float64
}

// Receives two Pools and returns the distance between them
func dist(p1, p2 Pool) float64 {
	return 6371 * 2 * math.Asin(math.Sqrt(math.Pow(math.Sin((p2.ltR-p1.ltR)/2), 2)+math.Pow(math.Sin((p2.lnR-p1.lnR)/2), 2)*math.Cos(p1.ltR)*math.Cos(p2.ltR)))
}

/* CREATING A TREE */
func readPools(fileName string) []Node {
	var pools []Node

	file, e := os.Open(fileName)
	if e != nil {
		fmt.Println(e)
	}
	defer file.Close()
	fileScanner := bufio.NewScanner(file)

	for fileScanner.Scan() {
		// line is split and parsed
		s := strings.Split(fileScanner.Text(), ",")
		lt, _ := strconv.ParseFloat(s[1], 64)
		ln, _ := strconv.ParseFloat(s[2], 64)

		// new node is created and appended to pools
		n := Node{data: Pool{s[0], lt, ln, (math.Pi * lt) / 180.0, (math.Pi * ln) / 180.0}}
		pools = append(pools, n)
	}

	// the slice is sorted from west to east and returned
	sort.Slice(pools, func(i, j int) bool { return pools[i].data.lnD < pools[j].data.lnD })

	return pools
}

// Creates links within a portion of the tree
func makeTree(todo, done []Node, out chan bool) {
	for i, p := range todo {
		var minDist float64 = 1000
		var minNode int
		for j := 0; j < len(done)-len(todo)+i; j++ {
			tmp := dist(p.data, done[j].data)
			if tmp < minDist {
				minDist = tmp
				minNode = j
			}
		}
		done[minNode].children = append(done[minNode].children, &todo[i])

	}
	out <- true
}

/* TRAVERSAL */
// visits nodes in preorder and sends them to channel
func trav(n *Node, c chan *Node) {
	if n == nil {
		return
	}
	c <- n
	for _, i := range n.children {
		trav(i, c)
	}
}

// receives nodes from channel in preorder,
// creates Edges and appends them to produce the solution
func traverse(tr []Node) (sol []Edge) {
	c := make(chan *Node)
	defer close(c)
	go trav(&tr[0], c)

	var distSum float64 = 0
	prev := <-c
	sol = append(sol, Edge{prev.data.name, 0})

	for i := 2; i < len(tr); i++ {
		curr := <-c
		distSum += dist(prev.data, curr.data)
		sol = append(sol, Edge{curr.data.name, distSum})
		prev = curr
	}

	return sol
}

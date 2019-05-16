Preprocessing.py
produces 
	wading-pools-java.txt, 
	wading-pools-scheme.txt, 
	wading-pools-go.txt, 
	and edits the pre-existing findRoute.pl

java -jar FindRoute.jar wading-pools-java.txt
produces solution.txt

findroute.scm produces a .txt

go run findroute.go
produces go-solution.txt
The number of go routines can be configures by editting the source code.



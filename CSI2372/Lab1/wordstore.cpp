/* LAB ONE 			Sept. 15th, 2017.		*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

#include <iostream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;
int main(){
	vector<string> v;
	string s;
	
	int sum1;
	double sum2;

	double mean;
	double dev;
	
	/* Take user input and store it in vector, unless the input is "stop" */
	cout<<"Input:\n";
	cin >> s;
	while(s != "stop"){
		v.push_back(s);
		cin >> s;
	}
	
	/* Print out what was read from the console, not including "stop" */
	for(int i=0;i<v.size();i++){
		cout<<v[i]<<" ";
	}
	cout<<"\n";
	
	/* Loop through each word in the vector to calculate mean and standard deviation. */
	for(int i=0;i<v.size();i++){
		sum1=0;
		sum2=0;
		
		/* Loop through the length of the word, summing the ASCII code of each letter. */
		for(int j=0; j<v[i].length(); j++){
			sum1+=static_cast<int>(v[i][j]);
		}
		
		/* Calculate the mean, and print the result. */
		/* sum1 is cast to avoid integer division. */
		mean = static_cast<double>(sum1)/(v[i].length());
		
		cout<<"Mean of "<<v[i]<<" : "<<mean<<"\n";
		
		/* Loop through the length of the word, calculating the sum part of the standard deviation equation, using the ASCII codes and the mean. */
		for(int j=0; j<v[i].length(); j++){
			sum2+=(static_cast<int>(v[i][j])-mean)*(static_cast<int>(v[i][j])-mean);
		}
		
		/* Finish calculating the standard deviation, and print the result. */
		dev = sqrt(sum2 / ((v[i].length())-1));
		
		cout<<"Standard Deviation : "<<dev<<"\n";
	}

	return 0;
}

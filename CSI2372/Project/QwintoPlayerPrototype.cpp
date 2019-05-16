// QwintoPlayerPrototype.cpp
#include <iostream>
#include <typeinfo>
#include <exception>
#include <string>
//#include <vector>
using namespace std;


class ScoreSheet { 
protected:
	int idPlayer;
    //string namePlayer; // = 0;  //the name of the player, 
    int overallScore; // = 0; //the overall score.  
//  bool score( int&, RollOfDice, Colour, int position = -1) = 0;
//  virtual bool score( int&, int position = -1) = 0;

public:
	int numFailed; // = 0;  //the number of failed attempts and 
	string namePlayer; // = 0;  //the name of the player, 
	ScoreSheet( );
	ScoreSheet( ScoreSheet& other ); //Copy constructor
	ScoreSheet& operator=( ScoreSheet& other );
	// You should have a virtual destructor:
    virtual ~ScoreSheet() {} ;
   //int calcTotal( . . .) = 0;
   //virtual . . . setTotal ( . . . ) = 0;
   //virtual bool operator!() const = 0;
	friend ostream& operator<<(ostream& os, const ScoreSheet & aScoreSheet); // = 0;
	virtual ostream& print(ostream& os) const = 0;// =0;
	virtual void setname(string& _name) = 0;
    virtual void setid(int& _id) = 0;
    virtual void setNumFailed(int& _numFailed) = 0;
};

ScoreSheet::ScoreSheet( ) {};
ScoreSheet::ScoreSheet( ScoreSheet& other ){}; //Copy constructor
ScoreSheet& ScoreSheet::operator=( ScoreSheet& other ){
};

class QwintoScoreSheet : public ScoreSheet{
//class QwintoScoreSheet : protected ScoreSheet{
//class QwintoScoreSheet : private ScoreSheet{	
public:
	int id;
	int numFail; // = 0;  //the number of failed attempts and 
	string name;
		
	QwintoScoreSheet( QwintoScoreSheet& other ); //Copy constructor
	QwintoScoreSheet& operator=( QwintoScoreSheet& other );
	QwintoScoreSheet(){
	  cout<<"I am QwintoScoreSheet empty Constructor\n";
	}
	QwintoScoreSheet(string _name){
	  name = _name; namePlayer=_name;
	  cout<<"I am QwintoScoreSheet Constructor with name setting\n";
	  cout<<"name is"<<name;
	}
	friend ostream& operator<<(ostream& os, const QwintoScoreSheet & aQwintoScoreSheet); // = 0;
	virtual ostream& print(ostream& os) const; // =0;
   	virtual void setname(string& _name) {name = _name; namePlayer=_name;};
    virtual void setid(int& _id) {id = _id; idPlayer=_id;};
    virtual void setNumFailed(int& _numFailed) {
		numFail = _numFailed; 
		cout<<"QwintoScoreSheet set number of failed numFail = "<<numFail<<endl;
		numFailed = _numFailed;
		cout<<"ScoreSheet set number of failed numFailed = "<<numFailed<<endl;	
	};
};
	
QwintoScoreSheet::QwintoScoreSheet( QwintoScoreSheet& other ){
	cout << "I am QwintoScoreSheet Copy constructor. \n" << endl;
	this->id = other.id;
	this->numFail = other.numFail; // = 0;  //the number of failed attempts and 
	this->name = other.name;
}; //Copy constructor

QwintoScoreSheet& QwintoScoreSheet::operator=( QwintoScoreSheet& other ){
	cout << "I am QwintoScoreSheet operator=. \n" << endl;
	if( this != &other ) {
        this->id = other.id;
		this->numFail = other.numFail; // = 0;  //the number of failed attempts and 
		this->name = other.name;
      }
      return *this;
}; 
// void QwintoScoreSheet::setname(string& _name){};

ostream& ScoreSheet::print(ostream& os) const {
// code to print the ScoreSheet class
    cout << "I am ScoreSheet class\n"<< endl;
    return os;
}

ostream& operator<<(ostream& os, const ScoreSheet & aScoreSheet)
{
    return aScoreSheet.print(os);
}

ostream& QwintoScoreSheet::print(ostream& os) const
{
    //Employee::print(os);
    // code to print the Manager class
    cout<< "I am QwintoScoreSheet class\n" << endl;
    return os;
}
ostream& operator<<(ostream& os, const QwintoScoreSheet & aQwintoScoreSheet)
{
    return aQwintoScoreSheet.print(os);
} 
class QwixxScoreSheet : public ScoreSheet{
	
	public:
	
};


class Player{
//protected:
  //string namePlayer; // = 0;  //the name of the player, 
public: 
	string namePlayer;
	virtual ~Player() {}
	ScoreSheet* pScoreSheet;
	//ScoreSheet pScoreSheet;
	friend ostream& operator<<(ostream& os, const Player & aPlayer); // = 0;
    virtual ostream& print(ostream& os) const = 0;// =0;
    virtual void setname(string& _name) = 0;
    virtual void setScoreSheet(ScoreSheet* _pScoreSheet) = 0;
};

class QwintoPlayer : public Player{

public:
	//QwintoScoreSheet* pQwintoScoreSheet;
	QwintoScoreSheet aQwintoScoreSheet;
	 	QwintoPlayer(){}
	 	QwintoPlayer(QwintoScoreSheet& _aQwintoScoreSheet){
	 		aQwintoScoreSheet = _aQwintoScoreSheet;
		 }
 	//QwintoPlayer(Player* pPlayer){}
	//QwintoPlayer(std::string _name){ namePlayer = _name;}
	~QwintoPlayer(){}
	friend ostream& operator<<(ostream& os, const QwintoPlayer & aQwintoPlayer); // = 0;
    virtual ostream& print(ostream& os) const;// =0;
    virtual void setname(string& _name) {};
	virtual void setScoreSheet(ScoreSheet* _pScoreSheet);
};

void QwintoPlayer::setScoreSheet(ScoreSheet* _pScoreSheet) {
	_pScoreSheet = new QwintoScoreSheet;
};

ostream& Player::print(ostream& os) const
{
// code to print the ScoreSheet class
    cout << "I am Player class\n"<< endl;
    return os;
}

ostream& operator<<(ostream& os, const Player & aPlayer)
{
    return aPlayer.print(os);
}

ostream& QwintoPlayer::print(ostream& os) const
{
    //Employee::print(os);
    // code to print the Manager class
    cout<< "I am QwintoPlayer class\n" << endl;
    return os;
}
ostream& operator<<(ostream& os, const QwintoPlayer & aQwintoPlayer)
{
    return aQwintoPlayer.print(os);
}



int main(){
	int id = 12345;
	int numFailed1 = 2;
	string name1 = {"Jim"};
	try{
	 int * a; cout << "a is: " << typeid(a).name() << '\n';
	} catch (exception& e) {cout << "Exception: " << e.what();}
	char* Version = new char[128]; 
	cout<<"Enter A for 'version A game' ";
	cin >> Version; // = 'A';
	//if(Version[0]=='A') cout << *Version <<endl;
	//Declaring pointers to interfaces
	 Player* pPlayer1;
	 //cout << *pPlayer1 << endl;//NOT FUNCTIONAL YET
	 ScoreSheet* pScoreSheet1;
	 //cout << *pScoreSheet1 <<typeid(pScoreSheet1).name()<< endl;
	 //user choice of the game
	if( *Version=='A') { cout << *Version <<endl;
	//instantiate objects for the game A
		//QwintoScoreSheet aQwintoScoreSheet1;
		//QwintoScoreSheet* pQwintoScoreSheet1 = new QwintoScoreSheet;
		//pQwintoScoreSheet1->setname( dynamic_cast<string&>("Jim") );
		pScoreSheet1 = new QwintoScoreSheet;
		//pQwintoScoreSheet1->setid( id );
		//pQwintoScoreSheet1->setname( name1 );
		//pScoreSheet1 = dynamic_cast<QwintoScoreSheet*> (pQwintoScoreSheet1);
		pScoreSheet1 -> setname( name1 );
		pScoreSheet1 -> setNumFailed( numFailed1 );
		//cout<<pQwintoScoreSheet1->name<<endl;
		cout<<pScoreSheet1->namePlayer<<endl;
		cout<<pScoreSheet1->numFailed<<endl;
		//pScoreSheet1 = static_cast<QwintoScoreSheet*> (pQwintoScoreSheet1);
	//	pScoreSheet1 = static_cast<QwintoScoreSheet&> (aQwintoScoreSheet1);
	//	pScoreSheet1 = (QwintoScoreSheet*) &aQwintoScoreSheet1;
	  //pScoreSheet1 = new QwintoScoreSheet(pPlayer1);
	  //pScoreSheet1 = new QwintoScoreSheet();
	  //pPlayer1 = new QwintoPlayer(pScoreSheet1->aScoreSheet);
	  //pScoreSheet1 = pPlayer1 -> pScoreSheet;
	//QwintoScoreSheet aQwintoScoreSheet1;
	//ScoreSheet& ScoreSheet1 = aQwintoScoreSheet1;
	pPlayer1 = new QwintoPlayer;
	cout << *pPlayer1 << endl;
	pPlayer1->pScoreSheet = pScoreSheet1;
	cout<<pPlayer1->pScoreSheet->namePlayer<<endl;
	cout<<pPlayer1->pScoreSheet->numFailed<<endl;
	} else {	}
	cout<<"End 'if'\n";
	//Player& aPlayer1 = *pPlayer1;
	//cout << *pPlayer1 << endl;
	try{
	  cout << "*pScoreSheet1 is: " <<typeid(pScoreSheet1).name()<< endl;
	} catch (exception& e) {cout << "Exception: " << e.what();}
	cout<<"Call ScoreSheet direct\n";
	cout << *pScoreSheet1 << endl;
	cout<<pScoreSheet1->namePlayer<<endl;
	cout<<pScoreSheet1->numFailed<<endl;
	//cout<<pQwintoScoreSheet1->name<<endl; //THIS LINE IS NONFUCTIONAL NO MORE
	cout<<"Call ScoreSheet via Player\n";
	cout<<pPlayer1->pScoreSheet->namePlayer<<endl;
	cout<<pPlayer1->pScoreSheet->numFailed<<endl;
	cout<<"Change numFailed in ScoreSheet via Player \n";
	numFailed1 = 3;
	pPlayer1->pScoreSheet->setNumFailed(numFailed1);
	cout<<pPlayer1->pScoreSheet->namePlayer<<endl;
	cout<<pPlayer1->pScoreSheet->numFailed<<endl;
	return 0;
}


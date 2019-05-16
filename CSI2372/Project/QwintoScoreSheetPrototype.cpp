// QwintoScoreSheetPrototype.cpp
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
  int numFailed; // = 0;  //the number of failed attempts and 
  int overallScore; // = 0; //the overall score.  
//  bool score( int&, RollOfDice, Colour, int position = -1) = 0;
//  virtual bool score( int&, int position = -1) = 0;

public:
	string namePlayer; // = 0;  //the name of the player, 
	// You should have a virtual destructor:
    virtual ~ScoreSheet() {}
   //int calcTotal( . . .) = 0;
   //virtual . . . setTotal ( . . . ) = 0;
   //virtual bool operator!() const = 0;
   friend ostream& operator<<(ostream& os, const ScoreSheet & aScoreSheet); // = 0;
   virtual ostream& print(ostream& os) const = 0;// =0;
   virtual void setname(string& _name) = 0;
    virtual void setid(int& _id) = 0;
};

class QwintoScoreSheet : public ScoreSheet{
//class QwintoScoreSheet : protected ScoreSheet{
//class QwintoScoreSheet : private ScoreSheet{
	
public:
	int id;
	string name;
	QwintoScoreSheet(){
	//QwintoScoreSheet(QwintoPlayer* pPlayer){
	//QwintoScoreSheet(Player* pPlayer){	
	  cout<<"I am QwintoScoreSheet Constructor\n";
	}
	QwintoScoreSheet(string _name){
	//QwintoScoreSheet(QwintoPlayer* pPlayer){
	//QwintoScoreSheet(Player* pPlayer){	
	  name = _name; namePlayer=_name;
	  cout<<"I am QwintoScoreSheet Constructor\n";
	  cout<<"name is"<<name;
	}
   friend ostream& operator<<(ostream& os, const QwintoScoreSheet & aQwintoScoreSheet); // = 0;
   virtual ostream& print(ostream& os) const; // =0;
   virtual void setname(string& _name) {name = _name; namePlayer=_name;};
    virtual void setid(int& _id) {id = _id; idPlayer=_id;};
};

// void QwintoScoreSheet::setname(string& _name){};

ostream& ScoreSheet::print(ostream& os) const
{
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
		//cout<<pQwintoScoreSheet1->name<<endl;
		cout<<pScoreSheet1->namePlayer<<endl;
		//pScoreSheet1 = static_cast<QwintoScoreSheet*> (pQwintoScoreSheet1);
	//	pScoreSheet1 = static_cast<QwintoScoreSheet&> (aQwintoScoreSheet1);
	//	pScoreSheet1 = (QwintoScoreSheet*) &aQwintoScoreSheet1;
	  //pScoreSheet1 = new QwintoScoreSheet(pPlayer1);
	  //pScoreSheet1 = new QwintoScoreSheet();
	  //pPlayer1 = new QwintoPlayer(pScoreSheet1->aScoreSheet);
	  //pScoreSheet1 = pPlayer1 -> pScoreSheet;
	//QwintoScoreSheet aQwintoScoreSheet1;
	//ScoreSheet& ScoreSheet1 = aQwintoScoreSheet1;
	} else {	}
	cout<<"End 'if'\n";
	//Player& aPlayer1 = *pPlayer1;
	//cout << *pPlayer1 << endl;
	try{
	  cout << "*pScoreSheet1 is: " <<typeid(pScoreSheet1).name()<< endl;
	} catch (exception& e) {cout << "Exception: " << e.what();}
	cout << *pScoreSheet1 << endl;
	cout<<pScoreSheet1->namePlayer<<endl;
	//cout<<pQwintoScoreSheet1->name<<endl; //THIS LINE IS NONFUCTIONAL NO MORE
	return 0;
}


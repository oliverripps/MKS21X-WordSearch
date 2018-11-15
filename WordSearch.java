import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
import java.lang.Math;
public class WordSearch{
    private char[][]data;
    //the random seed used to produce this WordSearch
    private int seed;
    //a random Object to unify your random calls
    private Random randgen;
    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;
    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    public WordSearch( int rows, int cols, String fileName, int randSeed, boolean key) throws FileNotFoundException{
      if ((rows<=0) || (cols<=0)){
        throw new IllegalArgumentException("indeces less than 1");
    }
      else{
      seed=randSeed;
      randgen = new Random(seed);
      data = new char[rows][cols];
      wordsToAdd = wordlist(fileName);
      clear();
      wordsAdded = new ArrayList<String>();
      addAllWords();
      if(key==false){
        fillpuzzle();
      }
      toString();

    }}

      public static void main(String args[]){
        if(args.length<3 || args.length>5){
          System.out.println("PUT IN DIRECTIONS");
        }
        else if (args.length==3){
          WordSearch w = new WordSearch(args[0],args[1],args[2],makeseed(),false);}//DO PARSE INT
        else if (args.length==4){
            WordSearch w = new WordSearch(args[0],args[1],args[2],args[3],false);}
        else if(args[4].equals("key")){
          WordSearch w = new WordSearch(args[0],args[1],args[2],args[3],true);}
        else {
          WordSearch w = new WordSearch(args[0],args[1],args[2],args[3],false);
        }
        System.out.println(w);
      }
    /*public WordSearch( int rows, int cols, String fileName, int randSeed)  throws FileNotFoundException{
      if ((rows <= 0) || (cols <= 0)) {
          throw new IllegalArgumentException("error, negative index(es)");}
      else{
        data = new char[rows][cols];
        wordsToAdd = new ArrayList<String>();
        wordsAdded = new ArrayList<String>();
        seed = randSeed;
        randgen = new Random(randSeed);
        clear();
        try {
          File f = new File(fileName);
          Scanner p = new Scanner(f);

          while (p.hasNext()) {
            String newWord = p.nextLine().toUpperCase();
            wordsToAdd.add(newWord);
          }

          this.addAllWords();
        } catch (FileNotFoundException e) {
          System.out.println("File not found: " + fileName);
        }
      }}*/

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for(int i=0;i<data.length;i++){
        for(int c =0;c<data[i].length;c++){
          data[i][c]='_';
        }
      }
    }

    private boolean addWord(String word, int row, int col, int rowIncrement, int columnIncrement){
		word = word.toUpperCase();
    if(row + rowIncrement * word.length() > data.length || col + columnIncrement * word.length() > data[0].length){
			return false;
		}
    for(int i = 0; i < word.length(); i++){
			char c = data[row][col];
			if(c != '_' && c != word.charAt(i)){
				return false;
			}

			row += rowIncrement;
			col += columnIncrement;
		}
    for(int i = 0; i < word.length(); i++){
			char c = data[row][col];
			data[row][col]=word.charAt(i);
      row+= rowIncrement;
      col+= columnIncrement;
			}
    return true;
		}
    private void addAllWords(){
      /*Attempt to add all of the words from the wordsToAdd list using the following algorithm:
Choose a random word, and a random direction (rowIncrement/colIncrement)
Try to add that word to different starting positions* until:
you successfully add the word
you run out of positional tries
Repeat this process until you added all of the words, or you tried to add unsuccessfully too many times in a row.*/
    }
    private void fillpuzzle(){}
    private static int makeseed(){
      int i = (int)(Math.random()*100000);
      return i;
    }
    private ArrayList<String> wordlist(String fileName){
      ArrayList<String> i= new ArrayList<String>();
      try{
      File f = new File(fileName);
      Scanner p = new Scanner(f);
      while (p.hasNext()) {
            String n = p.nextLine().toUpperCase();
            i.add(n);}}
      catch(FileNotFoundException e){
      System.out.println("File not found: " + fileName);
    }
  }


    public String toString(){
      String s = "|";
      for(int i=0;i<data.length;i++){
        for(int c =0;c<data[i].length;c++){
          s+=data[i][c];
          if(c<data[i].length-1){
            s+=" ";
          }
          else{
            s+="|";
        }
        }
      if(i<data.length-1){
        s+="\n|";
      }
      }
      s+="\n";
      s+=wordsAdded.toString();
      s+="("+seed+")";
      return s;
    }
}

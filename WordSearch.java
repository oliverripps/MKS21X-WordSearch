import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
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

    public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
      Random r = new Random();
      int rn= r.nextInt() % 1000;
      Random rng = new Random(rn);
      seed= rng.nextInt();
      char[][] n = new char[rows][cols];
      data=n;
      clear();
      ArrayList<String> wordsToAdd = new ArrayList<String>();
      try{
      File f = new File(fileName);
      Scanner p = new Scanner(f);
      while( p.hasNext()){
        String line = p.nextLine();
        wordsToAdd.add(line);
}
    }catch(FileNotFoundException e){
      System.out.println("File not found: " + fileName);
    }
  }
    public WordSearch( int rows, int cols, String fileName, int randSeed){
      //  Both will read in the word text file, then run addAllWords(). Do not fill in random letters after.
}


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

    public String toString(){
      String s = "|";
      for(int i=0;i<data.length;i++){
        for(int c =0;c<data[i].length;c++){
          s+=data[i][c];
          if(c<data[i].length-1){
            s+=" ";
          }
          else{
            s+="| \n";
        }
        }
      if(i<data.length-1){
        s+="|";
      }
      }
      return s;
    }
}

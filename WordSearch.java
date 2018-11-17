import java.util.*;
import java.io.*;
import java.lang.Math;
public class WordSearch{

    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    public WordSearch( int rows, int cols, String fileName, int randSeed, boolean key) throws FileNotFoundException{
      if ((rows<=0) || (cols<=0)){
        throw new IllegalArgumentException("Indeces less than 1");
    }
      else{
      seed=randSeed;
      randgen = new Random(seed);
      data = new char[rows][cols];
      wordsToAdd = wordlist(fileName);
      clear();
      wordsAdded = new ArrayList<String>();
      addAllWords();
      fillpuzzle(key);
      toString();

    }}
      private void fillpuzzle(boolean b){
        if (!b){
          for(int i=0;i<data.length;i++){
            for(int c =0;c<data[i].length;c++){
              if(data[i][c]=='_'){
                data[i][c]=' ';
              }
            }}}
        else{
          Random r = new Random();
          for(int i=0;i<data.length;i++){
            for(int c =0;c<data[i].length;c++){
              if(data[i][c]=='_'){
                char d = (char)(r.nextInt(26)+'a');
                data[i][c]=d;}
              }}}}

      public static void main(String args[]){
        if(args.length<3 || args.length>5){
          System.out.println("PUT IN DIRECTIONS");
        }
        else if (args.length==3){
          WordSearch w = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],makeseed(),false);
          System.out.println(w);}
        else if (args.length==4){
          WordSearch w = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],Integer.parseInt(args[3]),false);
          System.out.println(w);}
        else if (args[4].equals("key")){
          WordSearch w = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],Integer.parseInt(args[3]),true);
          System.out.println(w);}
        else {
          WordSearch w = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],Integer.parseInt(args[3]),false);
          System.out.println(w);}
      }
    private void clear(){
      for(int i=0;i<data.length;i++){
        for(int c =0;c<data[i].length;c++){
          data[i][c]='_';
        }
      }
    }

    private boolean addWord(String word, int row, int col, int rowIncrement, int columnIncrement){
		word = word.toUpperCase();
    if(col + columnIncrement * word.length() > data[0].length || row + rowIncrement * word.length() > data.length){
			return false;}
    for(int i = 0; i < word.length(); i++){
			char c = data[row][col];
			if(c != '_' && c != word.charAt(i)){
				return false;}
			col += columnIncrement;
      row += rowIncrement;}
    for(int i = 0; i < word.length(); i++){
			char c = data[row][col];
			data[row][col]=word.charAt(i);
      col+= columnIncrement;
      row+= rowIncrement;}
    return true;}
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

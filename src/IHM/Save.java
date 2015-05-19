package IHM;
import java.awt.*;
import java.*;
import java.util.*;
import java.lang.Math;
import java.io.*;

import Modele.Joueur;


// a metre dans le moteur 
public class Save{

public void sauvegarde( int gaufre [][], Joueur j1, Joueur j2){
  int i,j;
  //nb ligne et colonne matrice
  String text = gaufre.length + " " + gaufre[0].length + "\n";

  //sauvegarde matrice
  for(i=0;i<gaufre.length;i++){
    for(j=0;j<gaufre[0].length;j++){
      text = text + gaufre[i][j] + " ";
    }
    text= text + "\n";
  }

  //param joueur11.
  int iaj1 = j1.getOrdi() ? 1 : 0;
  int tourj1 = j1.getTour() ? 1 : 0;
  text = text +iaj1 + " " + j1.getScore() + " " + tourj1 + "\n";

  //param joueur2
  int iaj2 = j2.getOrdi() ? 1 : 0;
  int tourj2 = j2.getTour() ? 1 : 0;
  text = text +iaj2 + " " + j2.getScore() + " " + tourj2 + "\n";
  FileWriter writer = null;
  try{
    writer = new FileWriter("save.txt", true);
    writer.write( text, 0, text.length());
  }
  catch(IOException ex){
    ex.printStackTrace();
  }finally{
    try{
    if(writer != null){
      writer.close();
    }
    }catch(Exception e){}
    
  }

}

public void charger( String filePath){
  int i,j;
  int c = 0,l = 0;
  try{
    Scanner scanner = new Scanner( new File( filePath));
    try{
    boolean iaj1,iaj2;
    if(scanner.hasNextInt()){
      l = scanner.nextInt();
    }
    if(scanner.hasNextInt()){
      c = scanner.nextInt();
    }
    int [][]gaufre = new int[l][c];
    for(i=0;i<c;i++){
      for(j=0;j<l;j++){
	  if(scanner.hasNextInt()){
	    gaufre[i][j]= scanner.nextInt();
	  }
      }
    }
    
    if(scanner.hasNextInt()){
	if (scanner.nextInt() == 1)
	  iaj1 = true;
	else
	  iaj1 = false;
    }
    if(scanner.hasNextInt()){
      int scorej1 = scanner.nextInt();
    }
    if(scanner.hasNextInt()){
      int tourj1 = scanner.nextInt();
    }
    if(scanner.hasNextInt()){
      if (scanner.nextInt() == 1)
	  iaj2 = true;
	else
	  iaj2 = false;
      }
    if(scanner.hasNextInt()){
      int scorej2 = scanner.nextInt();
    }
    if(scanner.hasNextInt()){
      int tourj2 = scanner.nextInt();
    }
    }catch(Exception e){}finally{
      scanner.close();
    }
  }catch(IOException ioe){
    System.out.println("Erreur : " + ioe.toString());
  }
  }

}
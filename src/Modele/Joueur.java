package Modele;

import java.awt.*;
import java.*;
import java.util.*;
import java.lang.Math;

import Moteur.Coup;
import Moteur.Moteur;

public abstract class Joueur{

  int score;


  public Joueur(){

    this.score = 0;

  }
  
  public Joueur(int sc){
    this.score = sc;
  }


  public void setScore(int s){
    score = s;
  }
  public int getScore(){
    return this.score;
  }

  public abstract Coup jouer(Moteur m);

}
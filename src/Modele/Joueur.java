package Modele;

import java.awt.*;
import java.*;
import java.util.*;
import java.lang.Math;

import Moteur.Coup;
import Moteur.Moteur;

public abstract class Joueur{
  
  int score;
  boolean estOrdi;
  boolean estMonTour;
  
  public Joueur(boolean b, boolean t){
	this.estOrdi = b;
    this.score = 0;
    this.estMonTour = t;
  }
  
  public Joueur(boolean b){
    this.estOrdi = b;
    this.score = 0;
    this.estMonTour = false;
  }
  
  public Joueur(int score, boolean b){
  
    this.score = score;
    this.estOrdi = b;
  }
  
  public void setScore(int s){
    score = s;
  }
  public int getScore(){
    return this.score;
  }

  public boolean getOrdi(){
    return this.estOrdi;
  }
  public boolean getTour(){
    return this.estMonTour;
  }
  public abstract Coup jouer(Moteur m);

}
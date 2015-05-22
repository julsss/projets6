package Modele;

import java.awt.*;
import java.*;
import java.util.*;
import java.lang.Math;

import Moteur.Moteur;


public class Joueur{
  
  int score;
  boolean estOrdi;
  boolean estMonTour;
  Moteur m;
  public Joueur(boolean b, boolean t, Moteur m){
	this.estOrdi = b;
    this.score = 0;
    this.estMonTour = t;
    this.m=m;
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
  
  public int getScore(){
    return this.score;
  }
  
  public boolean getOrdi(){
    return this.estOrdi;
  }
  public boolean getTour(){
    return this.estMonTour;
  }
}
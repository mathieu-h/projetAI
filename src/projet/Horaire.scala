package projet;

import JaCoP.scala.jacop
import JaCoP.scala._

object Horaire extends jacop {

  def main(args: Array[String]): Unit = {
    // 2 series
    val series = List("serie1","serie2");
	val serie1 = 0;
    val serie2 = 1;
    
    // 4 professeurs
    val profs = List("grolaux", "vandermeulen","seront","frank");
    val grolaux = 0;
    val vandermeulen = 1;
    val seront = 2;
    val frank = 3;
    
    // inventer les matieres
    val matieres = List("IA","C++","Reseau","Android");
    val ia = 0;
    val cplusplus = 1;
    val reseau = 2;
    val android = 3;
    
    // 2 locaux exercices (019, 017)
    val locaux = List("017","019");
    val l017 = 0;
    val l019 = 1;
    
    // HORAIRE
    // creer deux list correspondant aux deux horaires (un par série)
    // elles auront 40 "cases", chaque indice correspond a un cours (exemple lundi a 8H30 => indice 0 du tableau)
    // chaque case d'un meme tableau comporte un élément "cours" qui est lui meme composé d'un professeur , d'une matiere et d'un local
  
    // dans un tableau on peut avoir x fois la meme matiere (en fonction du nombre de fois qu'il est donné dans la semaine)
    // mais par contre on ne peut pas avoir 
    // un cours qui a le même professeur ou le meme local au meme indice dans les 2 tableaux en meme temps => un prof ne peut pas etre
    // a deux endroits a la fois
    
    // COURS
    // Pour définir un cours, on agrege trois choses : une matiere, un professeur et un local
    
    // Soit un cours qui est un liste comportant les trois, chacun définit via un IntVar sur lequel on mettra des 
    // contraintes telles que ( ce prof doit etre représenté trois fois avec ce cours la, 2 fois pour ce cours la) 
    
    val horaireS1 = for(i <- List.range(0,39)) yield (IntVar("profs",0,3),IntVar("matieres",0,3),IntVar("locaux",0,1));
    val horaireS2 = for(i <- List.range(0,39)) yield (IntVar("profs",0,3),IntVar("matieres",0,3),IntVar("locaux",0,1));
    
    for(i <- List.range(0, 39)){
      // deux profs ne peuvent pas etre au meme indice des deux horaires (un prof ne peut pas donner deux cours en meme temps)
      horaireS1(i)._1 #\= horaireS2(i)._1; 
      // deux cours ne peuvent pas être au même local pour les deux séries en même temps
      horaireS1(i)._3 #\= horaireS2(i)._3;      
      // suite de l algo
    }
    
  }
}
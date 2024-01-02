class Memory extends Program{
    final String[][] CATEGORIE=new String[][]{{"hello","bonjour"},
                                              {"bye","au revoir"},
                                              {"welcome","bienvenue"},
                                              {"today","aujourd'hui"},
                                              {"red","rouge"},
                                              {"green","vert"},
                                              {"blue","bleu"},
                                              {"tomorrow","demain"}};

    void testTypes(){
        Carte[] cc=newCarteParPaire(CATEGORIE[1]);
        assertEquals("c1=bye; c2=au revoir",toString(cc));
        Carte c1=selectCarte(0,cc);
        Carte c2=selectCarte(1,cc);
        assertEquals("bye",toString(c1));
        assertEquals("au revoir",toString(c2));
        Joueur j=newJoueur("azertyuiop");
        assertEquals("pseudo : azertyuiop; points : 0; victoires : 0",toString(j));
        Carte[] cc1=newCarteParPaire(CATEGORIE[2]);
        Carte[] cc2=newCarteParPaire(CATEGORIE[3]);
        Carte[] cc3=newCarteParPaire(CATEGORIE[4]);
        Carte[] pl=new Carte[] {selectCarte(0,cc),selectCarte(1,cc),selectCarte(0,cc1),selectCarte(1,cc1),selectCarte(0,cc2),selectCarte(1,cc2),selectCarte(0,cc3),selectCarte(1,cc3),};
        Plateau p=newPlateau(pl);
        assertEquals("bye; au revoir; welcome; bienvenue; today; aujourd'hui; red; rouge; ",toString(p));
    }

    Carte[] newCarteParPaire(String[] valeur){
        Carte c1=new Carte();//initialise c1
        Carte c2=new Carte();//initialise c2
        c1.valeur=valeur[0];//etablis la valeur de c1 au premier élément devaleur
        c2.valeur=valeur[1];//etablis la valeur de c2 au deuxième élément de valeur
        c1.paire=c1;//
        c2.paire=c2;
        c1.decouvert=false;
        c2.decouvert=false;
        Carte[] retour=new Carte[]{c1,c2};
        return retour;
    }

    String toString(Carte[] paire){
        return "c1="+paire[0].valeur+"; c2="+paire[1].valeur;
    }

    Carte selectCarte(int nbCarte, Carte[] paire){
        return paire[nbCarte];
    }

    String toString(Carte carte){
        return carte.valeur;
    }

    Joueur newJoueur(String pseudo){
        Joueur j=new Joueur();
        j.points=0;
        j.pseudo=pseudo;
        return j;
    }

    String toString(Joueur j){
        return "pseudo : "+j.pseudo+"; points : "+j.points;
    }

    Plateau newPlateau(Carte[] plateau){
        Plateau p=new Plateau();
        p.plateau=plateau;
        return p;
    }

    String toString(Plateau plateau){
        String retour = "";
        for (int indiceI =0 ; indiceI< length(plateau.plateau);indiceI++){
            retour+= toString(plateau.plateau[indiceI]) + "; ";
        }
        return retour;
    }

    Carte[] stringToCarte(String[][] categorie){
        Carte[] retour=new Carte[length(categorie,1)*2];
        for (int i=0;i<length(retour);i+=2){
            Carte[] cc=newCarteParPaire(categorie[i/2]);
            retour[i]=cc[0];
            retour[i+1]=cc[1];
        }
        return retour;
    }

    String affiche(Plateau plateau){
        String retour="";
        double nombreDimension = Math.sqrt(length(plateau.plateau));
        final char NEW_LINE = '\n';
        final String display = "*** Welcome to your favourite game of Memory! *** " + NEW_LINE;
        retour+=display;
        int compteur=0;

        for(int indiceI = 0; indiceI < nombreDimension; indiceI++){
            for (int indiceJ = 0 ; indiceJ < nombreDimension; indiceJ++){
                retour+="----  ";
            }
            retour+=NEW_LINE;
            for (int indiceJ = 0 ; indiceJ < nombreDimension; indiceJ++){
                if(plateau.plateau[indiceI].decouvert){
                    compteur++;
                    if(compteur>9){
                        retour+="|"+ANSI_RED+compteur+ANSI_WHITE+"|  ";
                    }else{
                        retour+="|"+ANSI_RED+compteur+ANSI_WHITE+" |  ";
                    }
                }else{
                    compteur++;
                    if(compteur>9){
                        retour+="|"+ANSI_BLUE+compteur+ANSI_WHITE+"|  ";
                    }else{
                        retour+="|"+ANSI_BLUE+compteur+ANSI_WHITE+" |  ";
                    }
                }
            }
            retour+=NEW_LINE;
            for (int indiceJ = 0 ; indiceJ < nombreDimension; indiceJ++){
                retour+="----  ";
            }
            retour+=NEW_LINE;
        }
        return retour;
    }

    void consigne(){
        final char NEW_LIGNE = '\n';
        println(NEW_LIGNE+
            "  ======================================================================= " + NEW_LIGNE+
            "||                             " + ANSI_RED+"Memory"+ ANSI_WHITE+"                                    ||"+ NEW_LIGNE+
            "  ======================================================================="+NEW_LIGNE);
        println("ienvenue dans le jeu de Memory !"+NEW_LIGNE+

            "Le Memory est un jeu de société classique qui met à l'épreuve votre mémoire."+NEW_LIGNE+
            "Le but du jeu est de trouver toutes les paires de cartes correspondantes."+NEW_LIGNE+

            "Règles du jeu :"+NEW_LIGNE+
            "1. Le jeu se joue avec un ensemble de cartes face cachée."+NEW_LIGNE+
            "2. Retournez deux cartes à la fois pour trouver des paires."+NEW_LIGNE+
            "3. Si les cartes que vous retournez ne sont pas identiques, retournez-les face cachée."+NEW_LIGNE+
            "4. Rappelez-vous de l'emplacement des cartes que vous avez retournées pour pouvoir former des paires."+NEW_LIGNE+
            "5. Continuez à retourner les cartes jusqu'à ce que toutes les paires aient été trouvées."+NEW_LIGNE+
            "6. Le joueur qui trouve le plus grand nombre de paires gagne."+NEW_LIGNE+

            NEW_LIGNE+"Conseils :"+NEW_LIGNE+
            "- Concentrez-vous et mémorisez l'emplacement des cartes."+NEW_LIGNE+
            "- Jouez avec des amis pour rendre le jeu plus amusant et compétitif."+NEW_LIGNE+

            NEW_LIGNE+"Amusez-vous bien en jouant au Memory !"+NEW_LIGNE);
    }

    Joueur [] initialiseJoeur(){
        int taille;
        do{
            println(""+ANSI_BLUE+"Combien de joueur êtes vous ? (max 4)"+ANSI_WHITE);
            taille = readInt();
        }while(taille>4||taille<=0);
        Joueur [] listeJoueur = new Joueur[taille];

        for (int indice = 0; indice < taille ; indice++){
            println("Saisir le pseudo de joueur "+ (indice+1)+" SVP=  ");
            listeJoueur[indice] = newJoueur(readString());
        }
        return listeJoueur;
    }

   /* boolean coupValide(int coup,Plateau plateau,int nbCoup){
        if(nbCoup==1){
            if (coup[0]==null||plateau.plateau[coup[0]].decouvert==true||coup[0]<0||coup[0]>=length(plateau.plateau)){
                return false;
            }
            return true;
        }
        if (coup[1]==null||plateau.plateau[coup[1]].decouvert==true||coup[1]<0||coup[1]>=length(plateau.plateau)||coup[1]==coup[0]){
            return false;
        }
        return true;
    }
    */

/*
    void pseudoalgorithm(){
        init joueur
        init plateau+afficher
        joueur qui joue = p1
        do{
            joueur = joueur qui joue;
            print stat joueur
            coup=readint[]
            if coupvalide{
                si(coup == est paire){
                    pointsjoueur++
                    Carte paire = decouverte
                    joueur qui joue = joueur
                }sinon{
                    joueur qui joue = autre joueur
                }
            }
        }while(il y a paire && coup!=stop)
        print(gagnant)
    }


     ______
    |      |
    |      |
    |______|

*/

    void algorithm(){
        Carte[] cc=newCarteParPaire(CATEGORIE[1]);
        Carte[] cc1=newCarteParPaire(CATEGORIE[2]);
        Carte[] cc2=newCarteParPaire(CATEGORIE[3]);
        Carte[] cc3=newCarteParPaire(CATEGORIE[4]);
        Carte[] pl=stringToCarte(CATEGORIE);
        Plateau p=newPlateau(pl);
        Joueur[] j=initialiseJoeur();
        println(affiche(p));
    }
}
import java.util.Scanner;

class Main{
    static int bois = 0;
    static int pierre = 0;
    static int or = 50;
    static int nourriture = 100;
    static int habitants = 1;
    static boolean mineBuilt = false;
    static boolean validDay = true;
    static boolean castleBuilt = false;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        while (habitants > 0 && !castleBuilt) {
            validDay = true;
            displayRessources();
            displayMenu();
            int action = getAction();
            
            switch(action){
                case 1:
                    exploreForest();
                    break;

                case 2:
                    createMine();
                    break;

                case 3:
                    workInMine();
                    break;

                case 4:
                    recruitSoldier();
                    break;

                case 5:
                    trade();
                    break;

                case 6:
                    buildCastle();
                    break;

                default:
                System.out.println("OUPS. Il y a un soucis.");
                validDay = false;
            }
        
            if(validDay){
                nourriture -= habitants;
                if (nourriture <= 0){
                    habitants += nourriture;
                    nourriture = 0;
                }
            }
        }

        scanner.close();
        if(!castleBuilt){
            System.out.println("Tous les habitants sont morts, Game Over !");
        }
        
        
    }
    /**
     * affiche le menu d'actions possibles à l'utilisateur
     */
    static void displayMenu(){
        System.out.println("Que voulez-vous faire");
        System.out.println();
        System.out.println("Explorer la forêt (tapez 1): ");
        System.out.println("Créer une mine (tapez 2): ");
        System.out.println("Travailler à la mine (tapez 3): ");
        System.out.println("Recruter un soldat (tapez 4): ");
        System.out.println("Commercer (tapez 5): ");
        System.out.println("Construire le château (tapez 6): ");
        
    }
    /**
     * demande l'action à réaliser à l'utilisateur
     * @return {int} le numéro de l'action à réaliser ( 1 à 6 )
     */
    static int getAction(){
       
        boolean actionIsValid = false;
        int action = 0;
        
        while (!actionIsValid) {
            try{
                System.out.print("votre action: ");
                String choice = scanner.nextLine();
                System.out.println();
                int choiceNumber = Integer.parseInt(choice);
                

                if(choiceNumber < 1 || choiceNumber > 6){
                    System.out.println("action inconnue (choisissez un nombre entre 1 et 6)");
                    
                }else{
                    action = choiceNumber;
                    actionIsValid = true;
                }

            
            
            }catch(Exception e){
                System.out.println("action inconnue (choisissez un nombre entre 1 et 6)");
            }
        }
        
        
        return action;
    }
    /**
     * function qui permet d'explorer la foret
     */
    static void exploreForest(){
        System.out.println("Vous avez trouvé 5 de bois et 3 de Nourriture");
        bois += 5;
        nourriture += 3;
    }
    /**
     * function qui permet de créer une mine si les ressources de bois sont suffisante (>=10)
     */
    static void createMine(){
        if (mineBuilt){
            System.out.println("La mine est déjà créée.");
            validDay = false;
        }else if(bois >= 10){
            bois -= 10;
            mineBuilt = true;
            System.out.println("Mine créée. Elle a nécessité 10 bois.");
        }else{
            validDay = false;
            int boisManquant = 10 - bois;
            System.out.printf("Il vous manque %d bois pour créer une mine.", boisManquant);
            System.out.println();
        }
    }
    /**
     * fonction qui permet de recruter un soldat si le nombre d'or' (>=30) est suffisant
     * le village gagne 1 habitant
     */
    static void recruitSoldier(){
        if(or >= 30){
            habitants++;
            or -= 30;
            System.out.println(("Félicitation, vous avez recruté un soldat. Mais cela vous a coûté 30 or."));
        }else{
            validDay = false;
            int orManquant = 30 - or;
            System.out.printf("Désolé il vous manque %d pour recruter un soldat.", orManquant);
            System.out.println();
        }
    }

    /**
     * function qui permet de travailler à la mine (si elle est construite)
     */
    static void workInMine(){
        if (mineBuilt == true){
                nourriture -= 5;
                pierre += 5;
                or += 2;
                System.out.println("Exploration de la mine fructueuse. Vous avez gagné 5 pierres et 2 d'or mais vous avez consommé 5 rations de nourriture.");
        } else {
            System.out.println("La mine n'a pas encore été créée");
            validDay = false;
        }
    }
    /**
     * fonction qui permet de construire le chateau si le nombre de ressources le permette
     */
    static void buildCastle(){
        if(
            bois < 100 ||
            pierre < 100 ||
            or < 200 ||
            habitants < 40
        ){
            if(bois < 100){
                int boisManquant = 100 - bois;
                System.out.printf("Il vous manque %d bois pour construire votre château", boisManquant);
                System.out.println();
            }
            if(pierre < 100){
                int pierreManquant = 100 - pierre;
                System.out.printf("Il vous manque %d pierre pour construire votre château", pierreManquant);
                System.out.println();
            }
            if(or < 200){
                int orManquant = 200 - or;
                System.out.printf("Il vous manque %d or pour construire votre château",orManquant);
                System.out.println();
            }
            if(habitants < 40){
                int habitantsManquant = 40 - habitants;
                System.out.printf("Il vous manque %d habitants pour construire votre château",habitantsManquant);
                System.out.println();
            }

            validDay = false;
        }else{
            System.out.println("FÉLICITATIONS VOUS AVEZ CONSTRUIT UN MAGNIFIQUE CHATEAU !!!");
            castleBuilt = true;
        }
    }
    /**
     * function qui permet de commercer
     */
    static void trade(){
        if (pierre >= 5){
            pierre -= 5;
            or += 10;
        } else{
            int pierreManquante = 5 - pierre;
            System.out.printf("Il vous manque %d pierres pour commercer.", pierreManquante);
            System.out.println();
            validDay = false;
        }
    }
    /**
     * function qui permet d'afficher les ressources
     */
    static void displayRessources(){
        System.out.println();
        System.out.println("Vos ressources :");
        System.out.printf("%d Bois | %d Pierre(s) | %d Or | %d Ration(s) de nourriture | %d Habitant(s)", bois, pierre, or, nourriture, habitants);
        System.out.println();
        System.out.println();
    }
}
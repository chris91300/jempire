import java.util.Scanner;
import java.util.concurrent.*;
import java.util.Random;
import java.lang.Math;


class Main{
    static int totalLoop = 0;
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
            action( getAction() );  
        
            thereMightBeAnAttack();

            
            if(validDay){
                nourriture -= habitants;
                if (nourriture <= 0){
                    habitants += nourriture;
                    nourriture = 0;
                }

                totalLoop++;
            }
        }

        scanner.close();
        if(!castleBuilt){
            genericLoose();
        }
        
        
    }


    static void action(int actionNumber){
        switch(actionNumber){
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
                    System.out.println("========== ERREUR =========");
                    System.out.println("|                         |");
                    System.out.println("| OUPS. Il y a un soucis. |");
                    System.out.println("|                         |");
                    System.out.println("===========================");
                validDay = false;
            }
    }
    /**
     * affiche le menu d'actions possibles à l'utilisateur
     */
    static void displayMenu(){
        System.out.println("================ JEMPIRE ================");
        System.out.println("|                                       |");
        System.out.println("|         Que voulez-vous faire         |");
        System.out.println("|                                       |");
        System.out.println("|     Explorer la forêt     | (tapez 1) |");
        System.out.println("|       Créer une mine      | (tapez 2) |");
        System.out.println("|   Travailler à la mine    | (tapez 3) |");
        System.out.println("|     Recruter un soldat    | (tapez 4) |");
        System.out.println("|         Commercer         | (tapez 5) |");
        System.out.println("|    Construire le château  | (tapez 6) |");
        System.out.println("|                                       |");
        System.out.println("=========================================\n");
        System.out.println();
        
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
                    System.out.println("====================== ATTENTION ======================");
                    System.out.println("|                                                     |");
                    System.out.println("| action inconnue (choisissez un nombre entre 1 et 6) |");
                    System.out.println("|                                                     |");
                    System.out.println("=======================================================\n");
                    
                }else{
                    action = choiceNumber;
                    actionIsValid = true;
                }

            
            
            }catch(Exception e){
                System.out.println("====================== ATTENTION ======================");
                System.out.println("|                                                     |");
                System.out.println("| action inconnue (choisissez un nombre entre 1 et 6) |");
                System.out.println("|                                                     |");
                System.out.println("=======================================================\n");
            }
        }
        
        
        return action;
    }
    /**
     * function qui permet d'explorer la foret
     */
    static void exploreForest(){
        System.out.println("====================== NOTIFICATION =====================");
        System.out.println("|                                                       |");
        System.out.println("| Vous avez trouvé 5 de Bois et 3 Rations de nourriture |");
        System.out.println("|                                                       |");
        System.out.println("=========================================================\n");
        bois += 5;
        nourriture += 3;
    }
    /**
     * function qui permet de créer une mine si les ressources de bois sont suffisante (>=10)
     */
    static void createMine(){
        if (mineBuilt){
            System.out.println("======== ATTENTION ========");
            System.out.println("|                         |");
            System.out.println("| La mine est déjà créée. |");
            System.out.println("|                         |");
            System.out.println("===========================");
            validDay = false;
        }else if(bois >= 10){
            bois -= 10;
            mineBuilt = true;
            System.out.println("============== NOTIFICATION =============");
            System.out.println("|                                       |");
            System.out.println("| Mine créée. Elle a nécessité 10 bois. |");
            System.out.println("|                                       |");
            System.out.println("=========================================\n");
        }else{
            validDay = false;
            int boisManquant = 10 - bois;
            String boisToString = formatNumber(boisManquant);
            System.out.println("=================== ATTENTION ===================");
            System.out.println("|                                               |");
            System.out.printf("| Il vous manque %s bois pour créer une mine. |\n", boisToString);
            System.out.println("|                                               |");
            System.out.println("=================================================\n");
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
            System.out.println("=============================== NOTIFICATION ================================");
            System.out.println("|                                                                           |");
            System.out.println("| Félicitations, vous avez recruté un soldat. Mais cela vous a coûté 30 or. |");
            System.out.println("|                                                                           |");
            System.out.println("=============================================================================\n");
        }else{
            validDay = false;
            int orManquant = 30 - or;
            String orManquantToString = formatNumber(orManquant);
            System.out.println("====================== ATTENTION ======================");
            System.out.println("|                                                     |");
            System.out.printf("|   Il vous manque %s d'Or pour recruter un soldat. |\n", orManquantToString);
            System.out.println("|                                                     |");
            System.out.println("=======================================================\n");

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
                System.out.println("======================================================= NOTIFICATION ======================================================");
                System.out.println("|                                                                                                                         |");
                System.out.println("| Exploration de la mine fructueuse. Vous avez gagné 5 pierres et 2 d'or mais vous avez consommé 5 rations de nourriture. |");
                System.out.println("|                                                                                                                         |");
                System.out.println("===========================================================================================================================\n");
            } else {
                System.out.println("============= ATTENTION ============");
                System.out.println("|                                  |");
                System.out.println("| La mine n'a pas encore été créée |");
                System.out.println("|                                  |");
                System.out.println("====================================\n");
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
                String boisManquantToString = formatNumber(boisManquant);
                System.out.println("======================= ATTENTION ========================");
                System.out.println("|                                                        |");
                System.out.printf("| Il vous manque %s bois pour construire votre château |\n", boisManquantToString);
                System.out.println("|                                                        |");
                System.out.println("==========================================================\n");
            }
            if(pierre < 100){
                int pierreManquant = 100 - pierre;
                String pierreManquantToString = formatNumber(pierreManquant);
                System.out.println("======================== ATTENTION =========================");
                System.out.println("|                                                          |");
                System.out.printf("| Il vous manque %s pierre pour construire votre château |\n", pierreManquantToString);
                System.out.println("|                                                          |");
                System.out.println("============================================================\n");

            }
            if(or < 200){
                int orManquant = 200 - or;
                String orManquantToString = formatNumber(orManquant);
                System.out.println("======================= ATTENTION ========================");
                System.out.println("|                                                        |");
                System.out.printf("| Il vous manque %s d'Or pour construire votre château |\n",orManquantToString);
                System.out.println("|                                                        |");
                System.out.println("==========================================================\n");
            }
            if(habitants < 40){
                int habitantsManquant = 40 - habitants;
                String habitantsManquantToString = formatNumber(habitantsManquant);
                System.out.println("========================== ATTENTION ==========================");
                System.out.println("|                                                             |");
                System.out.printf("| Il vous manque %s habitants pour construire votre château |\n",habitantsManquantToString);
                System.out.println("|                                                             |");
                System.out.println("===============================================================\n");
            }

            validDay = false;
        }else{
            System.out.println("============================ WINNER ===========================");
            System.out.println("|                                                             |");
            System.out.println("| FÉLICITATIONS VOUS AVEZ CONSTRUIT UN MAGNIFIQUE CHATEAU !!! |");
            System.out.println("|                                                             |");
            System.out.println("===============================================================\n");
            castleBuilt = true;
            genericWon();
        }
    }
    /**
     * function qui permet de commercer
     */
    static void trade(){
        if (pierre >= 5){
            pierre -= 5;
            or += 10;
            System.out.println("================ NOTIFICATION ================");
            System.out.println("|                                            |");
            System.out.println("| Vous avez échangé 5 Pierres contre 10 d'Or | ");
            System.out.println("|                                            |");
            System.out.println("==============================================\n");
        } else{
            int pierreManquante = 5 - pierre;
            String pierreManquantetToString = formatNumber(pierreManquante);
            System.out.println("====================== ATTENTION ======================");
            System.out.println("|                                                     |");
            System.out.printf("|     Il vous manque %s pierres pour commercer.     |\n", pierreManquantetToString);
            System.out.println("|                                                     |");
            System.out.println("=======================================================\n");
            validDay = false;
        }
    }
    /**
     * function qui permet d'afficher les ressources
     */
    static void displayRessources(){
        
        String pierreToString = formatNumber(pierre);
        String boisToString = formatNumber(bois);
        String orToString = formatNumber(or);
        String nourritureToString = formatNumber(nourriture);
        String habitantsToString = formatNumber(habitants);
        System.out.println();
        System.out.println("====================================== VOS RESSOURCES ======================================");
        System.out.println("|                                                                                          |");
        System.out.printf("|  %s Bois | %s Pierre(s) | %s Or | %s Ration(s) de nourriture | %s Habitant(s)  |\n", boisToString, pierreToString, orToString, nourritureToString, habitantsToString);
        System.out.println("|                                                                                          |");
        System.out.println("============================================================================================\n");
    }

    static void genericWon(){
        
        System.out.println("");
        System.out.println("");
        Runnable sautDeLigne = () -> System.out.println("");
        Runnable l1 = () ->  System.out.println("                    ** ** ** **                    ");
        Runnable l2 = () ->  System.out.println("                    ** ** ** **                    ");
        Runnable l3 = () ->  System.out.println("                    ***********                    ");
        Runnable l4 = () ->  System.out.println("                    ***********                    ");
        Runnable l5 = () ->  System.out.println("                    ****   ****                    ");
        Runnable l6 = () ->  System.out.println("       ** ** ** **  ****   ****  ** ** ** **       ");
        Runnable l7 = () ->  System.out.println("       ** ** ** **  ***********  ** ** ** **       ");
        Runnable l8 = () ->  System.out.println("       *************************************       ");
        Runnable l9 = () ->  System.out.println("       *************************************       ");
        Runnable l10 = () -> System.out.println("       ****   ***********************   ****       ");
        Runnable l11 = () -> System.out.println("       ****   ***********************   ****       ");
        Runnable l12 = () -> System.out.println("       ****************     ****************       ");
        Runnable l13 = () -> System.out.println("       ****************     ****************       ");
        Runnable l14 = () -> System.out.println("       ****************     ****************       "); 

        Runnable sautWithBorder =  () ->   System.out.println("       *                                   *       ");
        Runnable border =  () -> System.out.println("       *************************************       ");// 7 37 7
        Runnable showDev = () -> System.out.println("       *           DÉVELOPPEURS            *       ");// 12 attention border right et left
        Runnable showQuentin = ()->System.out.println("       *        Quentin  DEHAINAULT        *       ");
        Runnable showChristophe = ()-> System.out.println("       *         Christophe MICHEL         *       ");// 17
        Runnable showMerci = () -> System.out.println("       *  UN GRAND MERCI À NOTRE FORMATEUR *       ");// 32
        Runnable showFlavien = ()->System.out.println("       *             Flavien               *       ");// 7
        Runnable showThanks = ()-> System.out.println("       *   Thanks to all the participants  *       ");// 30
        Runnable showEnd = () -> System.out.println("       *                END                *       ");// 3
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        // On planifie l'exécution après 1 secondes
        scheduler.schedule(l1, 300, TimeUnit.MILLISECONDS);
        scheduler.schedule(l2, 600, TimeUnit.MILLISECONDS);
        scheduler.schedule(l3, 900, TimeUnit.MILLISECONDS);
        scheduler.schedule(l4, 1200, TimeUnit.MILLISECONDS);
        scheduler.schedule(l5, 1500, TimeUnit.MILLISECONDS);
        scheduler.schedule(l6, 1800, TimeUnit.MILLISECONDS);
        scheduler.schedule(l7, 2100, TimeUnit.MILLISECONDS);
        scheduler.schedule(l8, 2400, TimeUnit.MILLISECONDS);
        scheduler.schedule(l9, 2700, TimeUnit.MILLISECONDS);
        scheduler.schedule(l10, 3000, TimeUnit.MILLISECONDS);
        scheduler.schedule(l11, 3300, TimeUnit.MILLISECONDS);
        scheduler.schedule(l12, 3600, TimeUnit.MILLISECONDS);
        scheduler.schedule(l13, 3900, TimeUnit.MILLISECONDS);
        scheduler.schedule(l14, 4200, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 4500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 5000, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 5500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 6000, TimeUnit.MILLISECONDS);
        scheduler.schedule(border, 6500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 7000, TimeUnit.MILLISECONDS);
        scheduler.schedule(showDev, 6500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 7000, TimeUnit.MILLISECONDS);
        scheduler.schedule(showQuentin, 7500, TimeUnit.MILLISECONDS);
        scheduler.schedule(showChristophe, 8000, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 8500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 9000, TimeUnit.MILLISECONDS);
        scheduler.schedule(showMerci, 9500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 10000, TimeUnit.MILLISECONDS);
        scheduler.schedule(showFlavien, 10500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 11000, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 11500, TimeUnit.MILLISECONDS);
        scheduler.schedule(showThanks, 12000, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautWithBorder, 12500, TimeUnit.MILLISECONDS);
        scheduler.schedule(showEnd, 13000, TimeUnit.MILLISECONDS);
        scheduler.schedule(border, 13500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 14000, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 14500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 15000, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 15500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 16000, TimeUnit.MILLISECONDS);

        // N'oublie pas de fermer le scheduler proprement plus tard
        scheduler.shutdown();
    }

    static void genericLoose(){
         
        System.out.println("");
        System.out.println("");
        Runnable sautDeLigne = () -> System.out.println("");
        Runnable l1 = () ->  System.out.println("                        ***                        ");
        Runnable l2 = () ->  System.out.println("                        ***                        ");
        Runnable l3 = () ->  System.out.println("                    ***********                    ");
        Runnable l4 = () ->  System.out.println("                    ***********                    ");
        Runnable l5 = () ->  System.out.println("                        ***                        ");
        Runnable l6 = () ->  System.out.println("                        ***                        ");
        Runnable l7 = () ->  System.out.println("                        ***                        ");
        Runnable l8 = () ->  System.out.println("                        ***                        ");
        Runnable l9 = () ->  System.out.println("                  ***************                  ");
        Runnable l10 = () -> System.out.println("                   ***************                 ");
        Runnable l11 = () -> System.out.println("                    ***************                ");
        Runnable l12 = () -> System.out.println("                     ***************               ");
        Runnable l13 = () -> System.out.println("                      ***************              ");
        Runnable l14 = () -> System.out.println("                       ***************             "); 
        Runnable l15 = () -> System.out.println("                                                   ");        
        Runnable l16 = () -> System.out.println("                                                   "); 
        Runnable l17 = () -> System.out.println("       *************************************       ");
        Runnable l18 = () -> System.out.println("       *                                   *       ");
        Runnable l19 = () -> System.out.println("       *                PERDU              *       ");
        Runnable l20 = () -> System.out.println("       *                                   *       ");
        Runnable l21 = () -> System.out.println("       *   VOS HABITANTS SONT TOUS MORTS   *       ");
        Runnable l22 = () -> System.out.println("       *                                   *       ");
        Runnable l23 = () -> System.out.println("       *************************************       ");



        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        // On planifie l'exécution après 1 secondes
        scheduler.schedule(l1, 300, TimeUnit.MILLISECONDS);
        scheduler.schedule(l2, 600, TimeUnit.MILLISECONDS);
        scheduler.schedule(l3, 900, TimeUnit.MILLISECONDS);
        scheduler.schedule(l4, 1200, TimeUnit.MILLISECONDS);
        scheduler.schedule(l5, 1500, TimeUnit.MILLISECONDS);
        scheduler.schedule(l6, 1800, TimeUnit.MILLISECONDS);
        scheduler.schedule(l7, 2100, TimeUnit.MILLISECONDS);
        scheduler.schedule(l8, 2400, TimeUnit.MILLISECONDS);
        scheduler.schedule(l9, 2700, TimeUnit.MILLISECONDS);
        scheduler.schedule(l10, 3000, TimeUnit.MILLISECONDS);
        scheduler.schedule(l11, 3300, TimeUnit.MILLISECONDS);
        scheduler.schedule(l12, 3600, TimeUnit.MILLISECONDS);
        scheduler.schedule(l13, 3900, TimeUnit.MILLISECONDS);
        scheduler.schedule(l14, 4200, TimeUnit.MILLISECONDS);
        scheduler.schedule(l15, 4500, TimeUnit.MILLISECONDS);
        scheduler.schedule(l16, 4800, TimeUnit.MILLISECONDS);
        scheduler.schedule(l17, 5100, TimeUnit.MILLISECONDS);
        scheduler.schedule(l18, 5400, TimeUnit.MILLISECONDS);
        scheduler.schedule(l19, 5700, TimeUnit.MILLISECONDS);
        scheduler.schedule(l20, 6000, TimeUnit.MILLISECONDS);
        scheduler.schedule(l21, 6300, TimeUnit.MILLISECONDS);
        scheduler.schedule(l22, 6600, TimeUnit.MILLISECONDS);
        scheduler.schedule(l23, 6900, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 7200, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 7500, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 7800, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 8100, TimeUnit.MILLISECONDS);
        scheduler.schedule(sautDeLigne, 8400, TimeUnit.MILLISECONDS);

        // N'oublie pas de fermer le scheduler proprement plus tard
        scheduler.shutdown();
    }

    static String formatNumber(int number){
        String numberFormatted = "";
        if( number < 10 ){
            numberFormatted = "   "+number;
        }else if (number < 100 ){
            numberFormatted = "  "+number;
        }else if (number < 1000){
            numberFormatted = " "+number;
        }else {
            numberFormatted = ""+number;
        }
        return numberFormatted;
    }

    static void thereMightBeAnAttack(){
        double random = Math.random();
        if(random <= 0.95){
            Math.exp(random);
            int soldiers =  (int)( Math.exp(random * totalLoop * 0.15) );
            
            String soldiersToString = formatNumber(soldiers);
            System.out.println("======================== ALERTE =======================");
            System.out.println("|                                                     |");
            System.out.println("|               Votre village est attaqué             |");
            System.out.printf("|                   %s soldat(s)                     |\n", soldiersToString);
            System.out.println("|                                                     |");
            System.out.println("=======================================================\n");


            if( habitants >= soldiers ){
                int habitantsDead = (int) (Math.random() * soldiers);
                habitants -= habitantsDead;
                String habitantsToString = formatNumber(habitantsDead);
                String text = "";
                System.out.println("========================= BRAVO =======================");
                System.out.println("|                                                     |");
                System.out.println("|               Vous avez gagné la bataille           |");
                if( habitantsDead > 0 ){
                    System.out.printf("|        mais vous avez perdu %s habitant(s)         |\n", habitantsToString);
                }else{
                    System.out.printf("|              vous avez perdu aucun habitant         |\n", habitantsToString);
                }
                
                System.out.println("|                                                     |");
                System.out.println("=======================================================\n");
            }else{
                habitants -= soldiers;
                System.out.println("========================= DOMMAGE =====================");
                System.out.println("|                                                     |");
                System.out.println("|               Vous avez perdu la bataille           |");
                System.out.println("|           vous avez perdu tout vos habitants        |");
                System.out.println("|                                                     |");
                System.out.println("=======================================================\n");
            }
            
            
        }
    }
}

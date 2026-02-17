import java.util.Scanner;

class Main{
    static int bois = 0;
    static int pierre = 0;
    static int or = 50;
    static int nourriture = 100;
    static int habitants = 1;
    static boolean mineBuilt = false;
    static boolean validDay = true;
    public static void main(String[] args){
        while (habitants > 0) {
            validDay = true;

        }
        System.out.println("Tous les habitants sont morts, Game Over !");
    }


    /**
     * affiche le menu d'actions possibles à l'utilisateur
     */
    static void displayMenu(){
        System.out.println("Que voulez-vous faire");
        System.out.println("Explorer la forêt (tapez 1): ");
        System.out.println("Créer une mine (tapez 2): ");
        System.out.println("Travailler à la mine (tapez 3): ");
        System.out.println("Recruter un soldat (tapez 4): ");
        System.out.println("Commercer (tapez 5): ");
        System.out.println("Construire le château (tapez 6): ");
        System.out.println("votre action: ");
    }


    /**
     * demande l'action à réaliser à l'utilisateur
     * @return {int} le numéro de l'action à réaliser ( 1 à 6 )
     */
    static int getAction(){
        Scanner scanner = new Scanner(System.in);
        boolean actionIsValid = false;
        int action = 0;
        
        while (!actionIsValid) {
            try{
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice < 1 || action > 6){
                throw new Exception();
            }

            action = choice;
            actionIsValid = true;
            
            }catch(Exception e){
                System.out.println("action inconnue (choisissez un nombre entre 1 et 6)");
            }
        }
        
        scanner.close();
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
        if(bois >= 10){
            bois -= 10;
            mineBuilt = true;
            System.out.println("Mine créée. Elle a nécessité 10 bois.");
        }else{
            int boisManquant = 10 - bois;
            System.out.printf("Il vous manque %d bois pour créer une mine.", boisManquant);
            System.out.println();
        }
    }
    /**
     * function qui permet de travailler à la mine (si elle est construite)
     */
    static void workInMine(){
        if (mineBuilt == true){
            if (nourriture >= 5) {
                nourriture -= 5;
                pierre += 5;
                or += 2;
                System.out.println("Exploration de la mine fructueuse. Vous avez gagné 5 pierres et 2 d'or mais vous avez consommé 5 rations de nourriture.");
            } else {
                int nourritureManquante = 5 - nourriture;
                System.out.printf("Il vous manque %d rations de nourriture pour travailler à la mine.", nourritureManquante);
                System.out.println();
                validDay = false;
            }
        } else {
            System.out.println("La mine n'a pas encore été créée");
            validDay = false;
        }
    }
}
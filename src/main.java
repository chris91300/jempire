import java.util.Scanner;

class Main{

    public static void main(String[] args){

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
}
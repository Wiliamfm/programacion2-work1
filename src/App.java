import java.util.Scanner;

import model.ManageDAO;

public class App {

    private static Scanner sc;
    private static ManageDAO m;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);
        m = new ManageDAO();
        int out = 2;
        while (out == 2) {
            System.out.println(
                    "Enter a requst: \nREQ1: Upload data. \nREQ2: Assign ID. \nREQ3: Find by microship. \nREQ4: Count by species. \nREQ5: Find dangerous pets in a specific neighborhood.  \nREQ6: Find by multiple fields.");
            String option = sc.nextLine();
            switch (option) {
                case "REQ1":
                    if (m.uploadData()) {
                        System.out.println("El proceso de carga del archivo ha finalizado");
                    } else {
                        System.out.println("Error during the files upload");
                    }
                    break;
                case "REQ2":
                    if (m.assignID(m.getPetsList())) {
                        System.out.println("El proceso de asignación de IDs ha finalizado");
                    } else {
                        System.out.println("Error during assign IDs");
                    }
                    break;
                case "REQ3":
                    System.out.println("Enter the pet´s microship: ");
                    long ms = Long.parseLong(sc.nextLine());
                    System.out.println(m.findByMicroship(ms).toString());
                    break;
                case "REQ4":
                    System.out.println("Enter the specie to search: ");
                    String specie = sc.nextLine();
                    System.out.println(
                            "El número de animales de la especie " + specie + " es: " + m.countBySpecies(specie));
                    break;
                case "REQ5":
                    System.out.println("Enter the index: ");
                    int n = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter the TOP or LAST parameter:");
                    String p = sc.nextLine();
                    System.out.println("Enter the neighborhood:");
                    String nh = sc.nextLine();
                    String[] pets = m.findBypotentDangerousInNeighborhood(n, p, nh);
                    for (int i = 0; i < pets.length; i++) {
                        System.out.println(pets[i]);
                    }
                    break;
                case "REQ6":
                    System.out.println("Enter the Species:");
                    String species = sc.nextLine();
                    System.out.println("Enter the sex: ");
                    String sex = sc.nextLine();
                    System.out.println("Enter the Size:");
                    String size = sc.nextLine();
                    System.out.println("Enter the Dangerous:");
                    String potentDangerous = sc.nextLine();
                    String[] pets2 = m.findByMultipleFields(species, sex, size, potentDangerous);
                    for (int i = 0; i < pets2.length; i++) {
                        System.out.println(pets2[i]);
                    }
                    break;
            }
            System.out.println("Do you want to exit the program? \n1.Yes. \t2.No.");
            try {
                out = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                out = 1;
            }
        }
    }
}

import java.util.Scanner;

import model.ManageDAO;

public class App {

    private static Scanner sc;
    private static ManageDAO m;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in, "ISO-8859-1");
        m = new ManageDAO();
        int out = 2;
        while (out == 2) {
            try {
                System.out.println(
                        "Enter a request: \n1: Upload data. \n2: Assign ID. \n3: Find by microchip. \n4: Count by species. \n5: Find dangerous pets in a specific neighborhood.  \n6: Find by multiple fields.  \n7: Exit the program.");
                String option = sc.nextLine();
                switch (option) {
                    case "1":
                        if (m.uploadData()) {
                            System.out.println("El proceso de carga del archivo ha finalizado.");
                        } else {
                            System.out.println("Error during the files upload");
                        }
                        break;
                    case "2":
                        if (m.assignID(m.getPetsList())) {
                            System.out.println("El proceso de asignación de IDs ha finalizado.");
                        } else {
                            System.out.println("Error during assign IDs");
                        }
                        break;
                    case "3":
                        System.out.println("Enter the pet´s microchip: ");
                        long ms = Long.parseLong(sc.nextLine());
                        System.out.println(m.findByMicroship(ms).toString());
                        break;
                    case "4":
                        System.out.println("Enter the specie to search: ");
                        String specie = sc.nextLine();
                        System.out.println(
                                "El número de animales de la especie " + specie + " es: " + m.countBySpecies(specie));
                        break;
                    case "5":
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
                    case "6":
                        System.out.println("Enter the species:");
                        String species = sc.nextLine();
                        System.out.println("Enter the sex: ");
                        String sex = sc.nextLine();
                        System.out.println("Enter the size:");
                        String size = sc.nextLine();
                        System.out.println("Enter the dangerous:");
                        String potentDangerous = sc.nextLine();
                        System.out.println(size);
                        String[] pets2 = m.findByMultipleFields(species, sex, size, potentDangerous);
                        for (int i = 0; i < pets2.length; i++) {
                            System.out.println(pets2[i]);
                        }
                        break;
                    case "7":
                    	System.out.println("Exit the program.");
                    	break;
                }
            } catch (Exception e) {
                System.out.println("Error in input.");
            }
            System.out.println("¿Do you want to exit the program? \n1. Yes. \t2. No.");
            try {
                out = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                out = 1;
            }
        }
    }
}

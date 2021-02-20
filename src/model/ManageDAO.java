package model;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.fourpawscitizens.Pet;

public class ManageDAO {

  private File file;
  private FileReader fr;
  private BufferedReader br;
  private ArrayList<Pet> petsList;
  private boolean b = false;

  public ManageDAO() {
    this.file = new File(".\\data\\pets-citizens.csv");
  }

  /**
   * Upload the pets register file.
   * 
   * @return True if the upload was successfull; False if wasn´t.
   */
  public boolean uploadData() {
    try {
      fr = new FileReader(file);
      br = new BufferedReader(fr);
      petsList = new ArrayList<Pet>();
      String line = "";
      // skip headel
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] aux = line.split(";");
        try {
          if (aux.length == 6) {
            Pet auxP = new Pet("NO-ID", Long.parseLong(aux[0]), aux[1], aux[2], aux[3], convertToBoolean(aux[4]),
                aux[5]);
            petsList.add(auxP);
          }
        } catch (NumberFormatException e) {
          // TODO: handle exception
        }
      }
      b = true;
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Assign an ID to all pets.
   * 
   * @return True if all pets has been assigned an ID; False if hasn´t.
   */
  public boolean assignID(ArrayList<Pet> pets) {
    if (b) {
      for (int i = 0; i < pets.size(); i++) {
        Pet pet = pets.get(i);
        pet.setId(setID(pet, 3));
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * set an ID to a pet.
   * 
   * @param pet   the pet to assign the ID.
   * @param index the actual index of the micrship to assign to the ID.
   * @return the String with the corresponding ID.
   */
  private String setID(Pet pet, int index) {
    String aux = Long.toString(pet.getMicrochip());
    String id = "";
    for (int i = aux.length() - 1; i >= 0; i--) {
      id.concat(String.valueOf(aux.charAt(i)));
      index -= 1;
      if (index == 0) {
        break;
      }
    }
    id.concat("-" + pet.getSpecies().charAt(0) + pet.getSex().charAt(0) + pet.getSize()
        + getBooleanValue(pet.getPotentDangerous()) + "-" + pet.getNeighborhood());
    if (searchID(id)) {
      return setID(pet, index + 1);
    } else {
      return id;
    }
  }

  /**
   * Search the id in the pets list.
   * 
   * @param id the id to search.
   * @return True if the id was found, False other way.
   */
  private boolean searchID(String id) {
    for (int i = 0; i < petsList.size(); i++) {
      if (id.equals(petsList.get(i).getId())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return the character of the id for the potentDangerous attribute.
   * 
   * @param v the boolean attribute.
   * @return the String of the ID attribute for potentDangerous.
   */
  private String getBooleanValue(boolean v) {
    if (v) {
      return "T";
    } else {
      return "F";
    }
  }

  /**
   * convert potentDangerous attribute to boolean value.
   * 
   * @return the boolean value.
   */
  private boolean convertToBoolean(String value) {
    if (value.equalsIgnoreCase("si")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Find a pet given by his micrship.
   * 
   * @param ms the microship to search
   * @return a pet object.
   */
  public Pet findByMicroship(long ms) {
    if (b) {
      for (int i = 0; i < petsList.size(); i++) {
        if (ms == petsList.get(i).getMicrochip()) {
          return petsList.get(i);
        }
      }
      return null;
    } else {
      return null;
    }
  }

  /**
   * count the number of pets given by his species.
   * 
   * @param specie the specie to search.
   * @return the number of pets corresponding to the species.
   */
  public int countBySpecies(String specie) {
    if (b) {
      int n = 0;
      for (int i = 0; i < petsList.size(); i++) {
        if (specie.equalsIgnoreCase(petsList.get(i).getSpecies())) {
          n++;
        }
      }
      return n;
    } else {
      return 0;
    }
  }

  /**
   * Find the pets in the neigborhood that are dangerous.
   * 
   * @param n the index to start to search.
   * @param p the position to search, TOP or LAST.
   * @param n the neigborhood to search.
   * @return list of the pets that comply wiht the parameters.
   */
  public String[] findBypotentDangerousInNeighborhood(int n, String p, String nh) {
    if (b) {
      ArrayList<Pet> pets = new ArrayList<Pet>();
      switch (p) {
        case "TOP":
          for (int i = 0; i < petsList.size(); i++) {
            if (nh.equalsIgnoreCase(petsList.get(i).getNeighborhood()) && n > 0) {
              pets.add(petsList.get(i));
              n--;
            }
          }
          break;
        case "LAST":
          for (int i = petsList.size() - 1; i >= 0; i++) {
            if (nh.equalsIgnoreCase(petsList.get(i).getNeighborhood()) && n > 0) {
              pets.add(petsList.get(i));
              n--;
            }
          }
          break;
      }
      String[] lPets = new String[pets.size()];
      for (int i = 0; i < lPets.length; i++) {
        lPets[i] = pets.get(i).toString();
      }
      return lPets;
    } else {
      return null;
    }
  }

  public String[] findByMultipleFields(String sex, String species, String size, String potentDangerous) {
    if (b) {
      boolean pd = convertToBoolean(potentDangerous);
      ArrayList<Pet> pets = new ArrayList<Pet>();
      for (int i = 0; i < petsList.size(); i++) {
        if (sex.equalsIgnoreCase(petsList.get(i).getSex()) && species.equalsIgnoreCase(petsList.get(i).getSpecies())
            && size.equalsIgnoreCase(petsList.get(i).getSize()) && pd == petsList.get(i).getPotentDangerous()) {
          pets.add(petsList.get(i));
        }
      }
      String[] lPets = new String[pets.size()];
      for (int i = 0; i < lPets.length; i++) {
        lPets[i] = pets.get(i).toString();
      }
      return lPets;
    } else {
      return null;
    }
  }

  // set and get methods.

  public ArrayList<Pet> getPetsList() {
    return petsList;
  }
}

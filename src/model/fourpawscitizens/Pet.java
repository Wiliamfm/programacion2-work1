package model.fourpawscitizens;

public class Pet {

    private String id;
    private long microchip;
    private String species;
    private String sex;
    private String size;
    private boolean potentDangerous;
    private String neighborhood;

    /**
     * Constructor of the class Pet.
     * 
     * @param id              the id given by a String.
     * @param microchip       the microchip given by a long.
     * @param species         the species given by a String.
     * @param sex             the sex given by a String.
     * @param size            the size given by a String.
     * @param potentDangerous the potentDangerous given by a boolean.
     * @param neighborhood    the neighborhood given by a String.
     */
    public Pet(String id, long microchip, String species, String sex, String size, boolean potentDangerous,
            String neighborhood) {
        this.id = id;
        this.microchip = microchip;
        this.species = species;
        this.sex = sex;
        this.size = size;
        this.potentDangerous = potentDangerous;
        this.neighborhood = neighborhood;
    }

    /**
     * override the toString method to show all attributes for Pet class.
     * 
     * @return return the string with all the information of Pet class.
     */
    @Override
    public String toString() {
        return "id: " + id + " microchip: " + microchip + " species: " + species + " sex: " + sex + " size: " + size
                + " potentDangerous: " + potentDangerous + " neigborhood:" + neighborhood;
    }

}

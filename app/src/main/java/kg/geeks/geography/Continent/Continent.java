package kg.geeks.geography.Continent;

import java.io.Serializable;

public class Continent implements Serializable {

    private String image;
    private String continent;

    public Continent(String image, String continent) {
        this.image = image;
        this.continent = continent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}

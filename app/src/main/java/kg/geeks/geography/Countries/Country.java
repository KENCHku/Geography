package kg.geeks.geography.Countries;

import java.io.Serializable;

public class Country implements Serializable {

    private String country;
    private String capital;
    private String flag;
    private String language;

    public Country(String country, String capital, String flag, String language) {
        this.country = country;
        this.capital = capital;
        this.flag = flag;
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

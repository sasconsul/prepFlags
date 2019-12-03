package net.sasconsul.jackson;

import java.util.List;

public class Continent {
    int id;
    String continent;
    List<Country> countries;

    public Continent(int id, String continent, List<Country> countries) {
        this.id = id;
        this.continent = continent;
        this.countries = countries;
    }

    public String getContinent() {
        return continent;
    }

    public List<Country> getCountries() {
        return countries;
    }

}

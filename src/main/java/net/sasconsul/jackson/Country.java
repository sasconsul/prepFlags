package net.sasconsul.jackson;


import java.util.Objects;

public class Country {
    String name;
    String flag;

    public Country(String country, String flag) {
        this.name = country;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country1 = (Country) o;
        return name.equals(country1.name) &&
                flag.equals(country1.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, flag);
    }
}

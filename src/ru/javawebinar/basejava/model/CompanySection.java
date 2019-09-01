package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class CompanySection implements Section {
    private List<Company> descriptions;

    public CompanySection(List<Company> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Company> get() {
        return descriptions;
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "descriptions=" + descriptions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return descriptions.equals(that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }
}

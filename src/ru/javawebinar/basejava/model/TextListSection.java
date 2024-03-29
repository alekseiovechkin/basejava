package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TextListSection extends Section {
    private static final long serialVersionUID = 1L;
    private List<String> descriptions;

    public TextListSection() {
    }

    public TextListSection(String... descriptions) {
        this(Arrays.asList(descriptions));
    }

    public TextListSection(List<String> descriptions) {
        Objects.requireNonNull(descriptions, "descriptions must not be null");
        this.descriptions = descriptions;
    }

    public List<String> get() {
        return descriptions;
    }

    @Override
    public String toPrintHtml() {
        StringBuilder result = new StringBuilder();
        result.append("<ul>");
        for (String string : descriptions) {
            result.append("<li>").append(string).append("</li>");
        }
        result.append("</ul>");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextListSection that = (TextListSection) o;
        return descriptions.equals(that.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptions);
    }

    @Override
    public String toString() {
        return "TextListSection{" +
                "descriptions=" + descriptions +
                '}';
    }
}

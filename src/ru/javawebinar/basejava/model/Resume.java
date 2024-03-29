package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContact() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public String getContacts(ContactType type) {
        return contacts.get(type);
    }

    public String sectionByType(SectionType type) {
        if (!sections.containsKey(type)) {
            return "";
        }
        switch (type) {
           case OBJECTIVE:
           case PERSONAL:
                return ((TextSection) sections.get(type)).get();
           case ACHIEVEMENT:
           case QUALIFICATIONS:
                return String.join("\n", ((TextListSection) sections.get(type)).get());
           default:return "";
        }
    }

    private CompanySection companySection(String sectionType) {
        return (CompanySection)sections.get(SectionType.valueOf(sectionType));
    }

    public List<Company> getCompanyList(String sectionType) {
        List<Company> companies = companySection(sectionType).get();
        Collections.sort(companies);
        return companies;
    }

    public List<Company.Position> getPositionList(String sectionType, String companyName) {
        return companySection(sectionType).getCompany(companyName).getPositions();
    }

    public void addContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void addSection(SectionType sectionType,Section section) {
        sections.put(sectionType, section);
    }

    public void deleteCompany(String sectionType, String companyName) {
        companySection(sectionType).deleteCompany(companyName);
    }

    public void deletePosition(String sectionType, String companyName, String positionTitle) {
        companySection(sectionType).deletePosition(companyName, positionTitle);
    }

    public void addCompany(String sectionType, Company company) {
        companySection(sectionType).addCompany(company);
    }

    public void addPosition(String sectionType, String companyName, Company.Position position) {
        companySection(sectionType).addPosition(companyName, position);
    }

    public Company getCompany(String sectionType, String companyName) {
        return companySection(sectionType).getCompany(companyName);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        int compareFullName = fullName.compareTo(o.getFullName());
        return compareFullName != 0 ?
                                compareFullName :
                                uuid.compareTo(o.getUuid());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) &&
                fullName.equals(resume.fullName) &&
                contacts.equals(resume.contacts) &&
                sections.equals(resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }
}

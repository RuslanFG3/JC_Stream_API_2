import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<PersonData.Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new PersonData.Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    PersonData.Sex.values()[new Random().nextInt(PersonData.Sex.values().length)],
                    PersonData.Education.values()[new Random().nextInt(PersonData.Education.values().length)])
            );
        }

        System.out.println("Несовершеннолетних граждан: " + persons.stream()
                .filter(person -> person.getAge() < 18)
                .count());

        List<String> personsСonscript = persons.stream()
                .filter(person -> person.getSex() == PersonData.Sex.MAN)
                .filter(person -> person.getAge() > 18 && person.getAge()<27)
                .map(person -> person.toString()).collect(Collectors.toList());
        System.out.println(personsСonscript);

        System.out.println(persons.stream()
                .filter(person -> person.getEducation()==PersonData.Education.HIGHER)
                .filter(person -> ((person.getSex() ==PersonData.Sex.MAN && person.getAge()> 18 && person.getAge()<65)
                        || (person.getSex() ==PersonData.Sex.WOMAN && person.getAge()> 18 && person.getAge()<60)))
                .sorted(Comparator.comparing(PersonData.Person::getFamily))
                .collect(Collectors.toList()));
    }
}
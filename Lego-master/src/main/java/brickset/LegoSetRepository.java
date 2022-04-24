package brickset;

import repository.Repository;

import java.time.Year;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     *
     *   Returns whether each legoset has @param word in tags.
     *
     * @return Returns whether each legoset has @param word in tags.
     */
    public boolean returnsIfThereIsALegoSteWithTagName(String word){
        return  getAll().stream()
                .map(LegoSet::getTags)
                .anyMatch(tags ->tags.contains(word));
    }

    /**
     * prints every tag if the starting letter is the same letter as the param
     * @param letter this a variable which contains the starting letter
     */
    public  void  listAllLegoSetWithThe(String letter){
        getAll().stream()
                .filter(brickset -> brickset.getTags() != null)
                .flatMap(brickset ->brickset.getTags().stream())
                .filter(s->s.startsWith(letter.toUpperCase(Locale.ROOT)))
                .forEach(System.out::println);

    }

    /**
     * prints out the maximum number of pieces of the legset
     */
    public void printMaxOfLegoPieces() {
        getAll().stream()
                .map(LegoSet::getPieces)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    /**
     * Returns a Map object, that contains a summary of the legosets' years and their frequency.
     *
     * @return {@code Map<Year, Long>} object wrapping how many legosets have the same year.
     */
    public Map<Year, Long> getYearSummary() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getYear, Collectors.counting()));
    }
    /**
     * Returns a Map object, that contains every theme and their Packaging type.
     *
     * @return {@code Map<String, Set<PackagingType>>} object wrapping the legosets' themes and their Packaging type.
     */
    public Map<String, Set<PackagingType>> getMapOfThemesWithTheirPackagingType() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getTheme,
                        Collectors.mapping(LegoSet::getPackagingType,
                                Collectors.filtering(Objects::nonNull,
                                        Collectors.toSet()))));
    }
    // ...

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));
        System.out.println(repository.returnsIfThereIsALegoSteWithTagName("Car"));
        repository.listAllLegoSetWithThe("C");
        repository.printMaxOfLegoPieces();
        System.out.println(repository.getYearSummary());
        System.out.println(repository.getMapOfThemesWithTheirPackagingType());



    }

}
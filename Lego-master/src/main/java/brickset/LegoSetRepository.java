package brickset;

import repository.Repository;

import java.util.Comparator;

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
     * Returns the number of LEGO sets with the theme specified.
     *
     * @param theme a LEGO set theme
     * @return the number of LEGO sets with the theme specified
     */
    public long countLegoSetsByThemes(String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme()!= null && legoSet.getTheme().contains(theme))
                .count();
    }
    /**
     * Returns the number of LEGO sets bigger than the number darab
     *
     * @param darab a LEGO set of number of tags
     * @return the number of LEGO sets with more then the amount of darab
     */
    public long countLegoSetsBiggerThan(int darab) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags()!= null && legoSet.getTags().size()>darab)
                .count();
    }
    /**
     * Prints every LEGO sets name in alphabetical order which is filtered by theme and the number of piceces
     */
    public char[] printLegoSetsNameinAlphabeticalOrderByThemeAndNum(String theme, int number) {
        getAll().stream().
                filter(legoSet -> legoSet.getTheme()!=null && legoSet.getTheme().contains(theme) && legoSet.getPieces()>number).
                map(legoSet -> legoSet.getName()).
                sorted(Comparator.nullsFirst(Comparator.naturalOrder())).
                forEach(System.out::println);
        return new char[0];
    }
    /**
     * Prints out the average length of the LegoSet names
     */
    public double printAvgLengthOfLegoSetName(){
        return getAll().stream().
                map(LegoSet::getName).
                mapToInt(String::length).
                average().
                getAsDouble();

    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));
        System.out.println(repository.countLegoSetsByThemes("Education"));
        System.out.println(repository.countLegoSetsBiggerThan(11));
        System.out.println(repository.printLegoSetsNameinAlphabeticalOrderByThemeAndNum("Duplo",100));
        System.out.println(repository.printAvgLengthOfLegoSetName());


        // ...
    }


}

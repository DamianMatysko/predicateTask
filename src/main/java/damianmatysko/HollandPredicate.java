package damianmatysko;


import java.util.function.Predicate;
import java.util.regex.Pattern;

public class HollandPredicate implements Predicate<WordX> {
    @Override
    public boolean test(WordX wordX) {
        String string = ".*Holland$";
        Pattern pattern = Pattern.compile(string);
        return pattern.matcher(wordX.getDistrict()).find();
    }
}

package StepProject.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Destination {
    BAKU("BAKU"),
    ISTANBUL("ISTANBUL"),
    PARIS("PARIS"),
    ANKARA("ANKARA"),
    LONDON("LONDON"),
    NEWYORK("NEWYORK"),
    BERLIN("BERLIN"),
    FRANKFURT("FRANKFURT");
    private final String code;
    Destination(String code) {
        this.code =code;
    }
    public String getCode(){return code ;}
    private static final List<Destination> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Destination randomDestination()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

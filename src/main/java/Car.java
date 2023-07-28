import lombok.Data;
import java.util.regex.Pattern;

@Data
public class Car {
    private static final Pattern PATTERN_OF_CAR_NAME = Pattern.compile("[А-Яа-яA-Za-z1-9\\s]+");

    protected static boolean checkFullNameCarByPatterns(String fullNameCar, StatisticVoting statisticVoting){
        if(statisticVoting.getStatisticInMap().containsKey(fullNameCar)){
            System.out.println("Данный автомобиль уже учавствует в голосовании");
            return false;
        }
        String[] arrayWords = fullNameCar.split("\\s");
        if (arrayWords.length > 3 || arrayWords.length < 2) {
            System.out.println("В названии должно быть не более трёх слов и не менее двух слов");
        }
        if (!fullNameCar.matches(String.valueOf(PATTERN_OF_CAR_NAME))){
            System.out.println("Название автомобиля должно состоять только из букв латиницы или кириллицы и(или) цифр");
            return false;
        }
      return true;
    }
}

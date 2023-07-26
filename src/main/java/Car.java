import lombok.Data;

import java.util.Map;
import java.util.regex.Pattern;

@Data
public class Car {
    private static final Pattern PATTERN_OF_CAR_BRAND = Pattern.compile("[А-Яа-яA-Za-z\\s]+");
    private static final Pattern PATTERN_OF_CAR_MODEL = Pattern.compile("[А-Яа-яA-Za-z1-9]+");

    protected static boolean checkFullNameCarByPatterns(String fullNameCar, StatisticVoting statisticVoting){
        if(statisticVoting.getStatisticInMap().containsKey(fullNameCar)){
            System.out.println("Данный автомобиль уже учавствует в голосовании");
            return false;
        }
        String[] arrayWords = fullNameCar.split("\\s");
        if (arrayWords.length > 3 || arrayWords.length < 2) {
            System.out.println("В названии должно быть не более трёх слов и не менее двух слов");
        }
        String brand = "";
        String model = "";
        for (int i = 0; i < arrayWords.length; i++){
            if(i == arrayWords.length - 1){
                model = arrayWords[i];
                continue;
            }
            brand = brand + arrayWords[i] + " ";
        }
        if (!brand.matches(String.valueOf(PATTERN_OF_CAR_BRAND))){
            System.out.println("Марка автомобиля должна состоять только из букв латиницы или кириллицы");
            return false;
        }
        if (!model.matches(String.valueOf(PATTERN_OF_CAR_MODEL))){
            System.out.println("Модель автомобиля должна состоять только из букв латиницы или кириллицы (и)или цифр");
            return false;
        }
      return true;
    }
}

import java.util.Collections;
import java.util.Scanner;

public class Voting {
    StatisticVoting statisticVoting = new StatisticVoting();

    protected void startVoting() {
        Scanner console = new Scanner(System.in);
        System.out.println("Голосование за автомобиль года\n");
        System.out.print("Сколько моделей учавствует в голосовании?: ");
        int numberOfCarsInTheVoting = console.nextInt();
        while (numberOfCarsInTheVoting > 99) {
            System.out.println("Максимальное число автомобилей в голосовании - 99");
            System.out.print("Сколько моделей учавствует в голосовании?: ");
            numberOfCarsInTheVoting = console.nextInt();
        }
        int i = 1;
        while (i <= numberOfCarsInTheVoting) {
            console = new Scanner(System.in);
            System.out.print("Введите модель " + i + "-го автомобиля: ");
            String fullNameCar = console.nextLine();
            fullNameCar = fullNameCar.replaceAll("\\s+", " ").strip();
            if (!Car.checkFullNameCarByPatterns(fullNameCar, statisticVoting)) continue;
            statisticVoting.getStatisticInMap().put(fullNameCar, 0);
            i++;
        }
        System.out.println("\nГолосование создано!");
    }

    protected void voting() {
        System.out.print("Выберите модель из списка: ");
        statisticVoting.getStatisticInMap().keySet().stream().forEach(x -> System.out.print(x + "; "));
        System.out.println("\nДля подсчета голосов введите 0");
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.print("\nВаш выбор?: ");
            String fullNameCar = console.nextLine();
            if (fullNameCar.equals(String.valueOf(0))) {
                System.out.println("\nГолосование завершено!");
                break;
            }
            fullNameCar = fullNameCar.replaceAll("\\s+", " ").strip();
            if (statisticVoting.getStatisticInMap().keySet().contains(fullNameCar)) {
                int numberOfVotes = statisticVoting.getStatisticInMap().get(fullNameCar) + 1;
                statisticVoting.getStatisticInMap().put(fullNameCar, numberOfVotes);
            }
            if (!statisticVoting.getStatisticInMap().keySet().contains(fullNameCar)) {
                System.out.println("Данного автомобиля нет в списке голосования");
                continue;
            }
            System.out.println("Ваш голос принят!");
        }
    }

    protected void resultVoting() {
        System.out.print("Лучший автомобиль года: ");
        int maxNumberOfVotes = Collections.max(statisticVoting.getStatisticInMap().values());
        statisticVoting.getStatisticInMap().entrySet().stream().filter(e -> e.getValue() == maxNumberOfVotes).
                forEach(e -> System.out.print(e.getKey() + "; "));
        System.out.println("\nКоличество голосов " + maxNumberOfVotes);
    }

}

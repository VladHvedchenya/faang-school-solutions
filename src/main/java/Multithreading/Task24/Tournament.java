package Multithreading.Task24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class Tournament {
    public static void main(String[] args) {
        Set<Student> hogwartsTeam = new HashSet<>();
        hogwartsTeam.add(new Student("Гарри Поттер", 5));
        hogwartsTeam.add(new Student("Гермиона Грейнджер", 5));
        hogwartsTeam.add(new Student("Рон Уизли", 5));
        hogwartsTeam.add(new Student("Седрик Диггори", 7));
        hogwartsTeam.add(new Student("Полумна Лавгуд", 4));

        Set<Student> beauxbatonsTeam = new HashSet<>();
        beauxbatonsTeam.add(new Student("Флёр Делакур", 7));
        beauxbatonsTeam.add(new Student("Габриэль Делакур", 2));
        beauxbatonsTeam.add(new Student("Люсиль Дюпон", 5));
        beauxbatonsTeam.add(new Student("Анри Лефевр", 6));

        Set<Student> durmstrangTeam = new HashSet<>();
        durmstrangTeam.add(new Student("Виктор Крам", 7));
        durmstrangTeam.add(new Student("Поляков", 6));
        durmstrangTeam.add(new Student("Каркаров мл.", 5));
        durmstrangTeam.add(new Student("Василий Димитров", 6));

        List<School> schools = List.of(
                new School("Хогвартс", hogwartsTeam),
                new School("Шармбатон", beauxbatonsTeam),
                new School("Дурмстранг", durmstrangTeam)
        );

        List<Task> poolOfTasks = new ArrayList<>();
        poolOfTasks.add(new Task("Битва с Драконом", 5, 100));
        poolOfTasks.add(new Task("Спасение друзей от Русалок", 3, 50));
        poolOfTasks.add(new Task("Лабиринт Страха", 6, 120));
        poolOfTasks.add(new Task("Полет на гиппогрифе", 2, 30));
        poolOfTasks.add(new Task("Защита от дементоров", 4, 80));
        poolOfTasks.add(new Task("Разгадка Сфинкса", 3, 40));
        poolOfTasks.add(new Task("Дуэль на палочках", 4, 70));

        Set<Task> generatedTournamentTasks = generateTasks(poolOfTasks);

        CompletableFuture<?>[] allSchoolsFuture = schools.stream()
                .map(school -> startTask(school, generatedTournamentTasks))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(allSchoolsFuture).join();

        School winner = schools.stream()
                .max(Comparator.comparingInt(School::getTotalPoints))
                .orElseThrow();

        System.out.printf("\n🏆 ТУРНИР ЗАВЕРШЕН! Победила школа: %s с результатом в %d очков!\n",
                winner.getName(), winner.getTotalPoints());
    }

    public static CompletableFuture<School> startTask(School school, Set<Task> tasks) {
        return CompletableFuture.supplyAsync(
                () -> {
                    tasks.forEach(task -> completeTask(school, task));

                    return school;
                }
        );
    }

    private static void completeTask(School school, Task task){
        try{
            Thread.sleep(task.difficulty() * 1000L);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        double multiplier = ThreadLocalRandom.current().nextDouble(0.5, 1.5);
        int finalPoints = (int) (task.reward() * multiplier);
        int randomIndex = ThreadLocalRandom.current().nextInt(0, school.getStudents().size());
        Student student = school.getStudents().get(randomIndex);
        student.setPoints(student.getPoints() + finalPoints);
    }

    private static Set<Task> generateTasks(List<Task> allTasks){
        Set<Task> tasks = new HashSet<>(3);

        while(tasks.size() < 3){  //hardcoded count of tasks. should take it from .config
            Task randomTask = allTasks.get(ThreadLocalRandom.current().nextInt(0, allTasks.size()));
            tasks.add(randomTask);
        }

        return tasks;
    }
}
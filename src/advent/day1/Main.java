package advent.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    static int run(int part) throws FileNotFoundException {
        List<Elf> elfs = new ArrayList<>();

        URL resourceAsStream = Main.class.getClassLoader().getResource("advent/day1/data.txt");

        if (resourceAsStream != null) {
            File file = new File(resourceAsStream.getFile());
            Scanner scanner = new Scanner(file);

            Elf currentElf = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line != null) {
                    if (line.isEmpty()) {
                        currentElf = new Elf();
                        elfs.add(currentElf);
                    } else if (currentElf != null) {
                        currentElf.addCalories(Integer.parseInt(line));
                    }
                }
            }

            scanner.close();
            elfs.sort((a, b) -> Math.toIntExact(b.getAllCalories() - a.getAllCalories()));

            if (part == 1) {
                return (int) elfs.get(0).getAllCalories();
            }

            if (part == 2) {
                int top3Calories = 0;

                for (int i = 0; i < 3; i++) {
                    top3Calories += elfs.get(i).getAllCalories();
                }

                return top3Calories;
            }
        }

        return -1;
    }

    static class Elf {
        
        private final List<Integer> calorieList = new ArrayList<>();

        public void addCalories(int calories) {
            this.calorieList.add(calories);
        }

        public long getAllCalories() {
            return this.calorieList.stream().reduce(0, Integer::sum);
        }
    }
}

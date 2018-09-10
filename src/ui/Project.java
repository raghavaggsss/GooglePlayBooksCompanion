package ui;

public class Project {
    private static int weekNumber;

    public static void main(String[] args) {
        setWeekNumber(2);
        System.out.println("This is the week " + (weekNumber() + " of my project!" ));
    }

    private static int weekNumber() {
        System.out.println(weekNumber);
        return weekNumber;
    }

    private static void setWeekNumber(int n) {
        System.out.println(n);
        weekNumber = n;
    }
}

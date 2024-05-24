import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Appointment implements Comparable<Appointment> {
        String seniorName;
        int week;
        int day;
        int neededMoney;

        public Appointment(String seniorName, int week, int day, int neededMoney) {
            this.seniorName = seniorName;
            this.week = week;
            this.day = day;
            this.neededMoney = neededMoney;
        }

        @Override
        public int compareTo(Appointment other) {
            if (this.week != other.week)
                return Integer.compare(this.week, other.week);
            return Integer.compare(this.day, other.day);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  

        Map<String, Appointment> appointments = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            String name = line[0];
            int week = Integer.parseInt(line[1]);
            int day = Integer.parseInt(line[2]);
            int price = Integer.parseInt(line[3]);
            appointments.put(name, new Appointment(name, week, day, price));
        }

        List<Appointment> validAppointments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            String name = line[0];
            int money = Integer.parseInt(line[1]);

            Appointment appt = appointments.get(name);
            if (appt != null && money >= appt.neededMoney) {
                validAppointments.add(appt);
            }
        }

        Collections.sort(validAppointments);

        int maxConsecutiveDays = calculateMaxConsecutiveDays(validAppointments);
        System.out.println(maxConsecutiveDays);
    }

    private static int calculateMaxConsecutiveDays(List<Appointment> appointments) {
        if (appointments.isEmpty()) return 0;

        int maxDays = 1;
        int count = 1;
        int lastWeek = appointments.get(0).week;
        int lastDay = appointments.get(0).day;

        for (int i = 1; i < appointments.size(); i++) {
            Appointment current = appointments.get(i);
            if(current.week == lastWeek && current.day == lastDay) {
                continue;
            }

            if (current.week == lastWeek && current.day == lastDay + 1) {
                count++;
                maxDays = Math.max(maxDays, count);
            } else if (current.week == lastWeek + 1 && current.day == 0 && lastDay == 6) {
                count++;
                maxDays = Math.max(maxDays, count);
            } else {
                count = 1;
            }
            lastWeek = current.week;
            lastDay = current.day;
        }

        return maxDays;
    }
}

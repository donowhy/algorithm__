import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Set<Integer> carNumbers = new TreeSet<>();

        Map<Integer, Integer> inRecords = new HashMap<>();

        Map<Integer, Integer> totalTimes = new HashMap<>();

        for (String record : records) {
            String[] data = record.split(" ");

            int time = convertToMinutes(data[0]);
            int carNumber = Integer.parseInt(data[1]);
            String status = data[2];

            carNumbers.add(carNumber);

            if (status.equals("IN")) {
                inRecords.put(carNumber, time);
            } else {
                int inTime = inRecords.get(carNumber);
                int parkingTime = time - inTime;

                totalTimes.put(
                    carNumber,
                    totalTimes.getOrDefault(carNumber, 0) + parkingTime
                );

                inRecords.remove(carNumber);
            }
        }

        int lastTime = convertToMinutes("23:59");

        for (Map.Entry<Integer, Integer> entry : inRecords.entrySet()) {
            int carNumber = entry.getKey();
            int inTime = entry.getValue();
            int parkingTime = lastTime - inTime;

            totalTimes.put(
                carNumber,
                totalTimes.getOrDefault(carNumber, 0) + parkingTime
            );
        }

        int[] answer = new int[carNumbers.size()];
        int index = 0;

        for (int carNumber : carNumbers) {
            int totalMinutes = totalTimes.get(carNumber);
            answer[index++] = calculateFee(totalMinutes, fees);
        }

        return answer;
    }

    private int calculateFee(int totalMinutes, int[] fees) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (totalMinutes <= baseTime) {
            return baseFee;
        }

        int excessTime = totalMinutes - baseTime;

        int units = (excessTime + unitTime - 1) / unitTime;

        return baseFee + units * unitFee;
    }

    private int convertToMinutes(String time) {
        String[] parts = time.split(":");

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }
}
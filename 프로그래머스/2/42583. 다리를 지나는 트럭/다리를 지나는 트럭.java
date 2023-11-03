import java.util.*;

class Solution {
    static class Truck {
        int weight;
        int entryTime; // the time at which the truck enters the bridge

        public Truck(int weight, int entryTime) {
            this.weight = weight;
            this.entryTime = entryTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Truck> bridge = new LinkedList<>();
        int time = 0;
        int currentWeightOnBridge = 0;

        int i = 0;
        while (i < truck_weights.length || !bridge.isEmpty()) {
            time++;

            // Remove trucks that have crossed the bridge
            if (!bridge.isEmpty() && time - bridge.peek().entryTime >= bridge_length) {
                currentWeightOnBridge -= bridge.poll().weight;
            }

            // Add trucks to the bridge if possible
            if (i < truck_weights.length && currentWeightOnBridge + truck_weights[i] <= weight) {
                bridge.offer(new Truck(truck_weights[i], time));
                currentWeightOnBridge += truck_weights[i];
                i++;
            }
        }

        return time;
    }
}

import java.util.*;

class Solution {
    
    public class Truck {
        int w;
        int endTime;
        
        public Truck (int w, int endTime) {
            this.w = w;
            this.endTime = endTime;
        }
        
        public String toString() {
            return "weigt => " + this.w + " endTime => " + this.endTime;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        //정해진 순으로 
        
        ArrayDeque<Integer> list = new ArrayDeque<>();
        for(int t : truck_weights) {
            list.add(t);
        }
        
        int tw = weight - list.peek();
        int bl = bridge_length;
        int tt = 1;
        
        ArrayDeque<Truck> cars = new ArrayDeque<>();
        cars.offer(new Truck(list.poll(), bl + tt));
        
        System.out.println();
        while(true) {
            tt += 1;

                        
            if(cars.size() > 0) {
                Truck car = cars.peek();
                if(car.endTime == tt) {
                    cars.poll();
                    tw += car.w;
                }
            }
            
            if(list.size() > 0){
                int w = list.peekFirst();
                if(tw - w >= 0) {
                    list.pollFirst();
                    cars.offer(new Truck(w, tt + bl));
                    tw -= w;
                }
            }
            
            if(list.size() == 0 && cars.size() == 0) {
                return tt;
            }
        }
    }
}
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        String answer = "";
        int arrivedBusCount = 0;
        int arrivedBusTime = TimeToInt("09:00");
        PriorityQueue<Integer> waiters = new PriorityQueue<Integer>();
        setPriorityQueue(timetable,waiters);
        
        while(arrivedBusCount<=n-1){
            if(arrivedBusCount!=0)arrivedBusTime +=  t;
            int boardCount = 0;
            while(waiters.size()!=0 && boardCount <=m-1){
                if(waiters.peek()>arrivedBusTime)break;
                if(boardCount == m-1 && arrivedBusCount ==n-1) {// 막찬데 자리가 없을 경우 
                    return answer= TimeToStr(waiters.poll()-1);
                }
                waiters.poll();
                boardCount++;
            }
            arrivedBusCount++;
        }
        
        //막차에 탈 수 있는 경우 
            
        return TimeToStr(arrivedBusTime);
    }
    
    static public int TimeToInt(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        return hour*60+min;
    }
    
    static public String TimeToStr(int time){
        String hour = String.format("%02d",time/60);
        String min =  String.format("%02d",time%60);
        return hour+":"+min;
    }
    
    static public void setPriorityQueue(String[] timetable,PriorityQueue<Integer> waiters){
        for(String time:timetable){
            waiters.add(TimeToInt(time));
        }
    }
}
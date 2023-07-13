class Solution {
    public int solution(String[] board) {
        String[][] map = new String[3][3];
        int o_cnt = 0;
        int x_cnt = 0;
        
        for(int i = 0; i<3; i++) {
            String[] tmp = board[i].split("");
            for(int j = 0; j<3; j++) {
                map[i][j] = tmp[j];
    
                if ("O".equals(tmp[j]))
                    o_cnt++;
                if ("X".equals(tmp[j]))
                    x_cnt++;
            }
        }
        
        // X 는 O 보다 많을 수 없고, O는 X 보다 1개 많아야 한다 
        if (x_cnt - o_cnt > 0 || o_cnt - x_cnt > 1) {
            return 0;
        }
        
        boolean o_check = check("O", map);
        boolean x_check = check("X", map);
        
        if (o_check && x_cnt + 1 != o_cnt)
            return 0;
        
        if (x_check && x_cnt != o_cnt)
            return 0;
        
        return 1;
    }
    
    private boolean check(String target, String[][] map) {
        // 가로
        for(int i = 0; i<3; i++) {
            if (map[i][0].equals(target) && map[i][1].equals(target) && map[i][2].equals(target))
                return true;
        }
        
        // 세로
        for(int i = 0; i<3; i++) {
            if (map[0][i].equals(target) && map[1][i].equals(target) && map[2][i].equals(target))
                return true;
        }
        
        // 대각선
        if (map[0][0].equals(target) && map[1][1].equals(target) && map[2][2].equals(target))
            return true;
        
        if (map[2][0].equals(target) && map[1][1].equals(target) && map[0][2].equals(target))
            return true;
        
        return false;
    }
}
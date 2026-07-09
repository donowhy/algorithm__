class Solution {
    public int[] solution(int brown, int yellow) {
        return test(yellow, brown);
    }

    public int[] test(int yellow, int brown) {
        for (int h = 1; h <= yellow; h++) {
            if (yellow % h != 0) continue;

            int w = yellow / h;

            if (w < h) continue;

            if (test2(w, h, brown)) {
                return new int[]{w + 2, h + 2};
            }
        }

        return new int[]{};
    }

    public boolean test2(int w, int h, int brown) {
        int brownCount = w * 2 + h * 2 + 4;
        return brownCount == brown;
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class Solution {
  static class Mineral{
        private int diamond;
        private int iron;
        private int stone;

        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }

    static int[][] scoreBoard;
    static List<Mineral> list;

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        scoreBoard = new int[][]{{1,1,1},{5,1,1},{25, 5, 1}};

        int totalPicks = Arrays.stream(picks).sum();
        list = new ArrayList<>();
        for (int i = 0; i < minerals.length; i+=5) {
            if(totalPicks == 0)break;

            int dia = 0, iron = 0, stone = 0;
            // 하나의 곡객이는 5번에 광물을 캘 수 있다.
            for (int j = i; j < i + 5; j++) {
                if(j == minerals.length)break;

                String mineral = minerals[j];
                int val = mineral.equals("iron") ? 1 : mineral.equals("stone") ? 2 : 0;

                dia += scoreBoard[0][val];
                iron += scoreBoard[1][val];
                stone += scoreBoard[2][val];

            }

            list.add(new Mineral(dia, iron, stone));
            totalPicks--;

        }
        // 내림 차순
        Collections.sort(list, ((o1, o2) -> (o2.stone - o1.stone)));
        for (Mineral mineral : list) {
            int dia = mineral.diamond;
            int iron = mineral.iron;
            int stone = mineral.stone;

            if(picks[0] > 0){
                answer += dia;
                picks[0]--;
                continue;
            }
            if(picks[1] > 0){
                answer += iron;
                picks[1]--;
                continue;
            }
            if(picks[2] > 0){
                answer += stone;
                picks[2]--;
                continue;
            }
        }


        return answer;
    }
}
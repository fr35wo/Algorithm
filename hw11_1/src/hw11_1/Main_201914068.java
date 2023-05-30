package hw11_1;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_201914068 {
    public static void main(String[] args) {
    	System.out.println("hw11_1 : 홍석현");

        Scanner input = new Scanner(System.in);

        // 배낭 최대 허용 무게 입력       
        int m = input.nextInt();

        // 물건 개수 입력        
        int n = input.nextInt();

        int[][] items = new int[n][3]; // 물건 배열 (n행 3열)

        // 물건 무게와 가치 입력       
        for (int i = 0; i < n; i++) {           
            items[i][0] = input.nextInt(); // 물건 무게
            items[i][1] = input.nextInt(); // 물건 가치
            items[i][2] = i + 1; // 물건 번호
        }

        // 2차원 배열을 단위 가치를 기준으로 내림차순으로 정렬 **코드 참고함
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 단위 가치를 기준으로 내림차순 정렬
                double unitValue1 = (double) o1[1] / o1[0];
                double unitValue2 = (double) o2[1] / o2[0];
                if (unitValue2 != unitValue1) {
                    return Double.compare(unitValue2, unitValue1);
                } else {
                    // 가치가 같은 경우에는 무게를 기준으로 내림차순 정렬
                    return Integer.compare(o2[0], o1[0]);
                }
            }
        });

        int currentweight = 0; // 현재 배낭에 담긴 무게
        int currentvalue = 0; // 현재 배낭에 담긴 가치
        int[] selectednumbers = new int[n]; // 선택된 물건 번호를 저장할 배열
        int[] selectedweights = new int[n]; // 선택된 물건의 무게를 저장할 배열
        int[] selectedvalues = new int[n]; // 선택된 물건의 가치를 저장할 배열
        int count = 0; // 선택된 물건의 개수

        // 그리디 알고리즘을 이용하여 배낭에 물건을 담음
        for (int i = 0; i < n; i++) {
            int remainingWeight = m - currentweight; // 남은 무게
            if (items[i][0] <= remainingWeight) { //현재 물건을 배낭에 담을 수 있는지 
                currentweight += items[i][0];
                currentvalue += items[i][1];
                selectednumbers[count] = items[i][2];
                selectedweights[count] = items[i][0];
                selectedvalues[count] = items[i][1];
                count++;
            } else { //남은 무게에 나눠 담기
                double ratio = (double) remainingWeight / items[i][0]; //남은 허용 무게에 대한 비율
                currentweight += remainingWeight;//꽉차게 담음
                currentvalue += (int) (ratio * items[i][1]);//물건 나눠 담기
                selectednumbers[count] = items[i][2];
                selectedweights[count] = remainingWeight;
                selectedvalues[count] = (int) (ratio * items[i][1]);
                count++;
                break; // 허용 무게를 초과
            }
        }

        // 결과 출력
        System.out.println(currentvalue);
    
        for (int i = 0; i < count; i++) {
        	if(selectedweights[i] == 0) { //ratio가 0인경우 출력 제외
        		continue;
        	}
            System.out.println(selectednumbers[i] + " " + selectedweights[i] + " " + selectedvalues[i]);
        }
    }
}

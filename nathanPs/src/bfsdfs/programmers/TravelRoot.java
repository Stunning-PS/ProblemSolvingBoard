package bfsdfs.programmers;

import java.util.*;

public class TravelRoot {

    // 가능한 방문 경로를 담고 있는 List.
    private List<List<String>> travelRoots = new ArrayList<>();

    // 티켓 사용 여부 확인.
    private boolean[] useTicket;

    // 현재까지 사용한 티켓의 수.
    private int ticketCount;

    public String[] solution(String[][] tickets) {
        useTicket = new boolean[tickets.length];
        ticketCount = tickets.length;

        List<String> root = new ArrayList<>();
        root.add("ICN");
        dfs("ICN", root, tickets, 0);

        // 알파벳 순서로 방문 경로 정렬.
        travelRoots.sort(new Comparator<List<String>>() {
            @Override
            public int compare(final List<String> s1, final List<String> s2) {
                for (int i = 0; i < s1.size(); i++) {
                    if (!s1.get(i).equals(s2.get(i))) {
                        return s1.get(i).compareTo(s2.get(i));
                    }

                }
                return 0;
            }
        });

        List<String> ans = travelRoots.get(0);
        String[] answer = new String[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    private void dfs(final String prevRoot, final List<String> root, final String[][] tickets, int useTicketCount) {
        // 현재까지 사용한 티켓의 수가 주어진 티켓의 수와 같으면 재귀식 종료.
        if (ticketCount == useTicketCount) {
            travelRoots.add(new ArrayList<>(root));
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            String arrive = ticket[0];
            String dest = ticket[1];


            // 이전에 출발한 경로가 현재 티켓의 도착 지점일 경우 & 사용한 적이 없는 티켓의 경우 해당 티켓 사용.
            if (prevRoot.equals(arrive) && !useTicket[i]) {
                useTicket[i] = true;
                // 해당 티켓의 목적지를 방문 경로에 추가.
                root.add(dest);
                useTicketCount++;
                dfs(dest, root, tickets, useTicketCount);

                // 재귀식이 종료된 경우 사용 티켓, 방문 경로, 티켓 사용 여부 리셋.
                useTicketCount--;
                root.remove(root.size() - 1);
                useTicket[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[][] tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"},
                {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};

        TravelRoot travelRoot = new TravelRoot();
        travelRoot.solution(tickets);
    }
}
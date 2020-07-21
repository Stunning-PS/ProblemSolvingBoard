package bruteforce.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Baekjoon1966 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = Integer.parseInt(br.readLine());
        int[] ans = new int[caseCount];

        for (int i = 0; i < caseCount; i++) {
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            Deque<PrintDocument> docs = new ArrayDeque<>();
            String[] inputDocs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                docs.add(new PrintDocument(j, Integer.parseInt(inputDocs[j])));
            }

            Deque<PrintDocument> sortByPriority = sortByPriority(docs);

            int idx = 0;

            for (PrintDocument printDocument : sortByPriority) {
                if (printDocument.initIdx == m) {
                    ans[i] = idx;
                }
                idx++;
            }
        }

        for (int an : ans) {
            System.out.println(an + 1);
        }

    }

    public static Deque<PrintDocument> sortByPriority(Deque<PrintDocument> docs) {
        Deque<PrintDocument> sorted = new LinkedList<>();

        while (!docs.isEmpty()) {
            PrintDocument frontDoc = docs.removeFirst();

            if (hasMorePriorityDocs(frontDoc, docs)) {
                docs.addLast(frontDoc);
            } else {
                sorted.addLast(frontDoc);
            }
        }

        return sorted;
    }

    private static boolean hasMorePriorityDocs(final PrintDocument frontDoc, final Deque<PrintDocument> docs) {
        for (PrintDocument doc : docs) {
            if (frontDoc.priority < doc.priority) {
                return true;
            }
        }

        return false;
    }

    private static class PrintDocument {
        int initIdx;
        int priority;

        public PrintDocument(final int initIdx, final int priority) {
            this.initIdx = initIdx;
            this.priority = priority;
        }
    }
}

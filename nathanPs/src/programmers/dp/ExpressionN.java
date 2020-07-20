package programmers.dp;

import java.util.TreeSet;

public class ExpressionN {

    static int _N;
    TreeSet<Integer>[] dynamic;

    public TreeSet<Integer> solve(int n) {

        if ((dynamic[n]!=null) &&!dynamic[n].isEmpty()) {
            return dynamic[n];//전에 있던 집합 찾기.
        }

        int num = 0;

        for (int i = 0; i < n; i++) {
            num = 10 * num + _N;
        } // NNNN만들기.

        TreeSet<Integer> temp = new TreeSet<>();
        temp.add(num);

        for(int i =1; i<n;i++){
            int j = n-i;
            TreeSet<Integer> from = solve(i);
            TreeSet<Integer> to = solve(j);

            for(int n1:from) {
                for (int n2 : to) {//d[n] = d[n-1] + d[i];
                    for (Operator operator : Operator.values()) {
                        temp.add(operator.calculate(n1, n2));
                    }
                }
            }
        }

        return dynamic[n]= temp;
    }

    public int solution(int N, int number) {
        _N = N;

        dynamic = new TreeSet[10];
        for(int i =1 ; i<= 8; i++){
            solve(i);
            if (dynamic[i].contains(number)) return i;
        }
        return -1;
    }

    enum Operator {
        PLUS {
            @Override
            public int calculate(int i, int j) {
                return i + j;
            }
        }, MINUS {
            @Override
            public int calculate(int i, int j) {
                return i - j;
            }
        }, BACKMINUS {
            @Override
            public int calculate(int i, int j) {
                return j - i;
            }
        }, MUL {
            @Override
            public int calculate(int i, int j) {
                return i * j;
            }
        }, DIV {
            @Override
            public int calculate(int i, int j) {
                if (j == 0){
                    return 0;
                }
                return i / j;
            }
        }, BACKDIV {
            @Override
            public int calculate(int i, int j) {
                if (i == 0){
                    return 0;
                }
                return j / i;
            }
        };

        public abstract int calculate(int i, int j);
    }
}

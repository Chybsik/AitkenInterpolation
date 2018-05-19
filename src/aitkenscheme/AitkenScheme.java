package aitkenscheme;

public class AitkenScheme {

    double[] x;
    double[] y;
    double[][] res;

    int steps;

    public static void main(String[] args) {

        double[] x = new double[]{5, 10, 15, 20, 25, 30, 35, 40};
        double[] y = new double[]{1.71, 2.154, 2.466, 2.714, 2.924, 3.107, 3.271, 3.42};
        double arg = 26;

        AitkenScheme a = new AitkenScheme(x, y);

        System.out.println(a.Calculate(arg) + " " + a.steps);
    }

    public AitkenScheme(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double Calculate(double arg) {
        res = new double[50][50];
        steps = 0;

        int i = DetermineStartingIndex(arg);
        if (x.length - (i + 1) > x.length / 2) {
            int k = i + 3;

            Func(i, k, arg);

            while (Math.abs(res[i][k - i - 3] - res[i][k - i - 2]) > Math.abs(res[i][k - i - 2] - res[i][k - i - 1]) & x.length - 1 >= k + 1) {
                k++;

                Func(i, k, arg);
                steps++;
            }
            return res[i][k - 2];
        } else {
            int k = i;
            i-=3;

            Func(i, k, arg);

            while (Math.abs(res[i][k - i - 3] - res[i][k - i - 2]) > Math.abs(res[i][k - i - 2] - res[i][k - i - 1]) & x.length - 1 >= k + 1) {
                i--;

                Func(i, k, arg);
                steps++;
            }
            return res[i+2][k];
        }

    }

    public double Func(int i, int k, double arg) {
        double result;

        if (i + 1 == k) {
            result = 1 / (x[k] - x[i]) * (((arg - x[i]) * y[k]) - ((arg - x[k]) * y[i]));
            res[i][0] = result;
        } else {
            result = 1 / (x[k] - x[i]) * (((arg - x[i]) * Func(i + 1, k, arg)) - ((arg - x[k]) * Func(i, k - 1, arg)));
            res[i][k - i - 1] = result;
        }
        return result;
    }

    public int DetermineStartingIndex(double arg) {
        int i = 0;
        while (this.x[i] < arg) {
            i++;
        }
        return i -= i == 0 ? 0 : 1;
    }

}

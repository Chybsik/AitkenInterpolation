package aitkenscheme;

public class AitkenScheme {

    double[] x;
    double[] y;
    double[][] res;

    int power;

    public static void main(String[] args) {

        double[] x = new double[]{5, 10, 15, 20, 25, 30, 35, 40};
        double[] y = new double[]{1.71, 2.154, 2.466, 2.714, 2.924, 3.107, 3.271, 3.42};
        double arg = 12;

        AitkenScheme a = new AitkenScheme(x, y);

        System.out.println(a.Calculate(arg) + " \nрезультирующая степень многочлена:" + a.power);
        for (int i = 0; i < a.res.length; i++) {
            for (int j = 0; j < a.res[i].length; j++) {
                System.out.print(new java.text.DecimalFormat("#.####").format(a.res[i][j])+" | ");
            }
            System.out.print("\n");
        }
    }

    public AitkenScheme(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double Calculate(double arg) {
        res = new double[this.x.length][this.x.length-1];

        int i = DetermineStartingIndex(arg);
        if (x.length - (i + 1) > x.length / 2) {
            int k = i + 3;

            Func(i, k, arg);

            while (Math.abs(res[i][k - i - 3] - res[i][k - i - 2]) > Math.abs(res[i][k - i - 2] - res[i][k - i - 1]) & x.length - 1 >= k + 1) {
                k++;

                Func(i, k, arg);
            }
            if (Math.abs(res[i][k - i - 3] - res[i][k - i - 2]) > Math.abs(res[i][k - i - 2] - res[i][k - i - 1]) &x.length - 1 < k + 1) {
                System.out.println("Достигнут предел массива данных");
                power = k-i;
                return res[i][k - i - 1];
            }
            power = k-i-1;
            return res[i][k - i - 2];
        } else {
            i++;
            int k = i-3;

            FuncReverse(i, k, arg);

            while (Math.abs(res[i][Math.abs(k - i) - 3] - res[i][Math.abs(k - i) - 2]) > Math.abs(res[i][Math.abs(k - i) - 2] - res[i][Math.abs(k - i) - 1]) & k - 1>=0) {
                k--;

                FuncReverse(i, k, arg);
            }
            if (Math.abs(res[i][Math.abs(k - i) - 3] - res[i][Math.abs(k - i) - 2]) > Math.abs(res[i][Math.abs(k - i) - 2] - res[i][Math.abs(k - i) - 1]) &k - 1<0) {
                System.out.println("Достигнут предел массива данных");
                power = i-k;
                return res[i][Math.abs(k-i)-1];
            }
            power = i-k-1;
            return res[i][Math.abs(k-i)-2];
        }

    }

    public double Func(int i, int k, double arg) {
        double result;

        if (i + 1 == k) {
            result = 1 / (x[k] - x[i]) * (((arg - x[i]) * y[k]) - ((arg - x[k]) * y[i]));
            res[i][k-i-1] = result;
        } else {
            double P0= res[i][(k-1)-i-1]==0?Func(i, k - 1, arg):res[i][(k-1)-i-1];
            double P1= res[i+1][k-i-1]==0?Func(i + 1, k, arg):res[i+1][k-i-1];
            
            result = 1 / (x[k] - x[i]) * (((arg - x[i]) * P1) - ((arg - x[k]) * P0));
            res[i][k - i - 1] = result;
        }
        return result;
    }
    
    public double FuncReverse(int i, int k, double arg) {
        double result;

        if (i - 1 == k) {
            result = 1 / (x[k] - x[i]) * (((arg - x[i]) * y[k]) - ((arg - x[k]) * y[i]));
            res[i][Math.abs(k-i)-1] = result;
        } else {
            double P0= res[i][Math.abs((k+1) - i) - 1]==0?FuncReverse(i, k + 1, arg):res[i][Math.abs((k+1) - i) - 1];
            double P1= res[i-1][Math.abs(k-(i-1))-1]==0?FuncReverse(i - 1, k, arg):res[i-1][Math.abs(k-(i-1))-1];
            
            result = 1 / (x[k] - x[i]) * (((arg - x[i]) * P1) - ((arg - x[k]) * P0));
            res[i][Math.abs(k - i) - 1] = result;
        }
        return result;
    }

    public int DetermineStartingIndex(double arg) {
        if (this.x[x.length-1]<arg) {
            return x.length-2;
        }else if(arg<this.x[0]){
            return 0;
        }
        int i = 0;
        while (this.x[i] < arg) {
            i++;
        }
        return i -= i == 0 ? 0 : 1;
    }

}

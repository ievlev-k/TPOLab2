package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;

public class Sin {
    public double sin(double x, double eps){
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY){
            return Double.NaN;
        }
//        System.out.println(x);
        if (x >= 0){
            while (x >= 2 * Math.PI) {
                x -= 2*Math.PI;
            }
        }else if (x < 0){
            while (x < 2*Math.PI){
                x += 2*Math.PI;
            }
        }
//        System.out.println(x);
        double result = 0;
        double resultLast = 1;
        int step = 0;
        while (Math.abs(resultLast - result) > eps){
            resultLast = result;
            result += Math.pow(-1, step)*Math.pow(x,2*step+1)/factorial(2*step+1);
//            System.out.println(factorial(2*step+1));
            step++;
//            System.out.println(result);
        }

        if (Math.abs(result) +0.0001> 1) return Double.NaN;
        if (Math.abs(result) < eps) return 0;
        return result;
    }

    private double factorial(int x){
        double result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    public double writeToCSV(double x, double eps, Writer writer){
        double res = sin(x, eps);
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
            printer.printRecord(x,res);
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
        return res;
    }
}

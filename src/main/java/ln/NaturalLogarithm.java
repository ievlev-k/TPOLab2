package ln;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class NaturalLogarithm {
    public double lnCalculate(double x, double eps){
        if (Double.isNaN(x) || x < 0.0) {
            return Double.NaN;
        } else if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }

        int step = 0;
        double currentResult;
        double result = 0;

        double constY = yVal(x);
        currentResult = yVal(x);
//        System.out.println(yVal(x));
        while (Math.abs(currentResult) > eps/2){

            currentResult  = Math.pow(constY, 2*step +1)/(2*step+1);
//            System.out.println(currentResult);
            result += currentResult;
            step++;
        }
        result += currentResult;
//        System.out.println(currentResult );
        return result*2;
    }

    private double yVal(double x){
        return (x-1)/(x+1);
    }

    public double writeToCSV(double x, double eps, Writer writer){
        double res = lnCalculate(x, eps);
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
            printer.printRecord(x,res);
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
        return res;
    }
}

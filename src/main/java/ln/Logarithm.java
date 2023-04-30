package ln;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Logarithm {
    private final NaturalLogarithm naturalLogarithm;

    public Logarithm(NaturalLogarithm naturalLogarithm) {
        this.naturalLogarithm = naturalLogarithm;
    }

    public Logarithm() {
        this.naturalLogarithm = new NaturalLogarithm();
    }

    public double logCalculate(double a, double b, double esp){

        return naturalLogarithm.lnCalculate(b, esp) / naturalLogarithm.lnCalculate(a,esp);
    }

    public double writeToCSV(double a,double b, double eps, Writer writer){
        double res = logCalculate(a, b, eps);
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
            printer.printRecord(a,b,res);
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
        return res;
    }
}

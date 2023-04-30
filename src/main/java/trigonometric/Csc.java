package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Csc {
    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public Csc() {
        this.sin = new Sin();
    }

    public double cosec(double x, double esp){

        double sinVal = sin.sin(x, esp);
        if (Double.isNaN(sinVal) || sinVal == 0) return Double.NaN;
        return 1/sinVal;
    }

    public double writeToCSV(double x, double eps, Writer writer){
        double res = cosec(x, eps);
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
            printer.printRecord(x,res);
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
        return res;
    }
}

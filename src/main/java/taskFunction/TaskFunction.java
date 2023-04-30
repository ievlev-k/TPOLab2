package taskFunction;

import ln.Logarithm;
import ln.NaturalLogarithm;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import trigonometric.*;

import java.io.IOException;
import java.io.Writer;

public class TaskFunction {
    private Sin sin;
    private Cos cos;
    private Sec sec;
    private Csc csc;
    private Logarithm logarithm;
    private NaturalLogarithm naturalLogarithm;

    public TaskFunction(Sin sin, Cos cos, Sec sec, Csc csc, Logarithm logarithm, NaturalLogarithm naturalLogarithm) {
        this.sin = sin;
        this.cos = cos;
        this.sec = sec;
        this.csc = csc;
        this.logarithm = logarithm;
        this.naturalLogarithm = naturalLogarithm;
    }

    public TaskFunction() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.sec = new Sec();
        this.csc = new Csc();
        this.logarithm = new Logarithm();
        this.naturalLogarithm = new NaturalLogarithm();
    }

    public double countTaskFuntion(double x, double esp){
        double res;
        if (x < 0 || x < esp){

            return Math.pow((Math.pow(sec.sec(x, esp), 2) + csc.cosec(x, esp)), 2)/(csc.cosec(x, esp)) - (cos.cos(x, esp) + sin.sin(x, esp));
        }else {
            return (((Math.pow(logarithm.logCalculate(10, x, esp), 3)* logarithm.logCalculate(2, x, esp))/ logarithm.logCalculate(5, x,esp)) + ((Math.pow(naturalLogarithm.lnCalculate(x, esp), 3)) - naturalLogarithm.lnCalculate(x, esp)) - (logarithm.logCalculate(5, x, esp) - logarithm.logCalculate(5, x ,esp)));
        }
    }

    public double writeToCSV(double x, double eps, Writer writer){
        double res = countTaskFuntion(x, eps);
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
            printer.printRecord(x,res);
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
        return res;
    }

}

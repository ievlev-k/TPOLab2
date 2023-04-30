package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

public class Cos {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {

        this.sin = new Sin();
//        System.out.println("vd");
    }

    public double cos(double x, double esp){
//        System.out.println("cos: " + x);
        double x_init = x;
        x %= Math.PI * 2;
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY){
            return Double.NaN;
        }

        if (x > Math.PI){
            while (x > Math.PI) {
                x -= 2*Math.PI;
            }
        }

        if (x < -Math.PI){
            while (x < -Math.PI) {
                x += 2*Math.PI;
            }
        }
        double res;

//        System.out.println("dvsv"+x);
        if (x > Math.PI / 2 || x < -Math.PI / 2){
            res = -1*Math.sqrt(1 - Math.pow(sin.sin(x_init,esp), 2));
        } else res = Math.sqrt(1 - Math.pow(sin.sin(x_init,esp), 2));

//        System.out.println("rerb" + res);
        if (Math.abs(res) > 1) return  Double.NaN;
        if (Math.abs(res) <= esp) return 0;
//        System.out.println("cos: "+ res);
        return res;
    }

    public double writerResultToCsv(double x, double esp, Writer output){
        double res = cos(x, esp);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(output)){
            printer.printRecord(x, res);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return res;
    }

    public double writeToCSV(double x, double eps, Writer writer){
        double res = cos(x, eps);
        try(CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
            printer.printRecord(x,res);
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
        return res;
    }
}

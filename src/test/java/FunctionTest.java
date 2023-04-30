import ln.Logarithm;
import ln.NaturalLogarithm;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import taskFunction.TaskFunction;
import trigonometric.Cos;
import trigonometric.Csc;
import trigonometric.Sec;
import trigonometric.Sin;

import java.io.*;

public class FunctionTest {
    static double eps = 0.1;
    static double epsFun = 0.00001;
    static Sin sinMock;
    static Cos cosMock;
    static Sec secMock;
    static Csc cscMock;
    static Logarithm logarithmMock;
    static NaturalLogarithm naturalLogarithmMock;

    static Reader secIn;
    static Reader cosIn;
    static Reader sinIn;
    static Reader cscIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log5In;
    static Reader log10In;



    @BeforeAll
    static void init(){
        sinMock = Mockito.mock(Sin.class);
        cosMock = Mockito.mock(Cos.class);
        secMock = Mockito.mock(Sec.class);
        cscMock = Mockito.mock(Csc.class);
        logarithmMock = Mockito.mock(Logarithm.class);
        naturalLogarithmMock = Mockito.mock(NaturalLogarithm.class);

        try {
            secIn = new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\SecInp.csv");
            sinIn = new  FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\SinInp.csv");
            cosIn =  new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\CosInp.csv");
            cscIn =  new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\CscInp.csv");
            lnIn =  new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\LnInp.csv");
            log2In =  new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\Log2Inp.csv");
            log5In =  new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\Log5Inp.csv");
            log10In =  new FileReader("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Inp\\Log10Inp.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.sec(Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.cos(Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.sin(Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records) {
                Mockito.when(cscMock.cosec(Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(naturalLogarithmMock.lnCalculate(Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logarithmMock.logCalculate(2, Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log5In);
            for (CSVRecord record : records) {
                Mockito.when(logarithmMock.logCalculate(5, Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));

            }
            records = CSVFormat.DEFAULT.parse(log10In);
            for (CSVRecord record : records) {
                Mockito.when(logarithmMock.logCalculate(10, Double.parseDouble(record.get(0)), epsFun)).thenReturn(Double.valueOf(record.get(1)));
            }


        }catch (IOException ioException){
            System.err.println("Проверь файл!");
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionWithMocks(double val, double res){
        TaskFunction function = new TaskFunction(sinMock,cosMock,secMock,cscMock,logarithmMock,naturalLogarithmMock);
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun), eps);
        try {

            function.writeToCSV(val, epsFun, new FileWriter("B:\\IdeaProjects\\TPOLab2\\src\\main\\resources\\CSVFiles\\Out\\Fun.csv",true));
        }catch (IOException ioException){
            System.err.println("Wrong file");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionOnSec(double val, double res){
        TaskFunction function  = new TaskFunction(sinMock,cosMock,new Sec(cosMock),cscMock,logarithmMock,naturalLogarithmMock);

        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionOnCSC(double val, double res){
        TaskFunction function  = new TaskFunction(sinMock,cosMock,secMock,new Csc(sinMock),logarithmMock,naturalLogarithmMock);
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionOnCos(double val, double res){
        TaskFunction function  = new TaskFunction(sinMock,new Cos(sinMock),new Sec(new Cos(sinMock)),cscMock,logarithmMock,naturalLogarithmMock);
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionOnSin(double val, double res){
        TaskFunction function  = new TaskFunction(new Sin(),new Cos(new Sin()),new Sec(new Cos(new Sin())),new Csc(new Sin()),logarithmMock,naturalLogarithmMock);
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionOnLog(double val, double res){
        TaskFunction function  = new TaskFunction(sinMock,cosMock,secMock,cscMock,new Logarithm(naturalLogarithmMock),naturalLogarithmMock);
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunctionOnLn(double val, double res){
        TaskFunction function  = new TaskFunction(sinMock,cosMock,secMock,cscMock,new Logarithm(new NaturalLogarithm()),new NaturalLogarithm());
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "CSVFiles/Inp/FunctionInp.csv")
    void testFunction(double val, double res){
        TaskFunction function  = new TaskFunction();
        Assertions.assertEquals(res, function.countTaskFuntion(val, epsFun) , eps);
    }

}

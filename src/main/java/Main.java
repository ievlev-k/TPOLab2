import ln.NaturalLogarithm;
import taskFunction.TaskFunction;
import trigonometric.Cos;
import trigonometric.*;

public class Main {
    public static void main(String[] args) {

//        System.out.println(new NaturalLogarithm().lnCalculate(999, 0.000001));
        System.out.println(new Sin().sin(Math.PI/2, 0.01));
//        System.out.println("jnd: " + new Csc().cosec(-1.570796, 0.00001));
        TaskFunction taskFunction = new TaskFunction();
        NaturalLogarithm naturalLogarithm = new NaturalLogarithm();
        System.out.println(taskFunction.countTaskFuntion(-6.283185, 0.000001));
    }
}

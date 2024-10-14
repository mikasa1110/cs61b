package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        Stopwatch timer = new Stopwatch();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);
        Ns.addLast(64000);
        Ns.addLast(128000);
        int M = 10000;
        for (int i =0; i< Ns.size(); i += 1) {
            opCounts.addLast(M);
        }

        for (int i = 0; i < Ns.size(); i += 1) {

            SLList list = new SLList();
            for (int j = 0; j < Ns.get(i); j += 1) {
                list.addLast(Ns.get(i));
            }
            timer = new Stopwatch();
            for (int j = 0; j < M; j += 1) {
                list.getLast();
            }
            double timeInSeconds = timer.elapsedTime();
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }
    }



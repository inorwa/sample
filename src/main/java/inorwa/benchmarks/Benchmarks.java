package inorwa.benchmarks;

import java.util.ArrayList;
import java.util.Random;

/**
 * Testy wydajności różnego rodziaju rozwiązań
 */
public class Benchmarks {
    public static class Value{
        private double value;

        public Value(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
        @Override
        public String toString(){return ""+this.value;}
    }
    public static ArrayList<Value> createList(int size){
        ArrayList<Value> values = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<size;i++) {
            Value value = new Value(rand.nextDouble()*100d);
            values.add(value);
        }
        return values;
    }
    public static void calculate(ArrayList<Value> values){
        values.forEach(value -> {value.setValue(value.getValue()*100d);});
    }
    public static ArrayList<Value> calculate2(ArrayList<Value> values){
        values.forEach(value -> {value.setValue(value.getValue()*100d);});
        return values;
    }
    public static void checkReturnValueByArgs(){
        ArrayList<Value> list = createList(10000000);
        ArrayList<Value> list2 = (ArrayList<Value>) list.clone();
        long start, end;

        start = System.currentTimeMillis();
        ArrayList<Value> values = calculate2(list2);
        end = System.currentTimeMillis();
        //list.forEach(val->{System.out.println(val);});
        System.out.println("Calculation 2 takes:" + (end-start) + " ms");

        start = System.currentTimeMillis();
        for(int i=0;i<100;i++)
            values = calculate2(list2);
        end = System.currentTimeMillis();
        //list.forEach(val->{System.out.println(val);});
        System.out.println("Calculation 2 takes:" + (end-start) + " ms");

        start = System.currentTimeMillis();
        for(int i=0;i<100;i++)
            calculate(list);
        end = System.currentTimeMillis();
        //list.forEach(val->{System.out.println(val);});
        System.out.println("Calculation takes:" + (end-start) + " ms");



    }
}

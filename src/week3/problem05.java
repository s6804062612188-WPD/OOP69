package week3;

import java.util.Scanner;

class RoachPopulation {
    protected int pop; // population
    
    RoachPopulation(int count) {
        this.pop = count;
    }
    public void simulate(int iteration) {
        for (int i=0; i<iteration; i=i+1) {
            this.nextGen();
            this.spray();
        }
    }
    
    public void nextGen() {
        pop *= 2;
    }

    public void spray() {
        pop -= (int)( (double)pop*0.1 );
    }
}

public class problem05 {
    public static void main(String[] args) {
        int count, iteration;
        Scanner input = new Scanner(System.in);
        count = input.nextInt();
		iteration = input.nextInt();
        
        RoachPopulation RP = new RoachPopulation(count);
        RP.simulate(iteration);
        
        System.out.println(RP.pop);
    }
}

import java.util.*;



public class Layer{
    int length;
    Neural[] neurals;
    double[] lin;
    double[] lout;
    double[] net;
    double[] realout;
    Layer pre;
    Layer Next;

    void setin(double[] in){
        length = in.length;
        if(lin == null)
            lin = new double[length];
        if(net == null)
            net = new double[length];
        lin = in;
    }
    void settarget(double[] out){
        length = out.length;
        if(lout == null)
            lout = new double[length];
        lout = out;
    }
    void randomWeight(int len){
        Random r = new Random();
        length = len;
        if(neurals ==null)
            neurals = new Neural[length];
        for(int i = 0; i < neurals.length; i++){
            if (neurals[i] ==null)
                neurals[i] = new Neural();
            if (neurals[i].Weight ==null)
                neurals[i].Weight = new double[length];
            for(int j = 0; j< neurals[i].Weight.length;j++){
                neurals[i].Weight[j] = r.nextDouble();
            }
            
        }
    }



}
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Bp{
    int length;//层数长度
    List<Layer> mLayer;
    void setlen(int len){ //设置层数
        length = len;
    }
    void setin(double[] in){//输入初始化
        mLayer.get(0).setin(in);
    }
    void settarget(double[] target){//输出初始化 
        mLayer.get(length -1).settarget(target);
    }
    void init(int len,double[] in ,int nelen,double[] out){//初始化权值和偏置  
        //随机生成
        //len 总层数
        //in 输入值数组
        //nelen 层中总neural个数
        //out 目标输出
    
        setlen(len);
        mLayer = new ArrayList<>();
        Layer inlayer = new Layer();
        inlayer.addcount();
        mLayer.add(inlayer);
        for(int i = 0; i < len -2;i++){
            Layer l = new Layer();
            l.addcount();
            l.randomWeight(nelen,nelen);
            mLayer.add(l);
        }
        //输出层初始化
        Layer l = new Layer();
        l.addcount();
        l.randomWeight(out.length,nelen);
        mLayer.add(l);

        for(int j =0; j < mLayer.size();j++){
            if(j == 0)
                mLayer.get(j).Next =  mLayer.get(j+1);
            else if(j == length -1)
                mLayer.get(j).pre =  mLayer.get(j-1);
            else{
                mLayer.get(j).pre = mLayer.get(j-1);
                mLayer.get(j).Next =  mLayer.get(j+1);
            }
        }
        setin(in);
        settarget(out);
        
    }


    void feedward(){
        for(int i =0;i< length;i++)
            mLayer.get(i).feedward();
    }
    
    void backward(){
        for(int i =length -1;i >= 0 ;i--)
            mLayer.get(i).calc_deta();
        for(int i =length -1;i >= 0 ;i--)
            mLayer.get(i).updatew();
    }
    double get_error(){
        double sum =0;
        for( int i =0; i < mLayer.get(length-1).length ;i ++)
            sum += mLayer.get(length-1).realout[i] - mLayer.get(length-1).lout[i];
        sum = sum /(length);
        return Math.abs(sum);
    }
    void printConstruction(){
        System.out.println("输入值：");
        for( int i =0; i < mLayer.get(0).length;i ++)
                System.out.println("第"+i+"层:"+mLayer.get(0).lin[i]);
        System.out.println("目标输出值：");
        for( int i =0; i < mLayer.get(length-1).length ;i ++)
                System.out.println("第"+i+"层:"+mLayer.get(length-1).lout[i]);
        System.out.println("真实输出值：");
        for( int i =0; i < mLayer.get(length-1).length ;i ++)
                System.out.println("第"+i+"层:"+mLayer.get(length-1).realout[i]);
    }
    void printError(){
        double sum = 0;
        for( int i =0; i < mLayer.get(length-1).length ;i ++)
            sum += mLayer.get(length-1).realout[i] - mLayer.get(length-1).lout[i];
        sum = Math.abs(sum /(length -1));
        System.out.println("误差："+sum);
    }
    void printWeight(){
        String s = "";
        //System.out.println("");
        for( int i = 0; i < mLayer.get(1).length ;i ++)
            for( int j = 0; j < mLayer.get(1).neurals[i].Weight.length ;j ++)
                s = s + ""+mLayer.get(1).neurals[i].Weight[j]+";";
        System.out.println(s);
    }

    double[] get_out(){
       return mLayer.get(length -1).realout;
    }
    double[] get_in(){
        return mLayer.get(0).lin;
    }
    public static void main(String[] args) {
        LocalTime time1 = LocalTime.now();
        Bp backfeed = new Bp();
        double[] in = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double[] out = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        backfeed.init(3,in,24,out);
        for (int i =0;i <2000;i++){
            double[] in1 = {1,0,0};
            double[] out1 ={}; 
            //Random r = new Random();
            int y =0;
            y = i%3;
            if (y ==0){
                in1 = new double[] {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                out1 = new double[] {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            }
            
            if (y ==1){
                in1 = new double[] {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                out1 = new double[] {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            }
            if (y ==2){
                in1 = new double[] {0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                out1 = new double[] {0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            }
            backfeed.setin(in1);
            backfeed.settarget(out1);
            backfeed.feedward();
            backfeed.backward();
            backfeed.printError();
            //   if ( backfeed.get_error() <= 0.001 / backfeed.length )
            //       break;
        }
        LocalTime time2 = LocalTime.now();

        double[] in1 = {};
        System.out.println("共："+backfeed.length+"层Layer");
        System.out.println("当： {0,0,0,0,1}时");
        in1 = new double[] {0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        backfeed.setin(in1);
        backfeed.feedward();
        backfeed.printConstruction();
        System.out.println("当： {1,0,0,0,0}时");
        in1 = new double[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        backfeed.setin(in1);
        backfeed.feedward();
        backfeed.printConstruction();
        System.out.println("开始时间："+time1.toString());
        System.out.println("结束时间："+time2.toString());
    }
}
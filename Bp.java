import java.util.ArrayList;
import java.util.List;

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
        mLayer.get(length -1).setin(target);
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
        mLayer.add(inlayer);
        for(int i = 0; i < len -2;i++){
            Layer l = new Layer();
            l.randomWeight(len);
            mLayer.add(l);
        }
        Layer outlayer = new Layer();
        mLayer.add(outlayer);
        for(int j =0; j < mLayer.size() -1;j++){
            if(j == 0)
                mLayer.get(j).Next =  mLayer.get(j+1);
            else if(j == length)
                mLayer.get(j).pre =  mLayer.get(j-1);
            else{
                mLayer.get(j).pre = mLayer.get(j-1);
                mLayer.get(j).Next =  mLayer.get(j+1);
            }
        }
        setin(in);
        settarget(out);
        
    }

    void printConstruction(){
        System.out.println("输入值：");
        for( int i =0; i < mLayer.get(0).length;i ++)
                System.out.println("第"+i+"层:"+mLayer.get(0).lin[i]);
        System.out.println("目标输出值：");
        for( int i =0; i < mLayer.get(length-1).length ;i ++)
                System.out.println("第"+i+"层:"+mLayer.get(0).lin[i]);
                
    }
    public static void main(String[] args) {
        Bp backfeed = new Bp();
        double[] in = {1,0,0};
        double[] out = {1,0,0};
        backfeed.init(3,in,3,out);
        backfeed.printConstruction();
        System.out.println("Hello_World");
    }
}


import java.util.*;
import java.lang.Math;


public class Layer{
    static int _layercount = 0;//总层数
    int layercount;//该层数
    int length;
    Neural[] neurals;
    double[] lin;
    double[] lout;//目标输出点
    double[] net;
    double[] realout;//真实输出点
    Layer pre;
    Layer Next;
    double[] delta;//误差
    double meu = Settings.meu;
    double basis = 1; //截距项，如果不存在，最大值将为0.5
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
            if (neurals[i] ==null){
                neurals[i] = new Neural();
                neurals[i].length = len;
            }
                
            if (neurals[i].Weight ==null)
                neurals[i].Weight = new double[length];
            for(int j = 0; j< neurals[i].Weight.length;j++){
                neurals[i].Weight[j] = r.nextDouble();
            }
            
        }
    }


    void feedward(){
        if(realout == null)
            realout = new double[length]; 
        if(net == null)
            net = new double[length];
        if(layercount == 1)
            for (int i =0;i < length ;i++){
                realout[i] = lin[i];    //输入层的输出 = 输入
            }
        else{
            for (int i =0;i < length ;i++){ //这一层细胞数
                double sum = 0;
                for(int j =0;j< neurals[i].length;j++)//细胞数的weight总数
                    sum += neurals[i].Weight[j] * pre.realout[i]; //前者所有细胞的输出分别与该层这个细胞的权值相乘。
                
                net[i] = sum + basis; 
                realout[i] = sigmoid(net[i]);
            }
            
        }

        //初始化


    }
    
    void calc_deta(){//计算误差delta δ
        if (delta == null){
            delta = new double[length];
        }
        if (layercount == _layercount)
            for(int i = 0;i < length;i++){
                double aj = realout[i];
                delta[i] = aj * (1-aj)*(aj -lout[i]);
                //该层下这个细胞对应delta误差 
            }
        else{
            for(int i =0; i < length;i++){
                double sum  =0;
                double aj = realout[i];
                for(int j =0; j < Next.neurals[i].Weight.length;j++){
                    sum += Next.delta[i] * Next.neurals[j].Weight[i];//下一层细胞的对应权值 * 通用误差δ 之和
                }
                   
                delta[i] = sum * aj * (1-aj);
            }
            
        }
    }
    void updatew(){
        if(layercount == 1)
            return;
        if(layercount == _layercount){
            for(int i =0; i< length; i++)
                for(int j =0; j < neurals[i].Weight.length;j++){
                    neurals[i].Weight[j] = neurals[i].Weight[j] - (delta[i] * pre.realout[i] * meu);
                    basis = basis - delta[i] * meu; //Δ截距项 = -1 * 学习率 * δ 
                }
                    
            return;
        }else{
            for(int i =0; i< length; i++)
                for(int j =0; j < neurals[i].Weight.length;j++){
                    neurals[i].Weight[j] = neurals[i].Weight[j] - (delta[i] * pre.realout[i] * meu);
                    basis = basis - delta[i] * meu; //Δ截距项 = -1 * 学习率 * δ 
                }
                    
        }
        
    //这一层的Δw = 这一层权值 - （上一层 与 权值 相对应的细胞输出值 * 学习率 * δ）
    }
    void backward(){
        calc_deta();
        updatew(); 
    }
    void addcount(){
        _layercount++;
        layercount = _layercount;
    }
    double sigmoid(double mnet){
        return 1 / (1 + Math.exp(-mnet));
    }
}
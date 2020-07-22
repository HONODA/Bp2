public class Martix{

    public double [][] By(double[][] x ,double[][] y,int n){
        double[][] result = new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result[i][j] = 0;
                for(int k=0;k<n;k++){
                    result[i][j] += x[i][k]*y[k][j];
                }
            }
        }                
        return result;
    }
    public static double [][] Froce_By(double[][] x ,double[][] y,int n){
        double[][] result = new double[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result[i][j] = 0;
                for(int k=0;k<n;k++){
                    result[i][j] += x[i][k]*y[k][j];
                }
            }
        }                
        return result;
    }

}
class test{
    public static void main(String[] args) {
        test t = new test();
        // for (int j = 0; j <100 ;j++){
            double i = t.sigmoid(0.5);
            //System.out.println("第"+j); 
            System.out.println(i);
        // }
        
    }
    double sigmoid(double mnet){
        return 1 / (1 + Math.exp(-mnet));
    }
}

class test{
    public static void main(String[] args) {
        Bp backfeed = new Bp();
        Bp feedward = new Bp();
        double[] backfeed_in = {1,0,1,0,0};
        double[] backfeed_out = {0,1,0,0,0};
        backfeed.init(3,backfeed_in,5,backfeed_out);
        double[] feedward_in = {0,1,0,1,0};
        double[] feedward_out = {0,0,1,0,0};
        feedward.init(3,feedward_in,5,feedward_out);

        for (int i = 0; i <1 ;i++){
            feedward.feedward();
        }
        feedward.printConstruction();
        backfeed.printConstruction();
        
    }

}
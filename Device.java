import java.util.Random;

public class Device extends Thread {
    String name,type;
    Router router;
    int connectionNum;
    gui g;
    Device(){}
    Device(String name,String type,Router router){
        this.name=name;
        this.type=type;
        this.router=router;
    }

    @Override
    public void run(){
        connectionNum=router.addConnection(this);

        Random random=new Random();

        int onlineTime=random.nextInt();

        if(onlineTime<0)onlineTime*=-1;

        onlineTime%=5000;
        try {
            Thread.sleep(onlineTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        router.logoutConnection(this);
    }
}
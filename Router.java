import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class Router {
    private semaphore sem;
    private Queue<Integer> connectionIds;
    private final Object object=new Object();
    private final Object writing=new Object();

    Router(int maxNumberOfConnections){
        sem=new semaphore(maxNumberOfConnections);
        connectionIds = new LinkedList<>();
        for(int i=1 ; i<=maxNumberOfConnections ; i++){
            connectionIds.offer(i);
        }
    }
    int addConnection(Device device){
        BufferedWriter file=null;
        synchronized (object){
            try {
                file = new BufferedWriter(new FileWriter("out.txt", true));
                file.write(device.name + " (" + device.type + ")" + " arrived and waiting\n");
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
            Network.curG.textArea1.append(device.name + " (" + device.type + ")" + " arrived and waiting\n");
        }
        sem.P();
        int curConnected = 0;
        synchronized (object) {
            curConnected = connectionIds.poll(); //return the available port
            Network.curG.textArea1.append("Connection " + curConnected + ": " + device.name + " Occupied\n");
            Network.curG.textArea1.append("Connection " + curConnected + ": " + device.name + " Performs online activity\n");
            try {
                file.write("Connection " + curConnected + ": " + device.name + " Occupied\n");
                file.write("Connection " + curConnected + ": " + device.name + " Performs online activity\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return curConnected;
    }
    void logoutConnection(Device device){
        synchronized(object){
            sem.V();
            connectionIds.offer(device.connectionNum);
            Network.curG.textArea1.append("Connection "+device.connectionNum+": "+device.name+" Logged out\n");

            BufferedWriter file = null;
            try {
                file = new BufferedWriter(new FileWriter("out.txt", true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.write("Connection "+device.connectionNum+": "+device.name+" Logged out\n");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
frst cmp
scnd mob
thrd tablet
foutth any
fifth mob
sixth cmp
sevneth cmp
eighth mob
ninth lab
tenth lab
 */
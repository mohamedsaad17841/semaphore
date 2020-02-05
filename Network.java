import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Network {
    static gui curG;
    public static void main(String[] args) throws IOException {
        curG=gui.Start();
        Scanner scn=new Scanner(System.in);
        System.out.println("What is number of WI-FI Connections?");

        int mxNumOfDev=scn.nextInt();

        Router router=new Router(mxNumOfDev);
        System.out.println("What is number of devices Clients want to connect?");
        int numOfDev=scn.nextInt();

        scn.nextLine();
        ArrayList<Device> devices=new ArrayList<>();
        while(numOfDev-->0){
            String[] line =scn.nextLine().split(" ");
            Device d=new Device(line[0],line[1],router);
            devices.add(d);
        }
        for(Device d:devices){
            d.start();
        }
    }
}
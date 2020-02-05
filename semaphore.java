public class semaphore {
    protected int value = 0 ;

    protected semaphore() { value = 0 ; }

    protected semaphore(int initial) { value = initial ; }

    synchronized void P() {

        value-- ;
        if (value < 0)
            try {
                wait();
            } catch(  InterruptedException e ) { }
    }

    public synchronized void V() {
        value++ ; if (value <= 0) notify() ;
    }
}
package quoter;

import javax.annotation.PostConstruct;
@Profiling
public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min = 2, max = 7)
    private int repeat;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @PostConstruct
    public  void init(){
        System.out.println("phase2");
        System.out.println(repeat);
}

    public TerminatorQuoter() {
        System.out.println("phase1");
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println("Message =" + message);
        }
    }
}

package quoter;

public class TerminatorQuoter implements Quoter{
  @InjectRandomInt(min =2,max =7)
    private int repeat;
private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        System.out.println("Message ="+ message);
    }
}

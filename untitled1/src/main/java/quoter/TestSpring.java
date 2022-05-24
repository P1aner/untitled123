package quoter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        TerminatorQuoter terminatorQuoter = context.getBean(TerminatorQuoter.class);
        terminatorQuoter.sayQuote();
    }
}

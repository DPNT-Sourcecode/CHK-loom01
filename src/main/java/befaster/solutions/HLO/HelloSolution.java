package befaster.solutions.HLO;

public class HelloSolution {
    public String hello(String friendName) {
        if (friendName == null) {
        	throw new RuntimeException("Wrong value provided");
        }
        
        return "Hello, " + friendName + "!";
    }
}





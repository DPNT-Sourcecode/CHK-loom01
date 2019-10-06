package befaster.solutions.HLO;

public class HelloSolution {
    public String hello(String friendName) {
        if (friendName == null) {
        	return new RuntimeException("Wrong value provided");
        }
        
        return "Hello " + friendName;
    }
}


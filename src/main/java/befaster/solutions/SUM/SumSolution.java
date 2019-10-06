package befaster.solutions.SUM;

import befaster.runner.SolutionNotImplementedException;

@SuppressWarnings("unused")
public class SumSolution {

    public int compute(int x, int y) throws Exception {
    	if (x < 0 || x > 100) {
    		throw new Exception("Wrong value provided.");
    	}
    	
    	if (y < 0 || y > 100) {
    		throw new Exception("Wrong value provided.");
    	}
    	
        return x + y;
    }

}


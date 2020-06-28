
public class EvolutionaryGameTheory {

	private class Strategy {
		// T: Cooperate
		// F: Defect
		public Strategy() {
		}
	}

	private class Match {
		Strategy a;
		Strategy b;

		public Match(Strategy x, Strategy y) {
			this.a = x;
			this.b = y;
		}

	}

	public static void main(String[] args) {
		Strategy[] strategies = new Strategy[15];
		strategies[0] = new Strategy();
		boolean x;
		System.out.println("x = "+x);

		// Max score: 15,000
		// Min score: 0

		// Run 200 rounds of 15 players against 15 opponents. 
		for (int i=200 ; i<200 ; i++) {
			// Run a round of 15 players against 15 opponents. 
			Match m = new Match(0,0);
		}
	}

}

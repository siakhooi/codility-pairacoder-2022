package nsh.codility;

public class PairACoder2022A implements PairACoder2022Interface {
	// A
	private final int MAXLENGTH = 100000;

	class Data {
		int[] Segments = new int[MAXLENGTH];
		int[] SegmentCount = new int[MAXLENGTH];
		int[] SegmentPosition = new int[MAXLENGTH];
		int[] AccumulatedMovable = new int[MAXLENGTH];

		int SegmentsLastIndex = -1;
		int maxStartPoint = MAXLENGTH;
		int MinRemain = MAXLENGTH;

		int[][] CharSegmentIndexes = new int[26][MAXLENGTH];
		int[] CharLastIndex = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1 };

		void initSegments(String S) {
			char currentChar = 'z' + 1;
			for (int i = 0; i < S.length(); i++) {
				char c = S.charAt(i);
				if (c == currentChar) {
					SegmentCount[SegmentsLastIndex]++;
				} else {
					currentChar = c;
					int charCode = c - 'a';
					Segments[++SegmentsLastIndex] = charCode;
					SegmentCount[SegmentsLastIndex] = 1;

					int lastIndex = ++CharLastIndex[charCode];
					CharSegmentIndexes[charCode][lastIndex] = SegmentsLastIndex;
					SegmentPosition[SegmentsLastIndex] = lastIndex;
					if (maxStartPoint == MAXLENGTH && lastIndex == 1)
						maxStartPoint = SegmentsLastIndex;
				}
			}
		}

		int calculateAccumulator() {
			int a = 0;
			for (int i = 0; i <= SegmentsLastIndex; i++) {
				if (SegmentCount[i] == 1)
					a++;
				AccumulatedMovable[i] = a;
			}
			MinRemain = a;
			return a;
		}

		int find(int currentMin, int start, int accumulatedNonMove) {
			if (currentMin == 0) {
				return 0;
			}
			if (accumulatedNonMove > D.MinRemain) {
				return currentMin;
			}

			if (start > SegmentsLastIndex) {
				D.MinRemain = Math.min(D.MinRemain, currentMin);
				return currentMin;
			}

			int c = D.Segments[start];
			int segmentsLastIndex = CharLastIndex[c];
			int segmentCurrentIndex = SegmentPosition[start];

			int count = D.SegmentCount[start];
			int acc = D.AccumulatedMovable[start];

			if (segmentCurrentIndex == segmentsLastIndex) {
				return find(currentMin, start + 1, accumulatedNonMove + (count == 1 ? 1 : 0));
			}

			int R = currentMin;
			if (count > 1) {
				int r1 = find(currentMin, start + 1, accumulatedNonMove);
				R = Math.min(R, r1);
			}

			for (int i = segmentCurrentIndex + 1; i <= segmentsLastIndex; i++) {
				int n2 = CharSegmentIndexes[c][i];
				int acc2 = D.AccumulatedMovable[n2];
				int x = acc2 - acc + (count == 1 ? 1 : 0);

				int r1 = find(currentMin - x, n2 + 1, accumulatedNonMove);
				R = Math.min(R, r1);
			}

			return R;
		}

	}

	Data D = new Data();

	public int solution(String S) {
		D.initSegments(S);
		int M=D.calculateAccumulator();
		int R=M;
		
		for (int i = 0; i < 26; i++) {
			D.MinRemain=M;
			if (D.CharLastIndex[i] != -1) {
				int r=D.find(D.MinRemain, D.CharSegmentIndexes[i][0], D.AccumulatedMovable[D.CharSegmentIndexes[i][0]]);
				R = Math.min(R, r);
			}
		}
		return R;
	}
//	printSegmentsTable(D);
//	printSegmentPositions(D);
//	printMaxPoint(D);

	// return D.find(D.MinWithoutMove, 0, D.SegmentsLastIndex);

	// -----------------------------------------
	static void pf(String f, Object... v) {
		System.out.printf(f, v);
		System.out.println("");
	}

	private void printMaxPoint(Data D) {
		System.out.printf("Max: %d\n", D.maxStartPoint);
		System.out.printf("SegmentLastIndex: %d\n", D.SegmentsLastIndex);
	}

	static void printSegmentPositions(Data D) {
		for (int i = 0; i < 26; i++) {
			if (D.CharLastIndex[i] == -1)
				continue;
			System.out.printf("%2d %s [%2d]:", i, (char) (i + 'a'), D.CharLastIndex[i] + 1);
			for (int j = 0; j <= D.CharLastIndex[i]; j++)
				System.out.printf("%d, ", D.CharSegmentIndexes[i][j]);
			System.out.println("");
		}

	}

	static void printSegmentsTable(Data D) {
		for (int i = 0; i <= D.SegmentsLastIndex; i++)
			System.out.printf("%2d %s %d %d : accum %d\n", i, D.Segments[i], D.SegmentCount[i], D.SegmentPosition[i],
					D.AccumulatedMovable[i]);

	}

	public static void main(String argv[]) {
		String S = "tqweqrtyr";
		int E = 1;

		int R = (new PairACoder2022A()).solution(S);
		pf(" input: %s", S);
		pf("expect: %d", E);
		pf("return: %d", R);
		pf("Result: %s", (R == E) ? "good" : "bad");
	}
	// java -cp target/classes nsh.codility.Challenge2021A
}

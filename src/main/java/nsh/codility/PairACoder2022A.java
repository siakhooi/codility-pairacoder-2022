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
		int[] CharLastIndex = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

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
		int M = D.calculateAccumulator();
		int R = M;

		for (int i = 0; i < 26; i++) {
			D.MinRemain = M;
			if (D.CharLastIndex[i] != -1) {
				int r = D.find(D.MinRemain, D.CharSegmentIndexes[i][0],
						D.AccumulatedMovable[D.CharSegmentIndexes[i][0]]);
				R = Math.min(R, r);
			}
		}
		return R;
	}
}

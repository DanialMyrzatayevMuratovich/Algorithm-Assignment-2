package org.example.danial;

public class Kadane {
    public static class Result {
        public int maxSum;
        public int start;
        public int end;
        public Result(int maxSum, int start, int end) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
        }
    }
    public static Result kadane(int[] arr) {
        int n = arr.length;
        Result res = new Result(arr[0], 0, 0);
        int currSum = arr[0];
        int tempStart = 0;
        for (int i = 1; i < n; i++) {
            if (currSum < 0) {
                currSum = arr[i];
                tempStart = i;
            } else {
                currSum += arr[i];
            }
            if (currSum > res.maxSum) {
                res.maxSum = currSum;
                res.start = tempStart;
                res.end = i;
            }
        }
        return res;
    }
}
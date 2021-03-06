package main;

import java.util.ArrayList;

public class MagicCalculator {

    // qOutput = The amount of the item you get from completing the child quest.
    // qNeeded = The amount of the item needed to complete the parent quest.
    // qItemsForQuest = The amount of times you can complete the child quest by collection the max stock pr. child quest item.
    public static ArrayList<Integer> calcTimesToDoChildQuest(int qOutput, int qNeeded, int qRepeatQuest) {
        ArrayList<Integer> result = new ArrayList<>();

        double maxRepOutput = qOutput * qRepeatQuest;
        double maxRepOutputCount = maxRepOutput;

        double maxRepTimesForNeeded = qNeeded / maxRepOutput;
        int maxRepTimesForNeededCeil = (int) Math.ceil(qNeeded / maxRepOutput);
        int count = 0;

        if(maxRepOutput >= qNeeded) {
            result.add(1);
            result.add(0);
            result.add(0);
            return result;
        }

        if(maxRepTimesForNeeded % 1 != 0) {
            for(int i = 0; i < maxRepTimesForNeededCeil-1; i++) {
                if(maxRepOutput < qNeeded) {
                    count++;
                    maxRepOutputCount += maxRepOutput;
                }
            }
        }

        double percentDecimal = maxRepTimesForNeeded % 1;
        int qRepeatQuestLast = (int) Math.round(qRepeatQuest * percentDecimal);

        int leftForNeeded = (count*(int)maxRepOutput) + (qOutput*qRepeatQuestLast);
        if(leftForNeeded < qNeeded) {
            int addOn = qNeeded - leftForNeeded;
            qRepeatQuestLast += addOn;
        }

        result.add(count);
        result.add(1);
        result.add(qRepeatQuestLast);

        return result;
    }


    // gained = the amount gained from completing the action.
    // needed = the amount needed to achieve something.
    // hasBoost = true: boost is on, false: boost is not off.
    public static int calcActionTimesToAchieveSomething(int gained, int needed, boolean hasBoost) {
        double tmpResult;
        int result;

        if(hasBoost) {
            gained *= 2;
        }

        tmpResult = needed / (double)gained;

        result = (int) Math.ceil(tmpResult);

        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainService;

/**
 *
 * @author LiemPT
 */
public class ProbabilityCalculation {
    
    public static double CalculationDB(String topicKeyword, String mainTopicName) {
        String key = topicKeyword + "|" + mainTopicName;
        double value = 0; //)=(nk + 1)/(n + |Vocabulary|)=(7+1)/(47+803)
        
        return value;
    }
    
    public static double ProbabilityWord(int Nk, int Vocabulary, int N) {
        return (Nk + 1)/(N + Vocabulary);
    }
    
    public static double LogBase2(int iNumber) {
        return Math.log10(iNumber)/Math.log10(2);
    }
}

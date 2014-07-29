/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extentEntity;

/**
 *
 * @author LiemPT
 */
public class MainTopicEnt {
    public long Id;
    public String TopicName;
    public String Content;
    public String Message1;
    public String Message2;
    public String Keyword;    
    public int NumKeyword;    
    public int NumKeywordNoDuplicate;
    
    public MainTopicEnt(){
        this.Id = 0;
        this.TopicName = "";
        this.Content = "";
        this.Message1 = "";
        this.Message2 = "";
        this.Keyword = "";
        this.NumKeyword = 0;
        this.NumKeywordNoDuplicate = 0;
    }
}

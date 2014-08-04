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
public class TrainTopicEnt {
    public long TopicId;
    public long MainTopicId;
    public String TopicName;
    public String Keyword;
    
    public TrainTopicEnt(){
        this.TopicId = 0;
        this.MainTopicId = 0;
        this.TopicName = "";
        this.Keyword = "";
    }
}

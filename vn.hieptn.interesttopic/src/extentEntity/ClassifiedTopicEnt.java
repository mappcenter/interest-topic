/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extentEntity;

/**
 *
 * @author liempt
 */
public class ClassifiedTopicEnt {
    public long TopicId;
    public String TopicName;
    public String Keyword;
    
    public ClassifiedTopicEnt(){
        this.TopicId = 0;
        this.TopicName = "";
        this.Keyword = "";
    }
}

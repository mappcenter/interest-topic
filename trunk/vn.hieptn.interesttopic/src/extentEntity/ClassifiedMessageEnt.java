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
public class ClassifiedMessageEnt {
    public long Id;
    public long PostId;
    public long TopicId;
    public double Probability;
    
    public ClassifiedMessageEnt(){
        this.Id = 0;
        this.PostId = 0;
        this.TopicId = 0;
        this.Probability = -1.0;
    }
}

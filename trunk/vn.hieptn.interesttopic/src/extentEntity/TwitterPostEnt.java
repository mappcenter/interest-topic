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
public class TwitterPostEnt {
    public long PostId;
    public long TweetId;
    public String Username;
    public long UserId;
    public String TweetContent;
    public String Message1;
    public String Message2;
    public String LinkUrl;    
    public int Status;
    public long CreatedDate;
    
    public static class STATUS{
        public static final int INIT = 0;
        public static final int ToMsg1 = 1;
        public static final int ToMsg2 = 2;
    }
    
    public TwitterPostEnt(){
        this.PostId = 0;
        this.TweetId = 0;
        this.Username = "";
        this.UserId = 0;
        this.TweetContent = "";
        this.Message1 = "";
        this.Message2 = "";
        this.LinkUrl = "";
        this.Status = TwitterPostEnt.STATUS.INIT;
        this.CreatedDate = 0;
    }
}

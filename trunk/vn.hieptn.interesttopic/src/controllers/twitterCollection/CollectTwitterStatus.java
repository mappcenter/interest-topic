/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.twitterCollection;

import com.nct.framework.util.DateTimeUtils;
import com.nct.framework.util.StringUtils;
import commonUtils.FunctionUtils;
import config.ConfigInfo;
import extentEntity.TwitterPostEnt;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import mainService.DataBaseService;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import vn.hieptn.interesttopic.InterestTopicMain;

/**
 *
 * @author liempt
 */
public class CollectTwitterStatus {
    private static final ConfigurationBuilder cb = new ConfigurationBuilder()
            .setDebugEnabled(true)
            .setOAuthConsumerKey("vJCDWp1lYcTvWa6V02g")
            .setOAuthConsumerSecret("0r7HrpoolKRfR7VFXFUEbYI9ELKz92VsC51ennugLy0")
            .setOAuthAccessToken("2213868380-qRzsxFQdUavjqCzAtGcqqV45WLRbacsbYk5L2Gf")
            .setOAuthAccessTokenSecret("tS3LedpAsq5mj3nifnokel7R3ymTbivknNpQ9sOTD6HGA");
    private static final TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
    private static FilterQuery fq = new FilterQuery();
    private static final String keywords1[] = {"trúng cử","ứng cử","thăm","nói chuyện","hoạt động","đi","chất vấn","công du",
            "tranh cử","tranh luận","trả lời","hỏi","quan hệ","tham nhũng","quyết định","chủ trương",
        "sắc lệnh","bí thư","chủ tịch","nhân dân","chính quyền","đoàn","cờ","bóng","y tế","sức khỏe","cộng đồng",
        "người","biểu diễn","nghệ thuật","cấm","thảo","nghị","khoa học","thuật","phủ","kiện","lệnh","thi hành","tòa","án",
        "lãnh đạo","nhạc","hội","báo","công","tục","ngừa","thuốc","nhập","xuất","nghỉ","lịch","diễn","mừng","tham nhũng",
        "đại","thể","ký","chiến","đấu","khai mạc","niệm","thành","mua bán","thành phố","nước","xã","huyện","tỉnh"
        ,"việc","trình","bày","nội","ngoại","hình ảnh","máy","kỹ thuật","điện","hãng","nhà","lịch","bóng",
        "bóng","sân","cờ","thi đấu","thời","thể","nhạc","viên","tính","trình","độ","chỉ","cầu","bằng","bằng cấp",
        "nghĩa","đoàn","thông","thể","phục","sát","yêu","dục","viện","bệnh","trị","tướng","quân","đội","tìm","dạy","đạt",
        "bình","luật","Nhật","ngừa","không","lĩnh","nghệ","thủ","hầm","bị","điều","chống","liêu","dựng","hóa","biên",
        "địa","sử","lý","toán","trực","giáo","cô","cộng","trọng","an ninh","sát","đường","trời","huấn","luyện","đạo","khẩn","cảnh",
        "trẻ","khấu","cấm","súng","lửa","khí","hạt","giới","đẹp","xấu","bình","tiến","giàu","văn","thi đấu","kết quả","xuất bản",
        "cây","thủy","sự","cái","giành","dành","khoan","toàn","chuẩn","sống","chết","sâu","ngựa","thể thao","khám phá","quốc tế",
        "chó","mèo","tây","chúng ta","chúng tôi","tôi","khuya","lỏng","chặt","vàng","sửa","thể dục","triển lãm","thời sự",
        "án","hình","tranh","cúp","xếp","hạng","giường","cóc","trương","đại","tiểu","tổ","đêm","tường thuật",
        "chơi","khách","sữa","chìm","mảnh","gần","răng","phẩu","họp","tuyển","sàn","vấn","kèm",
        "phiên","khơi"
        };
        
    private static StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();

//                 gets Username
                String username = status.getUser().getScreenName();
                long userIdTwitter = status.getUser().getId();
                System.out.println("@" + username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId();
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content + "\n");
// get Date                
                Date dateTweet=status.getCreatedAt();
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
                System.out.println(formatter.format(dateTweet));
                
                //======================== write file
                try {
                    
                    TwitterPostEnt tmpTweet = new TwitterPostEnt();
                    tmpTweet.TweetId = tweetId;
                    tmpTweet.UserId = DataBaseService.CreateUser(username, userIdTwitter);
                    tmpTweet.Username = username;
                    tmpTweet.TweetContent = StringUtils.removeNonPrintableCharactor(content);
                    tmpTweet.CreatedDate = DateTimeUtils.getMilisecondsNow();

                    long id = DataBaseService.Create(tmpTweet);
                    ConfigInfo.TWITTER_COLLECTION_OUTPUT += String.format("\nInsert : %s\t%s\t%s\t%s", id, username, content, FunctionUtils.DateToStringView(tmpTweet.CreatedDate));
                    if(id>0){
                        InterestTopicMain.txtOutput.setText(ConfigInfo.TWITTER_COLLECTION_OUTPUT);
                        ConfigInfo.TWITTER_COLLECTION_COUNTER++;
                    }
                    InterestTopicMain.txtNumCollected.setText(String.valueOf(ConfigInfo.TWITTER_COLLECTION_COUNTER));
                    
//                    String data = String.format("\r\n %s  %s  %s %s", "\r\n insert into POST values('"+ tweetId +"',", "'" + username, "',NULL,N'" 
//                    + content+"' , NULL,NULL,NULL,NULL,NULL,", "'"+formatter.format(dateTweet)+"')");
//
//                    File file = new File("Data18062014.txt");
//
//                    //if file doesnt exists, then create it
//                    if (!file.exists()) {
//                        file.createNewFile();
//                    }
//
//                    //true = append file
//                    FileWriter fileWritter = new FileWriter(file.getName(), true);
//                    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//                    bufferWritter.write(data);
//                    bufferWritter.close();
//
//                    System.out.println("Done");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };        
        
    
    public static void startCollectTwitterStatus() {
        fq.track(ConfigInfo.KEYWORD_COLLECT_TWITTER.toArray(new String[ConfigInfo.KEYWORD_COLLECT_TWITTER.size()]));
        twitterStream.addListener(listener);
        twitterStream.filter(fq);
        
    }
    
    public static void stopCollectTwitterStatus() {
        twitterStream.removeListener(listener);
        
    }
}

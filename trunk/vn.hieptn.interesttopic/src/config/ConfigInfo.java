/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import com.mysql.jdbc.StringUtils;
import com.nct.framework.common.Config;
import com.nct.framework.util.ConvertUtils;
import commonUtils.FunctionUtils;
import extentEntity.ClassifiedTopicEnt;
import extentEntity.TrainTopicEnt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author liempt
 */
public class ConfigInfo {
    private static final String configMainName = "main_settings";
    
    public static final String SERVER_DB = "database_server";
    public static final String LOCATION_OUTPUT_FILE = ConvertUtils.toString(Config.getParam(configMainName, "dir_output"), "");
    public static final String DIR_DATA_TRAIN = ConvertUtils.toString(Config.getParam(configMainName, "dir_data_train"), "");
    public static final String FILE_KEYWORD = ConvertUtils.toString(Config.getParam(configMainName, "file_keyword"), "");
    public static final String FILE_STOPWORD = ConvertUtils.toString(Config.getParam(configMainName, "file_stopword"), "");
    
    public static List<String> KEYWORD_COLLECT_TWITTER= new ArrayList<String>();
    public static final List<String> LIST_STOPWORD = FunctionUtils.ReadTextFileToList(FILE_STOPWORD);
    
    public static String TWITTER_COLLECTION_OUTPUT = "Twitter Collection START...";
    public static int TWITTER_COLLECTION_COUNTER = 0;
    public static String LOCATION_OUTPUT_FILE_DAT = "";
    public static List<Long> LIST_POSTID_LDA = new ArrayList<Long>();
    public static List<Long> LIST_TOPICID_LDA = new ArrayList<Long>();
    public static long MAIN_TOPICID_LDA = 0;
    public static HashMap<String, ClassifiedTopicEnt> MAP_TOPIC_REPORT = new HashMap<String, ClassifiedTopicEnt>();
    public static HashMap<String, TrainTopicEnt> MAP_TRAIN_TOPIC = new HashMap<String, TrainTopicEnt>();
    public static long cmbMainTopicResult_ID = 0;
    public static HashMap<String, String> MAP_MAIN_TOPIC = new HashMap<String, String>();
}

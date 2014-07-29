/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commonUtils;

import com.nct.framework.util.ConvertUtils;
import com.nct.framework.util.DateTimeUtils;
import config.ConfigInfo;
import extentEntity.TwitterPostEnt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author liempt
 */
public class FunctionUtils {
    
    public static boolean IsNullOrEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }
    
    public static String slug(String data) {
        //data = killUnicode(data).trim();
        if (data.length() > 0) {
            data = data.replaceAll("[^a-zA-Z0-9-_. ]*", "");
            data = data.replaceAll("[ ]{1,}", "-");
        }
        return data;
    }

    public static String removeSpecialCharacter(String data) {
        if (data.length() > 0) {
            data = data.replaceAll("[^a-zA-Z0-9-_. ]*", "");
        }
        return data;
    }
    
    public static String DateToStringView(long date) {
        SimpleDateFormat ad = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return ad.format(date);
    }
    
    public static String DateToDirView() {
        Date inDate = DateTimeUtils.getNow();
        String sYear = ConvertUtils.toString(inDate.getYear()+1900);
        int iNumber = inDate.getMonth()+1;
        String sMonth = iNumber>=10 ? ConvertUtils.toString(iNumber) : "0"+ConvertUtils.toString(iNumber);
        iNumber = inDate.getDate();        
        String sDay = iNumber>=10 ? ConvertUtils.toString(iNumber) : "0"+ConvertUtils.toString(iNumber);

        return ConvertUtils.toString(sYear + sMonth + sDay);
    }
    
    public static String ClearText(String inputText) {
        String regexSymbol = "[\\/\\[\\]|+@~!*@#$%^&?{}().,:;\\-=_\'\"<>]";
        
        return inputText.replaceAll(regexSymbol, "");
    }
    
    public static String ClearNumber(String inputText) {
        String regexSymbol = "[0-9]";        
        return inputText.replaceAll(regexSymbol, "");
    }
    
    public static String ClearTextUnicode(String inputText) {
        String regexSymbolSpecial = "[,.\\-=_:;]";
//        String regexSymbol = "[^\\sĐđa-z0-9A-Z_\\x{00C0}-\\x{00FF}\\x{1EA0}-\\x{1EFF}]*";
        String regexSymbol = "[^\\sa-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*";
        inputText = inputText.replaceAll(regexSymbolSpecial, " ");
        return inputText.replaceAll(regexSymbol, "");
    }
    
    public static String ClearStopWord(String inputText) {
        for(String tmpStopword : ConfigInfo.LIST_STOPWORD){
            inputText = inputText.replaceAll("\\b"+tmpStopword+"\\b", "");
    
        }
        return inputText;
    }
    
    public static TwitterPostEnt ConvertContentToMessage1(TwitterPostEnt twitterPostEnt) {
//        String regex = "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>";
        String regex = "http://\\S+\\s*";
        
        String newContent = twitterPostEnt.TweetContent;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(twitterPostEnt.TweetContent);
        String linkURL = "";
        if (matcher.find()) {
            String tmp = matcher.group();
            if(!tmp.isEmpty()){
                newContent = newContent.replaceAll(tmp, "");
                linkURL += "," + tmp;
            }
        }
        
        twitterPostEnt.LinkUrl = linkURL.isEmpty() ? "" : linkURL.substring(1);
        twitterPostEnt.Message1 = ClearTextUnicode(newContent);
        twitterPostEnt.Message1 = ClearStopWord(twitterPostEnt.Message1);
        twitterPostEnt.Status = TwitterPostEnt.STATUS.ToMsg1;
        
        return twitterPostEnt;
    }
    
    public static String ReadTextFile(String pathFileText, String charEnter) {
        String dataResult = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFileText));        
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line).append(charEnter);
                line = reader.readLine();
            }
            dataResult = sb.toString(); 
            reader.close();
        }catch (IOException ex){
            
        }
        return dataResult;
    }
    
    public static List<String> ReadTextFileToList(String pathFileText) {
        List<String> dataResult = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFileText));        
            String line = reader.readLine();

            while (line != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(line);
                dataResult.add(sb.toString());
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException ex){
            
        }
        return dataResult;
    }
    
    public static boolean WriteTextFile(String pathFileText, String fileContent) {
        try {
            File file = new File(pathFileText);

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //Collecting Data
            String fileDat = "";
            if(!IsNullOrEmpty(fileContent)){
                String[] listContent = StringUtils.split(fileContent, "\n");
                fileDat += listContent.length + "\n";

                for(String tmpPost : listContent){
                    if(tmpPost!=null){
                        fileDat += tmpPost + "\n";
                    }
                }
                //true = append file
                FileWriter fileWritter = new FileWriter(file.getPath(), false);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write(fileDat);
                bufferWritter.close();
                
                return true;
            }
        }catch (IOException ex){
            return false;
        }
        return false;
    }
}

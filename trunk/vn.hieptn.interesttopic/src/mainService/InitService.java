/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainService;

import com.nct.framework.common.LogUtil;
import com.nct.framework.util.StringUtils;
import commonUtils.FunctionUtils;
import config.ConfigInfo;
import org.apache.log4j.Logger;

/**
 *
 * @author LiemPT
 */
public class InitService {
    private static final Logger logger = LogUtil.getLogger(InitService.class);
    
    public static void Init() {
        
        String txtKeyword = FunctionUtils.ReadTextFile(ConfigInfo.FILE_KEYWORD, "");
        txtKeyword = txtKeyword.replaceAll("\"", ""); 
        ConfigInfo.KEYWORD_COLLECT_TWITTER.addAll(StringUtils.toList(txtKeyword.trim(),","));
    }
}

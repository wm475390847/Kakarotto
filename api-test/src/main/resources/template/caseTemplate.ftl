package ${packagePath};

import com.alibaba.fastjson.JSONObject;
import com.xhzy.qa.magic.pco.scene<#if apiPathNode??>.${apiPathNode}</#if>.${apiName};
import com.google.common.base.Preconditions;
import com.xhzy.qa.magic.pco.bc.config.BaseConfig;
import com.xhzy.qa.magic.pco.ba.logger.ResponseLog;
import okhttp3.Response;
import org.testng.annotations.Test;

/**
 * ${title}接口的用例
 *
 * @author wangmin
 * @date ${date}
 */
public class ${className} extends BaseCase {

    @Test(description = "${title}的单接口测试", enabled = false)
    public void test${apiName}() {
        //todo 参数逻辑需要自己填入
        ResponseLog<Response> response = new ${apiName}.Builder().build().run();
        logger.info(response.toString());
        String strResult = response.getStrResult();
        Boolean success = JSONObject.parseObject(strResult).getBoolean("success");
        Preconditions.checkArgument(success, "调用接口返回值应为：true" + " 实际结果为：" + success);
    }
}

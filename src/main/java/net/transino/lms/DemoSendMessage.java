package net.transino.lms;

import net.transino.core.util.Util;
import net.transino.lms.modules.comm.cfg.DefaultBody;
import net.transino.lms.modules.comm.cfg.Head;
import net.transino.lms.modules.comm.client.AbstractMessageAdapter;
import net.transino.lms.modules.comm.service.ICommTradeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.transino.core.Consts.CRLF;

public class DemoSendMessage extends AbstractMessageAdapter {
    public DemoSendMessage(String tradeCode, Object bodyBean, Map<String, List<?>> fileBean, ICommTradeService service) throws Exception {
//        super(tradeCode, bodyBean, fileBean);
        super(tradeCode,new DefaultBody(), bodyBean,fileBean);
    }

    public void setHead(String head){
        this.getInput().setHead(head);
    }
    @Override
    protected void makeReqHead() {
        Head bean = new Head();
        // 交易代码（右补空格）
        bean.setTradeCode("1002");
        // 终端号（无，给空格）
        bean.setClientNo("2002");
        // 终端ip(可空格)
        bean.setClientIP("192.168.1.3");
        // 交易模式,‘0’ 正常 ‘1’ 复核申请 ‘2’ 复核
        bean.setTradeMode("0");
        // ‘0’ 正常交易 ‘1’ 系统异常
        bean.setTradeStatus("0");
        // 通讯类型‘T’  tcp
        bean.setType("T");
        // 渠到类型。 ‘0’，前台交易；‘4’中间业务；
        bean.setTradeType("0");
        // 交易包类型。  ‘0’、‘ ’表示普通交易；
        bean.setBodyType("0");
//        this.getInput().setHead(bean.toString());
    }

    @Override
    protected void makeReqBody(DefaultBody body,Object bodyBean) throws Exception {
//        String strBody = "{\n" +
//                "\"TRANSCODE\":\"1001\",\n" +
//                "\"OCCURDATE\":\"20180425\",\n" +
//                "\"OCCURTIME\":\"091800\",\n" +
//                "\"OPERATOR\": \"0000\",\n" +
//                "\"REVIEWER\": \"\", \n" +
//                "\"AUDITOR\":  \"\",\n" +
//                "\"HOSTSERIAL\":   0,\n" +
//                "\"BRANCHSERIAL\": 1,\n" +
//                "\"OPENBRANCH\":   \"000000\",\n" +
//                "\"OPENBRANCH\":   \"000000\",\n" +
//                "\"RESPCODE\": \"0000\",\n" +
//                "\"TERMINAL\": \" \",\n" +
//                "\"FILEFLAG\": \"0\",\n" +
//                "\"RESPINFO\": \"\",\n" +
//                "\"CONTRACTNO\":   \"111111\",\n" +
//                "\"SEQUENCE\": 1,\n" +
//                "\"CUSTOMERNO\":   \"10000000018\",\n" +
//                "\"CUSTNAME\": \"测试\",\n" +
//                "\"CUSTTYPE\": \"1\",\n" +
//                "\"CURRENCY1\":\"CNY\",\n" +
//                "\"ACCOUNT1\":\"HQCKZH0000000001\",\n" +
//                "\"BALANCE3\": 10000000.00,\n" +
//                "\"BALANCE1\": 10000000.00,\n" +
//                "\"BALANCE2\": 5000000.00,\n" +
//                "\"TERMTYPE\": \"1\",\n" +
//                "\"BIZCODE\":  \"101101101\",\n" +
//                "\"AMOUNT1\":  888.00,\n" +
//                "\"ABSTRACT\": \"穷死了\"\n" +
//                "}";
       this.getInput().setBody(bodyBean.toString());
    }

    @Override
    protected void makeReqFiles(Map<String, List<?>> fileBean) throws Exception {

//        list.add("BZJ00000001|我的信贷保证金账户1|CNY|1|200000.00|0|");
//        list.add("BZJ00000002|我的信贷保证金账户2|CNY|1|300000.00|0|");
        fileBean.forEach((String k, List<?> v)->{
            List<String> list = new ArrayList<String>();
            for(int i=0;i< v.size();i++){
                String str = v.get(i).toString().trim();
                if (str.length() > 0) {
                    if (i > 0) {
                        list.add(CRLF + v.get(i).toString());
                    } else {
                        list.add(v.get(i).toString());
                    }
                }
            }
            if (list.size() != 0){
                this.getInput().getFiles().put(k,list);
            }

        });

    }
}
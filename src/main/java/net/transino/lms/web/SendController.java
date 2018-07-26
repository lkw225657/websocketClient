package net.transino.lms.web;

import net.transino.lms.DemoSendMessage;
import net.transino.lms.modules.comm.client.SocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @since 5.0
 */
@RestController
public class SendController {
    @Autowired
    SocketClient client;

    @PostMapping(value = "/send")
    Object send(@RequestBody PostDataEntity entity){
        DemoSendMessage demo = null;
        try {
            String tradeCode = entity.getHead().substring(0,4);
            Map<String, List<?>> fileBean = new HashMap<>(1);
            if (entity.getFiletext() != null) {
                fileBean.put(entity.getFilename(),entity.getFiletext());
            }
            demo = new DemoSendMessage(tradeCode, entity.getBody(), fileBean, null);
            demo.setHead(entity.getHead().substring(0,56));
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.send(demo);
        return demo.getOutput();
    }
}
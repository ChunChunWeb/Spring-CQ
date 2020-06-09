package com.huangyichun.auto_cq.plugin;

import com.huangyichun.auto_cq.utils.ParseUtil;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * 示例插件
 * 插件必须继承CQPlugin，上面要 @Component
 * <p>
 * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
 * 查看API参数类型：光标移动到方法括号中按Ctrl+P
 * 查看API说明：光标移动到方法括号中按Ctrl+Q
 */



@Component
public class MainPlugin extends CQPlugin {

    static Set<Long> sendGroupsNumber = new HashSet<>();
    static Set<Long> getGroupsNumber = new HashSet<>();
    static{
        sendGroupsNumber.add(530754133L);

//        getGroupsNumber.add(654129971L);
//        getGroupsNumber.add(558525849L);
        getGroupsNumber.add(680335967L);
    }
    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        // 获取 发送者QQ 和 消息内容
        long userId = event.getUserId();
        String msg = event.getMessage();
        System.out.println(msg);
        if (msg.equals("hi")) {
            // 调用API发送hello
            cq.sendPrivateMsg(userId, "hello", false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


    /**
     * 收到群消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送群消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        System.out.println("群" + groupId + "，人" + userId + ":\n" + msg);

        boolean isComingFromTargetGroup = getGroupsNumber.contains(groupId);
        if ( isComingFromTargetGroup || msg.contains("test")) {
            System.out.println("转发成功");


            boolean isContainTAOBAOURL = ParseUtil.isContainsTaoBaoUrl(msg);
//            boolean isContainJDURL = ParseUtil.isContainsJDUrl(msg);

            if (isContainTAOBAOURL) {
                ArrayList<String> taoBaoUrls = ParseUtil.getTaoBaoUrls(msg);
                for (String taoBaoUrl : taoBaoUrls) {
                    System.out.println(msg.replace(taoBaoUrl,
                            Objects.requireNonNull(ParseUtil.getTaoBaoRealUrl(ParseUtil.doGet(ParseUtil.TAO_KOU_LIN_PARSE + taoBaoUrl)))));
                }
            }

            String result = "未转换的msg:" + msg;

            // 调用API发送消息
            if (!msg.contains("www"))
            for (Long groupNumber : sendGroupsNumber) {
                cq.sendGroupMsg(groupNumber, result, false);
            }

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }

        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


}

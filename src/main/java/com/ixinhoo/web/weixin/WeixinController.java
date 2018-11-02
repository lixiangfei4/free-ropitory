package com.ixinhoo.web.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ixinhoo.web.weixin.Utils.CheckUtils;
import com.ixinhoo.web.weixin.Utils.MessageUtils;

@Controller
@RequestMapping(value="hello")
public class WeixinController {
  /**
   * 绑定账号到微信后台
   * @param signature
   * @param timestamp
   * @param nonce
   * @param echostr
   * @param req
   */

	public void hello(String signature,String timestamp,String nonce,String echostr,HttpServletResponse req) {
		
		
		PrintWriter out;
		try {
			out = req.getWriter();
			 if(CheckUtils.check(signature, timestamp, nonce)) {
		    	  System.out.println("yes");
		    	  out.print(echostr);
		    	}
		} catch (IOException e) {
		
			e.printStackTrace();
		}	
     
	
	}
	/**
	 * 发送文本请求
	 * @param res
	 * @param req
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void msg(HttpServletResponse res,HttpServletRequest req) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out =res.getWriter();
		try {
			//把req的xml数据转换成map格式
			Map<String, String> map = MessageUtils.xmlToMap(req);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtils.MSGTYPE_TEXT.equals(msgType)) {
				TestMessage text = new TestMessage();
				if("1".equals(content)) {
					text.setContent(MessageUtils.firstMenu());
				}
				if("2".equals(content)) {
					text.setContent(MessageUtils.secondMenu());
				}
				if("?".equals(content)||"？".equals(content)) {
					text.setContent(MessageUtils.allMenus());
				}
				
				text.setFromUserName(fromUserName);
				text.setCreateTime(new Date().getTime());
				text.setMsgType("text");
				text.setToUserName(toUserName);
				
				message = MessageUtils.textToXml(text);
				
			}else if(MessageUtils.MSGTYPE_EVENT.equals(msgType)) {
				String eventType = map.get("EVENT");
				TestMessage text = new TestMessage();
				//再来判断是什么事件
				if(MessageUtils.MSGTYPE_SUBSCRIBE.equals(eventType)) {
					text.setContent(MessageUtils.menus());
					text.setMsgType(MessageUtils.MSGTYPE_SUBSCRIBE);
					text.setCreateTime(new Date().getTime());
					text.setToUserName(toUserName);
					text.setFromUserName(fromUserName);
					message = MessageUtils.textToXml(text);
				}
			}
			//发送回去
			out.print(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
		
	}
}

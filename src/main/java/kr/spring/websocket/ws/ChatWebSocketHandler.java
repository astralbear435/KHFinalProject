package kr.spring.websocket.ws;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
 * 스프링 웹소켓을 이용해서 웹소켓 서버 구현
 * 1.WebSocketHandler 인터페이스를 구현
 * 2.<websocket:handlers>를 이용해 요청URL에 대한 WebSocketHandler 구현 객체 등로
 * */

public class ChatWebSocketHandler extends TextWebSocketHandler {
   
   private Map<String,WebSocketSession> users = new ConcurrentHashMap<String,WebSocketSession>();
   
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      
      Map<String,Object> map = session.getAttributes();
      String userId = (String)map.get("user_id");
      System.out.println(userId +"연결 됨");
      //유저를 저장
      users.put(session.getId(), session);

   }
   //웹 소켓 클라이언트가 데이터를 전송
   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      
      Map<String,Object> map = session.getAttributes();
      String userId = (String)map.get("user_id");
      System.out.println("로그인한 아이디 >>>>>>>>>> " + userId);
      
      System.out.println(session.getId() + "로 부터 메시지 수신 : " + message.getPayload());
      //클라이언트에 메시지 전송
      //session.sendMessage(new TextMessage(message.getPayload()));
      //메시지를 수신하면 등록된 유저 전원에게 메시지 전송
      for(WebSocketSession s : users.values()) {
         //유저에 메시지 전송
         s.sendMessage(message);
         System.out.println(s.getId() + "에게 메시지 발송 : " + message.getPayload());
      }
   }
   //웹소켓 클라이언트와 연결이 종료되면 호출
   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      
      System.out.println(session.getId()+" 연결 끊김");
      //연결 종료된 유저 삭제
      users.remove(session.getId());
   }
   
   @Override
   public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
      
      System.out.println(session.getId() + " 에러발생 : " + exception.getMessage());
   }
}

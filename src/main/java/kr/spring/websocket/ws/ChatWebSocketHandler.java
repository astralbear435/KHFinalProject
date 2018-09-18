package kr.spring.websocket.ws;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
 * ������ �������� �̿��ؼ� ������ ���� ����
 * 1.WebSocketHandler �������̽��� ����
 * 2.<websocket:handlers>�� �̿��� ��ûURL�� ���� WebSocketHandler ���� ��ü ���
 * */

public class ChatWebSocketHandler extends TextWebSocketHandler {
   
   private Map<String,WebSocketSession> users = new ConcurrentHashMap<String,WebSocketSession>();
   
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      
      Map<String,Object> map = session.getAttributes();
      String userId = (String)map.get("user_id");
      System.out.println(userId +"���� ��");
      //������ ����
      users.put(session.getId(), session);

   }
   //�� ���� Ŭ���̾�Ʈ�� �����͸� ����
   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      
      Map<String,Object> map = session.getAttributes();
      String userId = (String)map.get("user_id");
      System.out.println("�α����� ���̵� >>>>>>>>>> " + userId);
      
      System.out.println(session.getId() + "�� ���� �޽��� ���� : " + message.getPayload());
      //Ŭ���̾�Ʈ�� �޽��� ����
      //session.sendMessage(new TextMessage(message.getPayload()));
      //�޽����� �����ϸ� ��ϵ� ���� �������� �޽��� ����
      for(WebSocketSession s : users.values()) {
         //������ �޽��� ����
         s.sendMessage(message);
         System.out.println(s.getId() + "���� �޽��� �߼� : " + message.getPayload());
      }
   }
   //������ Ŭ���̾�Ʈ�� ������ ����Ǹ� ȣ��
   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      
      System.out.println(session.getId()+" ���� ����");
      //���� ����� ���� ����
      users.remove(session.getId());
   }
   
   @Override
   public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
      
      System.out.println(session.getId() + " �����߻� : " + exception.getMessage());
   }
}

package kr.spring.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.goods.domain.CartListCommand;
import kr.spring.goods.domain.GoodsCommand;
import kr.spring.goods.domain.GoodsListCommand;
import kr.spring.goods.domain.OrderCommand;
import kr.spring.goods.service.GoodsService;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.mypage.service.MypageService;
import kr.spring.note.domain.NoteCommand;
import kr.spring.note.service.NoteService;
import kr.spring.shelter.domain.ShelterCommand;
import kr.spring.shelter.service.ShelterService;
import kr.spring.util.PagingUtil;

@Controller
public class GoodsCartAjaxController {
	private Logger log=Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private NoteService noteService;
	@Resource
	private MypageService mypageService;
	

	//======================��ٱ��ϵ��==================
	
	@RequestMapping(value="/goods/insertCart.do")
	@ResponseBody
	public Map<String,String> saveCart(@RequestParam(value="p_num")int p_num,@RequestParam(value="p_name")String p_name,@RequestParam(value="p_id") String p_id,@RequestParam(value="p_price")int p_price,@RequestParam(value="p_amount")int p_amount,@RequestParam(value="p_goodsname") String p_goodsname,@RequestParam(value="p_goodsphoto") String p_goodsphoto,HttpSession session){
			
		Map<String,String> map=new HashMap<String,String>();
		String user_id=(String)session.getAttribute("user_id");
		if(user_id==null) {
			//�α��� �ȵ�.
			map.put("result","logout");
			
		}else {
			//�հ� ����ؼ� �־��ֱ�
			int p_total=p_price*p_amount;
			//��ٱ��� ���
			CartListCommand cart=new CartListCommand();
			
			cart.setP_num(p_num);
			cart.setP_id(p_id);
			cart.setP_name(p_name);
			cart.setP_price(p_total);
			cart.setP_amount(p_amount);
			cart.setP_goodsname(p_goodsname);
			cart.setP_goodsphoto(p_goodsphoto);

			if(log.isDebugEnabled()) {
				log.debug("<<�� ����>> :"+cart);
			}
			//��ٱ��Ͽ� ����ϱ�
			goodsService.insertCart(cart);			
			map.put("result","success");
		}
		return map;
	}
//==============��ٱ��� ���!================
	@RequestMapping("/goods/cartList.do")
	@ResponseBody
	public ModelAndView CartList(HttpSession session){

		String p_id=(String)session.getAttribute("user_id");
		ModelAndView cartmav = new ModelAndView();
		//�̷α��� ����
		if(p_id==null) {
			 cartmav.setViewName("member/memberLogin");
			 return cartmav;
		}else{
		//�α��� ����
		//��ٱ��� �������
		 List<CartListCommand> cartlist=null;
		 cartlist=goodsService.getCart(p_id);
		 int sum=0;
		 for(CartListCommand a:cartlist) {
			 sum+=a.getP_price();
		 }	 
		
		 cartmav.setViewName("goodsCart");
		 cartmav.addObject("cartList",cartlist);
		 cartmav.addObject("sum",sum);
		return cartmav;
		}
	}
	//====================��ٱ��� ����====================
	@RequestMapping("/goods/deleteCart.do")
	@ResponseBody
	public Map<String,String> deleteCart(@RequestParam("selectNum")String selectNum,@RequestParam("id") String id,HttpSession session){
//�޸��� �����ڷ� �迭�� �����ϱ�
		String[] temp= selectNum.split(",");

		if(log.isDebugEnabled()) {
			 log.debug("<<selectNum>> : "+selectNum);
			 log.debug("<<id>>:"+id);
			 }

	 Map<String,String> map=new HashMap<String, String>();
	 String user_id=(String)session.getAttribute("user_id");
	 if(user_id==null) {
		 //�̷α��� ����
		 map.put("result","logout");//���׾ƿ����¶�� �˸���
		 
	 }else if(user_id!=null&&user_id.equals(id)) {
		 //�α��� ���� �� �ۼ��ھ��̵�� ��ġ�ϴ°��
		 int i=0;
		for(String a:temp){
			int p_cartnum=Integer.parseInt(temp[i]);
			System.out.println(temp[i]);
			goodsService.deleteCart(p_cartnum);
		    i+=1;
		 }
		 map.put("result","success");		 
	 }else {
		 //�α��ξ��̵�� �ۼ��� ����ġ
		 map.put("result","wrongAccess");
	 }
	 return map;
 }
	//===============�κ��ֹ� �� ��������===============
@RequestMapping("/goods/orderprice.do")
@ResponseBody
public Map<String,Object>priceCart(@RequestParam("num") String price,@RequestParam("p_num") String p_num,@RequestParam("p_name") String p_name,@RequestParam("p_amount") String p_amount,HttpSession session){
	if(log.isDebugEnabled()) {
		 log.debug("<<��ٱ��� ��ȣ>> : "+price);
		 log.debug("<<��ǰ ��ȣ��>> : "+p_num);
		 log.debug("<<����� �̸�>> : "+p_name);
		 log.debug("<<������>> : "+p_amount);
		 }
	String[] temp= price.split(",");
	 Map<String,Object> map=new HashMap<String,Object>();
	 String user_id=(String)session.getAttribute("user_id");
	 List<CartListCommand> orderlist=null;
	 int pay=0;
	 int i=0;
	 //������ �ֹ� �� �� ������� (����)
	 for(String a:temp){
			int p_cartnum=Integer.parseInt(temp[i]);
			orderlist=goodsService.getOrderPrice(p_cartnum);
			i+=1;
			 for(CartListCommand p:orderlist) {
				 pay+=p.getP_price();
			 }
			}	
	 //=======�� ���� ���� ��
	 //�ֹ��� ���� �������
	 MemberCommand member=memberService.selectMember(user_id);
	 String email=member.getM_email();
	 String user_name=member.getM_name();
	 String tel=member.getM_phone();
	 String addr=member.getM_address();
	 String postcode=member.getM_zipcode();
	 //�ֹ��� ���� ������� ->>> ��
	 map.put("email",email);//�ֹ��� �̸���
	 map.put("user_name",user_name);//�ֹ��� �̸�
	 map.put("tel",tel);//�ֹ��� ��ȭ��ȣ
	 map.put("addr",addr);//�ֹ��� �ּ�
	 map.put("postcode",postcode);//�ֹ��� �����ȣ
	 map.put("user_id",user_id);//���� ���̵�
	 map.put("pay",pay);//�� ��
	 map.put("p_name",p_name);//����� �̸�
	 map.put("p_num",p_num);//��ǰ��ȣ��
	 map.put("p_amount",p_amount);//����
	 
	 return map;
}
//======================���� ������ ���� �ֹ��Ϸ� DB�� �����ϱ�!!================

@RequestMapping("/goods/order.do")
@ResponseBody
public Map<String,String> insertOrder(@RequestParam("as_id")String as_id,@RequestParam("dona_id")String dona_id,@RequestParam("dona_name") String dona_name,@RequestParam("dona_asname") String dona_asname,@RequestParam("goodsNum") String goodsnum,@RequestParam("ptotal") int dona_price,@RequestParam("amount") String dona_goodsamount,@RequestParam("dona_message")String dona_message,HttpSession session){
	Map<String,String> map=new HashMap<String,String>();
	//���� ī��Ʈ ����� ��
	Map<String,Object> map2=new HashMap<String,Object>();
	String user_id=(String)session.getAttribute("user_id");
	if(user_id==null) {
		//�α��� �ȵ�.
		map.put("result","logout");		
	}else{
		OrderCommand order=new OrderCommand();
		order.setDona_id(dona_id);//���̵�
		order.setDona_username(dona_name);//�����̸�
		order.setDona_asname(dona_asname);//������
		order.setDona_goodsnum(goodsnum);//��ǰ��ȣ
		order.setDona_goodsamount(dona_goodsamount);//��ǰ ����
		order.setDona_price(dona_price);//�� ����
		order.setDona_message(dona_message);//���� �޼���
		if(log.isDebugEnabled()) {
			log.debug("<<�������� �� ����>> :"+order);
			log.debug("<<��ȣ�� ���̋I>> :"+as_id);
		}
		//����DB�� ����ϱ�
		goodsService.insertOrder(order);
		//������ �� ��ŭ as_goods���� �����ϱ�
		map2.put("goodsnum",goodsnum);
		map2.put("dona_goodsamount",dona_goodsamount);
		map2.put("dona_asname",dona_asname);

		goodsService.minusCount(map2);
		
		NoteCommand note=new NoteCommand();
		note.setSender(dona_id);
		note.setRecipient(as_id);
		note.setNote_content(dona_message);
		noteService.insert(note);
		
		map.put("result","success");
	}
	return map;
}
//==================���� �ֹ� �����ϱ� ===================
@RequestMapping("/goods/multiOrder.do")
@ResponseBody
public Map<String,Object> insertMultiOrder(@RequestParam("dona_id")String dona_id,@RequestParam("dona_asname")String dona_asname,@RequestParam("goodsNum")String dona_goodsnum,@RequestParam("dona_price")int dona_price,@RequestParam("amount")String dona_goodsamount){
	Map<String,Object> map=new HashMap<String,Object>();
	OrderCommand multiorder=new OrderCommand();
	multiorder.setDona_id(dona_id);
	multiorder.setDona_asname(dona_asname);
	multiorder.setDona_price(dona_price);
	multiorder.setDona_goodsnum(dona_goodsnum);
	multiorder.setDona_goodsamount(dona_goodsamount);
	if(log.isDebugEnabled()) {
		log.debug("<<��Ƽ �������� �� ����>> :"+multiorder);
	}
	//��Ƽ �ֹ� ����
	goodsService.insertOrder(multiorder);
	//������� �ֹ� ������ �� �޾ƿ���
	int order_num=goodsService.selectDona_num();
	
/*	���� ��ŭ ī���� ���ִ°� ���� ����*/
	
	OrderCommand order=new OrderCommand();
	int count2=mypageService.selectCountdonation(dona_id);
	//0���� ������ ����
	if(count2>0) {
	order=mypageService.selectNowList(dona_id);
	
	String[] as_name= order.getDona_asname().split(",");
	String[] goodsNum=order.getDona_goodsnum().split(",");
	String[] goodsAmount=order.getDona_goodsamount().split(",");

	
	
	for(int i=0;i<as_name.length;i++){
		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("goodsnum",goodsNum[i]);
		map2.put("dona_goodsamount",goodsAmount[i]);
		map2.put("dona_asname",as_name[i]);
		goodsService.minusCount(map2);
		}
	}
	/*	���� ��ŭ ī���� ���ִ°� ���� ��*/
	
	map.put("result","success");
	map.put("order_num",order_num);
	if(log.isDebugEnabled()) {
		log.debug("<<�̹����� ������ ��ȣ �Դϴ�.>> :"+order_num);
	}
	return map;
}
//�����,���޸޼��� ������Ʈ
@RequestMapping("/goods/orderUpdate.do")
@ResponseBody
public Map<String,String> updateMultiOrder(@RequestParam("dona_username")String dona_username,@RequestParam("dona_message")String dona_message,@RequestParam("order_num")int order_num,HttpSession session){
	String id=(String)session.getAttribute("user_id");
	Map<String,String> map=new HashMap<String,String>();
	OrderCommand multiorder=new OrderCommand();
	multiorder.setDona_username(dona_username);
	multiorder.setDona_message(dona_message);
	multiorder.setDona_num(order_num);
	//�������� ���ؼ� �߰��� ������Ʈ�ֱ�.
	goodsService.updateOrder(multiorder);

	map.put("result","success");
	return map;	
}

//�������� ī��Ʈ ���� �׽�Ʈ�Դϴ�.
@RequestMapping("/goods/CountMinus.do")
@ResponseBody
public Map<String,Object> CountMinus(HttpSession session){
	String id=(String)session.getAttribute("user_id");
	Map<String,Object> newMap=new HashMap<String,Object>();
	OrderCommand order=new OrderCommand();
	int count2=mypageService.selectCountdonation(id);
	//0���� ������ ����
	if(count2>0) {
	order=mypageService.selectNowList(id);
	
	String[] as_name= order.getDona_asname().split(",");
	String[] goodsNum=order.getDona_goodsnum().split(",");
	String[] goodsAmount=order.getDona_goodsamount().split(",");
	if(log.isDebugEnabled()) {
		log.debug("<<����Ʈ ��� ���� ���ھƾ�>> :"+order);
		log.debug("<<�迭üũ ����� ���ֳ�>> :"+as_name+" / "+goodsNum+" / "+goodsAmount);
	}
	
	int i=0;
	for(String a:as_name){
		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("goodsnum",goodsNum[i]);
		map2.put("dona_goodsamount",goodsAmount[i]);
		map2.put("dona_asname",as_name[i]);
		goodsService.minusCount(map2);
		i+=1;
	}	
	newMap.put("result","success");
	}else {
		newMap.put("result","no");
	}	
	return newMap;
 }
}

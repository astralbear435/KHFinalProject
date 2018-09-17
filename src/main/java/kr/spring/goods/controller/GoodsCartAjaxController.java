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
	

	//======================장바구니등록==================
	
	@RequestMapping(value="/goods/insertCart.do")
	@ResponseBody
	public Map<String,String> saveCart(@RequestParam(value="p_num")int p_num,@RequestParam(value="p_name")String p_name,@RequestParam(value="p_id") String p_id,@RequestParam(value="p_price")int p_price,@RequestParam(value="p_amount")int p_amount,@RequestParam(value="p_goodsname") String p_goodsname,@RequestParam(value="p_goodsphoto") String p_goodsphoto,HttpSession session){
			
		Map<String,String> map=new HashMap<String,String>();
		String user_id=(String)session.getAttribute("user_id");
		if(user_id==null) {
			//로그인 안됨.
			map.put("result","logout");
			
		}else {
			//합계 계산해서 넣어주기
			int p_total=p_price*p_amount;
			//장바구니 등록
			CartListCommand cart=new CartListCommand();
			
			cart.setP_num(p_num);
			cart.setP_id(p_id);
			cart.setP_name(p_name);
			cart.setP_price(p_total);
			cart.setP_amount(p_amount);
			cart.setP_goodsname(p_goodsname);
			cart.setP_goodsphoto(p_goodsphoto);

			if(log.isDebugEnabled()) {
				log.debug("<<함 보자>> :"+cart);
			}
			//장바구니에 등록하기
			goodsService.insertCart(cart);			
			map.put("result","success");
		}
		return map;
	}
//==============장바구니 목록!================
	@RequestMapping("/goods/cartList.do")
	@ResponseBody
	public ModelAndView CartList(HttpSession session){

		String p_id=(String)session.getAttribute("user_id");
		ModelAndView cartmav = new ModelAndView();
		//미로그인 상태
		if(p_id==null) {
			 cartmav.setViewName("member/memberLogin");
			 return cartmav;
		}else{
		//로그인 상태
		//장바구니 갖고오기
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
	//====================장바구니 삭제====================
	@RequestMapping("/goods/deleteCart.do")
	@ResponseBody
	public Map<String,String> deleteCart(@RequestParam("selectNum")String selectNum,@RequestParam("id") String id,HttpSession session){
//콤마를 구분자로 배열에 저장하기
		String[] temp= selectNum.split(",");

		if(log.isDebugEnabled()) {
			 log.debug("<<selectNum>> : "+selectNum);
			 log.debug("<<id>>:"+id);
			 }

	 Map<String,String> map=new HashMap<String, String>();
	 String user_id=(String)session.getAttribute("user_id");
	 if(user_id==null) {
		 //미로그인 상태
		 map.put("result","logout");//러그아웃상태라고 알리기
		 
	 }else if(user_id!=null&&user_id.equals(id)) {
		 //로그인 상태 및 작성자아이디와 일치하는경우
		 int i=0;
		for(String a:temp){
			int p_cartnum=Integer.parseInt(temp[i]);
			System.out.println(temp[i]);
			goodsService.deleteCart(p_cartnum);
		    i+=1;
		 }
		 map.put("result","success");		 
	 }else {
		 //로그인아이디와 작성자 불일치
		 map.put("result","wrongAccess");
	 }
	 return map;
 }
	//===============부분주문 값 가져오기===============
@RequestMapping("/goods/orderprice.do")
@ResponseBody
public Map<String,Object>priceCart(@RequestParam("num") String price,@RequestParam("p_num") String p_num,@RequestParam("p_name") String p_name,@RequestParam("p_amount") String p_amount,HttpSession session){
	if(log.isDebugEnabled()) {
		 log.debug("<<장바구니 번호>> : "+price);
		 log.debug("<<상품 번호들>> : "+p_num);
		 log.debug("<<기부지 이름>> : "+p_name);
		 log.debug("<<수량들>> : "+p_amount);
		 }
	String[] temp= price.split(",");
	 Map<String,Object> map=new HashMap<String,Object>();
	 String user_id=(String)session.getAttribute("user_id");
	 List<CartListCommand> orderlist=null;
	 int pay=0;
	 int i=0;
	 //선택한 주문 총 값 갖고오기 (시작)
	 for(String a:temp){
			int p_cartnum=Integer.parseInt(temp[i]);
			orderlist=goodsService.getOrderPrice(p_cartnum);
			i+=1;
			 for(CartListCommand p:orderlist) {
				 pay+=p.getP_price();
			 }
			}	
	 //=======값 갖고 오기 끝
	 //주문자 정보 갖고오기
	 MemberCommand member=memberService.selectMember(user_id);
	 String email=member.getM_email();
	 String user_name=member.getM_name();
	 String tel=member.getM_phone();
	 String addr=member.getM_address();
	 String postcode=member.getM_zipcode();
	 //주문자 정보 갖고오기 ->>> 끝
	 map.put("email",email);//주문자 이메일
	 map.put("user_name",user_name);//주문자 이름
	 map.put("tel",tel);//주문자 전화번호
	 map.put("addr",addr);//주문자 주소
	 map.put("postcode",postcode);//주문자 우편번호
	 map.put("user_id",user_id);//유저 아이디
	 map.put("pay",pay);//총 값
	 map.put("p_name",p_name);//기부지 이름
	 map.put("p_num",p_num);//상품번호들
	 map.put("p_amount",p_amount);//갯수
	 
	 return map;
}
//======================실제 결제한 정보 주문완료 DB에 저장하기!!================

@RequestMapping("/goods/order.do")
@ResponseBody
public Map<String,String> insertOrder(@RequestParam("as_id")String as_id,@RequestParam("dona_id")String dona_id,@RequestParam("dona_name") String dona_name,@RequestParam("dona_asname") String dona_asname,@RequestParam("goodsNum") String goodsnum,@RequestParam("ptotal") int dona_price,@RequestParam("amount") String dona_goodsamount,@RequestParam("dona_message")String dona_message,HttpSession session){
	Map<String,String> map=new HashMap<String,String>();
	//결제 카운트 빼기용 맵
	Map<String,Object> map2=new HashMap<String,Object>();
	String user_id=(String)session.getAttribute("user_id");
	if(user_id==null) {
		//로그인 안됨.
		map.put("result","logout");		
	}else{
		OrderCommand order=new OrderCommand();
		order.setDona_id(dona_id);//아이디
		order.setDona_username(dona_name);//유저이름
		order.setDona_asname(dona_asname);//기부장소
		order.setDona_goodsnum(goodsnum);//상품번호
		order.setDona_goodsamount(dona_goodsamount);//상품 수량
		order.setDona_price(dona_price);//총 가격
		order.setDona_message(dona_message);//보낼 메세지
		if(log.isDebugEnabled()) {
			log.debug("<<결제내역 함 보자>> :"+order);
			log.debug("<<보호소 아이딬>> :"+as_id);
		}
		//결제DB에 등록하기
		goodsService.insertOrder(order);
		//결제된 수 만큼 as_goods에서 제거하기
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
//==================여러 주문 저장하기 ===================
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
		log.debug("<<멀티 결제내역 함 보자>> :"+multiorder);
	}
	//멀티 주문 저장
	goodsService.insertOrder(multiorder);
	//방금저장 주문 시퀀스 값 받아오기
	int order_num=goodsService.selectDona_num();
	
/*	구매 만큼 카운팅 빼주는거 진행 시작*/
	
	OrderCommand order=new OrderCommand();
	int count2=mypageService.selectCountdonation(dona_id);
	//0개가 넘으면 진행
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
	/*	구매 만큼 카운팅 빼주는거 진행 끝*/
	
	map.put("result","success");
	map.put("order_num",order_num);
	if(log.isDebugEnabled()) {
		log.debug("<<이번역은 시퀀스 번호 입니다.>> :"+order_num);
	}
	return map;
}
//기부자,전달메세지 업데이트
@RequestMapping("/goods/orderUpdate.do")
@ResponseBody
public Map<String,String> updateMultiOrder(@RequestParam("dona_username")String dona_username,@RequestParam("dona_message")String dona_message,@RequestParam("order_num")int order_num,HttpSession session){
	String id=(String)session.getAttribute("user_id");
	Map<String,String> map=new HashMap<String,String>();
	OrderCommand multiorder=new OrderCommand();
	multiorder.setDona_username(dona_username);
	multiorder.setDona_message(dona_message);
	multiorder.setDona_num(order_num);
	//시퀀스랑 비교해서 추가로 업데이트넣기.
	goodsService.updateOrder(multiorder);

	map.put("result","success");
	return map;	
}

//여러결제 카운트 빼기 테스트입니다.
@RequestMapping("/goods/CountMinus.do")
@ResponseBody
public Map<String,Object> CountMinus(HttpSession session){
	String id=(String)session.getAttribute("user_id");
	Map<String,Object> newMap=new HashMap<String,Object>();
	OrderCommand order=new OrderCommand();
	int count2=mypageService.selectCountdonation(id);
	//0개가 넘으면 진행
	if(count2>0) {
	order=mypageService.selectNowList(id);
	
	String[] as_name= order.getDona_asname().split(",");
	String[] goodsNum=order.getDona_goodsnum().split(",");
	String[] goodsAmount=order.getDona_goodsamount().split(",");
	if(log.isDebugEnabled()) {
		log.debug("<<리스트 어떻게 오나 보자아아>> :"+order);
		log.debug("<<배열체크 제대로 되있남>> :"+as_name+" / "+goodsNum+" / "+goodsAmount);
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

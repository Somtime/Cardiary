package com.cardiary.controller;

import com.cardiary.controller.action.*;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		// command를 입력 받아 실행 시킬 action을 넣을 변수 생성
		Action action = null;
		
		// console창에 커맨드 표시
		System.out.println("ActionFactory : " + command);
		
		if (command.equals("index")) { // 로그인창
			action = new LoginFormAction();
		} else if (command.equals("join_form")) {
			action = new JoinFormAction();
		} else if (command.equals("join")) {
			action = new JoinAction();
		} else if (command.equals("idcheck_form")) {
			action = new IdCheckFormAction();
		} else if (command.equals("login")) {
			action = new LoginAction();
		} else if (command.equals("card_list")) {
			action = new CardListAction();
		} else if (command.equals("new_card_form")) {
			action = new NewCardFormAction();
		} else if (command.equals("new_card")) {
			action = new NewCardAction();
		} else if (command.equals("favorite")) {
			action = new FavoriteAction();
		} else if (command.equals("mypage")) {
			action = new MypageAction();
		} else if (command.equals("change_info_form")) {
			action = new ChangeInformationFormAction();
		} else if (command.equals("change_info")) {
			action = new ChangeInformationction();
		} else if (command.equals("card")) {
			action = new CardAction();
		} else if (command.equals("change_card_form")) {
			action = new ChangeCardFormAction();
		} else if (command.equals("change_card")) {
			action = new ChangeCardAction();
		} else if (command.equals("delete_card")) {
			action = new DeleteCardAction();
		} else if (command.equals("favorite_card")) {
			action = new FavoriteCardAction();
		} else if (command.equals("user_info")) {
			action = new UserInformationAction();
		} else if (command.equals("insert_reply")) {
			action = new InsertReplyAction();
		} else if (command.equals("logout")) {
			action = new LogoutAction();
		} else if (command.equals("friend_list")) {
			action = new FriendListAction();
		} else if (command.equals("search_user")) {
			action = new SearchMemberAction();
		} else if (command.equals("add_friend")) {
			action = new AddFriendAction();
		} else if (command.equals("add_delete")) {
			action = new DeleteFriendAction();
		} else if (command.equals("update_friend")) {
			action = new UpdateFriendAction();
		}
		
		return action;
	}
}

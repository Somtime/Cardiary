<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드 목록</title>
<link rel="stylesheet" href="./assets/css/card.css">
<script type="text/javascript">
function change_card() {
	var url = "CardiaryServlet?command=change_card_form&cseq=${card.cseq}";
	/* window.open( url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=1200, height=800, left=400, top=100"); */
	location.href="CardiaryServlet?command=change_card_form&cseq=${card.cseq}";
}
function delete_card() {
	var url = "CardiaryServlet?command=delete_card_form&cseq=${card.cseq}";
	/* window.open( url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=1200, height=800, left=400, top=100"); */
	location.href="CardiaryServlet?command=delete_card&cseq=${card.cseq}";
}
</script>
</head>
<body>
	<form method="post" name="frm" id="frm" action="CardiaryServlet?command=insert_reply">
		<section>

				<div id="card">
					
					<!-- 카드 헤더 -->
					<div id="card_header">
						<input type="hidden" name="cseq" value="${card.cseq}">
						<!-- 유저 이미지 -->
						<div id="card_image_member">
							<a href="CardiaryServlet?command=user_info&id=${card.id}">
								<img src="./images/info/${card.image_member}" id="image_info" />
							</a>
						</div>
						
						<!-- 유저 아이디 -->
						<div id="card_id">
							<a href="CardiaryServlet?command=user_info&id=${card.id}">
								${card.id}
								<input type="hidden" name="id" value="${card.id}">
							</a>		
						</div>
						
						<!-- 좋아요 -->
						<div id="card_heart">
							<a href="CardiaryServlet?command=favorite&cseq=${card.cseq}">
								<c:forEach items="${card.favorite}" var="favorite">
									<c:if test="${favorite.favorite_yn == 'y'}">
										<img src="./images/redheart.png" width="30px" height="30px">
									</c:if>
									<c:if test="${favorite.favorite_yn == 'n'}">
										<img src="./images/blackheart.png" width="30px" height="30px">
									</c:if>
								</c:forEach>
								<c:if test="${card.favorite == null}">
										<img src="./images/blackheart.png" width="30px" height="30px">
								</c:if>
							</a>
						</div>
					</div>
					<!-- 카드 헤더 끝 -->
					
					<!-- 카드 콘텐트 -->
					<div id="card_content">
						
						<!-- 콘텐트 이미지 -->
						<div id="card_image_card">
							<a href="CardiaryServlet?command=card&cseq=${card.cseq}">
								<img src="./images/card/${card.image_card}" id="image_card" />
							</a>
						</div>
						
						<!-- 콘텐트 내용 -->
						<div id="card_content_article">
							<textarea style="overflow:hidden; resize:none;" disabled>${card.content}</textarea>
						</div>
					</div>
					
					<!-- 카드 콘텐트 끝 -->
					
					<c:if test="${card.reply != null}">
						<hr style="border: 1px solid rgba(252, 252, 254, 0.6); margin: 0px;">
					</c:if>
					
					<!-- 카드 푸터 -->
					<div id="card_footer">
						
						<!-- 댓글 입력 -->
						<div id="card_reply">
						
							<!-- 댓글 내용 -->
							<div id="card_reply_content">
								<input type="text" name="content" placeholder="댓글을 입력하세요.">
							</div>
							
							<!-- 댓글 입력 버튼 -->
							<div id="card_reply_submit">	
								<input type="submit" value="입력" id="reply_button">
							</div>
							
						</div>
						
						<!-- 댓글 목록 -->
						<div id="card_reply_list">
							<c:forEach items="${card.reply}" var="reply">
								<div>
									
									<div id="card_reply_image_member">
										<a href="CardiaryServlet?command=user_info&id=${reply.id}">
											<img src="./images/info/${reply.image}" id="image_info">
										</a>
									</div>
									<div id="card_reply_id">
										<a href="CardiaryServlet?command=user_info&id=${reply.id}">
											${reply.id}
										</a>
									</div> 
									
								</div>
								<div id="card_reply_article">
									<textarea style="overflow:hidden; resize:none;" disabled>${reply.content}</textarea>
								</div>
								
								<!-- 카드 수정 및 삭제 버튼 -->
								<div id="card_button">
									<c:if test="${card.id == loginUser.id}">
										<input type="button" class="card_button" value="수정" onclick="change_card()">
										<input type="button" class="card_button" value="삭제" onclick="delete_card()">
									</c:if>
								</div>
							</c:forEach>
						</div>
					</div>
					<!-- 카드 푸터 끝 -->
					
					<div id="clear"></div>
					
				</div>

		</section>
	</form>		
</body>
</html>
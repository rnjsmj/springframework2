<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>

    <style> 
        
        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>공지사항 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="noticelist">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${notice.noticeTitle }</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td>${notice.createDate }</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ notice.noticeContent }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <c:if test="${ sessionScope.loginUser.userId eq 'admin' }">
	                <a class="btn btn-primary" onclick="postSubmit(0);" >수정하기</a>
	                <a class="btn btn-danger" onclick="postSubmit(1);">삭제하기</a>
                </c:if>
                
          
                <form action="" method="post" id="postForm">
                	<input type="hidden" name="noticeNo" value="${notice.noticeNo }" />
                </form>
                
                <script>
                
                	function postSubmit(el) {
                		
                		$('#postForm').attr('action', 0 === el ? 'noticeUpdateForm' : 'noticeDelete').submit();

                	}
                
                </script>
            </div>
            <br><br>
		</div>
    </div>
    
    <jsp:include page="../common/footer.jsp" />
    
</body>
</html>
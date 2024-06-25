<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>

            <form action="update.do" method="post">
                <div class="form-group my-info">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" value="${ sessionScope.loginUser.userId }" name="userId" readonly> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" value="${ sessionScope.loginUser.userName }" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" value="${ sessionScope.loginUser.email }" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" value="${ sessionScope.loginUser.age }" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" value="${ sessionScope.loginUser.phone }" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" value="${ sessionScope.loginUser.address }" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                    
                    <script>
                    	window.onload = () => {
                    		
                    		//javascript
                    		//document.querySelector('input[value=${sessionScope.loginUser.gender}]').checked = true;
                    		
                    	}
                    	//jQuery
                    	$(() => {
                    		$('input[value=${sessionScope.loginUser.gender}]').attr('checked', true);
                    	})
                    </script>
                </div> 
                <br>
                <div class="btns" align="center">
                	<button type="button" class="btn btn-dark" data-toggle="modal" data-target="#passwordForm" style="float:left">비밀번호 변경</button>
                    <button type="submit" class="btn btn-primary" style="float:right">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm" style="float:right; margin-right:10px;">회원탈퇴</button>
                </div>
            </form>
        </div>
        <br><br>
        
    </div>

    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.do" method="post">
                	<input type="hidden" value="${ sessionScope.loginUser.userId }" name="userId"/>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger" onclick="return deletePrompt();">탈퇴하기</button>
                        <!-- 내가 작성한 이벤트(onclick)은 기존 이벤트보다 먼저 실행됨 -->
                    </div>
                </form>
                <script>
                	function deletePrompt() {
                		//prompt() => input 요소에 입력된 값 반환 / 입력값 없으면 빈 문자열 반환 / 취소하면 null 반환
                		return prompt('회원탈퇴를 위해 "회원탈퇴"를 정확히 입력해주세요.') === '회원탈퇴' ? true : false;
                	}
                </script>
            </div>
        </div>
    </div>
    
    <!-- 비밀번호 변경 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="passwordForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">비밀번호 변경</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                    <!-- Modal body -->
                    <div class="modal-body pass-form">
                            <label for="userPwd" class="mr-sm-2">기존 비밀번호 : </label>
                            <input type="password" class="form-control mb-2 mr-sm-2" placeholder="기존 비밀번호 입력" id="userPwd" name="userPwd"> <br>
                            <label for="userPwd" class="mr-sm-2">변경 비밀번호 : </label>
                            <input type="password" class="form-control mb-2 mr-sm-2" placeholder="변경할 비밀번호 입력" id="changePwd" name="changePwd"> <br>
                            <label for="userPwd" class="mr-sm-2">변경 비밀번호 확인 : </label>
                            <input type="password" class="form-control mb-2 mr-sm-2" placeholder="비밀번호 확인" id="checkPwd" name="checkPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                    	<div align="center" style="display:none;" id="check-msg">
                            
                        </div>
                        <button type="button" class="btn btn-danger" disabled>변경하기</button>
                        <!-- 내가 작성한 이벤트(onclick)은 기존 이벤트보다 먼저 실행됨 -->
                    </div>
                <script>
                $(() => {
                	
                	const $userId = $('.my-info #userId'); // 현재 로그인 유저 아이디가 저장된 요소
            		const $userPwd = $('.pass-form #userPwd'); // 기존 비밀번호 입력 요소
            		const $changePwd = $('#changePwd'); // 변경할 비밀번호 입력 요소
            		const $checkPwd = $('#checkPwd'); // 변경 비밀번호 확인 입력 요소
            		const $changeBtn = $('.modal-footer button[type=button]'); // 변경을 요청할 수 있는 버튼
            		
            		
            		$checkPwd.keyup(() => { // 비밀번호 확인 요소에 값을 입력했을 때
            			if($changePwd.val() === $checkPwd.val()) { //변경 비밀번호 확인이 일치하는 경우
            				
            				$changeBtn.attr('disabled', false); 
            				$('#check-msg').hide();
            				// 변경 요청 버튼을 활성화 하고, 출력했던 경고메시지를 숨김
            				
                		} else { //비밀번호 확인이 일치하지 않는 경우
                			
                			$('#check-msg').show().html('변경할 비밀번호를 다시 확인해주세요.<br>');
                			$changeBtn.attr('disabled', true);
                			
                			// 변경 요청 버튼을 비활성화 하고, 경고 메시지를 출력
                		}
            			
            		});
            		
            		$changeBtn.click(() => { // 변경 요청 버튼을 클릭했을 때
            			
                    	$.ajax({
                        	url:'change-pwd', 
                        	type:'post',
                        	data:{
                        		userId : $userId.val(),
                        		userPwd : $userPwd.val(),
                        		changePwd : $changePwd.val()},
                        		// 현재 로그인한 사용자의 아이디, 입력한 기존 비밀번호, 입력한 변경할 비밀번호 Post 요청
                        	success:(response) => {
                        	
                        		if(response === "Y") {
                        			// 현재 사용자의 비밀번호와 입력한 기존 비밀번호의 값이 일치하고, 비밀번호 변경도 수행된 경우
                        			alert('비밀번호가 변경되었습니다. 다시 로그인 해주세요.');
                        			location.href = 'logout.do';
                        		} else if(response === "WrongPwd") {
                        			// 현재 사용자의 비밀번호와 입력한 기존 비밀번호의 값이 일치하지 않은 경우
                        			$('#check-msg').show().html('기존 비밀번호가 올바르지 않습니다.<br>');
                        		} else {
                        			// 비밀번호 변경 구문 수행 시 오류가 발생한 경우
                        			alert('비밀번호 변경 중 오류가 발생했습니다. 다시 시도해주세요.');
                        		}},
                        	error : () => {
                        		//AJAX 요청 중 오류가 발생한 경우
                        		alert('비밀번호 변경 중 오류가 발생했습니다. 다시 시도해주세요.');
                        	}
                        });
                   });
            		
                }); 
                	
                
                </script>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>
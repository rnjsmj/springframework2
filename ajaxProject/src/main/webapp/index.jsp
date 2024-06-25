<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<h1>AJAX 요청 및 응답 받아오기</h1>
	
	<h5>1. 서버로 요청 시 인자값을 전달하고 응답데이터를 받아서 화면에 출력</h5>
	
	<label>목록 : </label>알밥, 돈까스, 파스타, 햄버거, 떡볶이<br/>
	
	메뉴 : <input type="text" id="menu" /> <br/>
	수량 : <input type="number" id="amount" value="0" required> <br/><br/>
	<button id="btn">서버로 전송</button>

	<div id="resultMsg">
	
	</div>

	<!--  버튼 클릭 시 메뉴에 입력한 음식명과 수량에 입력한 개수를 갖고 서버에 요청하여, 응답받은 응답데이터를 div요소의 content 영역에 출력 -->
	<script>
		document.getElementById('btn').onclick = () => {
			
			const menuValue = document.getElementById('menu').value;
			const amountValue = document.getElementById('amount').value;
			
			$.ajax({
				//매핑값 : 필수 정의 속성
				url : 'ajax1',
				type : 'get', 
				data : { //요청 시 전달 값 (key-value) => 객체로 전달
					menu: menuValue,
					amount : amountValue
					
				},
				success : (result) => { 
					console.log(result);
					const resultValue = result != 0 ? '선택하신 메뉴 '+ menuValue + ' ' + amountValue + '개의 가격은 ' + result + '원 입니다.' : '입력하신 메뉴는 존재하지 않습니다.';
					document.getElementById('resultMsg').innerHTML = resultValue;
				},
				error : () => {
					console.log('ajax1 요청 중 오류 발생');	
				}
				
			});
		}
		
	
	</script>

</body>
</html>
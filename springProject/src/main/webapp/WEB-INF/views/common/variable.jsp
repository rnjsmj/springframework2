<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>var, let, const</h1>
	
	
	<h2>변수 선언 시 사용할 수 있는 키워드</h2>
	<button onclick="defDeclare()">버튼</button>
	
	<script>
		
		function defDeclare() {
			
			var userId = 'user01';
			console.log(userId);
			
			var userId = 'user02';
			console.log(userId);
			
			//var : 변수 재선언(중복선언) 가능
			
			userId = 'user03';
			//var : 변수 재정의(값 할당) 가능
			
			console.log('---------------------------');
			
			let userName = '홍길동';
			console.log(userName);
			//let userName = '고길동'; << let : 재선언 불가
			userName = '고길동';
			console.log(userName);
			//let : 재정의(값 할당) 가능
			
			console.log('---------------------------');
			
			//const userPwd; // 선언만 하는 것은 불가능 / 초기화 필수
			
			const userPwd = 123;
			console.log(userPwd);
			//const userPwd = 456; //중복선언 불가
			//userPwd = 456; //재정의(값 할당 불가) => 상수
			
			//let, const 위주로 사용하는 것을 권장!
			
			//요소를 선택하는 경우 const로 선언하는 것이 좋음
			//? 해당 객체가 가져오는 속성값을 가져오기 때문에, 객체의 불변성을 지키기 위해 사용!
			
			const titleEl = document.getElementById('title');
		}
	</script>
	
	<h1 id="title">요소</h1>
	
	<button onclick="keywordScope()">버튼2</button>
	
	<script>
		name = '이름'; //키워드 없이 선언 및 정의 가능
		function keywordScope() {
			
			//let, const : block scope
			if(1) {
				let gender = 'M';
				const hobby = '독서';
			}
			//console.log(hobby); //블록 단위이므로 선언한 블록 밖에서 접근 불가
			
			for(var i = 0; i<5; i++) {
				
			}
			console.log(i);
			//var : 선언하면 해당하는 함수(function) 내부에서는 어디서든 선언 가능 => 함수 기준 전역변수와 같음!
			
			
			
			
			
			
		}
	
	
	
	</script>
</body>
</html>
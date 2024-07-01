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
	
	
	<h3> DB에서 SELECT문을 이용하여 조회했다는 가정하에 결과를 VO 객체로 응답받아 화면상에 출력 </h3>
	조회 음식 번호 : <input type="number" id="menuNo" /><br><br>
	<button id="select-btn">조회</button>
	<!-- 숫자입력 => 조회버튼 => 해당 숫자 버튼에 해당하는 메뉴를 조회하여 메뉴 객체의 필드 내용을 출력 -->
	<div id="today-menu">
	
	</div>
	
	<script>
		
		window.onload = () => {
			
			document.getElementById('select-btn').onclick = () => {
				
				$.ajax({
					//get방식의 경우 data가 아닌 쿼리스트링으로 key value set을 전송할 수도 있음
					//url : 'ajax2',
					url : 'ajax3',
					type : 'get',
					data : {
						menuNumber : document.getElementById('menuNo').value
					},
					success : (result) => {
						console.log(result);
						
						const obj = {
							"menuNumber" : "1",
							"menuName" : "열무국수",
							"price" : "8000",
							"material" : "열무김치"
						}
						
						console.log(obj);
						
						const menu = '<ul>오늘의 메뉴 : '
									+ '<li>' + result.menuName + '</li>'
									+ '<li>' + result.price + '원</li>'
									+ '<li> 재료 : ' + result.material + '</li>';
									
						document.getElementById('today-menu').innerHTML = menu;
					},
					error : e => {
						console.log(e);
					}
					
					
				});
			};
			
		}
	
	
	</script>
	
	
	
	
	
	<h3> 3. DB에서 SELECT문을 이용하여 조회된 리스트를 응답받아 출력 </h3>
	<button onclick="findAll()">메뉴전체조회</button>
	<br><br>
	
	<table border="1" id="find-result">
		<thead>
			<tr>
				<th>메뉴명</th>
				<th>가격</th>
				<th>재료</th>
			</tr>
		</thead>
		<tbody id="tbody">
		
		</tbody>
	</table>
	
	<script>
	function findAll() {
	
		$.ajax({
			url:'find',
			type:'get',
			success: result => {
				
				console.log(result);
				
				/*
					<tr>
						<td>김치찌개</td>
						...
					</tr>
				
				*/
				//map((순차적으로 접근한 요소, 해당 요소 인덱스) => {})
				
				result.map( (o, i) => {
					/* console.log(o);
					console.log(i); */
					
					const tbodyEl = document.getElementById('tbody');
					const trEl = document.createElement('tr'); //요소 생성 메서드
					const tdFirst = document.createElement('td');
					
					const firstText = document.createTextNode(o.menuName);
					tdFirst.style.width = '200px';
					tdFirst.appendChild(firstText);
					console.log(tdFirst);
					
					const tdSecond= document.createElement('td');
						
					const secondText = document.createTextNode(o.price);
					tdSecond.style.width = '200px';
					tdSecond.appendChild(secondText);
					console.log(tdSecond);
					
					const tdThird = document.createElement('td');
					
					const thirdText = document.createTextNode(o.material);
					tdThird.style.width = '200px';
					tdThird.appendChild(thirdText);
					console.log(tdThird);
					
					trEl.appendChild(tdFirst); // 해당 요소 안에 자식 요소로 추가
					trEl.appendChild(tdSecond);
					trEl.appendChild(tdThird);
					
					console.log(trEl);
					
					tbodyEl.appendChild(trEl);
				});
				
				
				
			}
		});
		
	}
	</script>
	

</body>
</html>
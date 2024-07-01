<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B강의장 프로젝트</title>
<style>
	.innerOuter {
		height:auto;
	}
</style>
</head>
<body>
	<jsp:include page="common/menubar.jsp"/>
	
	<div class="innerOuter">
		<h3>게시글 조회수 TOP 5</h3>
		<br>
		<a href="boardlist" style="float:right; color:lightgray; font-weight:800; font-size:14px;">더보기 >></a>
		<br/><br/>
		<table class="table table-hover" align="center" id="boardList">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
					<th>첨부파일</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		
		</table>
		<br><br>
		<table id="board-detail" class="table">
			
			
			
		</table>
		<div id="btn-group"> 
			
		</div>
	</div>
	
	<!-- BOARD 테이블 COUNT 컬럼 값 상위 5개 행 조회 -->
	<script>
		/*
			상세보기
			조회된 게시글 목록에서
			게시글을 클릭하면
			선택한 글번호로
			하나의 게시글 정보를
			AJAX 요청을 통해 응답받아
			id 속성값이 board-detail인 table에 자식요소를 만들어서 출력
		*/
	
		$(() => {
			
			//동적 요소에 대한 이벤트 처리 !!!!!
			
			//on() : overloading => (event type, target(상위요소로 선택한 요소의 자식 요소를 호출), handler)
			$(document).on('click', '#boardList > tbody > tr', e => {
				//document의 하위 요소 중 선택한 요소를 선택하면,
				//동적으로 생긴 요소여도 선택할 수 있음
				//jQuery의 경우 상위요소를 이용한 on 메서드를 사용하며
				//순수 javascript의 경우 addEventListener 사용!
				
				
				const boardNo = $(e.currentTarget).children().eq(0).text();
				
				
				
				$.ajax({
					url : 'boards/' + boardNo,
					type : 'get',
					success : result => {
					
						//console.log(result);
						
						let value = '<tr><td colspan="3">게시글 상세보기</td></tr>';
						
						value += '<tr>'
								+ '<td width="300">' + result.boardTitle + '</td>'
								+ '<td width="600">' + result.boardContent + '</td>'
								+ '<td width="200">' + result.boardWriter + '</td>'
								+ '</tr>';
						document.getElementById('board-detail').innerHTML = value;
						
						if(result.boardWriter === "${ sessionScope.loginUser.userId}") {
							let btn = '<a class="btn btn-sm btn-secondary" onclick="deleteBoards('
									+ result.boardNo
									+')">삭제하기</a>';
							document.getElementById('btn-group').innerHTML = btn;
							
						} else {
							document.getElementById('btn-group').innerHTML = '';
						}
					}				
				});
			})
			
		});
	
	
		function deleteBoards(boardNo) {
			
			$.ajax({
				url:'boards/' + boardNo,
				type:'delete',
				success : (response) => {
					console.log(response);
					if (response.data === "삭제 성공") {
						alert("삭제가 완료되었습니다.");
					} else {
						alert("오류가 발생했습니다.");
					}
					
					findTopFiveBoard();
					
				},
				error : () => {
					console.log('ajax 오류 발생');
				}
			});
			
		};
	
	
		$(()=>{
			
			findTopFiveBoard();
		})
		
		function findTopFiveBoard() {
			
			$.ajax({
				url : 'boards',
				type : 'get',
				success : (boards) => {
					
					console.log(boards);
					
					let value = "";
					
					for(let i in boards) {
						value += '<tr>'
								+ '<td>' + boards[i].boardNo + '</td>'
								+ '<td>' + boards[i].boardTitle + '</td>'
								+ '<td>' + boards[i].boardWriter + '</td>'
								+ '<td>' + boards[i].count + '</td>'
								+ '<td>' + boards[i].createDate + '</td>'
								+ '<td>';
								if (boards[i].originName != null) {
									value += '👽'
								}
								+ '</td></tr>';
						//해당 조회 테이블 AJAX 요청을 보내서 돌아온 응답을 통해 직접 만든 요소 => 동적 요소
						//그래서 머.... 위에 click이벤트가 왜안된다고??
						
					}
					
					$('#boardList tbody').html(value);
					
					
				}
			});
			
		}
	</script>
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>
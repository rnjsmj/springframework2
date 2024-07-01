<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<input type="text" id="email"><span id="result"></span>

<script>
$("#email").on("keyup",() => {
	var email = $("#email").val();
	var regex = /\w+@\w\.\w+/;

	if(regex.exec(email) == null){

		$("#result").html("email형식이 맞지 않습니다. ");
		return;
		
	} else {
		
		$.ajax({

			url:"emailCheck",
			type : "post",
			data:{email: $("#email").text()},
			success:resp => {
				$("#result").html(resp);
			})
		})
})

</script>
</body>
</html>
let index = {
	// _this = this를 호출해야 function 사용 가능
	init: function() {
		$("#btn-save").on("click", () => { // function(){} 대신, ()=> {}를 사용하는 이유는 this를 바인딩하기 위해서!!!
			this.save();
		});
	},
	save: function() {
		//alert("user의 save버튼 호출");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax 호출시 default가 비동기 호출 따라서, $.ajax가 100초가 걸린다면 이 다음 코드들을 수행하다가 ajax를 수행하러 다시 돌아온다.
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),//해당 데이터는 http body 데이터
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 type인지
			dataType: "json" //서버에게 응답이 왔을때 기본적으로는 문자열인데 문자방식을 dataType에 적어놓으면 자동으로 객체에 할당해준다. 
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();
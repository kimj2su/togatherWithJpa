$(function(){
    $('#already-userId').on("click" , function(){
        if($('#userId').val().length == 0 || $('#userId').val() == null ) return alert("아이디를 입력하세요.");
        $.ajax({
            type:'GET',
            url:'/members/userIdCheck',
            data:{userId:$('#userId').val()},
            success: function(data) {
                // $('#nicknameMessage').text(""+data+"");
                // $('#nicknameMessage').css('font-weight' ,  "bold");
                console.log(data);
                if(data == 1){
                    $('#userIdMessage').text("이미 사용중인 아이디 입니다.");
                    $('#sign-up-button').removeClass("btn btn-primary disabled");
                    $('#sign-up-button').addClass("btn btn-primary disabled");
                } else if(data == 0){
                    $('#userIdMessage').text("사용 가능한 아이디 입니다.");
                    $('#sign-up-button').removeClass("btn btn-primary disabled");
                    $('#sign-up-button').addClass("btn btn-primary");
                } else if(data == 3){
                    $('#userIdMessage').text("사용 불가능한 닉네임 입니다.");
                } else {
                    $('#userIdMessage').text("사용 가능한 닉네임 입니다.");
                }
            },
            error:function(data){
                $('#userIdMessage').text(data);
            }
        });
    });
});
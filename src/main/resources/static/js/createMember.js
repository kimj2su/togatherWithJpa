$(function(){
    $('#already-nickname').on("click" , function(){
        if($('#nickname').val().length == 0 || $('#nickname').val() == null ) return alert("닉네임을 입력하세요.");
        $.ajax({
            type:'GET',
            url:'/members/nicknameCheck',
            data:{nickname:$('#nickname').val()},
            success: function(data) {
                // $('#nicknameMessage').text(""+data+"");
                // $('#nicknameMessage').css('font-weight' ,  "bold");
                console.log(data);
                if(data == 1){
                    $('#nicknameMessage').text("이미 사용중인 닉네임 입니다.");
                    $('#sign-up-button').removeClass("btn btn-primary disabled");
                    $('#sign-up-button').addClass("btn btn-primary disabled");
                } else if(data == 0){
                    $('#nicknameMessage').text("사용 가능한 닉네임 입니다.");
                    $('#sign-up-button').removeClass("btn btn-primary disabled");
                    $('#sign-up-button').addClass("btn btn-primary");
                } else if(data == 3){
                    $('#nicknameMessage').text("사용 불가능한 닉네임 입니다.");
                } else {
                    $('#nicknameMessage').text("사용 가능한 닉네임 입니다.");
                }
            },
            error:function(data){
                $('#messages').text(data);
            }
        });
    });
});
$(function(){
    $('#already-userId').on("click" , function(){
        if($('#userId').val().length == 0 || $('#userId').val() == null ) return alert("아이디를 입력하세요.");
        $.ajax({
            type:'GET',
            url:'/members/userIdCheck',
            data:{userId:$('#userId').val()},
            success: function(data) {
                if(data == 1){
                    $('#userIdMessage').text("이미 사용중인 아이디 입니다.");
                    $('#sign-up-button').removeClass("btn btn-success disabled");
                    $('#sign-up-button').addClass("btn btn-success disabled");
                } else if(data == 0){
                    $('#userIdMessage').text("사용 가능한 아이디 입니다.");
                    $('#sign-up-button').removeClass("btn btn-success disabled");
                    $('#sign-up-button').addClass("btn btn-success");
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

function checkedBox(box){
    if(box.checked==true){
        let checked = box.value;

        if(checked=="ck1"){
            $('#checkBox1').val(checked);
            $('#ck1').remove();
            $('#category_first').remove();
            $('#category_first1').val("");
            $('#category_firstCheck1').val("");
            $('#ck1').prop('checked',false);
            $('#checkBox1').val("");
        }else if(checked=="ck2"){
            $('#checkBox2').val(checked);
            $('#ck2').remove();
            $('#category_second').remove();
            $('#category_first2').val("");
            $('#category_firstCheck2').val("");
            $('#ck2').prop('checked',false);
            $('#checkBox2').val("");
        }else{
            $('#checkBox3').val(checked);
            $('#ck3').remove();
            $('#category_third').remove();
            $('#category_first3').val("");
            $('#category_firstCheck3').val("");
            $('#ck3').prop('checked',false);
            $('#checkBox3').val("");
        }
    }else{
        let checked = box.value;
        if(checked=="ck1"){
            $('#checkBox1').val("");
        }else if(checked=="ck2"){
            $('#checkBox2').val("");
        }else{
            $('#checkBox3').val("");
        }
    }
}

function reset(){

    $('#ul2').remove();
    $('#ul3').remove();
    $('#button2').remove();
    $('#button3').remove();
    $('#selectButton').remove();
    let div2="";
    div2+="<button id='button2' type='button' class='btn btn-outline-light dropdown-toggle' onclick='javascript:reset2()' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
    div2+="<span id='span2'>하위관심사</span>";
    div2+="</button>";
    div2+="<ul id='ul2' class='dropdown-menu'>";
    div2+="</ul>";
    $('#div2').after(
        div2
    );
    let div3="";
    div3+="<button id='button3' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
    div3+="<span id='span3'>세부관심사</span>";
    div3+="</button>";
    div3+="<ul id='ul3' class='dropdown-menu'>";
    div3+="</ul>";
    $('#div3').after(
        div3
    );
    $('#button2').hide();
    $('#button3').hide();
}

function reset2(){

    $('#ul3').remove();
    $('#button3').remove();
    $('#selectButton').remove();
    let div3="";
    div3+="<button id='button3' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
    div3+="<span id='span3'>세부관심사</span>";
    div3+="</button>";
    div3+="<ul id='ul3' class='dropdown-menu'>";
    div3+="</ul>";
    $('#div3').after(
        div3
    );
    $('#button3').hide();
}

function selectedCategory(){
    let categoryValue1 =$('#category_first1').val();
    let categoryValue2 =$('#category_first2').val();
    let categoryValue3 =$('#category_first3').val();

    if(categoryValue1===categoryValue2 || categoryValue1===categoryValue3){

        alert("다른 카테고리를 선택해주세요.");
        return false;
    }else{
        if(categoryValue2!==""){
            if(categoryValue3===categoryValue2){
                alert("다른 카테고리를 선택해주세요.");
                return false;
            }else{
                if($('#category_firstCheck1').val()===""){

                    let selectedText1="";
                    selectedText1+="<input id='ck1' value='ck1' type='checkbox' onclick='checkedBox(this)'/>";
                    selectedText1+="<input id='category_first' type='text' name='category_first' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue1+"' readonly/>";
                    $('#firstAppendDiv').append(
                        selectedText1
                    );
                    $('#category_firstCheck1').val("1");
                }else if($('#category_firstCheck1').val()!=="" && $('#category_firstCheck2').val()===""){

                    let selectedText2="";
                    selectedText2+="<input id='ck2' value='ck2' type='checkbox' onclick='checkedBox(this)'/>";
                    selectedText2+="<input id='category_second' type='text' name='category_second' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue2+"' readonly/>";
                    $('#firstAppendDiv').append(
                        selectedText2
                    );
                    $('#category_firstCheck2').val("2");
                }else if($('#category_firstCheck1').val()!=="" && $('#category_firstCheck2').val()!=="" && $('#category_firstCheck3').val()==""){

                    let selectedText3="";
                    selectedText3+="<input id='ck3' value='ck3' type='checkbox' onclick='checkedBox(this)'/>";
                    selectedText3+="<input id='category_third' type='text' name='category_third' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue3+"' readonly/>";
                    $('#firstAppendDiv').append(
                        selectedText3
                    );
                    $('#category_firstCheck3').val("3");

                    $('#button2').remove();
                    $('#button3').remove();

                    $('#ul2').remove();
                    $('#ul3').remove();

                    $('#span2').remove();
                    $('#span3').remove();

                }else {

                    alert("카테고리는 3개까지만 선택가능합니다.");
                }
            }
        }else{
            if($('#category_firstCheck1').val()===""){

                let selectedText1="";
                selectedText1+="<input id='ck1' value='ck1' type='checkbox' onclick='checkedBox(this)'></input>";
                selectedText1+="<input id='category_first' type='text' name='category_first' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue1+"' readonly>";
                $('#firstAppendDiv').append(
                    selectedText1
                );
                $('#category_firstCheck1').val("1");
            }else if($('#category_firstCheck1').val()!=="" && $('#category_firstCheck2').val()===""){

                let selectedText2="";
                selectedText2+="<input id='ck2' value='ck2' type='checkbox' onclick='checkedBox(this)'></input>";
                selectedText2+="<input id='category_second' type='text' name='category_second' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue2+"' readonly>";
                $('#firstAppendDiv').append(
                    selectedText2
                );
                $('#category_firstCheck2').val("2");
            }else if($('#category_firstCheck1').val()!=="" && $('#category_firstCheck2').val()!=="" && $('#category_firstCheck3').val()==""){

                let selectedText3="";
                selectedText3+="<input id='ck3' value='ck3' type='checkbox' onclick='checkedBox(this)'/>";
                selectedText3+="<input id='category_third' type='text' name='category_third' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue3+"' readonly>";
                $('#firstAppendDiv').append(
                    selectedText3
                );
                $('#category_firstCheck3').val("3");

                $('#button2').remove();
                $('#button3').remove();

                $('#ul2').remove();
                $('#ul3').remove();

                $('#span2').remove();
                $('#span3').remove();

            }else {
                alert("카테고리는 3개까지만 선택가능합니다.");
            }
        }
    }

    $('#selectButton').remove();
    $('#button2').hide();
    $('#button3').hide();
    categories(5,0);

}


function categories(sequence, i) {

    if (sequence == 2) {
        $('#button2').show();
        let id = 'a' + i;
        let intOut = document.getElementById(id).getAttribute('value');
        $('#span1').text(intOut);
        let result = {"searchValue": intOut, "sequence": sequence};
        $.ajax({
            url: "/category",
            type: "GET",
            dataType: "json",
            data: result,
            contentType: "application/json",
            success: function (data) {
                let category = "";
                for (let i = 0; i < data.length; i++) {
                    category += "<li><a id='" + i + "' class='dropdown-item' href='javascript:categories(3," + i + ")' onclick='javascript:reset2()' data-value='" + data[i] + "'>" + data[i] + "</a></li>";
                }
                $('#ul2').append(
                    category
                );
            },
            error: function (request, status, error) {
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                let err = JSON.parse(request.responseText);
                console.log("에러: " + data);
            }
        });
    } else if (sequence == 3) {

        let index = i;
        let categoryValue = document.getElementById(index).getAttribute('data-value');
        $('#span2').text(categoryValue);
        let result = {"searchValue": categoryValue, "sequence": sequence};
        $.ajax({
            url: "/category",
            type: "GET",
            dataType: "json",
            data: result,
            contentType: "application/json",
            success: function (data) {
                let category = "";
                if (Object.keys(data).length != 1) {
                    $('#button3').show();
                    for (let i = 0; i < data.length; i++) {
                        category += "<li><a id='" + i + "a' class='dropdown-item' href='javascript:categories(4," + i + ")' onclick='javascript:reset3()' data-value='" + data[i] + "'>" + data[i] + "</a></li>";
                    }
                    $('#ul3').append(
                        category
                    );
                } else {
                    if ($('#category_firstCheck1').val() == "") {
                        $('#category_first1').val(categoryValue);
                    } else if ($('#category_firstCheck1').val() != "" && $('#category_firstCheck2').val() == "") {
                        $('#category_first2').val(categoryValue);
                    } else if ($('#category_firstCheck1').val() != "" && $('#category_firstCheck2').val() != "") {
                        $('#category_first3').val(categoryValue);
                    } else if ($('#category_firstCheck1').val() != "" && $('#category_firstCheck2').val() != "" && $('#category_firstCheck3').val() != "") {
                        alert("카테고리는 3개까지만 선택 가능합니다.");
                        categories(5, 0);
                    }
                    let categoryButton = "<button id='selectButton' type='button' class='btn btn-secondary' onclick='selectedCategory()' style='margin-top:7px' >선택</button>";
                    $('#cancelButton').before(
                        categoryButton
                    );
                }

            }
        });
    } else if (sequence == 4) {
        $('#selectButton').remove();
        let index = i;
        index += "a";
        let categoryValue = document.getElementById(index).getAttribute('data-value');
        $('#span3').text(categoryValue);
        if ($('#category_firstCheck1').val() == "") {
            $('#category_first1').val(categoryValue);
        } else if ($('#category_firstCheck1').val() != "" && $('#category_firstCheck2').val() == "") {
            $('#category_first2').val(categoryValue);
        } else if ($('#category_firstCheck1').val() != "" && $('#category_firstCheck2').val() != "") {
            $('#category_first3').val(categoryValue);
        } else if ($('#category_firstCheck1').val() != "" && $('#category_firstCheck2').val() != "" && $('#category_firstCheck3').val() != "") {
            alert("카테고리는 3개까지만 선택가능합니다.");
            categories(5, 0);
        }
        let categoryButton = "<button id='selectButton' type='button' class='btn btn-secondary' onclick='selectedCategory()' style='margin-top:7px' >선택</button>";
        $('#cancelButton').before(
            categoryButton
        );
    }else if(sequence==5){
        $('#button2').remove();
        $('#button3').remove();
        $('#ul2').remove();
        $('#ul3').remove();
        $('#span2').remove();
        $('#span3').remove();
        let div2="";
        div2+="<button id='button2' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
        div2+="<span id='span2'>하위관심사</span>";
        div2+="</button>";
        div2+="<ul id='ul2' class='dropdown-menu'>";
        div2+="</ul>";
        $('#div2').append(
            div2
        );
        let div3="";
        div3+="<button id='button3' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
        div3+="<span id='span3'>세부관심사</span>";
        div3+="</button>";
        div3+="<ul id='ul3' class='dropdown-menu'>";
        div3+="</ul>";
        $('#div3').append(
            div3
        );

    }else if(sequence==6){
        $('#category_first1').val("");
        $('#category_first2').val("");
        $('#category_first3').val("");
        $('#category_firstCheck1').val("");
        $('#category_firstCheck2').val("");
        $('#category_firstCheck3').val("");
        $('#category_first').remove();
        $('#category_second').remove();
        $('#category_third').remove();
        $('#button2').remove();
        $('#button3').remove();
        $('#ul2').remove();
        $('#ul3').remove();
        $('#span2').remove();
        $('#span3').remove();
        $('#selectButton').remove();
        $('#ck1').remove();
        $('#ck2').remove();
        $('#ck3').remove();

        let div2="";
        div2+="<button id='button2' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
        div2+="<span id='span2'>하위관심사</span>";
        div2+="</button>";
        div2+="<ul id='ul2' class='dropdown-menu'>";
        div2+="</ul>";
        $('#div2').after(
            div2
        );
        let div3="";
        div3+="<button id='button3' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
        div3+="<span id='span3'>세부관심사</span>";
        div3+="</button>";
        div3+="<ul id='ul3' class='dropdown-menu'>";
        div3+="</ul>";
        $('#div3').after(
            div3
        );
        $('#button2').hide();
        $('#button3').hide();
    }
}
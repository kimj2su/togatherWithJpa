function groupJoin(id, grade) {
    let groupTabId = id;
    let result = {"groupTabId": groupTabId, "gradeNumber": grade};
    $.ajax({
        url: "/mig/new/" + groupTabId,
        type: "POST",
        data: result,
        success: function (data) {
            location.reload();
        }
    });
}

function groupQuit(id, grade) {
    let groupTabId = id;
    let result = {"groupTabId": groupTabId, "gradeNumber": grade};
    $.ajax({
        url: "/mig/delete/" + groupTabId,
        type: "POST",
        data: result,
        success: function (data) {
            location.reload();
        }
    });
}

function gatheringForm(id) {
    let groupTabId = id;
    location.href = "/gatherings/new/" + id;
}

function reset() {
    $('#ul2').remove();
    $('#ul3').remove();
    $('#button2').remove();
    $('#button3').remove();
    $('#selectButton').remove();
    let div2 = "";
    div2 += "<button id='button2' type='button' class='btn btn-outline-light dropdown-toggle' onclick='javascript:reset2()' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
    div2 += "<span id='span2'>하위관심사</span>";
    div2 += "</button>";
    div2 += "<ul id='ul2' class='dropdown-menu'>";
    div2 += "</ul>";
    $('#div2').after(
        div2
    );
    let div3 = "";
    div3 += "<button id='button3' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
    div3 += "<span id='span3'>세부관심사</span>";
    div3 += "</button>";
    div3 += "<ul id='ul3' class='dropdown-menu'>";
    div3 += "</ul>";
    $('#div3').after(
        div3
    );
    $('#button2').hide();
    $('#button3').hide();
}

function reset2() {

    $('#ul3').remove();
    $('#button3').remove();
    $('#selectButton').remove();
    let div3 = "";
    div3 += "<button id='button3' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
    div3 += "<span id='span3'>세부관심사</span>";
    div3 += "</button>";
    div3 += "<ul id='ul3' class='dropdown-menu'>";
    div3 += "</ul>";
    $('#div3').after(
        div3
    );
    $('#button3').hide();
}

function reset3(){
    if($('#category_firstCheck1').val()!=1 ){
        $('#category_first1').val("");
        console.log("카테고리1");
    }
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
    }
}


function selectedCategory(){
    let categoryValue1 =$('#category_first1').val();
    if($('#category_first1').val()!=""){
        let selectedText1="";
        selectedText1+="<input id='category_first' type='text' name='interest' style='width:100px;height:50px;' value='"+categoryValue1+"' readonly>";
        $('#div1').append(
            selectedText1
        );
        $('#category_firstCheck1').val("1");
        $('#button1').remove();
        $('#button2').remove();
        $('#button3').remove();
        $('#Selectbutton').remove();
    }
}
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <title>Togather</title>
    <meta content="" name="description"/>
    <meta content="" name="keywords"/>

    <!-- Favicons -->
    <link href="/assets/img/favicon.png" rel="icon"/>
    <link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon"/>

    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
            rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link href="/assets/vendor/animate.css/animate.min.css" rel="stylesheet"/>
    <link href="/assets/vendor/aos/aos.css" rel="stylesheet"/>
    <link
            href="/assets/vendor/bootstrap/css/bootstrap.min.css"
            rel="stylesheet"
    />
    <link
            href="../assets/vendor/bootstrap-icons/bootstrap-icons.css"
            rel="stylesheet"
    />
    <link href="/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet"/>
    <link href="/assets/vendor/remixicon/remixicon.css" rel="stylesheet"/>
    <link href="/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet"/>

    <!-- Template Main CSS File -->
    <link href="/assets/css/style.css" rel="stylesheet"/>
    <!-- alert -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <!-- alert -->
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


    <script>
        function checkedBox(box){
            if(box.checked==true){
                let checked = box.value;
                console.log("box.checked: "+box.checked);
                console.log("checked: "+checked);
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
                console.log("checked: "+checked);
                console.log("box.checked: "+box.checked);
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
            let categoryCheckValue1 =$('#category_firstCheck1').val();
            let categoryCheckValue2 =$('#category_firstCheck2').val();
            let categoryCheckValue3 =$('#category_firstCheck3').val();
            if(categoryValue1==categoryValue2 || categoryValue1==categoryValue3){
                console.log("5");
                alert("다른 카테고리를 선택해주세요.");
                return false;
            }else{
                if(categoryValue2!=""){
                    if(categoryValue3==categoryValue2){
                        alert("다른 카테고리를 선택해주세요.");
                        return false;
                    }else{
                        if($('#category_firstCheck1').val()==""){
                            console.log("1: ");
                            let selectedText1="";
                            selectedText1+="<input id='ck1' value='ck1' type='checkbox' onclick='checkedBox(this)'></input>";
                            selectedText1+="<input id='category_first' type='text' name='category_first' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue1+"' readonly>";
                            $('#firstAppendDiv').append(
                                selectedText1
                            );
                            $('#category_firstCheck1').val("1");
                        }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()==""){
                            console.log("2");
                            let selectedText2="";
                            selectedText2+="<input id='ck2' value='ck2' type='checkbox' onclick='checkedBox(this)'></input>";
                            selectedText2+="<input id='category_second' type='text' name='category_second' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue2+"' readonly>";
                            $('#firstAppendDiv').append(
                                selectedText2
                            );
                            $('#category_firstCheck2').val("2");
                        }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!="" && $('#category_firstCheck3').val()==""){
                            console.log("3");
                            let selectedText3="";
                            selectedText3+="<input id='ck3' value='ck3' type='checkbox' onclick='checkedBox(this)'></input>";
                            selectedText3+="<input id='category_third' type='text' name='category_third' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue3+"' readonly>";
                            $('#firstAppendDiv').append(
                                selectedText3
                            );
                            $('#category_firstCheck3').val("3");
                            $('#button1').remove();
                            $('#button2').remove();
                            $('#button3').remove();
                            $('#ul1').remove();
                            $('#ul2').remove();
                            $('#ul3').remove();
                            $('#span1').remove();
                            $('#span2').remove();
                            $('#span3').remove();
                            categoryCheckValue1 =$('#category_firstCheck1').val();
                            categoryCheckValue2 =$('#category_firstCheck2').val();
                            categoryCheckValue3 =$('#category_firstCheck3').val();
                            console.log("categoryCheckValue1: "+categoryCheckValue1);
                            console.log("categoryCheckValue2: "+categoryCheckValue2);
                            console.log("categoryCheckValue3: "+categoryCheckValue3);
                        }else {
                            console.log("4");
                            alert("카테고리는 3개까지만 선택가능합니다.");
                        }
                    }
                }else{
                    if($('#category_firstCheck1').val()==""){
                        console.log("1: ");
                        let selectedText1="";
                        selectedText1+="<input id='ck1' value='ck1' type='checkbox' onclick='checkedBox(this)'></input>";
                        selectedText1+="<input id='category_first' type='text' name='category_first' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue1+"' readonly>";
                        $('#firstAppendDiv').append(
                            selectedText1
                        );
                        $('#category_firstCheck1').val("1");
                    }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()==""){
                        console.log("2");
                        let selectedText2="";
                        selectedText2+="<input id='ck2' value='ck2' type='checkbox' onclick='checkedBox(this)'></input>";
                        selectedText2+="<input id='category_second' type='text' name='category_second' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue2+"' readonly>";
                        $('#firstAppendDiv').append(
                            selectedText2
                        );
                        $('#category_firstCheck2').val("2");
                    }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!="" && $('#category_firstCheck3').val()==""){
                        console.log("3");
                        let selectedText3="";
                        selectedText3+="<input id='ck3' value='ck3' type='checkbox' onclick='checkedBox(this)'></input>";
                        selectedText3+="<input id='category_third' type='text' name='category_third' style='width:100px;height:50px;border:none;text-align: center' value='"+categoryValue3+"' readonly>";
                        $('#firstAppendDiv').append(
                            selectedText3
                        );
                        $('#category_firstCheck3').val("3");
                        $('#button1').remove();
                        $('#button2').remove();
                        $('#button3').remove();
                        $('#ul1').remove();
                        $('#ul2').remove();
                        $('#ul3').remove();
                        $('#span1').remove();
                        $('#span2').remove();
                        $('#span3').remove();
                        categoryCheckValue1 =$('#category_firstCheck1').val();
                        categoryCheckValue2 =$('#category_firstCheck2').val();
                        categoryCheckValue3 =$('#category_firstCheck3').val();
                        console.log("categoryCheckValue1: "+categoryCheckValue1);
                        console.log("categoryCheckValue2: "+categoryCheckValue2);
                        console.log("categoryCheckValue3: "+categoryCheckValue3);
                    }else {
                        console.log("4");
                        alert("카테고리는 3개까지만 선택가능합니다.");
                    }
                }
            }

            $('#selectButton').remove();
            $('#button2').hide();
            $('#button3').hide();
            categories(5,0);

        }

        <c:set var="intOut" value="${intOut}"/>
        function categories(sequence, i) {
            if (sequence == 1) {
                $('#button2').hide();
                $('#button3').hide();
                let category = "";
                category += "<c:forEach items='${intOut}' var='intOut' varStatus='index'>";
                category += "<li><a id='li${index.index}' class='dropdown-item' href='javascript:categories(2,${index.index})' onclick='javascript:reset()' data-value='${intOut}'>${intOut}</a></li>";
                category += "</c:forEach>";
                $('#ul1').append(
                    category
                );
            } else if (sequence == 2) {
                $('#button2').show();
                let index = "li";
                index += i;
                let categoryValue = document.getElementById(index).getAttribute('data-value');
                $('#span1').text(categoryValue);
                let result = {"searchValue": categoryValue, "sequence": sequence};
                $.ajax({
                    url: "/category",
                    type: "GET",
                    dataType: "json",
                    data: result,
                    contentType:"application/json",
                    success: function (data) {
                        let category = "";
                        for (let i = 0; i < data.length; i++) {
                            category += "<li><a id='" + i + "' class='dropdown-item' href='javascript:categories(3," + i + ")' onclick='javascript:reset2()' data-value='" + data[i] + "'>" + data[i] + "</a></li>";
                        }
                        $('#ul2').append(
                            category
                        );
                    },
                    error:function(request,status,error){
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        let err=JSON.parse(request.responseText);
                        console.log("에러: " + data);
                    }
                });
            }else if(sequence==3){
                console.log("3들어옴");
                let index=i;
                let categoryValue=document.getElementById(index).getAttribute('data-value');
                $('#span2').text(categoryValue);
                let result = {"searchValue":categoryValue,"sequence":sequence};
                $.ajax({
                    url: "/category",
                    type: "GET",
                    dataType: "json",
                    data: result,
                    contentType:"application/json",
                    success: function(data){
                        let category="";
                        if(Object.keys(data).length!=1){
                            $('#button3').show();
                            for(let i =0; i < data.length; i++){
                                category+="<li><a id='"+i+"li' class='dropdown-item' href='javascript:categories(4,"+i+")' onclick='javascript:reset3()' data-value='"+data[i]+"'>"+data[i]+"</a></li>";
                            }
                            $('#ul3').append(
                                category
                            );
                        }else{
                            if($('#category_firstCheck1').val()==""){
                                $('#category_first1').val(categoryValue);
                            }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()==""){
                                $('#category_first2').val(categoryValue);
                            }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!=""){
                                $('#category_first3').val(categoryValue);
                            }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!="" && $('#category_firstCheck3').val()!=""){
                                alert("카테고리는 3개까지만 선택 가능합니다.");
                                categories(5,0);
                            }
                            let categoryButton="<button id='selectButton' type='button' class='btn btn-secondary' onclick='selectedCategory()' style='margin-top:7px' >선택</button>";
                            $('#cancelButton').before(
                                categoryButton
                            );
                        }

                    }
                });
            }else if(sequence==4){
                $('#selectButton').remove();
                let index=i;
                index+="li";
                let categoryValue=document.getElementById(index).getAttribute('data-value');
                $('#span3').text(categoryValue);
                if($('#category_firstCheck1').val()==""){
                    $('#category_first1').val(categoryValue);
                }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()==""){
                    $('#category_first2').val(categoryValue);
                }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!=""){
                    $('#category_first3').val(categoryValue);
                }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!="" && $('#category_firstCheck3').val()!=""){
                    alert("카테고리는 3개까지만 선택가능합니다.");
                    categories(5,0);
                }
                let categoryButton="<button id='selectButton' type='button' class='btn btn-secondary' onclick='selectedCategory()' style='margin-top:7px' >선택</button>";
                $('#cancelButton').before(
                    categoryButton
                );
            }else if(sequence==4){
                $('#selectButton').remove();
                let index=i;
                index+="li";
                let categoryValue=document.getElementById(index).getAttribute('data-value');
                $('#span3').text(categoryValue);
                if($('#category_firstCheck1').val()==""){
                    $('#category_first1').val(categoryValue);
                }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()==""){
                    $('#category_first2').val(categoryValue);
                }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!=""){
                    $('#category_first3').val(categoryValue);
                }else if($('#category_firstCheck1').val()!="" && $('#category_firstCheck2').val()!="" && $('#category_firstCheck3').val()!=""){
                    alert("카테고리는 3개까지만 선택가능합니다.");
                    categories(5,0);
                }
                let categoryButton="<button id='selectButton' type='button' class='btn btn-secondary' onclick='selectedCategory()' style='margin-top:7px' >선택</button>";
                $('#cancelButton').before(
                    categoryButton
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
                $('#button1').remove();
                $('#button2').remove();
                $('#button3').remove();
                $('#ul1').remove();
                $('#ul2').remove();
                $('#ul3').remove();
                $('#span1').remove();
                $('#span2').remove();
                $('#span3').remove();
                $('#selectButton').remove();
                $('#ck1').remove();
                $('#ck2').remove();
                $('#ck3').remove();
                let div1="";
                div1+="<button id='button1' type='button' class='btn btn-outline-light dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false' style='color: black; border-color: black'>";
                div1+="<span id='span1'>관심사</span>";
                div1+="</button>";
                div1+="<ul id='ul1' class='dropdown-menu'>";
                div1+="<c:forEach items='${intOut}' var='intOut' varStatus='index'>";
                div1+="<li><a id='li${index.index}' class='dropdown-item' href='javascript:categories(2,${index.index})' onclick='javascript:reset()' data-value='${intOut}'>${intOut}</a></li>";
                div1+="</c:forEach>";
                div1+="</ul>";
                $('#div1').after(
                    div1
                );
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
    </script>
    <style>
        .field-error {
            border-color: #dc3545;
            color: #dc3545;
        }
    </style>
</head>

<body onload="categories(1,0)">
<!-- ======= Header ======= -->
<jsp:include page="../header.jsp" flush="true"/>
<!-- End Header -->

<main id="main">
    <!-- ======= Breadcrumbs ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
        <div class="container">
            <h1>회원가입</h1>
        </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- ======= Pricing Section ======= -->
    <section
            class="h-100"
            style="background-color: #eee; box-sizing: content-box"
    >
        <div class="container h-100" data-aos="flip-down">
            <div
                    class="row d-flex justify-content-center align-items-center h-100"
            >
                <div class="col-lg-12 col-xl-11">
                    <div class="card text-black" style="border-radius: 25px">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                                    <p class="text-center h1 fw-bold mb-3 mx-1 mx-md-4 mt-2">
                                    </p>
                                    <!--거주지/관심지역/이름/생년월일/비번/비번확인/전화번호/성별-->
                                    <form:form modelAttribute="member" class="mx-1 mx-md-4" id="create-member-form"
                                               action="/members/new" method="post">
                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <div class="form-outline flex-fill mb-2">
                                                <label class="form-label mb-0" for="username">이름</label>
                                                <form:input type="text"
                                                            name="username"
                                                            id="username"
                                                            class="form-control"
                                                            value="" path="username"/>
                                                <form:errors path="username" class="field-error"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <div class="form-outline flex-fill mb-2">
                                                <label class="form-label mb-0" for="userId">아이디</label>
                                                <form:input type="text"
                                                            name="userId"
                                                            id="userId"
                                                            class="form-control"
                                                            value="${member.userId}" path="userId"/>
                                                <form:errors path="userId" class="field-error"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <p class="text-center mt-3" id="userIdMessage"></p>
                                        </div>
                                        <div class="row mb-5 justify-content-md-center">
                                            <div class="col-sm-10 d-grid gap-2 d-sm-flex justify-content-sm-end">
                                                <button type="button" class="btn btn-primary" id="already-userId">중복확인
                                                </button>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <div class="form-outline flex-fill mb-2">
                                                <label class="form-label mb-0" for="email">이메일</label>
                                                <form:input type="email"
                                                            name="email"
                                                            id="email"
                                                            class="form-control"
                                                            placeholder="example@google.com"
                                                            value="${member.email}" path="email"/>
                                                <form:errors path="email" class="field-error"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <div class="form-outline flex-fill mb-2">
                                                <label class="form-label mb-0" for="birth">생년월일</label>
                                                <form:input type="date"
                                                            name="birth"
                                                            id="birth"
                                                            class="form-control"
                                                            value=""
                                                            path="birth"/>
                                                <form:errors path="birth" class="field-error"/>
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <div class="form-outline flex-fill mb-2">
                                                <label class="form-label mb-0" for="password">비밀번호</label>
                                                <form:input type="password"
                                                            name="password"
                                                            id="password"
                                                            class="form-control"
                                                            value="" path=""/>

                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <div class="form-outline flex-fill mb-2">
                                                <label class="form-label mb-0" for="password2">비밀번호 확인</label>
                                                <input type="password"
                                                       name="password2"
                                                       id="password2"
                                                       class="form-control"/>
                                            </div>
                                        </div>

                                        <div id='firstAppendDiv' style='padding: 0px 15px 7px'
                                             class='d-flex flex-row align-items-center mb-0'>
                                        </div>
                                        <div id="selectdiv1" class="d-flex flex-row align-items-center mb-0">
                                            <div id="selectdiv2" class="form-outline flex-fill mb-2">
                                                <!-- 카테고리 붙이기 -->
                                                <div id="div1" class="btn-group">
                                                    <button id="button1"
                                                            type="button"
                                                            class="btn btn-outline-light dropdown-toggle"
                                                            data-bs-toggle="dropdown"
                                                            aria-expanded="false"
                                                            style="color: black; border-color: black">
                                                        <span id='span1'>관심사</span>
                                                    </button>
                                                    <ul id="ul1" class="dropdown-menu">
                                                    </ul>
                                                </div>

                                                <div id="div2" class="btn-group" style="margin-left: 5px">
                                                    <button id="button2"
                                                            type="button"
                                                            class="btn btn-outline-light dropdown-toggle"
                                                            data-bs-toggle="dropdown"
                                                            aria-expanded="false"
                                                            style="color: black; border-color: black">
                                                        <span id='span2'>하위관심사</span>
                                                    </button>
                                                    <ul id="ul2" class="dropdown-menu">
                                                    </ul>
                                                </div>

                                                <div id="div3" class="btn-group" style="margin-left: 5px">
                                                    <button
                                                            type="button" id="button3"
                                                            class="btn btn-outline-light dropdown-toggle"
                                                            data-bs-toggle="dropdown"
                                                            aria-expanded="false"
                                                            style="color: black; border-color: black">
                                                        <span id='span3'>세부관심사</span>
                                                    </button>
                                                    <ul id="ul3" class="dropdown-menu">
                                                    </ul>
                                                </div>
                                                <input id="category_first1" type="hidden" value="">
                                                <input id="category_first2" type="hidden" value="">
                                                <input id="category_first3" type="hidden" value="">
                                                <input id="category_firstCheck1" type="hidden" value="">
                                                <input id="category_firstCheck2" type="hidden" value="">
                                                <input id="category_firstCheck3" type="hidden" value="">
                                                <input id="checkBox1" type="hidden" value="">
                                                <input id="checkBox2" type="hidden" value="">
                                                <input id="checkBox3" type="hidden" value="">
                                                <div align="right">
                                                    <button id='cancelButton' type='button' class='btn btn-secondary'
                                                            onclick='categories(6,0)' style='margin:7px 0px 0px 5px'>취소
                                                    </button>
                                                </div>
                                                <!-- 카테고리 붙이기 -->
                                            </div>
                                        </div>

                                        <div class="d-flex flex-row align-items-center mb-0">
                                            <label for="gender"
                                                   class="col-sm-2 col-lg-1 col-form-label text-sm-end">성별</label>
                                            <div class="col-sm-8 col-lg-9">
                                                <select id="gender" name="gender" aria-label="성별">
                                                    <option value selected>성별</option>
                                                    <option value="M">남자</option>
                                                    <option value="F">여자</option>
                                                    <option value="U">선택안함</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                            <button type="submit"
                                                    class="btn btn-success disabled"
                                                    style="margin-right: 30px" id="sign-up-button">
                                                가입하기
                                            </button>
                                            <button type="button" class="btn btn-secondary" onclick="joinCancel()">
                                                취소
                                            </button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Pricing Section -->
</main>
<!-- End Pricing Section -->

<!-- End #main -->

<!-- ======= Footer ======= -->
<jsp:include page="../footer.jsp" flush="true"/>
<!-- End Footer -->
</body>
<div id="preloader"></div>

<!-- Vendor JS Files -->
<script src="/assets/vendor/purecounter/purecounter.js"></script>
<script src="/assets/vendor/aos/aos.js"></script>
<script src="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="../assets/js/main.js"></script>
<script type="text/javascript" src="/js/members/createMember.js"></script>
</html>

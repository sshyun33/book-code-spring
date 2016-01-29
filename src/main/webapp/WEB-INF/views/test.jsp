<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #modDiv {
            width: 300px;
            height: 100px;
            background-color: gray;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -50px;
            margin-left: -150px;
            padding: 10px;
            z-index: 1000;
        }
    </style>
</head>
<body>
<h2>Ajax Test Page</h2>

<div>
    <div>
        REPLYER <input type="text" name="replyer" id="newReplyWriter">
    </div>
    <div>
        REPLY TEXT <input type="text" name="replytext" id="newReplyText">
    </div>
    <button id="replyAddBtn">ADD REPLY</button>
</div>

<div id="modDiv" style="display: none">
    <div class="modal-title"></div>
    <div>
        <input type="text" id="replytext">
    </div>
    <div>
        <button type="button" id="replyModBtn">Modify</button>
        <button type="button" id="replyDelBtn">DELETE</button>
        <button type="button" id="closeBtn">Close</button>
    </div>
</div>
<hr/>
<ul id="replies"></ul>

<hr/>
<ul class="pagination"></ul>

<%--jQuery 2.1.4--%>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>
    var bno = 800;
    var replyPage = 1;

    function getAllList() {
        $.getJSON("/replies/all/" + bno, function (data) {
            console.log(data.length);

            var str = '';
            $(data).each(
                    function () {
                        str += "<li data-rno='" + this.rno + "' class='replyLi'>"
                                + this.rno + ": " + this.replyer + ":" + this.replytext
                                + "<button>MOD</button></li>";

                    });
            console.log(str);
            $("#replies").html(str);
        });
    }

    function getPageList(page) {
        $.getJSON("/replies/" + bno + "/" + page, function (data) {
            console.log(data.list.length);

            var str = "";

            $(data.list).each(function () {
                str += "<li data-rno='" + this.rno + "' class='replyLi'>"
                        + this.rno + ": " + this.replyer + ":" + this.replytext
                        + "<button>MOD</button></li>";

            });
            $("#replies").html(str);

            printPaging(data.pageMaker);
        });
    }

    function printPaging(pageMaker) {

        var str = "";

        if (pageMaker.prev) {
            str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
        }

        for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
            var strClass = pageMaker.cri.page == i ? 'class=active' : '';
            str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
        }

        if (pageMaker.next) {
            str += "<li><a href='" + (pageMaker.endPage + 1) + "' > >> </a></li>";
        }
        $(".pagination").html(str);
    }

    $("#replyAddBtn").on("click", function () {
        var replyer = $("#newReplyWriter").val();
        var replytext = $("#newReplyText").val();

        $.ajax({
            type: 'post',
            url: 'replies',
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            dataType: 'text',
            data: JSON.stringify({
                bno: bno,
                replyer: replyer,
                replytext: replytext
            }),
            success: function (result) {
                if (result == 'SUCCESS') {
                    alert("Registered");
                    getAllList();
                }
            }
        });
    });

    $("#replies").on("click", ".replyLi button", function () {
        var reply = $(this).parent();
        var rno = reply.attr("data-rno");
        var replytext = reply.text();

        $(".modal-title").html(rno);
        $("#replytext").val(replytext);
        $("#modDiv").show("slow");
    });

    $("#replyDelBtn").on("click", function () {
        var rno = $(".modal-title").html();
        var replytext = $("#newReplyText").val();

        $.ajax({
            type: 'delete',
            url: 'replies/' + rno,
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "DELETE"
            },
            dataType: 'text',
            success: function (result) {
                if (result == 'SUCCESS') {
                    alert("Removed");
                    $("#modDiv").hide("slow");
                    getAllList();
                }
            }
        });

    });

    $("#replyModBtn").on("click", function () {
        var rno = $(".modal-title").html();
        var replytext = $("#replytext").val();

        $.ajax({
            type: 'put',
            url: 'replies/' + rno,
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "PUT"
            },
            data: JSON.stringify({replytext: replytext}),
            dataType: 'text',
            success: function (result) {
                if (result == 'SUCCESS') {
                    alert("Modified");
                    $("#modDiv").hide("slow");
//                    getAllList();
                    getPageList(replyPage);
                }
            }
        });
    })

    $(".pagination").on("click", "li a", function (event) {
        event.preventDefault();

        replyPage = $(this).attr("href");

        getPageList(replyPage);
    });


</script>
</body>
</html>

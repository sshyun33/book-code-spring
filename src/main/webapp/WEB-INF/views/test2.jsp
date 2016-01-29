<%--
  Created by IntelliJ IDEA.
  User: ilanian
  Date: 2016-01-29
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>

<div id="displayDiv">
</div>

<script id="template" type="text/x-handlebars-template">
    <span> {{ name }} </span>
    <div>
        <span>{{ userid }}</span>
        <span>{{ addr }}</span>
    </div>
</script>
<script>
    var source = $("#template").html();
    var template = Handlebars.compile(source);
    var data = {name:"hone", userid:"user00", addr:"Korea"};

    $("#displayDiv").html(template(data));
</script>
</body>
</html>

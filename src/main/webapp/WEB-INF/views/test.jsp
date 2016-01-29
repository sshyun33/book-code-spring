<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Ajax Test Page</h2>
<ul id="replies"></ul>

<%--jQuery 2.1.4--%>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>
    var bno = 800;

    $.getJSON("/replies/all/" + bno, function (data) {
        console.log(data.length);
    })
</script>
</body>
</html>

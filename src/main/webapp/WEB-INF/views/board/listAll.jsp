<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<script>
	var result = '${msg}';
	
	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>

<!-- Main content -->
<section class="content">
	<div class="row">
	    <!-- left column -->
        <div class="col-md-12">
        <!-- general form elements -->
        
       	<table class="table table-bordered">
       		<tr>
       			<th style="width: 10px">BNO</th>	
       			<th>Title</th>
       			<th>WRITER</th>
       			<th>REGDATE</th>
       			<th style="width: 40px">VIEWCNT</th>
       		</tr>	
       	</table> 
       	
        
		</div>
    	<!--/.col (left) -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->

<%@include file="../include/footer.jsp" %>
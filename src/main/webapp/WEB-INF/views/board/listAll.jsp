<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
	    <!-- left column -->
        <div class="col-md-12">
        <!-- general form elements -->
        
        <div class="box">
       		<div class="box-header with-border">
       			<h3 class="box-title">LIST ALL PAGE</h3>	
       		</div> 
       		<div class="box-body">
       			<table class="table table-bordered">
       				<tr>
       					<th style="width: 10px">BNO</th>
       					<th>TITLE</th>
       					<th>WRITER</th>
       					<th>REGDATE</th>
       					<th style="width: 40px">VIEWCNT</th>
       				</tr>	
       			</table>	
       		</div>
       		<div class="box-footer">Footer</div>
        </div>
		</div>
    	<!--/.col (left) -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->

<%@include file="../include/footer.jsp" %>

<script>
	var result = '${msg}';
	
	if (result == 'success') {
		alert("처리가 완료되었습니다.");	
	}
</script>
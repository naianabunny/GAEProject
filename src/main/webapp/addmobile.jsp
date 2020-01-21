<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	.myform
	{
		margin:0 auto;
	}
</style>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<c:if test="${mobile==null}">
			<form class="col-lg-6 col-md-6 col-sm-12 col-xs-12 myform" action="AddMobileController" method="post" enctype="multipart/form-data">
		</c:if>
		
		<c:if test="${mobile!=null}">
		<form class="col-lg-6 col-md-6 col-sm-12 col-xs-12 myform" action="EditController" method="post">
		</c:if>
		
		<c:if test="${mobile!=null}">
			<div class="form-group">
				<label>Enter product Id</label>
				<input type="text" name="productid" placeholder="Enter product id" class="form-control" value="${mobile.productid}" readonly/>
			</div>
			</c:if>
			
			<div class="form-group">
				<label>Enter mobile Name</label>
				<input type="text" name="mobilename" placeholder="Enter mobile Name" class="form-control" value="${mobile.mobilename}"/>
			</div>
			
			<div class="form-group">
				<label>Enter model</label>
				<input type="text" name="model" placeholder="Enter model" class="form-control" value="${mobile.model}"/>
			</div>
			
			<div class="form-group">
				<label>Enter Price</label>
				<input type="text" name="price" placeholder="Enter Price" class="form-control" value="${mobile.price}"/>
			</div>
			
			<div class="form-group">
				<label>Enter image</label>
				<input type="file"  accept="image/*" name="image" placeholder="Select Image" class="form-control"/>
			</div>
			
			<div class="form-group">
				<c:if test="${mobile==null}">
				<input type="submit" value="Add mobile" class="btn btn-primary btn-block"/>
				</c:if>
				
				<c:if test="${mobile!=null}">
				<input type="submit" value="Edit mobile" class="btn btn-primary btn-block"/>
				</c:if>
				<input type="reset" value="Reset" class="btn btn-danger btn-block"/>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
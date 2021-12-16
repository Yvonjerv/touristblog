<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspresources.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>错误提示</title>
		<style>
			.errorcontent{
				margin-left: 100px;
				width:1000px;
			}
			a{
				text-decoration: none;
				/*font: "宋体";*/
				font-size: 16px;
				color:#EE7621;
			}
			img{
				width:300px; height: 300px;
			}
		</style>
	</head>
	<body>
		<table class="errorcontent">
			<tr>
				<td rowspan="2" style="width:350px">
					<img src="../content_img/success.jpg">
				</td>
				<td><center>
						<p><h2>${promptMsg }</h2></p>
					</center>
				</td>
			</tr>
			<tr>
				<td>
					<center>
						<a href="${backUrl }">O K</a>
					</center>
				</td>
			</tr>
		</table>
	</body>
</html>

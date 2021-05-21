<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含base标签，css样使，jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

			<%--静态包含 manager管理模块菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:if test="${empty requestScope.allOrder}">
				<span>暂无订单</span>
			</c:if>
			<c:if test="${not empty requestScope.allOrder}">
				<c:forEach items="${requestScope.allOrder}" var="order">

					<tr>
						<td>${order.createTime}</td>
						<td>${order.price}</td>
						<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
						<td><td>
						<c:choose>
							<c:when test="${order.status == 0}">
								待支付
							</c:when>
							<c:when test="${order.status == 1}">
								<a onclick="return confirm('你确认发货吗？')" href="orderServlet?action=sendOrder&orderId=${order.orderId}">待发货</a>
							</c:when>
							<c:when test="${order.status == 2}">
								待收货
							</c:when>
							<c:when test="${order.status == 3}">
								已签收
							</c:when>
						</c:choose>
					</td></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
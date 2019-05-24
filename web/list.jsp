<%--
  Created by IntelliJ IDEA.
  User: arata.sashida
  Date: 2019-05-23
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>商品一覧</title>
</head>
<body>
<h1>エム・ビーショップ</h1>
<h2>商品一覧</h2>

<div>
    <%--検索ボタンとフォーム--%>
    <form action="/ItemsServlet" method="get">
        <input type="text" name="keyword">
        <input type="button" name="searchKeyword" value="検索">
    </form>
</div>

<div>
    <%--ページナビゲーション--%>
    <cetner><< 1,2,3,4,5 >></cetner>
</div>

<%--商品一覧部分--%>
<div>
    <form action="/CartServlet?mode=1&id=${item.id}" method="get">
        <table border="1" cellspacing="10">
                <c:forEach items="${items}" var="item">
            <tr>
                <td>
                    <img src="./img/dummy.png" alt="dummy"><br>
                    <input type="button" onclick="/CartServlet?mode=1&id=${item.id}" value="カートに入れる">
                </td>
                <td>
                    <a href="/ItemsServlet?mode=1&id=${item.id}">${item.title}</a><br>
                    価格:<br>
                    ${item.price}
                </td>
            </tr>
                </c:forEach>
        </table>
    </form>
</div>

</body>
</html>

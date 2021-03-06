<%--
  Created by IntelliJ IDEA.
  User: arata.sashida
  Date: 2019-05-23
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">

<html>
<head>
    <title>カート画面</title>
</head>
<body>
<h1 style="font-family: 'Lobster', cursive;">MB SHOP</h1>

<div>
    <h2>カート</h2>
    <c:if test="${empty sessionScope.cart.items}">
        現在、カートは空です。
    </c:if>

    <c:if test="${not empty sessionScope.cart.items}">
    <table border="1">
        <tr>
            <th align="center">タイトル</th>
            <th align="center">価格</th>
            <th align="center">数量</th>
            <th align="center">計</th>
            <th align="center">削除</th>
        </tr>


        <c:forEach items="${sessionScope.cart.items}" var="item">
        <tr>
            <td align="center" width="150">${item.getValue().title}</td>
            <td align="center" width="150">${item.getValue().price}</td>
            <form action="CartServlet" method="post">
                <td align="center" width="150">
                    <input type="hidden" name="mode" value="2">
                    <input type="hidden" name="id" value="${item.getValue().id}">
                    <input type="number" name="quantity" value="${item.getValue().quantity}">
                    <span>個</span>
                    <input type="submit" name="update" value="更新">
                </td>
            </form>
            <td align="center" width="150">${item.getValue().totalPrice}</td>
            <td align="center" width="150">
                <form action="CartServlet" method="post">
                    <input type="hidden" name="mode" value="3">
                    <input type="hidden" name="id" value="${item.getValue().id}">

                    <input type="submit" value="削除">
                </form>
            </td>
        </tr>
        </c:forEach>
        <tr>
            <td align="center" colspan="3">合計</td>
            <td align="center" colspan="1">${sessionScope.cart.totalPrice}</td>
            <td align="center" colspan="1"></td>
        </tr>

        </c:if>

    </table>
</div>

<br>

<div>
    <form action="ItemsServlet" method="post">
        <input type="submit" value="買い物を続ける">
    </form>
<%--    <input type="button" onclick="ItemsServlet" value="買い物を続ける">--%>
</div>

<br>
<c:if test="${not empty sessionScope.cart.items}">

<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
    <h2>お届け先</h2>
    <form action="/OrdersServlet?mode=1" method="post">
        <ul style="list-style: none;">
            <li>
                名前 <br>
                <input type="text" name="name">
            </li>
            <li>
                郵便番号 <br>
                <input type="text" name="postal">
            </li>
            <li>
                住所 <br>
                <input type="text" name="address">
            </li>
            <li>
                電話番号 <br>
                <input type="text" name="phone">
            </li>
        </ul>
        <input type="submit" value="注文する">
    </form>
</div>
</c:if>

</body>
</html>

<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yuta.doi
  Date: 2019-05-23
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>ordered</title>
</head>
<body>

<h1>{エム・ビーショップ}</h1>

<h2>注文完了</h2>
下記の通り注文を受け付けました
<div>
<table border="1" width="100%" style="table-layout: fixed;">
    <tr>
        <td align="center">タイトル</td>
        <td align="center">価格</td>
        <td align="center">数量</td>
        <td align="center">計</td>
    </tr>

<%--    <c:forEach items="${cart.cartList}" var="item">--%>
        <tr>
            <td align="center">{テッド}</td>
            <td align="center">{1,429}円</td>
            <td align="center">{1}</td>
            <td align="center">{1,429}円</td>
        </tr>
<%--    </c:forEach>--%>
    <tr>
        <td align="center" colspan="3">合計</td>
        <td align="center">{1,429}円</td>
    </tr>
</table>
</div>

<h3>お届け先</h3>

<ul style="list-style: none;">
    <li>
        名前
        <p>{山田太郎}</p>
    </li>
    <li>
        郵便番号
        <p>{111-2222}</p>
    </li>
    <li>
        住所
        <p>{東京都豊島区東池袋2-34-5}</p>
    </li>
    <li>
        電話番号
        <p>{03-1111-2222}</p>
    </li>
</ul>

<a href="/ItemsServlet">トップページへ</a>

</body>
</html>

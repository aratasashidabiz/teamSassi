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

<%--検索ボタンとフォーム--%>
<form action="/mbshop/ItemsController" method="get">
    <input type="text" name="keyword">
    <button name="search_button" value="検索">検索</button>
</form>

<%--ページナビゲーション--%>
<cetner><< 1,2,3,4,5 >></cetner>

<%--商品一覧部分--%>
<form action="/mbshop/CartController?mode=1&id=商品ID" method="get">
<% for(int i=1;i<11;i++){ %>
<br>
<a href="/mbshop/ItemsController?mode=1&id=商品ID">タイトル<%=i%></a> <br>
価格:items.price <br>
画像:/img/items/商品ID.jpg <br>
<button type="button" name="inItems" value="inItems">カートに入れる</button>
<br>
<% } %>
</form>

</body>
</html>

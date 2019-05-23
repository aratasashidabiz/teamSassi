<%--
  Created by IntelliJ IDEA.
  User: arata.sashida
  Date: 2019-05-23
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>カート画面</title>
</head>
<body>
<h1>エム・ビーショップ</h1>

<div>
    <h2>カート</h2>
    <table border="1">
        <tr>
            <th align="center">タイトル</th>
            <th align="center">価格</th>
            <th align="center">数量</th>
            <th align="center">計</th>
            <th align="center">削除</th>
        </tr>
        <%--    <c:forEach items="${cart.cartList}" var="item">--%>
        <tr>
            <td align="center" width="150">{テッド}</td>
            <td align="center" width="150">{1429円}</td>
            <form action="/mbshop/CartController?mode=2" method="post">
                <td align="center" width="150">
                    <input type="hidden" name="id" value="${item.id}">
                    <select name="quantity" id="quantity">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>個
                    <input type="button" name="update" value="更新">
                </td>
            </form>
            <td align="center" width="150">{1429円}</td>
            <td align="center" width="150">
                <input type="button" onclick="/mbshop/CartController?mode=3&id=商品ID" value="削除">
            </td>
        </tr>
        <%--    </c:forEach>--%>
        <tr>
            <td align="center" colspan="3">合計</td>
            <td align="center" colspan="1">{1,429円}</td>
            <td align="center" colspan="1"></td>
        </tr>
    </table>
</div>

<br>

<div>
    <input type="button" onclick="/mbshop/ItemsController" value="買い物を続ける">
</div>

<br>

<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
    <h2>お届け先</h2>
    <form action="/mbshop/OrdersController?mode=1" method="post">
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

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String errorCode = (String) request.getAttribute("errorCode");
    if (errorCode == null) {
        errorCode = request.getParameter("errorCode");
    }
    String errorMessage = (String) request.getAttribute("errorMessage");

    if (errorMessage == null) {
        if ("010".equals(errorCode)) {
            errorMessage = "DB接続処理に失敗しました!管理者に連絡してください。";
        } else if ("011".equals(errorCode)) {
            errorMessage = "電話番号と氏名カナのいずれか、または両方を入力してください。";
        } else if ("012".equals(errorCode)) {
            errorMessage = "一致する情報は見つかりませんでした。";
        } else if ("013".equals(errorCode)) {
            errorMessage = "顧客情報検索処理に失敗しました!管理者に連絡してください。";
        } else {
        	errorMessage = "予期しないエラーが発生しました。";
        }
    }
    if (errorCode == null) {
        errorCode = "---";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>&lt;&lt;エラー&gt;&gt;KIDDA-LA業務システム</title>
<link rel="stylesheet" href="css/common.css">
</head>
<body>
    <div class="page-wrapper">
        <div class="header">
            <p class="system-name">KIDDA-LA 業務システム</p>
            <h1 class="screen-name">《エラー》</h1>
        </div>

        <div class="error-box">
            <div class="error-code">コード：<%= errorCode %></div>
            <div class="error-message"><%= errorMessage %></div>
            <form action="KiddaLaController" method="post">
                <input type="hidden" name="command" value="CustomerSearchDisplay">
                <button type="submit" class="btn btn-secondary">戻る</button>
            </form>
        </div>

        <p class="footer-credit">&copy;Infotech Serve Inc.</p>
    </div>
</body>
</html>


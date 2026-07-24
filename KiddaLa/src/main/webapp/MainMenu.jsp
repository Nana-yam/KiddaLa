<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>《メインメニュー》KIDDA-LA 業務システム</title>
<link rel="stylesheet" href="css/common.css">
</head>
<body>
    <div class="page-wrapper">

        <!-- 角の装飾画像。画像ファイルを images/ 配下に置くと自動的に表示される -->
        <div class="corner-deco top-left"><img src="images/pizza_topleft.png" alt="" onerror="this.style.display='none'"></div>
        <div class="corner-deco top-right"><img src="images/pizza_topright.png" alt="" onerror="this.style.display='none'"></div>
        <div class="corner-deco bottom-left"><img src="images/pizza_bottomleft.png" alt="" onerror="this.style.display='none'"></div>
        <div class="corner-deco bottom-right"><img src="images/pizza_bottomright.png" alt="" onerror="this.style.display='none'"></div>

        <div class="header">
            <p class="system-name">KIDDA-LA 業務システム</p>
            <h1 class="screen-name">《メインメニュー》</h1>
        </div>

        <!-- ロゴ画像。images/logo.png が無い場合は代替表示になる -->
        <div class="logo-area">
            <img src="images/logo.png" alt="KIDDA-LA PIZZA"
                 onerror="this.style.display='none'; document.getElementById('logoFallback').style.display='block';">
            <div id="logoFallback" class="logo-fallback" style="display:none;">KIDDA-LA PIZZA</div>
        </div>

        <!-- 「01 注文管理」ボタン：クリックすると顧客情報検索画面に遷移する -->
        <div class="menu-button-area">
            <form action="KiddaLaController" method="post">
                <input type="hidden" name="command" value="CustomerSearchDisplay">
                <button type="submit" class="btn btn-primary">01 注文管理</button>
            </form>
        </div>
        
       <!-- 「閉じる」ボタン -->
       <div class="menu-button-area">
            <button type="button" class="btn btn-secondary" onclick="window.close();">閉じる</button>
       </div>

        <p class="footer-credit">&copy;Infotech Serve Inc.</p>
    </div>
</body>
</html>

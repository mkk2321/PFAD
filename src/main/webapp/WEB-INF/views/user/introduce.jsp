<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>PFAD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/user/resources/stylesheets/introduce.css">
    <link rel="stylesheet" href="../resources/stylesheets/common.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
          integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
</head>
<body>
<%@ include file="/WEB-INF/views/header.jsp"%>
<div class="introduce">
    <div>
        <h2>Pure Juice</h2>
        <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Deserunt eaque omnis sed sequi veritatis. Accusantium
            consequuntur delectus deserunt dicta ea et hic iusto laudantium optio perferendis sint tempora, tempore veniam!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam cupiditate, delectus distinctio dolorum exercitationem harum hic illum inventore minus modi molestiae porro quaerat quidem recusandae repudiandae similique, tempore voluptate voluptatem?
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad dolores, enim error eveniet ex ipsam non quis velit. Commodi consequatur corporis deleniti dolore in incidunt iure perferendis quod sint veniam.
        </p>
    </div>
    <img src="../resources/images/introduce.png" alt="introduce">
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>
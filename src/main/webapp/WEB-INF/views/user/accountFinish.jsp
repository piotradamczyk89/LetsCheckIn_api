<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Headers_footers/header_inner.jsp"/>
<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
        <div class="container">

            <ol>
                <li><a href="index.html">Home</a></li>
                <li>Inner Page</li>
            </ol>
            <h2>Inner Page</h2>

        </div>
    </section><!-- End Breadcrumbs -->

    <section class="inner-page">
        <div class="container">
            <c:if test="${status=='ok'}">
            <h2>Poprawnie dodano konto</h2>
            </c:if>
            <c:if test="${status=='nok'}">
                <h2>Cos poszło nie tak :( nie dodano konta</h2>
            </c:if>
            <a href="/user/app">Powrót do menu głownego</a>
        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
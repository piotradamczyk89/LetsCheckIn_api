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
            <h2>po wyborze banku zostaniesz przekierowany na strone banku gdzie zostaniesz poporszony o autoryzacje w
            trakcie których bedziesz musiał wybrać konto które bedzie podłaczone do aplikacji Let's Check in.
            Wybierz tylko jedno konto które będzie obsługiwało twoje rachunki</h2>
            <div class="row row-cols-3 justify-content-md-start">
                <c:forEach items="${banks}" var="bankIter">
                    <div class="col">
                        <div class="row justify-content-center">
                            <div class="card" style="width: 18rem;">
                                <img src="${bankIter.logo}" class="card-img-top">
                                <div class="card-body">
                                    <h5 class="card-title">${bankIter.name}</h5>
                                    <form:form method="post" modelAttribute="bank">
                                        <form:hidden path="id" value="${bankIter.id}"/>
                                        <input type="submit" value="Wybierz bank">
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
            <div class="row row-cols-3 justify-content-md-start">
                <c:forEach items="${bills}" var="bill">
                    <div class="col">
                        <div class="row justify-content-center">
                            <div class="card  mb-4" style="max-width: 560px;">
                                <div class="card-header">
                                    Rachunek do rezerwavji: ${bill.reservation.name}
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${bill.name}</h5>
                                    <p class="card-text">Data wystawinia ${bill.createDate}</p>
                                    <p class="card-text">Termin opłaty ${bill.expireDate}</p>
                                    <p class="card-text">Wartość rachunku do zapłaty ${bill.cost}</p>
                                    <p class="card-text">Rachunek opłacony : ${bill.paid}</p>
                                    <c:if test="${user.id==bill.reservation.apartment.owner.id}">
                                        <c:if test="${bill.paid==false}">
                                        <a href="/bill/edit/${bill.id}"
                                           class="btn btn-primary">Zmień status rachunku</a>
                                        </c:if>
                                        <a href="/bill/delete/${bill.id}"
                                           class="btn btn-primary">Usuń rachunek</a>
                                    </c:if>
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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Headers_footers/header_inner.jsp"/>

<main id="main">
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

            <c:forEach items="${reservations}" var="reservation">
                <c:if test="${reservation.room==null}">
                <div class="row justify-content-center">
                    <div class="card  mb-4" style="max-width: 630px;background-color: #e5e5e5">
                        <div class="card-header">
                                Rezerwacja o numerze: ${reservation.name}
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${reservation.apartment.name}</h5>
                            <p class="card-text">Data zamledowania: ${reservation.startDate}</p>
                            <p class="card-text">Data wymeldowania: ${reservation.endDate}</p>
                            <c:forEach items="${reservation.bills}" var="bill">
                                <c:if test="${bill.name.equals('Advance payment')}">
                                    <p class="card-text">Rezerwacja opłacona : ${bill.paid}</p>
                                    <c:if test="${bill.paid==false && reservation.apartment.owner.id!=user.id}">
                                        <p style="color: red"><u>Zapłać zaliczke w wysokości <strong>${bill.cost} zł</strong> do <strong>${bill.expireDate}</strong> inaczej rezerwacja bedzie usunięta </u></p>
                                        <p><u>W tytule przelewu podaj numer rezerwacji ${reservation.name}</u></p>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            <c:if test="${reservation.apartment.owner.id==user.id}">
                            <p class="card-text">Rezerwujcy: ${reservation.tenant.userName}</p>
                            <p class="card-text">tel. ${reservation.tenant.phone} email:${reservation.tenant.email}</p>
                            </c:if>
                            <p> <a href="/bill/add/${reservation.id}" class="btn btn-primary">Dodaj swój rachunek</a>
                                <a href="/reservation/delete/${reservation.id}" class="btn btn-primary">Anuluj rezerwacje</a>
                                <a href="/bill/list/${reservation.id}" class="btn btn-primary">Zobacz wszytskie rachunki</a></p>
                            <p><a href="/room/details/${reservation.room.id}" class="btn btn-primary">Szczegóły obiektu</a></p>
                        </div>
                    </div>
                </div>
                </c:if>
                <c:if test="${reservation.room!=null}">
                    <div class="row justify-content-center">
                        <div class="card mb-4" style="max-width: 630px;background-color: #e5e5e5">
                            <div class="card-header">
                                    ${reservation.name}
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${reservation.room.roomName.name} w ${reservation.room.apartment.name}</h5>
                                <p class="card-text">Data zamledowania ${reservation.startDate}</p>
                                <p class="card-text">Data wymeldowania ${reservation.endDate}</p>
                                <c:forEach items="${reservation.bills}" var="bill">
                                    <c:if test="${bill.name.equals('Advance payment')}">
                                        <p class="card-text">Rezerwacja opłacona : ${bill.paid}</p>
                                        <c:if test="${bill.paid==false && reservation.apartment.owner.id!=user.id}">
                                            <p style="color: red"><u>Zapłać zaliczke w wysokości <strong>${bill.cost} zł</strong> do <strong>${bill.expireDate}</strong> inaczej rezerwacja bedzie usunięta </u></p>
                                            <p><u>W tytule przelewu podaj numer rezerwacji ${reservation.name}</u></p>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${reservation.apartment.owner.id==user.id}">
                                    <p class="card-text">Rezerwujcy: ${reservation.tenant.userName}</p>
                                    <p class="card-text">tel. ${reservation.tenant.phone} email:${reservation.tenant.email}</p>
                                </c:if>
                                <p> <a href="/bill/add/${reservation.id}" class="btn btn-primary">Dodaj swój rachunek</a>
                                <a href="/reservation/delete/${reservation.id}" class="btn btn-primary">Anuluj rezerwacje</a>
                                <a href="/bill/list/${reservation.id}" class="btn btn-primary">Zobacz wszytskie rachunki</a></p>
                                <p><a href="/room/details/${reservation.room.id}" class="btn btn-primary">Szczegóły obiektu</a></p>
                            </div>
                        </div>
                    </div>

                </c:if>
            </c:forEach>
        </div>
    </section>
</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>



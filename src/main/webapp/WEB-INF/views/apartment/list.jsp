<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${user!=null}">
    <jsp:include page="../Headers_footers/header_inner.jsp"/>
</c:if>
<c:if test="${user==null}">
    <jsp:include page="../Headers_footers/header.jsp"/>
</c:if>
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
            <c:if test="${searchDto!=null}">
                <div class="row mb-4">
                    <div class="col-md-6 mb-6">
                        <h5 class="text-center">Wyszukiwanie dla:</h5>
                        <h6><u>Kraj: <strong>${searchDto.country}</strong></u></h6>
                        <h6><u>Miasto <strong>${searchDto.city}</strong></u></h6>
                        <h6><u>Ilość osób: <strong>${searchDto.person}</strong></u></h6>
                        <h6><u>Data rozpoczęcia najmu: <strong>${searchDto.startDate}</strong></u></h6>
                        <h6><u>Data zakończenia najmu: <strong>${searchDto.endDate}</strong></u></h6>
                        <sec:authorize access="isAuthenticated()">
                            <h6 style="color: red"><u>Wyszukiwanie nie obejmuje twoich obiektów</u></h6>
                        </sec:authorize>
                    </div>
                </div>
            </c:if>
            <c:if test="${searchLongDto!=null}">
                <div class="row mb-4">
                    <div class="col-md-6 mb-6">
                        <h5 class="text-center">Wyszukiwanie dla:</h5>
                        <h6><u>Kraj: <strong>${searchLongDto.country}</strong></u></h6>
                        <h6><u>Miasto <strong>${searchLongDto.city}</strong></u></h6>
                        <h6><u>Minimalna powierzchnia: <strong>${searchLongDto.area}</strong></u></h6>
                        <h6><u>Data rozpoczęcia najmu: <strong>${searchLongDto.startDate}</strong></u></h6>
                        <h6><u>Data zakończenia najmu: <strong>${searchLongDto.endDate}</strong></u></h6>
                        <sec:authorize access="isAuthenticated()">
                            <h6 style="color: red"><u>Wyszukiwanie nie obejmuje twoich obiektów</u></h6>
                        </sec:authorize>
                    </div>
                </div>
            </c:if>
            <c:forEach items="${apartments}" var="apartment">

                <%--                    --%>
                <div class="card mb-3" style="max-width: 720px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <c:if test="${apartment.photos.size()>0}">
                            <img src="<c:url value="../../../img/LetsCheckIn_pictures/${apartment.photos.get(0).id}"/>"
                                 class="img-fluid rounded-start" alt="...">
                            </c:if>
                        </div>
                        <div class="col-md-4">
                            <div class="card-body">
                                <h5 class="card-title">${apartment.name}</h5>
                                <c:if test="${apartment.rentWay.id!=3}">
                                <p class="card-text"><spring:message code="DayPrice"/> ${apartment.apartmentPrice} <br>
                                    <spring:message code="RoomAmount"/> ${apartment.rooms.size()}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-md-4 row align-items-center">
                            <a href="/apartment/details/${apartment.id}" class="btn btn-info"><spring:message
                                    code="Details"/></a>
                            <c:if test="${user.id==apartment.owner.id}">
                                <a href="/reservation/list/old/${apartment.id}" class="btn btn-info">Zrealizowane Rezerwacje</a>
                                <a href="/reservation/list/actual/${apartment.id}" class="btn btn-info">Aktualne Rezerwacje</a>
                            </c:if>
                        </div>
                    </div>
                </div>

                <%--</a>--%>
            </c:forEach>
            <c:forEach items="${rooms}" var="room">
                <div class="card mb-3" style="max-width: 720px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <c:if test="${room.photos.size()>0}">
                            <img src="<c:url value="../../../img/LetsCheckIn_pictures/${room.photos.get(0).id}"/>"
                                 class="img-fluid rounded-start" alt="...">
                            </c:if>
                            <c:if test="${room.apartment.photos.size()>0}">
                                <img src="<c:url value="../../../img/LetsCheckIn_pictures/${room.apartment.photos.get(0).id}"/>"
                                     class="img-fluid rounded-start" alt="...">
                            </c:if>
                        </div>
                        <div class="col-md-4">
                            <div class="card-body">
                                <h5 class="card-title">${room.roomName.name} w ${room.apartment.name}</h5>
                                <p class="card-text"><spring:message code="DayPrice"/> ${room.roomPrice}</p>
                                <p class="card-text"></p>
                            </div>
                        </div>
                        <div class="col-md-4 row align-items-center">
                            <a href="/room/details/${room.id}" class="btn btn-info"><spring:message code="Details"/></a>
                            <c:if test="${user.id==apartment.owner.id}">
                                <a href="/reservation/listRoom/${room.id}" class="btn btn-info">Rezerwacje obiektu</a>
                            </c:if>
                        </div>
                    </div>
                </div>

            </c:forEach>

        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
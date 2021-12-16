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
            <div class="row justify-content-center">
                <div class="col-xl-8">
                    <form:form method="post" modelAttribute="reservation">
                        <div class="row">
                            <div class="col">
                                <h4 class="text-center">Od</h4>
                                <h4 class="text-center">${startDate}</h4>
                            </div>
                            <div class="col">
                                <h4 class="text-center">Do</h4>
                                <h4 class="text-center">${endDate}</h4>
                            </div>
                            <form:hidden path="tenant" value="${user.id}"/>
                        </div>
                        <div class="row justify-content-md-center">
                            <div class="col col-md-3">
                                <input type="submit" value="Potwierdź Rezerwacje"/>
                            </div>
                        </div>
                    </form:form>
                    <c:if test="${apartment!=null}">
                        <div class="row justify-content-md-center mb-4">
                            <div class="col-md-auto mb-6">
                                <h2 class="text-center"><u>${apartment.name}</u></h2>
                            </div>
                        </div>
                        <div class="row justify-content-md-center">
                            <div class="col ">
                                <h4 class="text-center"><u>Dane Adresowe</u></h4>

                            </div>
                            <c:if test="${apartment.rentWay.id!=3}">
                                <div class="col">
                                    <h4 class="text-center"><u>Cena</u></h4>
                                </div>
                            </c:if>
                            <div class="col">
                                <h4 class="text-center"><u>Szczegóły obiektu</u></h4>
                            </div>
                        </div>
                        <div class="row justify-content-md-start mb-4">
                            <div class="col">
                                <h6 class="text-center"> Kraj: <strong>${apartment.country.name}</strong></h6>
                                <h6 class="text-center"> Miasto: <strong>${apartment.city}</strong></h6>
                                <h6 class="text-center"> Adres: <strong>${apartment.address}</strong></h6>
                            </div>
                            <c:if test="${apartment.rentWay.id!=3}">
                                <div class="col">
                                    <c:if test="${apartment.rentWay.id==1}">
                                    <h6 class="text-center">
                                            <spring:message code="DayPrice"/>
                                        </c:if>
                                        <c:if test="${apartment.rentWay.id==2}">
                                        <h6 class="text-center">Cena za miesiąc:
                                            </c:if>
                                            <strong> ${apartment.apartmentPrice} </strong></h6>
                                </div>
                            </c:if>
                            <div class="col">
                                <h6 class="text-center"><spring:message code="RoomAmount"/>
                                    <strong>${apartment.rooms.size()}</strong></h6>
                                <c:if test="${apartment.rentWay.id!=3}">
                                    <h6 class="text-center"> Liczba łazienek: <strong>${apartment.toilets}</strong></h6>
                                    <h6 class="text-center">Powierzchnia apartamentu: <strong>${apartment.area}</strong>
                                    </h6>
                                </c:if>
                            </div>
                        </div>

                        <c:if test="${apartment.rentWay.id!=3}">
                            <div class="row justify-content-md-center mb-4 ">
                                <div class="col-md-auto">
                                    <h4><u>Szczegóły pokojów</u></h4>
                                </div>
                            </div>

                            <div class="row row-cols-3 justify-content-md-start">
                                <c:forEach items="${apartment.rooms}" var="room">
                                    <div class="col">
                                        <h4><u>${room.roomName.name}
                                            <c:if test="${room.persons!=0 && room.persons!=null}"> ${room.persons}-os</c:if> </u>
                                        </h4>
                                        <c:if test="${apartment.rentWay.id==3}">
                                            <h6>Cena pokoju za dobe: ${room.roomPrice}</h6>
                                        </c:if>
                                        <c:if test="${room.singleBed!=null && room.singleBed!=0}">
                                            <h6>Liczba łóżek pojedyńczych: ${room.singleBed}</h6>
                                        </c:if>
                                        <c:if test="${room.doubleBed!=null && room.doubleBed!=0}">
                                            <h6>Liczba łóżek podwójnych: ${room.doubleBed}</h6>
                                        </c:if>
                                        <c:if test="${room.coach!=null && room.coach!=0}">
                                            <h6>Liczba rozkładanych kanap: ${room.coach}</h6>
                                        </c:if>

                                        <c:if test="${room.interconnecting==true||room.isBalcony==true||room.withKitchenAnex==true || room.withOwnBathroom== true}">
                                            <h5>Dodatkowe informacje</h5>
                                            <ul>
                                                <c:if test="${room.interconnecting==true}">
                                                    <li><h6><em>Pokój jest przechodni</em></h6></li>
                                                </c:if>
                                                <c:if test="${room.isBalcony==true}">
                                                    <li><h6><em>Pokój posiada balkon</em></h6></li>
                                                </c:if>
                                                <c:if test="${room.withOwnBathroom==true}">
                                                    <li><h6><em>Pokój posiada własną łazienkę</em></h6></li>
                                                </c:if>
                                                <c:if test="${room.withKitchenAnex==true}">
                                                    <li><h6><em>Pokój z anksem kuchennym</em></h6></li>
                                                </c:if>
                                            </ul>

                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${room!=null}">
                        <div class="row justify-content-md-center mb-4">
                            <div class="col-md-auto mb-6">
                                <h2 class="text-center"><u>${room.roomName.name} w ${room.apartment.name}</u></h2>
                            </div>
                        </div>
                        <div class="row justify-content-md-start">
                            <div class="col col-md-4">
                                <h4 class="text-center"><u>${room.roomName.name}
                                    <c:if test="${room.persons!=0 && room.persons!=null}"> ${room.persons}-os</c:if> </u>
                                </h4>
                                <c:if test="${room.apartment.rentWay.id==3}">
                                    <h6 class="text-center">Cena pokoju za dobe: ${room.roomPrice}</h6>
                                </c:if>
                                <c:if test="${room.singleBed!=null && room.singleBed!=0}">
                                    <h6 class="text-center">Liczba łóżek pojedyńczych: ${room.singleBed}</h6>
                                </c:if>
                                <c:if test="${room.doubleBed!=null && room.doubleBed!=0}">
                                    <h6 class="text-center">Liczba łóżek podwójnych: ${room.singleBed}</h6>
                                </c:if>
                                <c:if test="${room.coach!=null && room.coach!=0}">
                                    <h6 class="text-center">Liczba rozkładanych kanap: ${room.coach}</h6>
                                </c:if>
                            </div>
                            <c:if test="${room.apartment.rentWay.id==3}">
                                <div class="col col-md-4">
                                    <h4 class="text-center"><u>Dane adresowe</u></h4>
                                    <h6 class="text-center">Kraj: ${room.apartment.country.name}</h6>
                                    <h6 class="text-center">Miasto: ${room.apartment.city}</h6>
                                    <h6 class="text-center">Kraj: ${room.apartment.address}</h6>
                                </div>
                            </c:if>
                            <div class="col col-md-4">
                                <c:if test="${room.interconnecting==true||room.isBalcony==true||room.withKitchenAnex==true || room.withOwnBathroom== true}">
                                    <h4 class="text-center"><u>Dodatkowe informacje</u></h4>
                                    <ul class="text-center">
                                        <c:if test="${room.interconnecting==true}">
                                            <li class="text-center"><h6><em>Pokój jest przechodni</em></h6></li>
                                        </c:if>
                                        <c:if test="${room.isBalcony==true}">
                                            <li><h6><em>Pokój posiada balkon</em></h6></li>
                                        </c:if>
                                        <c:if test="${room.withOwnBathroom==true}">
                                            <li><h6><em>Pokój posiada własną łazienkę</em></h6></li>
                                        </c:if>
                                        <c:if test="${room.withKitchenAnex==true}">
                                            <li><h6><em>Pokój z anksem kuchennym</em></h6></li>
                                        </c:if>
                                    </ul>
                                </c:if>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </section>
</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>



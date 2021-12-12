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
        <div class="container room">
            <form:form id="formRoom" modelAttribute="room" enctype="multipart/form-data" method="post">
                <form:hidden path="apartment" value="${apartment.id}"/>
                <div class="container" id="main">
                    <div class="row justify-content-md-center ">
                        <div class="col-md-4">
                            <h2 class="text-center"><u>Pokój ${apartment.rooms.size()+1}</u></h2>
                        </div>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="col-md-4">
                            <h4 class="text-center"><u>Nazwa pokoju</u></h4>
                            <form:select class="form-control md4" items="${roomName}" path="roomName" itemLabel="name"
                                         itemValue="id"/>
                        </div>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="col-sm-4 mb-4 mt-4">
                            <h4 class="text-center"><u>Mieszkalne Dane Pokoju</u></h4>
                            <c:if test="${room.apartment.rentWay.id!=2}">
                                <h6 class="text-center"><strong><spring:message code="HowMayPersonInRoom"/></strong>
                                </h6>
                                <form:input type="number" path="persons" class="form-control"
                                            required="required"/>
                            </c:if>
                            <h6 class="text-center"><strong><spring:message code="SingleBed"/></strong></h6>
                            <form:input type="number" path="singleBed" class="form-control"/>
                            <h6 class="text-center"><strong><spring:message code="doubleBed"/></strong></h6>
                            <form:input type="number" path="doubleBed" class="form-control"/>
                            <h6 class="text-center"><strong><spring:message code="doubleBed"/></strong></h6>
                            <form:input type="number" path="coach" class="form-control"/>
                        </div>
                        <c:if test="${room.apartment.rentWay.id==3}">
                            <div class="col-sm-4 mt-4">
                                <h4 class="text-center"><u>Cena</u></h4>
                                <h6 class="text-center"><strong>Podaj cene za doboę w pokoju</strong></h6>
                                <form:input type="number" path="roomPrice" class="form-control"/>
                            </div>
                        </c:if>
                        <div class="col-sm-4 ml-8 mb-4 mt-4">
                            <h4 class="text-center"><u>Cechy pokoju</u></h4>
                            <div class="form-check form-switch">
                                <form:checkbox path="withOwnBathroom" class="form-check-input" role="switch"
                                               id="flexSwitchCheckDefault"/>
                                <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój posiada
                                    własną łazienkę?</label>
                            </div>
                            <div class="form-check form-switch">
                                <form:checkbox path="withKitchenAnex" class="form-check-input" role="switch"
                                               id="flexSwitchCheckDefault"/>
                                <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój jest
                                    połączony z kuchnią?</label>
                            </div>
                            <div class="form-check form-switch">
                                <form:checkbox path="interconnecting" class="form-check-input" role="switch"
                                               id="flexSwitchCheckDefault"/>
                                <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój jest
                                    przechodni?</label>
                            </div>
                            <div class="form-check form-switch">
                                <form:checkbox path="isBalcony" class="form-check-input" role="switch"
                                               id="flexSwitchCheckDefault"/>
                                <label class="form-check-label" for="flexSwitchCheckDefault">Czy pokój ma
                                    balkon?</label>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${apartment.rentWay.id==3}">
                            <div class="row justify-content-md-center">
                                <div class="col-sm-4 mb-4 mt-4">
                                    <label for="formFileMultiple" class="form-label text-center"><spring:message
                                            code="UpladPicture"/>
                                        <input class="form-control" type="file" id="formFileMultiple" multiple
                                               name="pictures"
                                               accept="image/png, image/jpeg"/>
                                    </label>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row justify-content-md-center hide-form">
                                <div class="col-sm-4 mb-4 mt-4">
                                    <label for="formFileMultiple" class="form-label text-center"><spring:message
                                            code="UpladPicture"/>
                                        <input class="form-control" type="file" id="formFileMultiple" multiple
                                               name="pictures"
                                               accept="image/png, image/jpeg"/>
                                    </label>
                                </div>
                            </div>

                        </c:otherwise>
                    </c:choose>
                    <div class="row justify-content-md-center" id="outputDivCol">
                            <%--                    tutaj dodaja sie obrazki--%>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="col-sm-1 mb-4 mt-4">
                            <button id="formSubmited" type="submit" class="btn btn-info align-content-center">Zapisz
                            </button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
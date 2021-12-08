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

                <c:forEach items="${apartments}" var="apartment">

<%--                    --%>
                    <div class="card mb-3" style="max-width: 720px;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="<c:url value="../../../img/LetsCheckIn_pictures/${apartment.photos.get(0).id}"/>" class="img-fluid rounded-start" alt="...">
                            </div>
                            <div class="col-md-4">
                                <div class="card-body">
                                    <h5 class="card-title">${apartment.name}</h5>
                                    <p class="card-text"><spring:message code="DayPrice"/> ${apartment.apartmentPrice} <br> <spring:message code="RoomAmount"/> ${apartment.rooms.size()}</p>
                                    <p class="card-text"></p>
                                </div>
                            </div>
                            <div class="col-md-4 row align-items-center">
                                    <a href="/apartment/details/${apartment.id}" class="btn btn-info"><spring:message code="Details"/></a>
                            </div>
                        </div>
                    </div>

                    <%--</a>--%>
                </c:forEach>

        </div>
    </section>

</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
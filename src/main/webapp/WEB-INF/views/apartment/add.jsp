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
            <h3>
                <spring:message code="ApartemntAdding"/>
            </h3>
            <form:form method="post" enctype="multipart/form-data" modelAttribute="apartment">
                <div class="mb-3">
                    <label class="col-sm-2 col-form-label"><spring:message code="Cuntry.select"/>
                        <form:select path="country" items="${country}" itemLabel="country" itemValue="id" />
                    </label>
                </div>
                <div class="mb-3">
                    <label> <spring:message code="City.type"/>
                        <form:input type="text" path="city" class="form-control"/>
                    </label>

                </div>

                <div class="mb-3">
                    <label> <spring:message code="ApartmentAdress"/>
                        <form:input type="text" path="address" class="form-control"/>
                    </label>
                </div>
                <div class="mb-3">
                    <label> <spring:message code="ObjectName"/>
                        <form:input type="text" path="name" class="form-control"/>
                    </label>
                </div>
                <div class="mb-3">
                    <label> <spring:message code="DayPrice"/>
                        <form:input type="number" path="apartmentPrice" class="form-control"/>
                    </label>
                </div>
                <%--            <div class="mb-3">--%>
                <%--                <label> <spring:message code="RoomAmount"/>--%>
                <%--                    <form:input type="number" path="roomAmount" class="form-control"/>--%>
                <%--                </label>--%>
                <%--            </div>--%>
                <div class="mb-3">
                    <label> Powierzchnia
                        <form:input path="area" class="form-control"/>
                    </label>
                </div>
                <div class="mb-3">
                    <label> Liczba toalet
                        <form:input path="toilets" class="form-control"/>
                    </label>
                </div>
                <div class="lg-3">
                    <label class="form-label"><spring:message code="ObjectDescription"/>
                        <form:textarea class="form-control" rows="3" path="description"></form:textarea>
                    </label>
                </div>
                <div class="mb-3">
                    <label for="formFileMultiple" class="form-label"><spring:message code="UpladPicture"/>
                        <input class="form-control" type="file" id="formFileMultiple" multiple name="pictures"
                               accept="image/png, image/jpeg"/>
                        <div id="outputDiv" class="container-sm" style="max-width: 300px;">
                            <img id="output" class="img-fluid img-thumbnail"/>
                        </div>
                    </label>
                </div>
                <%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                <input type="submit" value="<spring:message code="Send"/>"/>
            </form:form>
        </div>

    </section>
</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>
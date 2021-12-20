<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="Headers_footers/header.jsp"/>

<!-- ======= Hero Section ======= -->
<section id="hero" class="d-flex flex-column justify-content-center">
    <div class="container">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-8">
                    <h1>Let's Check IN - travell, manage, rent apartments </h1>
                    <h2>Ggdzie dziś chcesz spać? </h2>
                    <form:form method="get" action="/apartment/listSearch" modelAttribute="searchDto">
                        <div class="row">
                            <div class="col">
                                <form:select class="form-select" aria-label=".form-select-sm example"
                                             items="${countries}" itemLabel="name" itemValue="name" path="country"/>
                            </div>
                            <div class="col">
                                <form:input type="text" path="city" name="city" class="form-control"/>
                                <p style="color:red;"><form:errors path="city"/></p>
                            </div>
                            <div class="col">
                                <form:input type="number" path="person" name="person" class="form-control"/>
                                <p style="color:red;"><form:errors path="person"/></p>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label> <p>Podaj date zameldowania</p>
                                    <form:input path="startDate"  type="date" class="form-select" aria-label=".form-select-sm example"
                                                name="startDate" placeholder="Wybierze poczatek"/>
                                </label>
                            </div>

                            <div class="col">
                                <label> Podaj date wymeldowania
                                    <form:input path="endDate" type="date" class="form-select" aria-label=".form-select-sm example"
                                                name="endDate" placeholder="Wybierze koniec"/>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <p class="text-center" style="color:red;"><form:errors/></p>
                        </div>
                        <input type="submit" value="<spring:message code="Send"/>"/>
                    </form:form>

                    <h2>Gdzie dziś chesz żyć? </h2>

                    <form:form method="get" action="/apartment/listLongSearch" modelAttribute="searchLongDto">
                        <div class="row">
                            <div class="col">
                                <form:select class="form-select" aria-label=".form-select-sm example"
                                             items="${countries}" itemLabel="name" itemValue="name" path="country"/>
                            </div>
                            <div class="col">
                                <form:input type="text" path="city" name="city" class="form-control"/>
                                <p style="color:red;"><form:errors path="city"/></p>
                            </div>
                            <div class="col">
                                <form:input type="number" path="area" name="area" class="form-control"/>
                                <p style="color:red;"><form:errors path="area"/></p>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label> Kiedy chcesz się wprowadzić?
                                    <form:input path="startDate"  type="date" class="form-select" aria-label=".form-select-sm example"
                                                name="startDate"/>
                                    <p style="color:red;"><form:errors path="startDate"/></p>
                                </label>
                            </div>

                            <div class="col">
                                <label> Na ile miesięcy? (minimalnie 3)
                                    <form:input path="month" type="number" class="form-select" aria-label=".form-select-sm example" name="month"/>
                                    <p style="color:red;"><form:errors path="month"/></p>
                                </label>
                            </div>
                        </div>
                        <input type="submit" value="<spring:message code="Send"/>"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Hero -->


<jsp:include page="Headers_footers/footer.jsp"/>

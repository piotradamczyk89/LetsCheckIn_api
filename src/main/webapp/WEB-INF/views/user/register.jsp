
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

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

            <div class="card">
                <div class="card-header">
                    Rejestracja
                </div>

                <div class="card-body">
                    <form:form modelAttribute="user" method="post">
                        <div><label> User Name : <form:input type="text" path="userName"/> </label></div>
                        <div><label> Password: <form:input type="password" path="password"/> </label></div>
                        <div><label> Phone: <form:input type="text" path="phone"/> </label></div>
                        <div><label> Email: <form:input type="text" path="email"/> </label></div>
                        <div><input type="submit" value="Sign In"/></div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </div>
            </div>

        </div>

    </section>
</main>
<!-- End #main -->
<jsp:include page="../Headers_footers/footer.jsp"/>"../Headers_footers/footer.jsp"/>
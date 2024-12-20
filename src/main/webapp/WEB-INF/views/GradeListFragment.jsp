<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<div>
    <c:if test="${not empty grades}">
      <ul>
        <c:forEach var="grade" items="${grades}">
          <li>${grade.courseName}: ${grade.grade}</li>
        </c:forEach>
      </ul>
    </c:if>
    <c:if test="${empty grades}">
      <p>No grades available.</p>
    </c:if>
</div>
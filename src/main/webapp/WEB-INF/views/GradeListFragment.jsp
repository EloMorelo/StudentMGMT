<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<div>
  <c:choose>
    <c:when test="${not empty myGrades}">
      <ul>
        <c:forEach var="grades" items="${myGrades}">
          <li>
            <strong>${grades.name}</strong><br>
            Grade: ${grades.grade} Crouse id: ${grades.course_id}<br>
          </li>
        </c:forEach>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No grades.</p>
    </c:otherwise>
  </c:choose>
</div>
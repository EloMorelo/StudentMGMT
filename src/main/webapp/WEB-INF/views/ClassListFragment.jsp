<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<div>
    <c:choose>
        <c:when test="${not empty classes}">
            <ul>
                <c:forEach var="course" items="${classes}">
                    <li>
                        <strong>${course.name}</strong><br>
                        Building: ${course.building}, Room: ${course.room}<br>
                        Time: ${course.startTime} - ${course.endTime} 
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No courses scheduled for this day.</p>
        </c:otherwise>
    </c:choose>
</div>
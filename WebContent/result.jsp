<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section>
	
<h2 align="center"><font><strong>Student Database</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>

<td><b>Student ID</b></td>
<td><b>Student Name</b></td>
		
		<c:forEach var="student" items="${students}">
			<tr>
				<td><c:out value="${student.id}"></c:out></td>
				<td><c:out value="${student.name}"></c:out></td>
			</tr>

		</c:forEach>


	</table>

	
</section>


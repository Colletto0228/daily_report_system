<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import= "constants.ForwardConst" %>

<c:set var= "actRep" value= "${ForwardConst.ACT_REP.getValue()}" />
<c:set var= "commIdx" value= "${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var= "commShow" value= "${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var= "commCer" value= "${ForwardConst.CMD_CERT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>承認済み日報</h2>

            <table id= "report_list">
            <tbody>
                <tr>
                    <th class= "report_name">氏名</th>
                    <th class= "report_date">日付</th>
                    <th class= "report_title">タイトル</th>
                    <th class= "report_action">操作</th>
                </tr>
                <c:forEach var= "report" items= "${reports}" varStatus= "status">
                <c:if test="${report.certFlag == 1}">
                    <fmt:parseDate value= "${report.reportDate}" pattern= "yyyy-MM-dd" var= "reportDay" type= "date" />

                    <tr class= "row${status.count % 2}">
                        <td class= "report_name"><c:out value="${report.employee.name}" /></td>
                        <td class= "report_date"><fmt:formatDate value="${reportDay}" pattern= "yyyy-MM-dd"/></td>
                        <td class= "report_title">${report.title}</td>
                        <td class= "report_action"><a href= "<c:url value= '?action=${actRep}&command=${commShow}&id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                </c:if>
                </c:forEach>
            </tbody>
        </table>


        <p>
            <a href= "<c:url value= '?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>
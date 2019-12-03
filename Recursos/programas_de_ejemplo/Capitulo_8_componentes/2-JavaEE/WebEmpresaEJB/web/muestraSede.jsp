<%@page import="com.miempresa.dao.SedeFacade"%>
<%@page import="com.miempresa.modelo.Sede"%>
<%@page import="com.miempresa.modelo.Departamento"%>

<%@page import="java.util.Iterator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="sede" scope="request" class="com.miempresa.modelo.Sede"/>
<jsp:setProperty name="sede" property="*"/>

<%
  Sede sedeBean = (Sede) request.getAttribute("sede");
  if (sedeBean == null) {
%>
ERROR: no se especific&oacute; sede a mostrar.
<%
} else {
  try {
    SedeFacade daoSede = new SedeFacade();
    sedeBean = daoSede.find(sedeBean.getIdSede());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sede <%=sedeBean.getIdSede()%> - <%=sedeBean.getNomSede()%></title>
    </head>
    <body>
        <p>Sede <%=sedeBean.getIdSede()%> - <%=sedeBean.getNomSede()%></p>
        <table border="1">
            <tr>
                <td colspan="2" align="center">Departamentos</td></tr>
                <%
              if (sedeBean.getDepartamentoSet().isEmpty()) {%>
            <tr><td colspan="2">No existen departamentos en esta sede</td></tr>
            <%
            } else {
              Iterator itDeptos = sedeBean.getDepartamentoSet().iterator();
              while (itDeptos.hasNext()) {
                Departamento unDepto = (Departamento) itDeptos.next();
            %>
            <tr>
                <td><%=unDepto.getIdDepto()%></td>
                <td><%=unDepto.getNomDepto()%></a></td>
            </tr>
            <%
                }
              }
            %>
        </table>
    </body>
</html>
<%
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }
%>

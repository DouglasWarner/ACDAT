<%@page import="java.util.List"%>
<%@page import="com.miempresa.dao.SedeFacade"%>
<%@page import="com.miempresa.modelo.Sede"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empresa</title>
    </head>
    <body>
        <form name="frm_muestra_sede" method="post" action="ServletSede">
            <input type="hidden" name="op" value="muestraSede">
            <input type="hidden" name="idSede">
            <table border="1">
                <tr><td colspan="2" align="center">Sedes [<a href="javascript:void(0)" onclick="javascript:document.frm_muestra_sede.op.value = 'altaSede';document.frm_muestra_sede.submit();">Nueva sede</a>]</td></tr>
                <%
                  try {
                    SedeFacade daoSede = new SedeFacade();
                    List<Sede> listaSedes = daoSede.findAll();

                    for (Sede unaSede : listaSedes) {%>
                <tr>
                    <td><%=unaSede.getIdSede()%></td>
                    <td><a href="javascript:void(0)" onclick="javascript:document.frm_muestra_sede.idSede.value = '<%=unaSede.getIdSede()%>';
                            document.frm_muestra_sede.submit();"><%=unaSede.getNomSede()%></a></td>
                </tr>
                <%
                    }
                  } catch (Exception e) {
                    e.printStackTrace(System.err);
                  }
                %>
            </table>
        </form>
    </body>
</html>

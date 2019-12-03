<%@page import="com.miempresa.modelo.Sede"%>
<%@page import="com.miempresa.dao.GestorSedes"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="sede" scope="request" class="com.miempresa.modelo.Sede"/>
<jsp:setProperty name="sede" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creación de sede</title>
    </head>
    <body>
        <%
          Sede sedeBean = (Sede) request.getAttribute("sede");
          if (sedeBean == null) {
        %>
        ERROR: no se proporcionaron datos de sede para crear.
        <%
        } else {
          try {
            GestorSedes gestorSedes = new GestorSedes();
            gestorSedes.createSede(sedeBean.getNomSede());
        %>
        Creada nueva sede: <%=sede.getNomSede()%><br/>
        <a href="ServletSede">Volver</a>
        <%
            } catch (Exception e) {
              e.printStackTrace(System.err);
            }
          }
        %>
    </body>
</html>

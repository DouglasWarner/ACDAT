<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="sede" scope="request" class="ORM.Sede"/>
<jsp:setProperty name="sede" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de Sede</title>
    </head>
    <body>
        <form method="post" action="controlador">
            <table>
                <tr>
                    <td>
                        <input type="hidden" name="op" value="insertSede"/>
                        <input name="nomSede" required type="text" size="20" maxlength="20"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Crear"/>
                        <input type="reset" name="cancelar" value="Cancelar"/>
                    </td>
                </tr>
            </table>
        </form>
        <a href="controlador">Inicio</a>
    </body>
</html>

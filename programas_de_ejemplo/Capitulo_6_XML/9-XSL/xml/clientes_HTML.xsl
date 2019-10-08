<?xml version="1.0" encoding="UTF-8"?>
<!--
    Plantilla para generar HTML
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Lista de clientes</title>
            </head>
            <body>
                <xsl:apply-templates select="clientes"/>                
            </body>
        </html>
    </xsl:template>        
    
    <xsl:template match="clientes">
        <h1>Clientes</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>DNI</th>
                    <th>Apellidos</th>
                    <th>CP</th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates select="cliente"/>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="cliente">
        <xsl:if test="not(validez/@estado='borrado')">
            <tr>
                <td><xsl:value-of select="@DNI"/></td>
                <xsl:apply-templates/>
            </tr>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="apellidos|CP">
        <td><xsl:apply-templates/></td>
    </xsl:template>

</xsl:stylesheet>

<?xml version="1.0" encoding="UTF-8"?>
<!--
    Plantilla para generar CSV
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="text" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <xsl:variable name='salto_linea'>
        <xsl:text>&#xa;</xsl:text>
    </xsl:variable>
    <xsl:variable name='separador_campos'>
        <xsl:text>|</xsl:text>
    </xsl:variable>
    
    <xsl:template match="/">
        <xsl:apply-templates select="clientes"/>                
    </xsl:template>        
    
    <xsl:template match="clientes">
        <xsl:apply-templates select="cliente"/>
    </xsl:template>
    
    <xsl:template match="cliente">
        <xsl:if test="not(validez/@estado='borrado')">
            <xsl:value-of select="@DNI"/>
            <xsl:apply-templates/>
            <xsl:value-of select="$salto_linea"/>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="apellidos">
        <xsl:value-of select="$separador_campos"/>
        <xsl:value-of select="substring(concat(., '                    '), 1, 20)"/>
    </xsl:template>
    
    <xsl:template match="CP">
        <xsl:value-of select="$separador_campos"/>
        <xsl:value-of select="substring(concat(., '     '), 1, 5)"/>
    </xsl:template>

</xsl:stylesheet>

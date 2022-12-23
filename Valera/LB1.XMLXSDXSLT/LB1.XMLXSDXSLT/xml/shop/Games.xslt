<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tns="http://www.nure.ua/entity/Games/"
	xmlns:substr="http://www.nure.ua/entity/catalogs/"
	xmlns:pr="http://www.nure.ua/entity/Game/"> 
	<xsl:template match="/"> 
	<html>
 		<head>
 		<meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
 		</head>
 		<body>
			 <ul type="disc">
 				<p>
 				<font size="5">Интернет игры</font></p>
 				<p><font size="4">Жанры: </font></p>
 				<ul type="square">
 				<li>Эекшены</li>
 				<li>Приключения</li>
				<li>Симеляторы</li>
 			</ul> <p></p> <p></p>
			<table border="5">
			 <tr>
			 <th>Title</th>
			 <th>Description</th>
			 <th>Category</th>
			 <th>Price</th>
			 </tr>
		 <xsl:for-each select="tns:catalogs/tns:catalog/tns:Games">
		 <xsl:sort select="title"/>
			<tr>
			 <td><xsl:value-of select="pr:title"></xsl:value-of>
			 </td>
			 <td><xsl:value-of select="pr:description"></xsl:value-of>
			 </td>
			 <td><xsl:value-of select="pr:catalog"></xsl:value-of>
			 </td>
			 <td><xsl:value-of select="pr:price"></xsl:value-of>
			 </td>
			</tr>
			</xsl:for-each>
		</table>
			 </ul>
		 </body>
	 </html>
	 </xsl:template>
 </xsl:stylesheet>

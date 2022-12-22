<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ord="http://eatshop.com/order"
                xmlns:tns="http://eatshop.com/orders"
                xmlns:prod="http://eatshop.com/product"
                xmlns:cus="http://eatshop.com/customer"
                version="1.0"
                xmlns:xsd="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" doctype-public="html"/>
    <xsl:template match="/">
        <html>
            <body>
                <h3>Orders Collection</h3>
                <table border="2">
                    <tr bgcolor="red">
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Customer Email</th>
                        <th>Count</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Dish weight</th>
                        <th>Category</th>
                    </tr>
                    <xsl:for-each select="tns:orders/tns:order">
                        <tr>
                            <td>
                                <xsd:value-of select="@id" />
                            </td>
                            <td>
                                <xsd:value-of select="ord:order_time"/>
                            </td>
                            <td>
                                <xsd:value-of select="ord:customer/cus:email"/>
                            </td>
                            <xsl:for-each select="ord:order_item">
                                <td>
                                    <xsd:value-of select="ord:count"/>
                                </td>
                                <td>
                                    <xsd:value-of select="ord:product/prod:name"/>
                                </td>
                                <td>
                                    <xsd:value-of select="ord:product/prod:price"/>
                                </td>
                                <td>
                                    <xsd:value-of select="ord:product/prod:dish_weight"/>
                                </td>
                                <td>
                                    <xsd:value-of select="ord:product/prod:category"/>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
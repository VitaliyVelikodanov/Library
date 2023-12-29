<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://book.nure.ua/entity">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Book List</h2>
                <ul>
                    <xsl:apply-templates select="//b:Book"/>
                </ul>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="b:Book">
        <li>
            <h3>Book ID: <xsl:value-of select="@Id"/></h3>
            <p>Title: <xsl:value-of select="b:Title"/></p>
            <p>Body: <xsl:value-of select="b:Body"/></p>

            <h4>Author Information</h4>
            <p>Author ID: <xsl:value-of select="b:Author/@Id"/></p>
            <p>Name: <xsl:value-of select="b:Author/b:Name"/></p>
            <p>Place of Birth: <xsl:value-of select="b:Author/b:PlaceOfBirth"/></p>
            <p>Biography: <xsl:value-of select="b:Author/b:Biography"/></p>
            <p>Major Works: <xsl:value-of select="b:Author/b:MajorWorks"/></p>

            <h4>Genre Information</h4>
            <p>Genre ID: <xsl:value-of select="b:Genre/@Id"/></p>
            <p>Genre Name: <xsl:value-of select="b:Genre/b:GenreName"/></p>
            <p>Book ID: <xsl:value-of select="b:Genre/b:BookId"/></p>
        </li>
    </xsl:template>

</xsl:stylesheet>

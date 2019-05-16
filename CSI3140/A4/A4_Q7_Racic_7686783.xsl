<?xml version = "1.0"?>

<!-- Aethelind Racic -->
<!-- 7686783 -->
	<!-- Assignment Four -->
	<!-- Question Seven -->
	
<xsl:stylesheet version = "1.0" 
   xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

   <xsl:output method = "html" doctype-system = "about:legacy-compat" />
   <xsl:template match = "/"> <!-- match root element -->

   <html>
      <head>
         <meta charset = "utf-8"/>
         <title>Question Seven</title>
      </head>
      <body>
         <table>
	    <caption>Nutritional Information</caption>
            <thead>
               <tr>
                  <th>Nutrition</th>
                  <th>Amount</th>
               </tr>
            </thead>

            <!-- insert each name and paragraph element value -->
            <!-- into a table row. -->
            <xsl:for-each select = "/information">
               <tr>  
                  <td><xsl:value-of select = "name"/></td>     
                  <td><xsl:value-of select = "paragraph"/></td>
               </tr>
            </xsl:for-each>
         </table>
      </body>
   </html>

   </xsl:template>
</xsl:stylesheet>
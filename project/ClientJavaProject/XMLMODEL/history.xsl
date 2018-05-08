<?xml version="1.0"?>

<!--
    Document   : history.xsl
    Created on : January 19, 2018, 8:57 PM
    Author     : Nourhan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:variable name="owner" select="/history/@owner"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/*[1]">
        <html>

            <head>
               
                <title><xsl:value-of select="$owner"/></title>
                <style type="text/css">
                    @import url(http://fonts.googleapis.com/css?family=Lato:100,300,400,700);
                    @import url(http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css);
                    .container {
                width: 516px;
                height: 680px;
                background-color: #eac9bf;
                box-sizing: border-box;
                display: inline-block;
        }


        #chat-header {
                width: 100%;
                height: 49px;
                background-color: #ca9f93;
                display: table;
                box-sizing: border-box;
                margin: 0;
                vertical-align: middle;
        }

        .left-items {
                width: 70%;
                display: table-cell;
                vertical-align: middle;
        }

        .left-items img {
                width: 36px;
                height: 36px;
                border-radius: 50%;
                margin-top: 0px;
                margin-left: 9px;
        }

        .left-items p {
                font-size: 24px;
                font-family: 'Segoe UI';
                margin-top: 0;
                display: inline-block;
                margin-left: 5px;
                color: #1d1a28;
                margin-bottom: 0;
                vertical-align: middle;
        }


        .right-items {
                display: table-cell;
                vertical-align: middle;
        }

        .right-items img{
                margin-right: 9px;
        }

        .fix-width {
                width: 15%; 
                display: inline-block;
        }

        .left-text {
                margin-left: 15px;
                padding-top: 15px;
                clear: both;
        }

        .left-text h3 {
                display: inline-block;
                font-family: 'Segoe UI';
                font-size: 15px;
                color: #1d1a28;
        }



        #chat-bubble-left {
                display: inline-block;

        }

        #chat-bubble-left p {
                display: inline-block;
                background-color: #e6dcd8;
                border-radius: 15px;
                padding:10px;
                font-family: 'Segoe UI';
                font-size: 15px;
                color: #1d1a28;
                width: 80%;
        }


        .right-text {
                box-sizing: border-box;
        }

        .right-text h3{
                display: inline-block;
                font-family: 'Segoe UI';
                font-size: 15px;
                color: #1d1a28;
                margin-right: 15px;
                margin-left: 10px;
        }

        #chat-bubble-right {
                display: inline-block;
                width: 80%;
                margin: 0;
                font-size: 0;
                text-align: right;
        }

        #chat-bubble-right p{
                text-align: left;
                display: inline-block;
                background-color: #ca9f93;
                border-radius: 15px;
                padding:10px;
                font-family: 'Segoe UI';
                font-size: 15px;
                color: #ffffff;
                margin-left: 70px;
        }
                    
 .center {
         display: table-cell;
         vertical-align: middle;
 }

 .center img{
         margin-left: 9px;
         vertical-align: middle;
 }

 .center input {
         width: 430px;
         height: 26px;
         border-radius: 15px;
 }
</style>
</head>
<body>
	<div class="container">
            <xsl:for-each select="message">
            <xsl:choose>
            <xsl:when test="from = $owner">
	    <div class="left-text">
	    <div class="fix-width">
           <h3><xsl:value-of select="message/from"/></h3>
	    </div>
	   <div id="chat-bubble-left" style="width: 80%;">
                <p style="
                       color:{color};
                       font-size: {size}px;
                      ;">
                      <xsl:value-of select="body"/>
                 </p>
		</div>

	   </div>
            </xsl:when>
            <xsl:otherwise>
		<div class="right-text">
			<div id="chat-bubble-right">
				<p style="
                        color:{color};
                       font-size: {size}px;">
                      <xsl:value-of select="body"/></p>
			</div>
			<h3><xsl:value-of select="message/from"/></h3>
		</div>
		</xsl:otherwise>
		</xsl:choose>
		</xsl:for-each>
		</div>
		
</body>
</html>
  </xsl:template>

</xsl:stylesheet>
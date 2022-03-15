<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<head>
	    <meta charset="utf-8">
		<meta name="viewport" content="width=1100">
	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	    <!--<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">-->
	    <base href="../" />
	    <title>일자리 내비게이터 관리자페이지</title>
	    <!-- include common vendor stylesheets & fontawesome -->    
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet" /> 
	    <link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/bootstrap.css'/>" />    
	    <link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/ace.css'/>" />
	    <!-- favicon -->
	    <link rel="icon" type="image/png" href="<c:url value='/assets/img/favicon.ico'/>" />    
		<!--추가-->
		<link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/font.css'/>" />
		<link rel="stylesheet" type="text/css" href="/assets/css/style_anchorData.css" />
		<script src="/assets/js/jquery-1.12.3.min.js"></script>
  </head>
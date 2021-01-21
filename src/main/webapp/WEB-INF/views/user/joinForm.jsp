<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>Stacked form</h2>
	<!-- form action은 옛날 방식 -->
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" id="username" placeholder="Enter Username" name="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
		</div>
	<button id="btn-save" class="btn btn-primary">회원가입 완료</button>
</div>

<script src = "/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
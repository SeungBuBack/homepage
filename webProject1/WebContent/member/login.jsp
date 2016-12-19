<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>  
<%-- <%@ include file="../header.jsp" %> --%>  
<%@ include file="sub_img.jsp"%> 
<%@ include file="sub_menu.jsp" %>  

     
  <article>
    <h1>Login</h1>
    <form method="post" action="login.do">
        <fieldset>
        <legend></legend>
          <label>User ID</label>
          <input name="id" type="text" value="${id}" ><br> 
          <label>Password</label> 
          <input name="pwd" type="password"><br> 
        </fieldset>
        <div class="clear"></div>
        <div id="buttons">
            <input type="submit" value="로그인" class="submit">
            <input type="button" value="회원가입" class="cancel"
                 onclick="location='joinForm.do'">
            <input type="button" value="아이디 비밀번호 찾기" class="submit"
                 onclick="location='findIdForm.do'">     
        </div>
    </form>  
  </article>
<%-- <%@ include file="../footer.jsp" %> --%>      

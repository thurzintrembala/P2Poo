<%-- 
    Document   : index
    Created on : 22 de nov de 2021, 21:03:40
    Author     : arthu
--%>
<%@page import="db.TasksConnector"%>
<%@page import="java.util.ArrayList"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    Exception requestException = null;
    ArrayList<String> taskList = new ArrayList<>();
    ArrayList<Integer> idList = new ArrayList<>();
    try {
        if(request.getParameter("add")!=null){
            String title = request.getParameter("taskName");
            String user = String.valueOf(session.getAttribute("login"));
            TasksConnector.addTask(title, user);
            response.sendRedirect(request.getRequestURI());
            
        }
         if(request.getParameter("remove")!=null){
            Integer id = Integer.valueOf(request.getParameter("id"));
            TasksConnector.removeTask(id);
            response.sendRedirect(request.getRequestURI());            
        }
         taskList = TasksConnector.getTasks();
    }
    catch(Exception ex){
        requestException = ex;
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P2Poo</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>

        <%if(sessionName==null) {%>
        <div style="color:red"> Por favor, faça login para cadastrar/ver tarefas! </div>
        <%}else{%>
        
        <form>
            <input type="text" name="taskName"/>
            <input type="submit" name="add" value="+"/>
        </form>
        
         <br>
            <table class="table table-hover">
            <% for (String title : taskList) { %>
            <tr>
                <td><%= title %></td>
                <td>
                 <form>
                    <input type="hidden" name="taskName" value="<%= title %>"/>
                    <input type="submit" name="remove" value="-"/>
                </form>                
                </td>
            </tr>
            
            <%}%>
            
        </table> 
        <%}%>
    </body>
</html>

package servlets;

import models.UserBean;
import models.MySQLConnector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

@WebServlet(name="showStudentCourses", urlPatterns = "/showStudentCourses")
public class ShowStudentCourses extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(!req.getParameter("ID").isEmpty()){
            try {
                showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allCoursesForStudent", req.getParameter("FNamn"), req.getParameter("LNamn"), req.getParameter("ID")));
            }catch (NumberFormatException e){
            }
       }else {
            showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allCoursesForStudentNoID", req.getParameter("FNamn"), req.getParameter("LNamn")));
        }

        showForm(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String firstName = req.getParameter("FNamn")==null?"":req.getParameter("FNamn");
        String lastName = req.getParameter("LNamn")==null?"":req.getParameter("LNamn");
        String id = req.getParameter("ID")==null?"":req.getParameter("ID");

    }

    private void showDataTable(HttpServletRequest req, HttpServletResponse resp,LinkedList<String[]> data) throws ServletException, IOException {


        String top = "<head><title>Hello " + req.getParameter("name") +  "</title>"
                + "</head>"
                + "<body>"
                + "<table>";

        String bottom = "</table>"
                + "<button  id=reset onclick=location.href='/showStudents'>RESET</button></div>"
                + "</body>"
                + "</html>";

        resp.setContentType("text/HTML");
        PrintWriter out = resp.getWriter();
        out.println(top);

        data.forEach(row -> {
            out.println("<tr>");
            Arrays.stream(row).forEach(dataPoint -> {
                out.println("<td>" + dataPoint + "</td>");
            });
            out.println("</tr>");
        });

        out.println(bottom);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        LinkedList data = MySQLConnector.getConnector().selectQuery("allCoursesForStudentNoID");
        UserBean usersBean = new UserBean();
        usersBean.setData(data);

        req.getSession().setAttribute("UserBean", usersBean);
        System.out.println(((UserBean) (req.getSession().getAttribute("UserBean"))).getData());
        req.getRequestDispatcher("showStudentCourses.jsp").forward(req, resp);
    }

}
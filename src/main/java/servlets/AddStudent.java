package servlets;

import models.MySQLConnector;
import models.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

@WebServlet(name="addStudent", urlPatterns = "/addStudent")
public class AddStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        LinkedList data = MySQLConnector.getConnector().selectQuery("allFromStudents");
        UserBean usersBean = new UserBean();
        usersBean.setData(data);

        req.getSession().setAttribute("UserBean", usersBean);
        System.out.println(((UserBean)(req.getSession().getAttribute("UserBean"))).getData());
        req.getRequestDispatcher("showStudents.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MySQLConnector.getConnector().insertQuery("addStudentToStudents", req.getParameter("FNamn"), req.getParameter("LNamn"), req.getParameter("city"), req.getParameter("hobby"),"S","S","S","S");
        showDataTable(req, resp, MySQLConnector.getConnector().selectQuery("allFromStudents"));
        showForm(req, resp);
    }

    private void showDataTable(HttpServletRequest req, HttpServletResponse resp, LinkedList<String[]> data) throws ServletException, IOException {

        String top = "<head><title>Hello " + req.getParameter("name") + "</title>"
                + "</head>"
                + "<body>"
                + "<table>";

        String bottom = "</table>"
                + "</body>"
                + "</html>";

        resp.setContentType("text/HTML");
        PrintWriter out = resp.getWriter();
        out.println(top);

        data.forEach(row -> {
            out.println("<tr>");
            Arrays.stream(row).forEach(dataPunkt -> {
                out.println("<td>" + dataPunkt + "</td>");
            });
            out.println("</tr>");
        });

        out.println(bottom);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(
                "<br>"
                        + "<div style='border:black solid; width:200px; padding:5px display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px;'>"
                        + "<form style='margin:5px;' action=/addStudent method=POST>"
                        + "            <label for=fname>First Name:</label>"
                        + "            <input type=text id=fname name=fname required><br><br>"
                        + "             <label for=fname>First Name:</label>"
                        + "            <input type=text id=lname name=lname required><br><br>"
                        + "             <label for=town>Town:</label>"
                        + "            <input type=text id=town name=town><br><br>"
                        + "             <label for=hobby>Hobby:</label>"
                        + "            <input type=text id=hobby name=hobby><br><br>"
                        + "            <input type=submit value=Submit>"
                        + "        </form>"
                        + "</div>"
                        + "<br>"
        );
    }

}

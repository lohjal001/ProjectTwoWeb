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
import java.util.LinkedList;


@WebServlet(name="addCourse", urlPatterns = "/addCourse")
public class AddCourse extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedList data = MySQLConnector.getConnector().selectQuery("allFromCourses");
        UserBean usersBean = new UserBean();
        usersBean.setData(data);

        PrintWriter out = resp.getWriter();

        req.getSession().setAttribute("UserBean", usersBean);
        System.out.println(((UserBean)(req.getSession().getAttribute("UserBean"))).getData());
        req.getRequestDispatcher("addCourse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //hämta data från addCourse
        String Namn = req.getParameter("Namn");
        String YHP = req.getParameter("YHP");
        String description = req.getParameter("description");

            try {
                LinkedList courseAdd = MySQLConnector.getConnector().selectQuery("addCourseToCourses", req.getParameter("Namn"), req.getParameter("YHP"), req.getParameter("beskrivning"));
            }catch (Exception e){
                System.out.println("ded");
            }

    }

}

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Start Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-top: 20px;
        }
        table {
            width: 70%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 5px;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        form {
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        input[type="text"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        a {
            display: block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<nav>
    <a href="showStudents">Show Students</a>
    <a href="showAttendance">Show Attendance</a>
    <a href="showCourses">Show Courses</a>
    <a href="addCourse">Add Course</a>
    <a href="addStudent">Add Student</a>
</nav>
<h1>Check Student Courses</h1>
<table>
    <c:forEach items="${UserBean.data }" var="dataPunkt">
        <tr>
            <td>${dataPunkt[1]}</td>
            <td>${dataPunkt[2]}</td>
            <td>${dataPunkt[3]}</td>
            <td>${dataPunkt[4]}</td>
        </tr>
    </c:forEach>
</table>
<form action="/showStudentCourses" method="POST">
    <label for="FNamn">First Name:</label><br>
    <input type="text" id="FNamn" name="FNamn"><br>
    <label for="LNamn">Last Name:</label><br>
    <input type="text" id="LNamn" name="LNamn"><br>
    <label for="id">ID:</label><br>
    <input type="text" id="id" name="ID"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>

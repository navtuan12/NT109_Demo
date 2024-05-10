<!DOCTYPE html>
<html lang="en">
<head>
<title>Employee management</title>

<link href="/bootstrap/4.0.0-beta/css/bootstrap.min.css"
    rel="stylesheet">
<style>
.container {
    margin-top: 80px;
}
.bg-dark {
    background-color: #3b8dbd !important;
}
</style>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
    <div class="container">

        <form class="form-inline" method="post" action="/add">
            <input
                type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
                id="employeeId" name="id" placeholder="ID" />
            <input
                type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="employeeName"
                placeholder="Name" name="name" /> 
            <input
                type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="employeeAge"
                placeholder="Age" name="age" /> 
            <input
                type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="employeeSalary"
                placeholder="Salary" name="salary" /> 
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
        <br/>
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Employee name</th>
                    <th>Age</th>
                    <th>Salary</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <#list model["employees"] as employee>
                <tr>
                    <th scope="row">${employee.id}</th>
                    <td>${employee.name}</td>
                    <td>${employee.age}</td>
                    <td>${employee.salary}</td>
                    <td><a class="btn btn-sm btn-warning" role="button"
                        href="/delete/${employee.id}">Delete</a></td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</body>
</html>
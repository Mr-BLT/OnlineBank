<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Регистрация</title>

  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
  <link rel="stylesheet" href="main.css">
</head>
<body>
<div class="container">
  <div class="row">

    <div class="col-md-offset-3 col-md-6">

      <form class="form-horizontal" action="/Up" method="post">
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
        <div class="form-group">
          <input type="text" class="form-control" name="name" placeholder="Имя" required>
          <i class="fa fa-user"></i>
        </div>
        <div class="form-group">
          <input type="text" class="form-control" name="lastname" placeholder="Фамилия" required>
          <i class="fa fa-user"></i>
        </div>
        <div class="form-group help">
          <input type="tel" pattern="[0-9]{11}" class="form-control" name="number"
                 placeholder="Номер телефона" required>
          <i class="fa fa-user"></i>
        </div>
        <div class="form-group help">
          <input type="password" pattern="[A-Za-z0-9]{5,}" class="form-control" name="password"
                 placeholder="Пароль. Не меньше 5 символов " required>
          <i class="fa fa-lock"></i>
          <a href="/In" class="fa fa-question-circle"></a>
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-default">Зарегистрироваться</button>
        </div>
      </form>
    </div>

  </div>
</div>

</body>
</html>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Пользователи</title>

  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
        integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
        crossorigin="anonymous">
  <link rel="stylesheet" href="main.css">
  <script
      src="https://code.jquery.com/jquery-2.2.4.js"
      integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
      crossorigin="anonymous"></script>
</head>
<body>

<div class="card">
  <ul class="list-group list-group-flush">
    <div class="list-group-item">

        <#list users as user>

          <h1>
            <div class="about">${user.name}</div>
            <div class="about">${user.lastname}</div>
            <div class="about">${user.number}</div>

            <div class="form-check about">
              <input class="form-check-input" type="checkbox"
                     id="btn1${user.id}" onclick="fof(${user.id})">
              <label class="form-check-label" for="btn1${user.id}" id="active${user.id}">
                  <#if user.isActive()>
                    Активный
                  <#else >
                    Забанен
                  </#if>
              </label>
            </div>
          </h1>

        </#list>

    </div>
  </ul>
</div>

<div class="container">
  <div class="row">
    <div class="col-sm"></div>
    <div class="col-sm"></div>
    <div class="col-sm">
      <button type="button" class="btn btn-danger"
              onclick="window.location.href='/profile'">
        Выйти
      </button>
    </div>
  </div>
</div>


</body>
</html>
<script type="text/javascript">

  function fof(id) {
    var ac = "active" + id;
    var t = document.getElementById(ac).innerText;
    if (t === "Активный") {
      ban(id, "#" + ac);
    } else {
      unban(id, "#" + ac);
    }
  }

  function ban(id, nameblock) {
    var userData = {
      'id': id
    }
    $.ajax({
      url: 'http://localhost:8080/admin/ban',
      method: 'POST',
      data: userData,
      type: 'POST',
      dataType: 'html',
      success: function () {
        $(nameblock).text("Забанен");
      }
    });
  }

  function unban(id, nameblcok) {

    var userData = {
      'id': id
    }

    $.ajax({
      url: 'http://localhost:8080/admin/unban',
      method: 'POST',
      data: userData,
      type: 'POST',
      dataType: 'html',
      success: function () {
        $(nameblcok).text("Активный");
      }
    });
  }

</script>

<style>
  .about {
    display: inline-block;
  }
</style>

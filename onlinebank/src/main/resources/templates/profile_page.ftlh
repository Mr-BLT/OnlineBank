<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Профиль</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
        integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
        crossorigin="anonymous">
</head>
<body style="background-color: cadetblue">


<div class="container">
  <div class="row align-items-center">
    <div class="col"></div>

    <div class="col align-self-center">
      <div class="card-body">

        <div class="card">
          <div class="card-body">
              <#if !user.isAdmin()>
                <h5 class="card-title">Карта</h5>
                <p class="card-text text-center">
                    ${accountbank.suma} Р.
                </p>
                <p class="text-center" style="color: black">
                  Номер карты: ${accountbank.numberCard}
                </p>

                <p>
                  <button type="button" class="btn btn-danger"
                          onclick="window.location.href='/subscription'">
                    Подписка
                  </button>
                </p>
                <p>
                  <button type="button" class="btn btn-warning"
                          onclick="window.location.href='/stock'">
                    Инвестиции
                  </button>
                </p>
                <p>
                  <button type="button" class="btn btn-info"
                          onclick="window.location.href='/transaction'">
                    Перевод
                  </button>
                </p>
              <#else >
                <p>
                  <button type="button" class="btn btn-danger"
                          onclick="window.location.href='/admin'">
                    Пользователи
                  </button>
                </p>
              </#if>
            <p>
              <button type="button" class="btn btn-dark"
                      onclick="window.location.href='/logout'">Выход
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>


    <div class="col"></div>
  </div>
</div>
</body>
</html>

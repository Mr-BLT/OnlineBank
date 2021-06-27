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
<p>
  <button type="button" class="btn btn-dark"
          onclick="window.location.href='/profile'"
          style="background-color: green">
    Профиль
  </button>
</p>

<div class="container">
  <div class="row align-items-center">
    <div class="col"></div>

    <div class="col align-self-center">
      <div class="card-body">

        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Карта</h5>
            <p class="card-text text-center">
                ${accountbank.suma} Р.
            </p>
            <p class="text-center" style="color: black">
              Номер карты: ${accountbank.numberCard}
            </p>



            <p>
              <button type="button" class="btn btn-danger"
                      onclick="window.location.href='/stock/buy'">
                Покупка
              </button>
            </p>
            <p>
              <button type="button" class="btn btn-warning"
                      onclick="window.location.href='/stock/sell'">
                Продажа
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

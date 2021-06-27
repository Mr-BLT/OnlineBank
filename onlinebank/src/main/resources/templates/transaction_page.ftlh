<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Переводы</title>

  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
          type="text/javascript"></script>

  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
        integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
        crossorigin="anonymous">
</head>
<body style="background-color: cadetblue">

<div id="my_modal" class="modal">
  <div class="modal_content">
    <span class="close_modal_window">×</span>
    <p id="model_text">Уверены ???</p>

    <button type="button" class="btn btn-primary ban"
            onclick="transaction()"
            id="model_event"
            style="background-color: green">

      Да
    </button>
  </div>
</div>

<div class="container">
  <div class="row align-items-center">
    <p>
      <button type="button" class="btn btn-danger"
              onclick="window.location.href='/profile'">
        Профиль
      </button>
    </p>

    <div class="col"></div>

    <div class="col align-self-center">
      <div class="card-body">

        <div class="card">
          <div class="card-body">

            <input type="text" id="sum_user" value="${suma}" hidden>
            <h5 class="card-title text-center" id="card">Карта: ${suma} ₽</h5>
            <p class="card-text text-center">


            <div class="input-group mb-3">
              <input type="number" pattern="[0-9]{11}" class="form-control"
                     placeholder="Номер телефона"
                     aria-label="Имя пользователя" aria-describedby="basic-addon1" id="number"
                     list="list-numbers"
                     required>

            </div>

            <datalist id="list-numbers">

                <#list historyNumbers as number>
              <option value="${number.name}"></#list>

            </datalist>

            <h6 id="correctNumber"></h6>

            <div class="input-group mb-3">
              <input type="number" min="1" class="form-control" placeholder="Сумма"
                     id="sum" required>
              <span class="input-group-text">₽</span>

            </div>
            <h6 id="correctSuma"></h6>


            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
              <button class="btn btn-primary" type="button" style="background-color: green"
                      onclick="MyOnclick()">
                Отправить
              </button>
            </div>


            </p>
            <h6 id="result"></h6>

          </div>
        </div>
      </div>
    </div>


    <div class="col"></div>
  </div>
</div>
</body>
<style>
  .modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.6);
    z-index: 1000;
  }

  .modal .modal_content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    z-index: 99999;
  }

  .modal .modal_content .close_modal_window {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
  }
</style>
</html>
<script>

  var your_id, your_status;
  var modal = document.getElementById("my_modal");
  var modalEvent = document.getElementById("model_event");
  var modalText = document.getElementById("model_text");
  var span = document.getElementsByClassName("close_modal_window")[0];

  span.onclick = function () {
    modal.style.display = "none";
  }

  function MyOnclick() {
    modal.style.display = "block";
  }

  function transaction() {
    modal.style.display = "none";
    ClearInput();
    var number = document.getElementById("number");
    var suma = document.getElementById("sum");
    var suma_user = document.getElementById("sum_user");

    if (suma.value < 1) {
      UnCorrectSuma();
      return;
    }

    if (suma.value > suma_user.value) {
      ErrorSuma();
      return;
    }

/*    console.log(number.value);*/

    var userData = {
      'number': number.value,
      'suma': suma.value
    }

    $.ajax({
      url: 'http://localhost:8080/transaction',
      method: 'POST',
      type: 'POST',
      data: userData,
      dataType: 'html',
      statusCode: {
        200: function (response) {
          var res = document.getElementById("result");
          res.innerText = 'Операция прошла успешно';
          res.style.backgroundColor = "green";
          console.log(response);

          document.getElementById("sum_user").value = response;
          document.getElementById("sum_user").innerText = response;
          document.getElementById("card").innerText = "Карта: " + response + " ₽";

        },
        400: function () {
          ErrorUser();
        },
        404: function () {
          ErrorSuma();
        }
      }
    });
  }

  function ErrorSuma() {
    var res = document.getElementById("correctSuma");
    res.innerText = 'Не достаточно средств,чтобы совершить оперцаию';
  }

  function ErrorUser() {
    var res = document.getElementById("correctNumber");
    res.innerText = 'Пользователя с таким номером нет !!! ';
  }

  function ClearInput() {

    var a = document.getElementById("correctNumber");
    var b = document.getElementById("correctSuma");
    var c = document.getElementById("result");

    a.innerText = "";
    b.innerText = "";
    c.innerText = "";
  }

  function UnCorrectSuma() {
    var res = document.getElementById("correctSuma");
    res.innerText = 'Не корректная сумма';
  }

</script>

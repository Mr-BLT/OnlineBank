<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Акции|ПОКУПКИ </title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
          type="text/javascript"></script>

  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
        integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I"
        crossorigin="anonymous">
</head>
<body style="background-color: cadetblue">

<meta http-equiv="refresh" content="3"/>

<div id="my_modal" class="modal">
  <div class="modal_content">
    <span class="close_modal_window">×</span>

    <p id="model_text"></p>

    <div class="number">
      <button class="number-minus"
              onclick="this.nextElementSibling.stepDown(); this.nextElementSibling.onchange();">-
      </button>
      <input type="number" min="1" value="1" id="count_stock">
      <button class="number-plus"
              onclick="this.previousElementSibling.stepUp(); this.previousElementSibling.onchange();">
        +
      </button>
    </div>

    <p id="verdict_suma"></p>

    <button type="submit" class="btn btn-primary ban"
            id="model_event" onclick="okClick()"
            style="background-color: green">

      Купить
    </button>

  </div>
</div>

<div id="my_modal_succes" class="modal">
  <div class="modal_content">

    <span class="close_modal_window_succes">×</span>
    <p id="model_text_succes"></p>
    <p id="model_count_suma_succe"></p>

    <p id="priceStock"></p>

    <button type="button" class="btn btn-primary ban"
            onclick="onClickSucces()"
            id="model_event"
            style="background-color: green">

      Ок
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

      <button type="button" class="btn btn-warning"
              onclick="window.location.href='/stock'">
        Инвестиции
      </button>

    </p>


    <div class="card">
      <p class="card-text text-center" id="card" value="${accountbank.suma}">
        На счету: ${accountbank.suma} Р.
      </p>
    </div>

    <div class="col"></div>

    <div class="col align-self-center">
      <div class="card-body">
        <div class="card">
          <input type="text" id="mySearch" onkeyup="SearchStock()" placeholder="Поиск...">

          <div class="card-body">


              <#list stocks as stock>

                <div class="card" style="width: 18rem;" id="Div${stock.id}">
                  <div class="card-body">
                    <h5 class="card-title" id="name${stock.id}">${stock.name}</h5>
                    <p class="card-text" id="stock${stock.id}">${stock.price} Р</p>


                    <button type="button" class="btn btn-primary ban"
                            onclick="buttonclick(${stock.id})"
                            id="buy${stock.id}"
                            style="background-color: green">

                      Купить
                    </button>


                    <h6 id="result${stock.id}"></h6>

                  </div>
                </div>


              </#list>

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

  .close_modal_window_succes {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
  }


</style>
</html>

<script type="text/javascript">

  var your_id, your_status, stock_price;
  var modal = document.getElementById("my_modal");
  var modalSucces = document.getElementById("my_modal_succes");
  var modalEvent = document.getElementById("model_event");
  var modalText = document.getElementById("model_text");

  var modalCountSuma = document.getElementById("count_stock");
  var modalCountSumaSucces = document.getElementById("model_count_suma_succe");

  var modalTextSucces = document.getElementById("model_text_succes");
  var modalSuma = document.getElementById("model_suma");
  var span = document.getElementsByClassName("close_modal_window")[0];
  var spanSucces = document.getElementsByClassName("close_modal_window_succes")[0];

  spanSucces.onclick = function () {
    var verdictSuma = document.getElementById("verdict_suma");
    verdictSuma.innerText = "";

    modalSucces.style.display = "none";
  }

  span.onclick = function () {
    var verdictSuma = document.getElementById("verdict_suma");
    verdictSuma.innerText = "";

    modal.style.display = "none";
  }

  function buttonclick(id) {

    modalCountSuma.value = 1;

    your_id = id;
    stock_price = document.getElementById("stock" + id);

    var stringSocks = document.getElementById("name" + id);

    modalText.innerText = "Купить акции " + stringSocks.innerText;

    modal.style.display = "block";
  }

  function okClick() {

    modalCountSuma = document.getElementById("count_stock");

    var verdictSuma = document.getElementById("verdict_suma");
    verdictSuma.innerText = "";

    if (modalCountSuma.value > 0 && modalCountSuma.value % 1 === 0) {
      modal.style.display = "none";

      buy(your_id);
    } else {
      var verdictSuma = document.getElementById("verdict_suma");
      verdictSuma.innerText = "Введите коректное число";
    }
  }

  function onClickSucces() {
    modalSucces.style.display = "none";
  }

  function buy(id) {

    modalCountSuma = document.getElementById("count_stock");

    var userData = {
      'id': id,
      'count': modalCountSuma.value
    }

    $.ajax({
      url: 'http://localhost:8080/stock/buy',
      method: 'POST',
      type: 'POST',
      data: userData,
      dataType: 'html',
      statusCode: {
        200: function (response) {

          modalSucces.style.display = "block";

          var stringSocks = document.getElementById("name" + id);

          modalTextSucces.innerText = "Приобтретены акции " + stringSocks.innerText;
          modalCountSumaSucces.innerText = "В количестве " + modalCountSuma.value;

          document.getElementById("card").innerText = "Карта: " + response + " ₽";

        },
        400: function () {
          console.log("400");
        },
        404: function () {
          modalSucces.style.display = "block";
          modalTextSucces.innerText = "Недостаточно средств";
        }
      }
    });

  }

  function SearchStock() {

    input = document.getElementById("mySearch");
    filter = input.value.toUpperCase();

    if (filter === "") {
      for (i = 1; ; i++) {
        var divStock = document.getElementById("Div" + i);
        divStock.style.display = "block";
        if (divStock === null) {
          break;
        }
      }
    }

    for (i = 1; ; i++) {
      var divStock = document.getElementById("Div" + i);
      var worldStock = document.getElementById("name" + i);

      if (divStock === null) {
        break;
      }

      index = worldStock.innerHTML.toUpperCase().indexOf(filter);

      console.log(worldStock.innerHTML.toUpperCase() + index);

      if (index < 0) {
        divStock.style.display = "none";
      } else {
        divStock.style.display = "block";
      }
    }

  }

  // $.ajax({
  //   url: 'http://localhost:8080/stock/list',
  //   dataType: 'html',
  //   method: 'GET',
  //   type: 'GET',
  //   statusCode: {
  //     200: function (response) {
  //
  //       console.log(response);
  //
  //     },
  //     400: function () {
  //       console.log("400");
  //     },
  //     404: function () {
  //
  //     }
  //   },
  //   timeout: 2000
  // });


</script>



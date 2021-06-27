<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Подписки</title>
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
    <p id="model_text"></p>

    <button type="button" class="btn btn-primary ban"
            onclick="okClick()"
            id="model_event"
            style="background-color: green">

      Подписаться
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

              <#list subscriptions as subscription>

                <div class="card" style="width: 18rem;" id="Div${subscription.id}">
                  <div class="card-body">
                    <h5 class="card-title" id="name${subscription.id}">${subscription.name}</h5>
                    <p class="card-text">${subscription.price} Р</p>

                      <#if subscription.flag>
                        <button type="button" class="btn btn-primary ban"
                                onclick="buttonclick(${subscription.id})"
                                id="sub${subscription.id}"
                                style="background-color: green">

                          Подписаться
                        </button>
                      <#else>
                        <button type="button" class="btn btn-primary ban"
                                onclick="buttonclick(${subscription.id})"
                                id="sub${subscription.id}"
                                style="background-color: red">

                          Отписаться
                        </button>
                      </#if>


                    <h6 id="result${subscription.id}"></h6>

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
</style>
</html>

<script type="text/javascript">

  ///

  var your_id, your_status;
  var modal = document.getElementById("my_modal");
  var modalEvent = document.getElementById("model_event");
  var modalText = document.getElementById("model_text");
  var span = document.getElementsByClassName("close_modal_window")[0];

  span.onclick = function () {
    modal.style.display = "none";
  }

  function buttonclick(id) {
    your_id = id;

    var btn = document.getElementById("sub" + id);

    var btnText = document.getElementById("name" + id);
    modalText.innerText = btnText.innerText;

    if (btn.innerText === "Подписаться") {
      your_status = 0;

      modalEvent.textContent = "Подписаться";
      modalEvent.style.backgroundColor = "green";

    } else {
      your_status = 1;

      modalEvent.textContent = "Отписаться";
      modalEvent.style.backgroundColor = "red";
    }

    modal.style.display = "block";
  }

  function okClick() {
    modal.style.display = "none";

    if (your_status === 0) {
      sub(your_id);
    } else {
      unsub(your_id);
    }
  }

  ///

  function sub(id) {

    var userData = {
      "id": id
    }

    var t = $.ajax({
      url: 'http://localhost:8080/s/sub',
      method: 'POST',
      data: userData,
      type: 'POST',
      dataType: 'html',
      success: function (response) {
        var btn = document.getElementById("sub" + id);
        btn.textContent = "Отписаться";
        btn.style.backgroundColor = "red";

        btn = document.getElementById("result" + id);
        btn.textContent = '';
        btn.style.backgroundColor = '';

        document.getElementById("card").innerText = "Карта: " + response + " ₽";

      },
      error: function () {
        var btn = document.getElementById("result" + id);
        btn.textContent = "Не хватает средств";
        btn.style.backgroundColor = "red";
      }
    });
  }

  function unsub(id, userId) {
    //ВСЛ
    var userData = {
      "id": id
    }

    $.ajax({
      url: 'http://localhost:8080/s/unsub',
      method: 'POST',
      data: userData,
      type: 'POST',
      dataType: 'html',
      success: function () {
        var btn = document.getElementById("sub" + id);
        btn.textContent = "Подписаться";
        btn.style.backgroundColor = "green";
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

</script>



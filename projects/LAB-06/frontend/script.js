$("#button").on("click", function () {
  const txt_name = $("#name").val();
  const txt_email = $("#email").val();
  const txt_comment = $("#comment").val();

  $.ajax({
    url: "http://127.0.0.1",
    type: "post",
    data: { name: txt_name, comment: txt_comment, email: txt_email },
    beforeSend: function () {
      console.log("Tentando enviar os dados....");
      $("#button").prop("disabled", true).text("Enviando...");
    },
  })
    .done(function (e) {
      $("#name").val("");
      $("#email").val("");
      $("#comment").val("");

      alert("Feedback enviado com sucesso! Agradecemos sua contribuição.");
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      alert(
        "Ocorreu um erro ao enviar seu feedback. Por favor, tente novamente."
      );
      console.error("Erro no envio:", textStatus, errorThrown);
    })
    .always(function () {
      $("#button").prop("disabled", false).text("Enviar");
    });
});

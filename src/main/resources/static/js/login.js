// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function iniciarSesion(){
    let usuario = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
    };

    for(let key of Object.keys(usuario)){
        if(usuario[key] == ""){
            return alert("Todos los campos deben ser llenados")
        }
    }

    const req = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario),
      });
    const res = await req.text();

    console.log(res);
    if(res != "unauthorized"){
    localStorage.token = res;
    localStorage.email = usuario.email;
        window.location.href = "users.html";
    } else {
        alert("Credenciales invalidas");
    }


}

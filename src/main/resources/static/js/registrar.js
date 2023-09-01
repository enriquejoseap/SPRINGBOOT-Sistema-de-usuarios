// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function registrarUsuario(){
    let usuario = {
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        email: document.getElementById("email").value,
        tel: document.getElementById("tel").value,
        password: document.getElementById("password").value == document.getElementById("repeatPassword").value ? document.getElementById("password").value : "" ,
    };

    for(let key of Object.keys(usuario)){
        if(usuario[key] == ""){
            return alert("Todos los campos deben ser llenados")
        }
    }
    const res = await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
      });
    alert(`Agregado el usuario:  ${usuario.nombre}`);

  //console.log(json);


}

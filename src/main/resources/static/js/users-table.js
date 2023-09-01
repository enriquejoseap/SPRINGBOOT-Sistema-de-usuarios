// Call the dataTables jQuery plugin
$(document).ready(function() {

    loadUsers()
    document.getElementById("user-email").innerHTML = localStorage.email;
  $('#usersTable').DataTable();
});

function getRequestHeaders(){
    return {'Accept': 'application/json','Content-Type': 'application/json','Authorization': localStorage.token};
}

async function loadUsers(){
  const res = await fetch('http://localhost:8080/api/users', {
    method: 'GET',
    headers: getRequestHeaders(),
  });
  const json = await res.json();
  //console.log(json);


  let tableContent = "";
  for(let user of json){
    let onclickContent = `eliminarUsuario(${user.idUsuario})`
    let deleteBtn = `<a href="#" onclick=${onclickContent} class="btn btn-danger btn-circle btn-sm">
                            <i class="fas fa-trash"></i>
                            </a>`
    tableContent += `<tr>
     <td>${user.idUsuario}</td>
     <td>${user.nombre}</td>
     <td>${user.apellido}</td>
     <td>${user.email}</td>
     <td>${user.tel}</td>
     <td>${deleteBtn}</td>
      </tr>`
  }
  //console.log(tableContent);
  document.querySelector("#usersTable tbody").innerHTML = tableContent;
}

async function eliminarUsuario(id){
    if(confirm("Desea eliminar el usuario?")){
        const res = await fetch('http://localhost:8080/api/users/' + id, {
                method: 'DELETE',
                headers: getRequestHeaders(),
            });
            loadUsers();
    }

}
// Show list
function showCityList() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: '/city/all',
        type: 'GET',
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += properties(data[i]);
            }
            document.getElementById("list").innerHTML = content;
            document.getElementById("city").value = "";
            document.getElementById("area").value = "";
            document.getElementById("population").value = "";
            document.getElementById("gdp").value = "";
            document.getElementById("description").value = "";
        }
    });
}

function properties(data) {
    return `<tr>
    <td>${data.id}</td>
    <td>${data.name}</td>
    <td>${data.country.name}</td>
    <td>
    <a id="${data.id}" href="/city/${data.id}" 
    onclick="showEditCity(id)" class="bi bi-pencil-square" 
    data-toggle="modal" data-target="#editCity">Edit</a>
    <a id="${data.id}" href="/city/${data.id}" 
    onclick="showRemove(id)" class="bi bi-pencil-square" 
    data-toggle="modal" data-target="#delteCity">Delete</a>
    <a id="${data.id}" href="/city/${data.id}" onclick="showInFor(id)"
     data-toggle="modal" data-target="#showIFor">VIEW</a>
    </td>
    </tr>`
}

// add City
function addCity() {
    let city = $("#city").val();
    let country = $("#country").val();
    let area = $("#area").val();
    let gdp = $("#gdp").val();
    let population = $("#population").val();
    let description = $("#description").val();
    let newCity = {
        name: city, country: {id: country},
        area: area, gdp: gdp, population: population, description: description
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newCity),
        url: `/city/add`,
        type: "POST",
        success: showCityList
    });
    event.preventDefault();
}

function showEditCity(id) {
    $.ajax({
        type: "GET",
        url: `/city/${id}`,
        success: function (data) {
            document.getElementById('editId').value = data.id;
            document.getElementById('cityName').value = data.name;
            document.getElementById('editCountry').value = data.country.id;
            document.getElementById('editArea').value = data.area;
            document.getElementById('editPopulation').value = data.population;
            document.getElementById('editGdp').value = data.gdp;
            document.getElementById('editDescription').value = data.description;
        }
    });
}

function editCity() {
    let editId = $("#editId").val();
    let editCity = $("#cityName").val();
    let editCountry = $("#editCountry").val();
    let editArea = $("#editArea").val();
    let editGdp = $("#editGdp").val();
    let editPopulation = $("#editPopulation").val();
    let editDescription = $("#editDescription").val();
    let newEditCity = {
        id: editId, name: editCity, country: {id: editCountry},
        area: editArea, gdp: editGdp, population: editPopulation, description: editDescription
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(newEditCity),
        url: `/city/edit`,
        type: "PUT",
        success: showCityList
    });
    event.preventDefault();
}

function showRemove(id) {
    $.ajax({
        url: `/city/${id}`,
        type: "GET",
        success: function (data) {
            document.getElementById('idDelete').value = data.id;
            document.getElementById('namedelete').innerText = data.name;
        }
    });
}

function removeCity() {
    let deleteId = $('#idDelete').val();
    $.ajax({
        url: `/city/${deleteId}`,
        type: 'DELETE',
        success: function () {
            document.getElementById(deleteId).parentElement.parentElement.remove();
        }
    });
    event.preventDefault();
}

function showInFor(id) {
    $.ajax({
        url: `/city/${id}`,
        type: "GET",
        success: function (data) {
            // document.getElementById('editId').value = data.id;
            document.getElementById('infoName').innerText = data.name;
            document.getElementById('infoCountry').innerText = data.country.name;
            document.getElementById('infoArea').innerText = data.area;
            document.getElementById('infoPopulation').innerText = data.population;
            document.getElementById('infoGdp').innerText = data.gdp;
            document.getElementById('infoDescription').innerText = data.description;

        }
    })
}


function searchName() {
    let search = $('#search').val();
    $.ajax({
        url: `/city/all?s=${search}`,
        type: 'GET',
        success: function (data) {
            console.log(data)
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += properties(data[i])
            }
            document.getElementById("list").innerHTML = content;
        }
    });
}

function Model(){
	//Lista de usuarios
	this.usuarios = null;

	//Usuarios en sesion
	this.usesion = null;


	//Cargamos los datos del servicio 
	this.load = function(){
		try{
			this.usuarios = UsuariosServicesRs.getUsuarios({
				type: "getUsuarios",
				rol:"usuario",
				rolID:  sessionStorage.getItem('rol')
			});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Reiniciamos la BBDD
	this.reiniciarBBDD = function(u){
		try{
			SettingsServiceRs.reinicioBBDD({url:u, rolID: sessionStorage.getItem('rol')});}
		catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Conseguimos un vector de usuarios en sesión
	this.usuariosEnSesion = function(){
		try{return SettingsServiceRs.getUsuarios({rolID: sessionStorage.getItem('rol')});}
		catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	this.logout = function(){
		var emails = sessionStorage.getItem('usuario');
		sessionStorage.removeItem('usuario');
		SettingsServiceRs.deleteSesion({
			email: emails,
			rolID:  sessionStorage.getItem('rol')
		});
		sessionStorage.removeItem('rol');
	}
	


	this.comprueba = function(select){
		let usesion = this.usuariosEnSesion();
		var indice = 0;
		while(indice < select.length ){
			var elemento = select[indice];
			var i = usesion.indexOf(elemento);
			try{
				if(i==-1) {

					//Borramos las publicaciones asociadas
					PublicacionesServicesRs.deletePublicacionesAmigos({
						type: "deletePublicacionesAmigos",
						email : elemento,
						rolID:  sessionStorage.getItem('rol')
					});

					//Borramos las amistades asociadas
					AmigosServicesRs.deleteAmigo({
						type: "deleteAmigo",
						email : elemento,
						rolID:  sessionStorage.getItem('rol')
					});

					//Borramos el usuario
					UsuariosServicesRs.deleteUsuario({
						type: "deleteUsuario",
						email : elemento,
						rolID:  sessionStorage.getItem('rol')
					});
					this.load();
				}
				else alert("No se puede borrar el usuario: " + elemento + " porque esta en sesión" );
				indice++;
			}
			catch(error){alert("Acceso incorrecto");
			window.location.href="../login.html";}
		};
	}


}

function Controller(varmodel, varview){
	var that = this;
	this.model = varmodel;
	this.view = varview;
	this.init = function(){
		//Cargamos la lista de usuarios
		this.model.load();

		this.view.list(this.model.usuarios);

		$("#bReiniciar").click(
				function(event){
					var u = $("#url").val();
					that.model.reiniciarBBDD(u);
					alert("Base de datos reiniciada con exito !!")
					window.location.href="indexAdmin.html";
				});

		$("#bBorrar").click(
				function(event){
					$("#url").val("");
				});


		$("#bBSeleccionados").click(
				function(event){
					let checks = Array.from(document.querySelectorAll("#tblList [type='checkbox']"));
					let select =[];
					let borrar =[];
					checks.map((c,i) =>{
						if(c.checked){select.push(c.name);}
					});
					that.model.comprueba(select);
					that.view.list(that.model.usuarios);
				});
		
		$("#bTodas").click(
				function(event){
					$('input[type="checkbox"]', '#tblList ').prop('checked', true);
					return false;
				});


		document.getElementById("logout").addEventListener("onclick", console.log("evento metido"));
		$("#logout").click(function(event){
			that.model.logout();
			window.location.href="../login.html";
			return false;
		}); 
		
		document.getElementById("contacto").addEventListener("onclick", console.log("contacto metido"));
		$("#contacto").click(function(event){
			 document.getElementById("info").innerHTML = "<aside><header><h3>Informacion y contacto</h3>"+
				"</header><p>Somos una red social centralizada ubicada en una pequeña ciudad en la costa gaditana: </p>"+
				"<p>En cuanto direcciones de contacto disponibles tenemos: </p>"+
				"<ol><li>Correo electronico: redSocial@gmail.com </li><li>Número de telefono fijo:+34 985543234</li><li>Movil:+34 654456689</li><li>Direccion de la sede: C/ Cadiz XIII n34 bajo</li></ol></aside>";
		}); 







	}

};

function View(){

	this.list = function (lista) {
		$("#tblList").html("");
		$("#tblList").html( "<thead>" + "<tr>" + "<th></th>"
				+  "<th>Email</th>"
				+ "<th>Rol</th>" + "<th>Nombre</th>" + "</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var usuario = lista[i];
			$("#tblList tbody").append("<tr>"
					+ "<td><input type='checkbox' name="+usuario.email+" value="+i+"></td>"
					+ "<td>" + usuario.email + "</td>" + "<td>" + usuario.rol + "</td>"
					+ "<td>" + usuario.nombre + "</td></tr>");
		}
	}; 

};


$(function(){
	//Creamos el modelo
	var model = new Model();
	// Creamos la vista que incluye acceso al modelo. 
	var view = new View();
	//Creamos el controlador
	var control = new Controller(model, view);
	//Iniciamos la aplicación
	control.init();
})
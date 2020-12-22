function Model(){

	//Lista publicaciones
	this.publicaciones = null;
	this.publicacionesOrden = null;
	this.publicacionesFecha = null;

	//Publicacion nueva
	this.publicacion = null;

	//Obtener las listas de publicaciones
	this.listarPublicaciones = function(email){
		try{this.publicaciones = PublicacionesServicesRs.findByEmail({
			type : "getPublicaciones",
			email : email,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="..//login.html";}

	}

	//Orden por fecha de publicaciones
	this.ordenFecha = function(email){
		try{this.publicaciones = PublicacionesServicesRs.ordenByFecha({
			type: "ordenByFecha",
			string: email,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="..//login.html";}
	}
	//Orden por titulo de publicaciones
	this.ordenTitulo = function(email){
		try{
			this.publicaciones = PublicacionesServicesRs.ordenByTitulo({
				type: "ordenByTitulo",
				string: email,
				rolID:  sessionStorage.getItem('rol')
			});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Crear una publicacion nueva
	this.publicacionNueva = function(publicacion){
		try{this.publicacion = PublicacionesServicesRs.savePublicacion({
			$entity: publicacion,
			$contentType: "application/json",
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Listar publicaciones de amigos
	this.publiAmigos=null;
	this.amigos=null;

	//Conseguimos los amigos del usuario
	this.obtenerAmigos = function(email){
		try{this.amigos = PublicacionesServicesRs.getAmigos({
			email: email,rolID:  sessionStorage.getItem('rol')		});
		}
		catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}



	//Obtener las listas de publicaciones de mis amigos
	this.listarPublicacionesAmigos = function(email){
		try{this.publiAmigos = PublicacionesServicesRs.findByEmail({
			type : "getPublicaciones",
			email : email,
			rolID:  sessionStorage.getItem('rol')
		});}
		catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}

	}


	//Obtenemos la lista de amigos del ususario
	this.sonA=null;
	this.sonAmigos = function(){
		try{this.sonA = AmigosServicesRs.getAmigos({
			type: "getAmigos",
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Obtenemos la lista de Usuarios sin admin sin login
	this.todosUsuarios=null
	this.tUsuarios = function(email){
		try{this.todosUsuarios = UsuariosServicesRs.getUsuariosSinUsLogin({
			type: "getUsuariosSinUsLogin",
			email: email,
			rolID:  sessionStorage.getItem('rol')			
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Filtrar usuarios por cadena pasada
	this.filtroUsuarios=null;
	this.filtrar = function(cadena,email){
		try{this.filtroUsuarios = UsuariosServicesRs.findByCadena({
			type: "findByCadena",
			cadena: cadena,
			emailUsuario: email,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	this.amistades=null;


	//LISTAR PETICIONES DE AMISTAD
	this.pet = null;
	this.peticionesAmistad = function(email){
		try{this.pet = AmigosServicesRs.getInvitacionesAmistad({
			type: "getInvitacionesAmistad",
			email: email,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//LISTA NO AMIGOS DE UN USUARIO
	this.listaNoAmistad=null;

	this.listNA = function(email){
		try{this.listaNoAmistad = AmigosServicesRs.getNoAmigos({
			type: "getNoAmigos",
			email: email,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//FILTRAR USUARIOS BUSQUEDA
	this.filter = function(texto){
		let aux = [];
		for(x in this.listaNoAmistad){
			var a = this.listaNoAmistad[x];
			if(a.nombre.startsWith(texto) || a.email.startsWith(texto)){
				aux.push(a);
			}
		}	
		return aux;
	}


	//ENVIO SOLICITUD DE AMISTAD A UN USUARIO
	this.envioSolicitud = function(email, emailAmigo){
		try{AmigosServicesRs.envio({
			type: "envio",
			email: email,
			emailAmigo: emailAmigo,
			rolID:  sessionStorage.getItem('rol')
		});
		this.listNA(email);}
		catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//ACEPTAR PETICION DE AMISTAD DE UN USUARIO
	this.aceptarSolicitud = function(email,emailAmigo){
		try{AmigosServicesRs.aceptar({
			type: "aceptar",
			email: email,
			emailAmigo: emailAmigo,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}

	}

	//ENCONTRAR USUARIO POR NOMBRE
	this.usu = null;
	this.usuNombre = function(nombre){
		try{this.usu = UsuariosServicesRs.findByName({
			type: "findByName",
			name: nombre,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}

	//Lista de usuarios
	this.usuarios = null;

	//Usuarios en sesion
	this.usesion = null;

	//Cargamos los datos del servicio 
	this.load = function(){
		try{this.usuarios = UsuariosServicesRs.getUsuarios({
			type : "getUsuarios",
			rol :"usuario",
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
	}


	//Conseguimos un vector de usuarios en sesión
	this.usuariosEnSesion = function(){
		try{
			return SettingsServiceRs.getUsuarios({rolID:  sessionStorage.getItem('rol')});
		}catch(error){alert("Acceso incorrecto");window.location.href="../login.html";}
		return null;
	}
	
	//Borrar amigos
	this.amigoOut = function(email){
		try{
			this.usu = AmigosServicesRs.deleteAmistad({
			type: "deleteAmistad",
			email: sessionStorage.getItem('usuario'),
			emailamigo: email,
			rolID:  sessionStorage.getItem('rol')
		});}catch(error){console.log(error);alert("Acceso incorrecto");window.location.href="../login.html";}
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

};


//CLASE VIEW
function View(){
	this.list = function(lista) {
		$("#tblListPublicaciones").html("");
		$("#tblListPublicaciones").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>EMAIL</th>" + "<th>TITULO</th>"
				+ "<th>TEXTO</th>" + "<th>FECHA</th>" + "</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var p = lista[i];
			$("#tblListPublicaciones tbody").append("<tr> <td>"
					+ "<td>" + p.email + "</td>"
					+ "<td>" + p.titulo + "</td>" + "<td>" + p.texto + "</td>"
					+ "<td>" + p.fechaCadena + "</td></tr>");
		}
	}

	this.listA = function(lista) {
		$("#tblListAmigos").html("");
		$("#tblListAmigos").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>EMAIL</th>" +  "<th>ELIMINAR</th>"+"</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var a = lista[i];
			$("#tblListAmigos tbody").append("<tr> <td>" 
					+"</td>"
					+ "<td>" + a + "</td>"
					+"<td><button type='submit' class='Delete' id='bdelete'>Borrar amigo </button></td>" 
					+ "</tr>");
		}
	}


	this.loadPublicacionFromForm = function(){
		//Cogemos la publicacion del formulario
		var p = {
				email: "",
				titulo: $("#titulo").val(),
				texto: $("#texto").val(),
				fecha: ""
		};
		return p;
	}

	this.loadBusquedaFromForm = function(){
		//Cogemos el texto para la busqueda de usuarios
		var b = { texto: $("#busqueda").val()};
		return b;
	}

	this.listAmigos = function(lista) {
		$("#tblListPublicacionesAmigos").html("");
		$("#tblListPublicacionesAmigos").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>EMAIL</th>" + "<th>TITULO</th>"
				+ "<th>TEXTO</th>" + "<th>FECHA</th>" + "</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {

			var p = lista[i];
			$("#tblListPublicacionesAmigos tbody").append("<tr> <td>"
					+ "<td>" + p.email + "</td>"
					+ "<td>" + p.titulo + "</td>" + "<td>" + p.texto + "</td>"
					+ "<td>" + p.fechaCadena + "</td></tr>");
		}
	}

	this.listPeticiones = function(lista) {
		$("#tblListPeticionesAmistad").html("");

		$("#tblListPeticionesAmistad").html( "<thead>" + "<tr>"
				+ "<th>NOMBRE</th>" + "<th>EMAIL</th>"+ "<th>ENVIAR PETICIÓN</th>"+"</tr>"
				+"</td></tr>" + "</thead>" + "<tbody>" + "</tbody>");

		for ( var i in lista) {
			var p = lista[i];
			$("#tblListPeticionesAmistad tbody").append("<tr>"
					+ "<td>" + p.nombre + "</td>"
					+ "<td id='email'>" + p.email + "</td>" 
					+ "<td>" + "<input type='button' id='btnEnviar' value='Agregar amigo' class='Agregar'/>"+"</td>"
					+"</tr>");


		}

	}

	this.listAmistades = function(lista) {
		$("#tblListAmistades").html("");
		$("#tblListAmistades").html( "<thead>" + "<tr>" + "<th></th>"
				+ "<th>NOMBRE</th>" + "<th>ACEPTAR AMISTAD</th>"+"</tr>"
				+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var p = lista[i];
			$("#tblListAmistades tbody").append("<tr> <td>"
					+ "<td>" + p.nombre + "</td>"
					+ "<td>" + "<input type='button' id='btnAceptar' value='Aceptar' class='Aceptar'/>"+"</td>"
					+"</td></tr>");


		}

	}



};


//CLASE DEL CONTROLLER
function Controller(varmodel, varview) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;
	// refefencia a la vista
	this.view = varview;
	// Funcion de inicialización para cargar modelo y vista, definición de manejadores
	this.init = function() {

		//Cargamos la lista de usuarios
		this.model.load();

		//Cargamos los amigos
		this.model.obtenerAmigos(sessionStorage.getItem('usuario'));

		//Cargamos los usuarios en sesion
		this.model.usesion = this.model.usuariosEnSesion();

		//Obtenemos el último en sesion
		var ultimo = this.model.usesion.length;
		var user = this.model.usesion[ultimo-1]; //usuario en sesion

		//Cargamos las publicaciones del usuario en sesion
		this.model.listarPublicaciones(user);

		//Mostramos las publicaciones en pantalla
		this.view.list(this.model.publicaciones);

		//Lista de amigos
		this.view.listA(this.model.amigos);

		function cargarPublis(){
			var loc = document.location + '';
			var vloc=loc.split("/");
			if(vloc[vloc.length - 1]=="listarAmigos.html"){
				//Mostrar las publicaciones de los amigos
				that.model.obtenerAmigos(user);//Obtenemos amigos
				var lista = [];//lista vacia para publicaciones de amigos
				//Recorremos la lista de amigos...
				for (x in that.model.amigos){
					that.model.listarPublicacionesAmigos(that.model.amigos[x]);
					//...y guardamos cada publicacion de cada uno de ellos
					for (y in that.model.publiAmigos){
						lista.push(that.model.publiAmigos[y]);
					}
				}
				//Mostramos por pantalla la lista de publicaciones de los amigos
				that.view.listAmigos(lista);}

		}

		setInterval(cargarPublis, 10000);
		cargarPublis();

		//AMISTADES
		this.model.tUsuarios(user); //todos los usuarios (-admin y -usersesion)
		this.model.sonAmigos();//todos los amigos en la red social

		var listaAmistadUser = []; //amigos en la red social del usuario
		for(x in this.model.sonA){
			if(this.model.sonA[x].email_usuario===user){
				listaAmistadUser.push(this.model.sonA[x]);
			}
		}

		$("#tblListAmigos").on("click", ".Delete",
				//Metodo que gestiona el aceptar las invitaciones
				function(event){
			
			var x = $(this).closest('tr').find('td').get(1).innerHTML;
			that.model.amigoOut(x);

		}); 


		this.model.listNA(user)
		var listaNoAmistadUser = this.model.listaNoAmistad; //NO amigos en la red social del usuario
		this.view.listPeticiones(listaNoAmistadUser);




		//LISTAR PETICIONES AMISTAD
		this.model.peticionesAmistad(user);
		var listAmistades = [];
		//Buscar los usuarios con peticiones de amistad
		for(x in this.model.todosUsuarios){
			var y=0;
			while(y<this.model.pet.length){
				if(this.model.todosUsuarios[x].email===this.model.pet[y].email_amigo){
					listAmistades.push(this.model.todosUsuarios[x]);
					y=this.model.pet.length;
				}else{y++}
			}
		}
		this.view.listAmistades(listAmistades);

		document.getElementById("contacto").addEventListener("onclick", console.log("contacto metido"));
		$("#contacto").click(function(event){
			document.getElementById("info").innerHTML = "<aside><header><h3>Informacion y contacto</h3>"+
			"</header><p>Somos una red social centralizada ubicada en una pequeña ciudad en la costa gaditana: </p>"+
			"<p>En cuanto direcciones de contacto disponibles tenemos: </p>"+
			"<ol><li>Correo electronico: redSocial@gmail.com </li><li>Número de telefono fijo:+34 985543234</li><li>Movil:+34 654456689</li><li>Direccion de la sede: C/ Cadiz XIII n34 bajo</li></ol></aside>";


		}); 



		//MANEJADOR DE EVENTOS
		//Cerrar sesion

		document.getElementById("logout").addEventListener("onclick", console.log("evento metido"));
		$("#logout").click(function(event){
			that.model.logout();
			window.location.href="../login.html";
			return false;
		}); 

		//Ordenar publicaciones(Fecha)
		$("#btnFecha").click(function(event){
			that.model.ordenFecha(user);
			//Ordenar lista por fecha
			that.view.list(that.model.publicaciones);		
		});

		//Ordenar publicaciones(Titulo)
		$("#btnTitulo").click(function(event){
			that.model.ordenTitulo(user);
			//Ordenar lista por orden alfabetico
			that.view.list(that.model.publicaciones);
		});

		//Crear publicacion
		$("#btnCrear").click(function(event){
			publicacion = that.view.loadPublicacionFromForm();
			if(publicacion.titulo != "" && publicacion.texto != "") {
				publicacion.email = user;
				publicacion.fecha = (new Date()).getTime();
				that.model.publicacionNueva(publicacion);
				window.location.href="listarPublicacion.html";
				return false;//para que redireccione a la página
			}
		});


		//FILTRAR USUARIOS
		$("#btnBuscar").click(function(event){
			var texto = busqueda.value.toLowerCase();
			if(texto!=null){
				var lista = that.model.filter(texto);
				that.view.listPeticiones(lista);
			}else{
				that.view.listPeticiones(that.model.listaNA(user));
			}
		});
		var busqueda = document.getElementById("busqueda");


		//ENVIO PETICIONES AMISTAD
		$("#tblListPeticionesAmistad").on("click", ".Agregar",
				// Método que gestiona el evento de clickar en el evento
				function(event) {
			var x = $(this).closest('tr').find('td').get(1).innerHTML;
			that.model.envioSolicitud(user,x);

			//Eliminar fila de tabla
			$(this).closest('tr').remove();

		}
		);

		//ACEPTAR SOLICITUD AMISTAD
		$("#tblListAmistades").on("click", ".Aceptar",
				//Metodo que gestiona el aceptar las invitaciones
				function(event){
			var x = $(this).closest('tr').find('td').get(1).innerHTML;
			that.model.usuNombre(x);
			var amigo = that.model.usu;
			//Aceptar solicitud(mutuo)
			that.model.aceptarSolicitud(user,amigo.email);
			//Eliminar fila de tabla
			$(this).closest('tr').remove();

		}
		);




	}
};


//APARTADO FINAL
$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos la vista que incluye acceso al modelo.
	var view = new View();
	// Creamos el controlador
	var control = new Controller(model, view);
	// Iniciamos la aplicación
	control.init();
});

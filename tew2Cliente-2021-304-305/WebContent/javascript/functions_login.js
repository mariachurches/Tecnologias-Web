function Model(){
	//Lista de usuarios
	this.usuarios = null

	//Usuarios en sesion
	var usesion = new Array();

	//Cargamos los datos del servicio 
	this.load = function(){
		try{this.usuarios = UsuariosServicesRs.getUsuarios({
			type:"getUsuarios", rol : "todos", rolID:"todas"
		});}catch(error){alert("Acceso incorrecto");window.location.href="..//login.html";}
	}
	
	this.anadir = function(Memail) {
		// Llamamos al servicio de borrado de Piso
		SettingsServiceRs.anadirSesion({
			email : Memail,rolID:"todas"
		});
	}


	this.comprueba = function(user, pass){
		for(var u in this.usuarios){
			var usuario = this.usuarios[u];
			if( (usuario.email == user) && (usuario.passwd == pass) ){
				sessionStorage.setItem('usuario', usuario.email);
				sessionStorage.setItem('rol', usuario.rol);
				if(usuario.rol=='usuario') return "usuario";
				else return 'admin';
			}
		}
		alert("Login Incorrecto");
		return null;

	}

	

}

function Controller(varmodel){
	var that = this;
	this.model = varmodel;
	this.init = function(){
		
		//Cargamos la lista de usuarios
		this.model.load();
		$("#bEnviar").click(
				function(event){
					var user=$("#username").val();
					var pass=$("#passwd").val();
					var rol = that.model.comprueba(user,pass);
					if(rol == "usuario"){
						that.model.anadir(user);
						//window.location.assign="./usuario/indexUser.html";
						window.location.href="./usuario/indexUser.html";

						return false;
					}
					else if(rol=="admin"){
						that.model.anadir(user);
						//window.location.assign="./administrador/indexAdmin.html";
						window.location.href="./administrador/indexAdmin.html";

						return false;
					}
					else{
						window.location.href="login.html";
					}
				});
		
		$("#logout").click(
				function(event){
					sessionStorage.removeItem("usuario");
					sessionStorage.removeItem("rol");
					window.location.href="index.html";
				});
		
	

		
	}
};



$(function(){
	//Creamos el modelo
	var model = new Model();
	//Creamos el controlador
	var control = new Controller(model);
	//Iniciamos la aplicación
	control.init();



})
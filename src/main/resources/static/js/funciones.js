/**
 * Función para enviar un formulario de forma parcial
 * 
 * Reemplaza el contenido de #contenido-dinamico con la respuesta HTML
 * @param {Event} event atrapa el evento del formulario
 * @param {string} formId id del formulario a enviar
 * @return {void} No retorna nada
 */
function enviarFormularioParcial(event, formId) {
    event.preventDefault(); // Evita el envío tradicional del formulario

    const form = document.getElementById(formId); // Obtiene el formulario por ID
    const formData = new FormData(form); // Crea un objeto FormData con los datos del formulario

    fetch(form.action, {
        method: "POST",
        body: formData
    })
        .then(res => res.text()) // Convierte la respuesta a texto (HTML)
        .then(html => {
            console.log(html);
            document.getElementById("contenido-dinamico").innerHTML = html; // Inserta el HTML recibido
        })
        .catch(err => {
            console.error("Error al enviar formulario:", err); // Muestra errores si algo falla
        });
}


/**
 * 
 * Función para cargar una vista parcial desde el servidor
 * 
 * Inserta el contenido recibido en #contenido-dinamico
 * @param {string} folder 
 * @param {string} nombreVista 
 */
function cargarVista(folder, nombreVista) {
    fetch(`/${folder}/${nombreVista}`) // Hace la solicitud al endpoint correspondiente
        .then(res => res.text()) // Convierte la respuesta a HTML
        .then(html => {
            document.getElementById("contenido-dinamico").innerHTML = html; // Inserta la vista en el contenedor
        });
}
/**
 * Función para confirmar una acción antes de enviar un formulario
 * 
 * @param {Event} event atrapa el evento del formulario
 * @param {string} formId id del formulario a enviar 
 * @returns {boolean} false para prevenir el envío normal
 */
function confirmarYEnviar(event, formId) {
    if (!confirm("¿Seguro de eliminarlo?")) {
        return false;
    }
    enviarFormularioParcial(event, formId);
    return false; // Previene el envío normal
}


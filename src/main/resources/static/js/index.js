$(document).ready(function($) {
    $("#error").toggle();
    
    var eureka_server = $("#eureka_server")[0].innerText;
    var data_service = $("#data_service")[0].innerText;
    
    var es = function() { return axios.get(eureka_server).catch((err)=>console.log(err)); }
    var ds = function() { return axios.get(data_service).catch((err)=>console.log(err)); }
    
    axios.all([es(), ds()])
        .then(axios.spread(function(e, d) {
            if(e != undefined && d != undefined && e.status == 200 && d.status == 200) {
                window.location.replace("/home");
            }
            else {
                $("#info").toggle();
                $("#error").toggle();
            }
        }))
})
var form = document.getElementById("searchForm");

form.addEventListener("submit", function (e){
    e.preventDefault();
    
    var formData = $(form).serializeArray();

    var data = {};
    for(let dic of formData) {
        if(dic.value !== "")
            data[dic.name] = dic.value;
    }
    
    console.log(formData)
    console.log(data)
    
    axios.post('', data)
        .then(function(response){
            console.log(response.data);
        })
})
$.noConflict();

var vm = new Vue({
    el: "#bodyWrapper",
    data: {
        show: {
            criteria: true,
            results: false,
            noResults: false,
            playerInfo: false
        },
        results: [],
        errorMessage: "Server error occured. Try agian later.",
        activePlayer: {}
    },
    computed: {
        criteriaChevron: function () { return this.show.criteria ? 'down' : 'up'; },
        resultsChevron: function () { return this.show.results ? 'down' : 'up'; },
    },
    methods: {
        chevron: function(el) { return this.show[el] ? 'down' : 'up'; },
        toggle: function(el, event) { this.show[el] = this.show[el] ? false : true; },
        search: function(e) {
            e.preventDefault();
            this.activePlayer = {};
            
            var formData = jQuery("#searchForm").serializeArray();
            var databaseQuery = {};

            //format form data into JSON query
            for(let dic of formData) {
                if(dic.value !== "" && dic.name.substr(-3) !== "-op"){ // avoid empty fields and operator fields
                    var valueInt = parseInt(dic.value);
                    
                    if(isNaN(valueInt)) { // if it is a string
                        if(dic.name == "Nationality" || dic.name == "Team") {
                            databaseQuery[dic.name] = dic.value;
                            continue;
                        }
                        databaseQuery["$text"] = {"$search": dic.value};
                    }
                    else { // it is a number
                        if(dic.name.substr(0, 2) == "Is") { // if it's a boolean number
                            databaseQuery[dic.name] = valueInt;
                            continue;
                        }
                        var op; // get operator of the respective field; like Age-op
                        for(let dict of formData) {
                            if(dict.name == dic.name+'-op') {
                                op = dict.value;
                            }
                        }
                        if(dic.name == "Highest score") {
                            if(op == "*") {
                                databaseQuery["$text"] = {"$search": dic.value+"*"};
                                continue;
                            }
                        }
                        var params = {};
                        var valueFloat = parseFloat(dic.value);
                        if(valueInt == valueFloat) {
                            // it is an int
                            params[op] = valueInt;
                        }
                        else { // it is a float
                            params[op] = valueFloat;
                        }
                        databaseQuery[dic.name] = params;
                    }
                }
            }
            
            console.log(databaseQuery);
            
            axios.post('', databaseQuery)
                .then(function(response) {
                    console.log(response);
                    if(response.data.length) {
                        vm.results = response.data;
                        vm.show.criteria = false;
                        vm.show.results = true;
                        vm.show.noResults = false;
                        jQuery("#criteria")[0].scrollIntoView(true, {behavior: 'smooth', block: 'start'});
                    }
                    else {
                        vm.results = response.data;
                        vm.show.criteria = false;
                        vm.show.results = false;
                        vm.show.noResults = true;
                    }
                })
                .catch(function(error) {
                    console.log(error.response);
                    vm.errorMessage = error.response.data.message;
                    jQuery("#errorModal").modal('show');
/*
                    else if(error.request) {
                        console.log(error.request);
                        jQuery("#errorModal").modal('show');
                    }
                    else {
                        console.log(error.message);
                        jQuery("#errorModal").modal('show');
                    }
*/
                });
        },
        clear: function(e) {
            jQuery("#searchForm")[0].reset();
            vm.results = [];
            vm.show.results = false;
        },
        getCaptaincy: function(player) { return player['Is Captain(1=yes)'] ? " (Captain)" : ""; },
        getRoles: function(player) {
            var roles = [];
            
            player['Is batsman'] ? roles.push("Batsman") : "";
            player['Is bowler?'] ? roles.push("Bowler") : "";
            player['Is Wktkeeper(1=Yes)'] ? roles.push("Wicketkeeper") : "";
            
            return roles.toString();
        },
        getDetailedInfo: function(i) {
            this.activePlayer = this.results[i];
            this.show.results = false;
            this.show.playerInfo = true;
            jQuery("#playerInfo")[0].scrollIntoView(true, {behavior: 'smooth', block: 'start'});
        },
        isActivePlayer: function(player) {
            return this.activePlayer.hasOwnProperty("_id") ? 
                player._id['$oid'] == this.activePlayer._id['$oid'] :
                false;
        }
    }
});
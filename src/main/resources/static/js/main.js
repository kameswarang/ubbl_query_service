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
                if(dic.value !== ""){
                    var valueInt = parseInt(dic.value);
                    
                    if(isNaN(valueInt)) { // if it is a string
                        databaseQuery[dic.name] = dic.value;
                    }
                    else {
                        var valueFloat = parseFloat(dic.value);
                        if(valueInt == valueFloat) { // if it is an int
                            //check that the int and string are of same length
                            
                            var n = Number(valueInt);
                            var i = 0;
                            while(n > 1) {
                                i++;
                                n /= 10;
                            }
                            
                            if(i == dic.value.length) {
                                databaseQuery[dic.name] = valueInt; // it is an int
                            }
                            else {
                                databaseQuery[dic.name] = dic.value; // it is a string
                            }
                        }
                        else { // it is a float
                            databaseQuery[dic.name] = valueFloat;
                        }
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
                        vm.show.criteria = false;
                        vm.show.results = false;
                        vm.show.noResults = true;
                    }
                })
                .catch(function(error) {
                    if(error.response) {
                        console.log(error.response);
                        jQuery("#errorModal").modal('show');
                    }
                    else if(error.request) {
                        console.log(error.request);
                        jQuery("#errorModal").modal('show');
                    }
                    else {
                        console.log(error.message);
                        jQuery("#errorModal").modal('show');
                    }
                    console.log(error.config);
                });
        },
        clear: function(e) {
            jQuery("#searchForm")[0].reset();
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
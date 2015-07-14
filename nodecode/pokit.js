var PokitDok = require('pokitdok-nodejs');
var pokitdok = new PokitDok("flhA2wEZtITbXQoOldiE","zLJmvlwrCUKDc6EuQsuK93MPZ4axaexlqZoqNvRD","v4");
//plans in texas
pokitdok.plans({plan_type:'PPO', state: 'CA'}, function (err, res) {
    if (err) {
        return console.log(err, res.statusCode);
    }
    // print the plan names and ids
    for (var i = 0, ilen = res.data.length; i < ilen; i++) {
        var plan = res.data[i];
        console.log(plan.plan_name + ':' + plan.plan_id);
    }
});
// get a provider using a npi id
/*pokitdok.providers({
    npi: '1881692002'
}, function(err, res){
    if(err) {
        return console.log(err, res.statusCode);
    }
    // res.data is a single result
    console.log(res.data.provider.first_name + ' ' + res.data.provider.last_name);
});*/






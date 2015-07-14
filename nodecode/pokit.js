var PokitDok = require('pokitdok-nodejs');
var pokitdok = new PokitDok("4WCZ6dX0bL3HW6TRJZOf","mUHQxAOayX2ePYjoTcbT2JkfvlO9Km0wiyt8UPNW","v4");
//plans in texas
/*pokitdok.plans({plan_type:'PPO', state: 'TX'}, function (err, res) {
    if (err) {
        return console.log(err, res.statusCode);
    }
    // print the plan names and ids
    for (var i = 0, ilen = res.data.length; i < ilen; i++) {
        var plan = res.data[i];
        console.log(plan.plan_name + ':' + plan.plan_id);
    }
});*/
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

// print the trading partner id's, used to identify a payer for other EDI transaction
/*pokitdok.payers(function (err, res) {
    if (err) {
        return console.log(err, res.statusCode);
    }
    // print the name and trading_partner_id of each payer
    for (var i = 0, ilen = res.data.length; i < ilen; i++) {
        var payer = res.data[i];
        console.log(payer.payer_name + ':' + payer.trading_partner_id);
    }
});*/

// print the procedure code and price for a particular zip/cpt combination
/*pokitdok.insurancePrices({
        zip_code: '94401',
        cpt_code: '90658'
    }, function (err, res) {
    if (err) {
        return console.log(err, res.statusCode);
    }
    // print the cpt and geo_zip
    console.log(res.data.cpt_code + ':' + res.data.geo_zip_area);
    // print the average price per payment types
    for (var i = 0, ilen = res.data.amounts.length; i < ilen; i++) {
        var price = res.data.amounts[i];
        console.log(price.payment_type + ':' + price.average);
    }
});*/

// print the procedure code and price for a particular zip/cpt combination
/*pokitdok.cashPrices({
        zip_code: '94401',
        cpt_code: '90658'
    }, function (err, res) {
    if (err) {
        return console.log(err, res.statusCode);
    }
    // print the cpt, geo_zip and average price
    for (var i = 0, ilen = res.data.length; i < ilen; i++) {
        var price = res.data[i];
        console.log(price.cpt_code + ':' + price.geo_zip_area +  ':' + price.average);
    }
});*/

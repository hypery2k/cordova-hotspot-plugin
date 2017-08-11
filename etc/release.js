#!/usr/bin/env node

var args = process.argv.slice(2),
    fs = require('fs'),
    xml2js = require('xml2js'),
    xmlParser = new xml2js.Parser(),
    builder = new xml2js.Builder(),
    exec = require('child_process').exec;

var packageJSON = require(__dirname + '/../package.json'),
    pluginXML = fs.readFileSync(__dirname + '/../plugin.xml');


xmlParser.parseString(pluginXML, function (err, result) {
    result.plugin.$.version = packageJSON.version;
    var xml = builder.buildObject(result, {includeWhiteChars: true});
    fs.writeFile(__dirname + '/../plugin.xml', xml, function (err) {
        if (err) throw err;
    });
});
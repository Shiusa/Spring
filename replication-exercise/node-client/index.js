const express = require('express');

const eureka = require('eureka-js-client');

const client = new eureka.Eureka({
    instance: {
        app: 'dummy',
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        vipAddress: 'dummy',
        port: {
            '$': 10000,
            '@enabled': true,
        },
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        }
    },
    eureka: {
        host: 'localhost',
        port: 9000,
        servicePath: '/eureka/apps/',
    }
});

client.start();

const app = express();
app.use(express.json());

app.get("/", (req, res) => res.send("Hello from node!"));

app.listen(10000);
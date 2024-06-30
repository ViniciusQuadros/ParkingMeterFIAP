var dbName = 'parkingmeterdb';
var user = 'user_parkingmeter';
var passwd = 'pass_parkingmeter';

db = db.getSiblingDB(dbName);
db.createUser({
    user: user,
    pwd: passwd,
    roles: [
        { role: 'readWrite', db: dbName }
    ]
});

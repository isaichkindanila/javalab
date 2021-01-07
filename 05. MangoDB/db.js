conn = new Mongo();
db = conn.getDB("abcde");

const a_id = db.a.insertMany([
    { value: 1.23 },
    { value: 4.56 },
]).insertedIds;

const b_id = db.b.insertMany([
    { value: "боп" },
    { value: "буп" },
]).insertedIds;

const c_id = db.c.insertMany([
    { value: { name: "QWE" } },
    { value: { name: "ASD" } },
]).insertedIds;

db.d.insert([
    { c: c_id[0] },
    { c: c_id[1] },
]);

db.e.insert([
    { a: a_id[0], b: b_id[0] },
    { a: a_id[1], b: b_id[1] },
]);

const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

fs.readFile(filePath, "utf-8", (err, d) => {
    const data = d.split(",");
    const boxes = [];
    for(let i = 0; i < 256; ++i) {
        boxes.push({});
    }
    for (let i = 0; i < data.length; ++i) {
        const [s, val] = data[i].split(/[-=]/);
        const box = hashValue(s);
        if(val.length === 0) {
            delete boxes[box][s];
        } else {
            boxes[box][s] = parseInt(val, 10);
        }
    }
    console.log(sum(boxes));
});

function sum(boxes) {
    let ans = 0;
    for(let i = 0; i < 256; ++i) {
        let box = 0;
        let curr = 1;
        for(let k in boxes[i]) {
            box += ((i+1) * (curr) * boxes[i][k]);
            curr++;
        }
        ans += box;
    }
    return ans;
}

function hashValue(s) {
    let current = 0;
    for (let j = 0; j < s.length; ++j) {
        current += s[j].charCodeAt(0);
        current *= 17;
        current %= 256;
    }
    return current;
}

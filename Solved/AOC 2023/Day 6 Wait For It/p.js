const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

fs.readFile(filePath, "utf-8", (err, data) => {
    data = data.split("\n");
    const times = data[0].split(" ");
    const dist = data[1].split(" ");
    let ans = 1;
    for(let q = 1; q < times.length; ++q) {
        const t = parseInt(times[q].trim())
        const d = parseInt(dist[q].trim())
        let count = 0;
        for(let i = 1; i <= t/2; ++i) {
            if(i * (t - i) > d) {
                if(i === t-i)
                    count += 1;
                else
                    count += 2;
            }
        }
        console.log(count);
        ans *= count;
    }
    console.log(ans);
});
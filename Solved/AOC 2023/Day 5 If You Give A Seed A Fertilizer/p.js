const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

fs.readFile(filePath, "utf8", (err, data) => {
    data = data.split("\n");
    const seeds = data[0]
        .split(" ")
        .splice(1)
        .map((str) => parseInt(str, 10));
    const m = [];
    for (let i = 0; i < seeds.length; i += 2) {
        const key1 = seeds[i];
        const key2 = seeds[i] + seeds[i + 1];
        m.push({ [key1]: key1, [key2]: key2 });
    }
    let idx = 2;
    for (let x = 0; x < 7; ++x) {
        const d = [];
        for (let i = idx; i < data.length; ++i) {
            const s = data[i].trim().split(" ");
            if (s[1] === "map:") {
                idx = i + 1;
                break;
            }
            const dest = parseInt(s[0]);
            const start = parseInt(s[1]);
            const range = parseInt(s[2]) - 1;
            const key1 = start;
            const key2 = start + range;
            d.push({ [key1]: dest, [key2]: dest + range });
        }
		for(let i = 0; i < d.length; ++i) {
			const k1 = Object.keys(d[i])[0];
			const k2 = Object.keys(d[i])[1];
			for(let j = 0; j < m.length; ++j) {
				const mk1 = Object.keys(m[j]);
			}
		}
    }
});

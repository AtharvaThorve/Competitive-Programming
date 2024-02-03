const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

const crypto = require('crypto');
function computeHash(array) {
    const hash = crypto.createHash("sha256");

    const concatenatedString = array.join("");

    return hash.update(concatenatedString).digest("hex");
}

fs.readFile(filePath, "utf-8", (err, d) => {
    d = d.split("\n");
    const seen = new Set();
    const seenMap = new Map();
    const arr = [0, -1, 0, 1];
    const queue = [arr];

    while (queue.length > 0) {
        let [r, c, dr, dc] = queue.shift();

        r += dr;
        c += dc;

        if (r < 0 || r >= d.length || c < 0 || c >= d[r].length-1) continue;

        const ch = d[r][c];
        if (ch === "." || (ch === "-" && dc != 0) || (ch == "|" && dr != 0)) {
            if (!seen.has(computeHash([r, c, dr, dc]))) {
                seenMap.set(computeHash([r, c, dr, dc]), [r, c]);
                seen.add(computeHash([r, c, dr, dc]));
                queue.push([r, c, dr, dc]);
            }
        } else if (ch === "/") {
            let temp = dr;
            dr = -dc;
            dc = -temp;
            if (!seen.has(computeHash([r, c, dr, dc]))) {
                seenMap.set(computeHash([r, c, dr, dc]), [r, c]);
                seen.add(computeHash([r, c, dr, dc]));
                queue.push([r, c, dr, dc]);
            }
        } else if (ch === "\\") {
            let temp = dr;
            dr = dc;
            dc = temp;
            if (!seen.has(computeHash([r, c, dr, dc]))) {
                seenMap.set(computeHash([r, c, dr, dc]), [r, c]);
                seen.add(computeHash([r, c, dr, dc]));
                queue.push([r, c, dr, dc]);
            }
        } else {
            if (ch === "|") {
                for (const [dr, dc] of [
                    [1, 0],
                    [-1, 0],
                ]) {
                    if (!seen.has(computeHash([r, c, dr, dc]))) {
                        seenMap.set(computeHash([r, c, dr, dc]), [r, c]);
                        seen.add(computeHash([r, c, dr, dc]));
                        queue.push([r, c, dr, dc]);
                    }
                }
            } else {
                for (const [dr, dc] of [
                    [0, 1],
                    [0, -1],
                ]) {
                    if (!seen.has(computeHash([r, c, dr, dc]))) {
                        seenMap.set(computeHash([r, c, dr, dc]), [r, c]);
                        seen.add(computeHash([r, c, dr, dc]));
                        queue.push([r, c, dr, dc]);
                    }
                }
            }
        }
    }
    const coords = new Set();
    for (const val of seen) {
        const x = seenMap.get(val);
        // console.log(x);
        coords.add(computeHash(x));
    }
    console.log(coords.size);
});

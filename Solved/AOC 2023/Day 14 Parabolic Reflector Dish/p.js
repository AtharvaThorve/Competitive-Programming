const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

const crypto = require("crypto");
function hashGrid(grid) {
    //sha256 hash
    const hash = crypto.createHash("sha256");
    return hash
        .update(grid.map((row) => row.join("")).join("\n"))
        .digest("hex");
}

fs.readFile(filePath, "utf-8", (err, d) => {
    let data = d.trim().split("\n");
    const n = data.length;
    for (let i = 0; i < n; ++i) {
        data[i] = data[i].trim().split("");
    }
    const visited = new Set();
    const visitedMap = new Map();
    let iter = 0;
    for (let i = 0; i < 1000000000; ++i) {
        iter++;
        data = cycle(JSON.parse(JSON.stringify(data)));
        const hash = hashGrid(data);

        if (visited.has(hash))
            break;

        visited.add(hash);
        visitedMap.set(hash, iter);
    }
    const first = visitedMap.get(hashGrid(data));
    const rem = (1000000000 - first) % (iter - first);
    for(let i = 0; i < rem; ++i) {
        data = cycle(JSON.parse(JSON.stringify(data)));
    }
    
    let ans = 0;
    let x = n;
    for (let i = 0; i < n; ++i) {
        let count = 0;
        for (let j = 0; j < data[i].length; ++j) {
            if (data[i][j] === "O") {
                count++;
            }
        }
        ans += x * count;
        x--;
    }
    console.log(ans);
});

function cycle(data) {
    const n = data.length;
    const m = data[0].length;
    const north = [];
    for (let i = 0; i < m; ++i) {
        north.push(0);
    }
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            if (data[i][j] === "#") {
                north[j] = i + 1;
            } else if (data[i][j] === "O") {
                let x = north[j];
                if (north[j] !== i) {
                    data[x][j] = "O";
                    data[i][j] = ".";
                }
                north[j]++;
            }
        }
    }
    // printData(data);
    // console.log("north");
    const west = [];
    for (let i = 0; i < n; ++i) {
        west.push(0);
    }
    for (let j = 0; j < m; ++j) {
        for (let i = 0; i < n; ++i) {
            if (data[i][j] === "#") {
                west[i] = j + 1;
            } else if (data[i][j] === "O") {
                let x = west[i];
                if (west[i] !== j) {
                    data[i][x] = "O";
                    data[i][j] = ".";
                }
                west[i]++;
            }
        }
    }
    // printData(data);
    // console.log("west");
    const south = [];
    for (let i = 0; i < m; ++i) {
        south.push(n - 1);
    }
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < m; ++j) {
            if (data[i][j] === "#") {
                south[j] = i - 1;
            } else if (data[i][j] === "O") {
                let x = south[j];
                if (south[j] !== i) {
                    data[x][j] = "O";
                    data[i][j] = ".";
                }
                south[j]--;
            }
        }
    }
    // printData(data);
    // console.log("south");
    const east = [];
    for (let i = 0; i < n; ++i) {
        east.push(m - 1);
    }
    for (let j = m - 1; j >= 0; --j) {
        for (let i = 0; i < n; ++i) {
            if (data[i][j] === "#") {
                east[i] = j - 1;
            } else if (data[i][j] === "O") {
                let x = east[i];
                if (east[i] !== j) {
                    data[i][x] = "O";
                    data[i][j] = ".";
                }
                east[i]--;
            }
        }
    }
    // printData(data);
    // console.log("east");
    return data;
}

function printData(data) {
    for (let i = 0; i < data.length; ++i) {
        console.log(data[i].join(""));
    }
}

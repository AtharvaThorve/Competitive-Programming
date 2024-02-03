const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

fs.readFile(filePath, "utf-8", (err, data) => {
    data = data.split("\n");

    for (let i = 0; i < data.length; ++i) {
        data[i] = data[i].trim().split("");
    }

    const temp = {};
    const galaxies = [];
    let x = 0;
    let y = 0;
    for (let i = 0; i < data.length; ++i) {
        let flag = false;
        for (let j = 0; j < data[i].length; ++j) {
            if (data[i][j] === "#") {
                temp[[i, j]] = x;
                flag = true;
                continue;
            }
        }
        if(!flag) {
            x += 999999;
        }
    }

    for (let j = 0; j < data[0].length; ++j) {
        let flag = false;
        for(let i = 0; i < data.length; ++i) {
            if(data[i][j] === "#") {
                const val = temp[[i, j]];
                galaxies.push([i + val, j + y]);
                flag = true;
                continue;
            }
        }
        if(!flag) {
            y += 999999;
        }
    }
    console.log(galaxies);
    const distances = [];
    for (let i = galaxies.length - 2; i >= 0; --i) {
        distances.push(allPairsDist(galaxies[i], galaxies.slice(i + 1)));
    }
    const sum = sumOfArrays(distances);
    // console.log(distances);
    console.log(sum);
});

function allPairsDist(start, ends) {
    const distances = [];
    for (let i = 0; i < ends.length; ++i) {
        distances.push(Math.abs(start[0] - ends[i][0]) + Math.abs(start[1] - ends[i][1]));
    }
    return distances;
}



function sumOfArrays(arrays) {
    let totalSum = 0;

    for (const subArray of arrays) {
        for (const value of subArray) {
            if (value === undefined || value === null) continue;
            totalSum += value;
        }
    }

    return totalSum;
}

const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");

fs.readFile(filePath, "utf-8", (err, data) => {
    data = data.split("\n");
    const sets = [];
    let tempSet = [];
    for (let i = 0; i < data.length; ++i) {
        if (data[i].trim().length !== 0) {
            tempSet.push(data[i].trim());
        } else {
            sets.push(tempSet);
            tempSet = [];
        }
    }
    let ans = 0;
    for (let q = 0; q < sets.length; ++q) {
        let temp = findMirror(sets[q]);
        ans += temp * 100;

        sets[q] = joinStringsVertically(sets[q]);
        temp = findMirror(sets[q]);
        ans += temp;
    }
    console.log(ans);
});

function findMirror(data) {
    for (let i = 0; i < data.length; ++i) {
        const above = data.slice(0, i).reverse();
        const below = data.slice(i);
        let x = 0;
        let y = 0;
        let sum = 0;
        while (x < above.length && y < below.length) {
            sum += charDifference(above[x], below[y]);
            x++;
            y++;
        }
        if (sum == 1) {
            return i;
        }
    }
    return 0;
}

function joinStringsVertically(arrayOfStrings) {
    if (arrayOfStrings.length === 0) {
        return "";
    }

    const stringLength = arrayOfStrings[0].length;

    const result = [];
    for (let i = 0; i < stringLength; i++) {
        let verticalConcatenation = "";
        for (let j = 0; j < arrayOfStrings.length; j++) {
            verticalConcatenation += arrayOfStrings[j][i];
        }
        result.push(verticalConcatenation);
    }

    return result;
}

function charDifference(str1, str2) {
    let differenceCount = 0;

    for (let i = 0; i < str1.length; i++) {
        if (str1[i] !== str2[i]) {
            differenceCount++;
        }
    }

    return differenceCount;
}

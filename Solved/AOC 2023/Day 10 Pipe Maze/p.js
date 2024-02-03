const fs = require("fs");
const path = require("path");
const filePath = path.join(__dirname, "input.txt");
const turf = require("@turf/turf");
const helpers = require("@turf/helpers");

fs.readFile(filePath, "utf-8", (err, d) => {
    const data = d.split("\n");
    let start = [];
    const mat = [];
    const visited = [];
    for (let i = 0; i < data.length; ++i) {
        const temp = [];
        const temp2 = [];
        for (let j = 0; j < data[i].length; ++j) {
            temp.push(0);
            temp2.push(0);
            if (data[i][j] === "S") {
                start = [i, j];
            }
        }
        mat.push(temp);
        visited.push(temp2);
    }
    let path = [];
    const color = [];
    let max = 0;
    path.push(start);
    mat[start[0]][start[1]] = 0;
    visited[start[0]][start[1]] = 1;
    const dirs = {
        "|": [
            [-1, 0],
            [1, 0],
        ],
        "-": [
            [0, -1],
            [0, 1],
        ],
        L: [
            [0, 1],
            [-1, 0],
        ],
        J: [
            [0, -1],
            [-1, 0],
        ],
        7: [
            [0, -1],
            [1, 0],
        ],
        F: [
            [0, 1],
            [1, 0],
        ],
        S: [
            [0, 1],
            [1, 0],
        ],
    };

    while (path.length > 0) {
        const temp = path.shift();
        let x = temp[0];
        let y = temp[1];
        if (x === start[0] && y === start[1] && max > 0) {
            break;
        }
        const char = data[x][y];
        if (char === ".") continue;
        for (let i = 0; i < dirs[char].length; ++i) {
            let new_x = x + dirs[char][i][0];
            let new_y = y + dirs[char][i][1];
            if (
                new_x >= 0 &&
                new_x < mat.length &&
                new_y >= 0 &&
                new_y < mat[new_x].length &&
                visited[new_x][new_y] === 0
            ) {
                mat[new_x][new_y] = mat[temp[0]][temp[1]] + 1;
                visited[new_x][new_y] = 1;
                if (max < mat[new_x][new_y]) max = mat[new_x][new_y];
                path.push([new_x, new_y]);
            }
        }
    }
    const coordinates = matrixToPolygon(data, visited);
    console.log(coordinates);
    console.log(calculatePolygonArea(coordinates));
    console.log(max);
});

function matrixToPolygon(data, visited) {
    const coordinates = [];
    for (let i = 0; i < data.length; ++i) {
        for (let j = 0; j < data[i].length; ++j) {
            if (
                visited[i][j] === 1 &&
                data[i][j] !== "|" &&
                data[i][j] !== "-"
            ) {
                coordinates.push([i, j]);
            }
        }
    }
    return coordinates;
}

function calculatePolygonArea(coordinates) {
    // Check if the coordinates array is valid
    if (!Array.isArray(coordinates) || coordinates.length < 3) {
        throw new Error(
            "Invalid input. The coordinates array must have at least 3 points."
        );
    }

    // Sort coordinates based on x-values and then y-values
    coordinates.sort((a, b) => a[0] - b[0] || a[1] - b[1]);

    // Calculate the area using the shoelace formula
    let area = 0;
    for (let i = 0; i < coordinates.length - 1; i++) {
        area +=
            coordinates[i][0] * coordinates[i + 1][1] -
            coordinates[i + 1][0] * coordinates[i][1];
    }

    // Take the absolute value and divide by 2
    area = Math.abs(area) / 2;

    return area;
}

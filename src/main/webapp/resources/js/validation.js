const rArray = [0, 1, 1.5, 2, 2.5, 3];
const colorArray = ["red", "415DFF", "FF92E4", "FF6156", "00E3FF", "44FF75"];
/*
FF92E4 розовый
00E3FF голубой
415DFF светло синий
44FF75 салатовый
FF6156 светло красный*/

graphInit()

function graphInit() {
    let pointsList = [...document.querySelector('#resultTable').rows].map((tr) => {
        return [...tr.cells].map((td) => td.textContent);
    })
    //Выбор радиусов для перерисовки
    for (let i = 1; i < 6; i++) {
        // вызовет ошибку так как значение в таблице теперь числовое
        if (getChecked(pointsList[1][6]).includes(i + "")) {
            let checkbox = document.getElementById("form:p"+i+"R");
            checkbox.checked = true;
            checkboxClick(checkbox);
        }
    }

}

function getChecked(strChecked) {
    let arr1 = strChecked;
    let arr2 = [];
    for (let i =0; i < arr1.length; i++) {
        if (isNumber(arr1[i])) arr2.push(arr1[i]);
    }
    return arr2;
}

function isNumber(char) {
    return /\d/.test(char);
}

//Меняет отрисовку на графике
function addArea(r) {
    let numberR = Number(rArray[r]);
    document.getElementById("g"+r).innerHTML += `<path id="path${r}" 
              d="M 480 480
              L 480 ${480-40*numberR}
              L ${480+80*numberR} 480
              A ${80*numberR} ${80*numberR} 0 0 1 480 ${480+80*numberR}
              L 480 ${480+40*numberR}
              L ${480-80*numberR} ${480+40*numberR}
              L ${480-80*numberR} 480
              L 480 480" stroke="black" fill="${"#" + colorArray[r]}""/>`
}

//вызывает получение точек из БД и отрисовку, чистит область при если радиус не выбран
function checkboxClick(checkbox){
    if (!checkbox.checked) {
        document.getElementById("g"+checkbox.id[6]).innerHTML = '';
        document.getElementById("dot"+checkbox.id[6]).innerHTML = '';
    } else {
        addArea(checkbox.id[6]);
        drawPointsFromDB(checkbox.id[6])
    }
}

//первичное изображение точки при клике
function svgClick(event, svg) {
    let svgCoord = svg.getBoundingClientRect(); // DOMRect object
    let xPartOfSvg = (event.clientX - svgCoord.x)/svgCoord.width; // координата(в долях) клика относительно размеров svg
    let yPartOfSvg = (event.clientY - svgCoord.y)/svgCoord.height;
    drawPoint(svg, (xPartOfSvg) * 960, (yPartOfSvg) * 960);
    let x = (xPartOfSvg - 0.5) * 12;
    let y = -1 * (yPartOfSvg - 0.5) * 12;
    formClick(x, y);
}
function drawPoint(svgOrG, x, y, resultFill='black'){
    svgOrG.innerHTML += `<circle cx="${x}" cy="${y}" r='7' fill="${resultFill}"/>`;
}

function formClick(x, y){
    document.getElementById("form:hiddenX").value = x;
    document.getElementById("form:hiddenY").value = y;
    document.getElementById("form:x").value = 0.0;
    document.getElementById("form:y-inputHidden").value = 0.0;
    document.getElementById("form:submit").click();
}

function drawPointsFromDB(r){
    let pointsList = [...document.querySelector('#resultTable').rows].map((tr) => {
        return [...tr.cells].map((td) => td.textContent);
    })
    for (let i = 1; i < pointsList.length; i++) {
        console.log(i);
        if (getChecked(pointsList[i][6]).includes(r + "")) {
            let result = pointsList[i][4];
            let x = Number(pointsList[i][1]);
            let y = Number(pointsList[i][2]);
            if (x <= 960 && y <= 960) {
                let fill = "red";
                if (result.startsWith("In")) {
                    //когда кликают чекбокс, отрисовываться должна точка только на g dot, соответствующей этому радиусу
                    let successR = result.substring(pointsList[i][4].indexOf(":") + 2, pointsList[i][4].length).split(" ")
                    if (successR.includes(r)) {
                        fill = "green";
                    }
                }
                drawPoint(document.getElementById("dot"+r), (x/12 + 0.5) * 960, (y/(-12) + 0.5) * 960, fill)
            }
        }
    }
}

function convert(yElem) {
    yElem.value = yElem.value.replace(",", ".")
}
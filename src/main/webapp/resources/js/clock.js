beginTime();

function beginTime() {
    let container = document.getElementById("time-container");
    changeTime(container);
    const interval = 5;
    setInterval(() => changeTime(container), interval * 1000);
}

function changeTime(container) {
    let d = new Date();
    let hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
    let minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
    let seconds = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
    let date_time = hours + ":" + minutes + ":" + seconds + "       " + d.getDate() + "." + (d.getMonth()+1) + "." + d.getFullYear();
    container.innerHTML = `<p>Time: ${date_time}</p>`;
}
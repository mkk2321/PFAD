const boardNo = document.getElementsByClassName('boardNo');
const boardNav = document.getElementsByClassName('boardNav');

(function () {
    document.write('함수 호출');
    for (let i = 0; i < boardNo.length; i++) {
        if(parseInt(boardNo[i].innerHTML) % 2 === 0) {
            boardNo[i].parentElement.style.backgroundColor = "#e8e8e8";
        }
    }
})();

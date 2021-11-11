const boardNo = document.getElementsByClassName('boardNo');
const boardNav = document.getElementsByClassName('boardNav');
const boardNavLi = document.body.querySelectorAll(':scope > main > div.boardNav > nav > ul > li');

(function () {
    const link = document.location.href;
    const linkArray = link.split('/');

    switch (linkArray[5]) {
        case 'notice':
            boardNavLi[0].style.color = '#f0a050';
            break;
        case 'free':
            boardNavLi[1].style.color = '#f0a050';
            break;
        case 'qna':
            boardNavLi[2].style.color = '#f0a050';
            break;
    }

    for (let i = 0; i < boardNo.length; i++) {
        if(parseInt(boardNo[i].innerHTML) % 2 === 0) {
            boardNo[i].parentElement.style.backgroundColor = "#e8e8e8";
        }
    }
})();


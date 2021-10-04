const a = window.document.querySelectorAll('.menuNav > li');
const content = window.document.querySelector('.content');
console.log(a);
let count = 0;
console.log(a);
function fetchFunction(name) {
    fetch(name).then(function (response) {
        response.text().then(function (text) {
                window.document.querySelector('.content').innerHTML = text;
        })
    });
}

if (location.hash) {
    fetchFunction(location.hash.substr(1));
} else {
    fetchFunction('home');
}

fetch('../engList').then(function (response) {
    response.text().then(function (eng) {
        fetch('korList').then(function (response) {
            response.text().then(function (kor) {
                let engItems = eng.split(',');
                let korItems = kor.split(',');
                let tags = '';
                for(let i = 0; i < engItems.length; i++){
                    a[i].addEventListener('click', ()=> {
                        fetchFunction(engItems[i]);
                    })
                }

            })
        })
    })
})



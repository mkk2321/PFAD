const sumPrice = window.document.getElementsByClassName('sumPrice');
const stock = window.document.getElementsByClassName('stock');
const price = window.document.getElementsByClassName('price');
const totalPrice = window.document.getElementsByClassName('totalPrice');
const checkBox = window.document.getElementsByClassName('checkBox');
const cartForm = window.document.querySelector('[rel="cart-form"]');
const checkbox = window.document.body.querySelectorAll('[type="checkbox"]');

let sum = 0;
for(let i = 0; i < stock.length; i++) {
    sumPrice[i].innerHTML = price[i].innerHTML * stock[i].innerHTML;
}

for (let j = 0; j < sumPrice.length; j++) {
    sum += parseInt(sumPrice[j].innerHTML);
    stock[j].oninput = function () {
        sumPrice[j].innerHTML = price[j].innerHTML * stock[j].innerHTML;
        sum = 0;
        for (let l = 0; l < sumPrice.length; l++) {
            sum += parseInt(sumPrice[l].innerHTML);
        }
        totalPrice[0].innerHTML = sum;
    }
    totalPrice[0].innerHTML = sum;
}
// stock.addEventListener('focusout'), () => {
//     if(stock.value > 50) {
//         alert('수량은 50개를 초과할 수 없습니다. \n대량주문은 문의 바랍니다.')
//     }
// }
// checkBox
/*
let checkboxNum = 0;
cartForm['checkAll'].addEventListener('click', () => {
    for (let i = 0; i < checkbox.length; i++) {
        if(cartForm['checkAll'].checked) {
            checkbox[i].checked = true;
            checkboxNum = checkbox.length-1;
        }else {
            checkbox[i].checked = false;
            checkboxNum = 0;
        }
    }
});

for (let i = 1; i < checkbox.length; i++) {
    checkbox[i].addEventListener('click', () => {
        checkbox[i].checked ? checkboxNum++ : checkboxNum--;
        checkbox[0].checked = checkboxNum === checkbox.length - 1;
    });
}

*/





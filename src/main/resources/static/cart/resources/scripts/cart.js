const sumPrice = window.document.getElementsByClassName('sumPrice');
const stock = window.document.getElementsByClassName('stock');
const price = window.document.getElementsByClassName('price');
const totalPrice = window.document.getElementsByClassName('totalPrice');
const checkBox = window.document.getElementsByClassName('checkBox');
const cartForm = window.document.querySelector('[rel="cart-form"]');
const checkbox = window.document.body.querySelectorAll('[type="checkbox"]');

console.log(checkBox);
let sum = 0;
for(let i = 0; i < stock.length; i++) {
    sumPrice[i].value = price[i].value * stock[i].value;
}

for (let j = 0; j < sumPrice.length; j++) {
    sum += parseInt(sumPrice[j].value);
    stock[j].oninput = function () {
        sumPrice[j].value = price[j].value * stock[j].value;
        sum = 0;
        for (let l = 0; l < sumPrice.length; l++) {
            sum += parseInt(sumPrice[l].value);
        }
        totalPrice[0].innerHTML = sum;
    }
    totalPrice[0].innerHTML = sum;
}

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






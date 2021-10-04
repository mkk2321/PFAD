const table = window.document.body.querySelectorAll(':scope > form > table');
const recoverButton = window.document.body.querySelectorAll(':scope > section > span');
const idRecoverForm = window.document.body.querySelector('[rel="id-recover"]');
const passwordRecoverForm = window.document.body.querySelector('[rel="password-recover"]');
const idInputLength = idRecoverForm.querySelectorAll('input');
const passwordInputLength = passwordRecoverForm.querySelectorAll('input');

for (let i = 0; i < recoverButton.length; i++) {
    recoverButton[i].addEventListener('click', () => {
        for (let j = 0; j < recoverButton.length; j++) {
            table[j].classList.remove('visible');
            recoverButton[j].classList.remove('click');
            table[i].classList.add('visible');
            recoverButton[i].classList.add('click');
        }
    });
}

passwordRecoverForm.onsubmit = () => {
    for(let i = 0; i <= passwordInputLength.length; i++) {
        if(passwordRecoverForm[i].value === '') {
            alert('입력하지 않은 항목이 있습니다.');
            return false;
        }
    }
}

idRecoverForm.onsubmit = () => {
    for(let i = 0; i <= idInputLength.length; i++) {
        if(idRecoverForm[i].value === '') {
            alert('입력하지 않은 항목이 있습니다.');
            return false;
        }
    }
}






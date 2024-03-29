
const emailRegEx = new RegExp('^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$');
const passwordRegEx = new RegExp('^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$');
const idRegEx = new RegExp('^([a-zA-Z0-9가-힣]){2,20}$');
const nameRegEx = new RegExp('^([가-힣]{2,4})$');
const birthRegEx = new RegExp('^([0-9]{6})$');
const genderRegEx = new RegExp('^([0-4]{1})$');
const contactSecondRegEx = new RegExp('^([0-9]{4})$');
const contactThirdRegEx = new RegExp('^([0-9]{4})$');
const addressPostalRegEx = new RegExp('^([0-9]{5})$');
const addressPrimaryRegEx = new RegExp('^([가-힣a-zA-Z0-9\\-\\.\\(\\) ]{1,})$');
const addressSecondaryRegEx = new RegExp('^([가-힣a-zA-Z0-9\\-\\.\\(\\) ]{0,})$');
const registerForm = window.document.body.querySelector('[rel="register-form"]');
const checkbox = window.document.body.querySelectorAll('[type="checkbox"]');

let isEmailChecked = false;
let isIdChecked = false;
let isPasswordChecked = false;
let isPasswordCheckChecked = false;

registerForm['addressButton'].addEventListener('click', () => {
    new daum.Postcode({
        oncomplete: function (data) {
            registerForm['addressPostal'].value = data.zonecode;
            registerForm['addressPrimary'].value = data.roadAddress;
            registerForm['addressSecondary'].focus();
            registerForm['addressSecondary'].select();
        }
    }).open();
});

registerForm['email'].addEventListener('input', () => {
    const emailMessage = window.document.querySelector('[rel="email-message"]');
    emailMessage.classList.remove('good');
    emailMessage.classList.remove('warning');
    emailMessage.innerText = '인증을 위해 존재하는 이메일을 입력해주세요.';
    emailMessage.classList.add('visible');
    isEmailChecked = false;
});

registerForm['email'].addEventListener('focusout', () => {
    const emailMessage = window.document.querySelector('[rel="email-message"]');
    var email = registerForm['email'].value;
    emailMessage.innerText = '';
    emailMessage.classList.remove('good');
    emailMessage.classList.remove('warning');
    if (!emailRegEx.test(email)) {
        emailMessage.innerText = '올바른 이메일을 입력해주세요.';
        emailMessage.classList.add('warning');
        return false;
    }
   
	$.ajax({
		url: '/check-email',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		data : {
			email
		},
		success: function(data) {
			switch (data.result.toLowerCase()) {
	            case 'existing':
	                emailMessage.innerText = '이미 가입된 이메일입니다.';
	                emailMessage.classList.add('warning');
	                break;
	            case 'non_existing':
	                isEmailChecked = true;
	                emailMessage.innerText = '사용 가능한 이메일입니다.';
	                emailMessage.classList.add('good');
	                break;
	            default:
	                emailMessage.innerText = '이메일 유효성 검사에 실패하였습니다.';
	                emailMessage.classList.add('warning');
	                break;
      	 	}
		},
		error: function(data) {
			console.log("error : " + data);
		}
	})

});


registerForm['password'].addEventListener('input', () => {
    const passwordMessage = window.document.body.querySelector('[rel="password-message"]');
    passwordMessage.innerText = '';
    passwordMessage.classList.remove('good');
    passwordMessage.classList.remove('warning');
    isPasswordChecked = false;
});

registerForm['password'].addEventListener('focusout', () => {
    const passwordMessage = window.document.body.querySelector('[rel="password-message"]');
    passwordMessage.innerText = '';
    passwordMessage.classList.remove('good');
    passwordMessage.classList.remove('warning');
    if (!passwordRegEx.test(registerForm['password'].value)) {
        passwordMessage.innerText = '특수문자, 문자, 숫자가 하나씩 포함된 8자리 이상의 비밀번호를 입력하세요';
        passwordMessage.classList.add('warning');
        return false;
    } else {
        isPasswordChecked = true;
    }
});

registerForm['passwordCheck'].addEventListener('input', () => {
    const passwordCheckMessage = window.document.body.querySelector('[rel="password-check-message"]');
    passwordCheckMessage.innerText = '';
    passwordCheckMessage.classList.remove('good');
    passwordCheckMessage.classList.remove('warning');
    isPasswordCheckChecked = false;
});

registerForm['passwordCheck'].addEventListener('focusout', () => {
    const passwordCheckMessage = window.document.body.querySelector('[rel="password-check-message"]');
    passwordCheckMessage.innerText = '';
    passwordCheckMessage.classList.remove('good');
    passwordCheckMessage.classList.remove('warning');
    if (registerForm['password'].value !== registerForm['passwordCheck'].value) {
        passwordCheckMessage.innerText = '비밀번호가 일치하지 않습니다.';
        passwordCheckMessage.classList.add('warning');
        return false;
    } else {
        isPasswordCheckChecked = true;
    }
});


registerForm['id'].addEventListener('input', () => {
    const idMessage = window.document.body.querySelector('[rel="id-message"]');
    idMessage.innerText = '';
    idMessage.classList.remove('good');
    idMessage.classList.remove('warning');
    isIdChecked = false;
});

registerForm['id'].addEventListener('focusout', () => {
    const idMessage = window.document.body.querySelector('[rel="id-message"]');
    var id = registerForm['id'].value;
    idMessage.innerText = '';
    idMessage.classList.remove('good');
    idMessage.classList.remove('warning');
    if (!idRegEx.test(id)) {
        idMessage.innerText = '올바른 아이디를 입력해주세요.';
        idMessage.classList.add('warning');
        return false;
    }
 
    $.ajax({
		url: '/check-id',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
		data: {
			id
		},
		success: function(data) {
			switch (data.result.toLowerCase()) {
	            case 'existing':
	                idMessage.innerText = '이미 사용중인 아이디입니다.';
	                idMessage.classList.add('warning');
	                break;
	            case 'non_existing':
	                isIdChecked = true;
	                idMessage.innerText = '사용 가능한 아이디입니다.';
	                idMessage.classList.add('good');
	                break;
	            default:
	                idMessage.innerText = '아이디 유효성 검사에 실패하였습니다.';
	                idMessage.classList.add('warning');
	                break;
	        }
		},
		error: function(data) {
			console.log("error : " + data);
		}
	})

});

let checkboxNum = 0;
registerForm['agreeCheckAll'].addEventListener('click', () => {
    for (let i = 0; i < checkbox.length; i++) {
        if(registerForm['agreeCheckAll'].checked) {
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

registerForm.onsubmit = () => {
    if (!isIdChecked) {
        registerForm['id'].focus();
        registerForm['id'].select();
        alert('아이디 유효성 검사에 실패하였습니다. 잠시 후 다시 시도해주세요.');
        return false;
    }

    if (!isPasswordChecked) {
        registerForm['password'].focus();
        registerForm['password'].select();
        alert('비밀번호 유효성 검사에 실패하였습니다. 잠시 후 다시 시도해주세요.');
        return false;
    }

    if (!isPasswordCheckChecked) {
        registerForm['passwordCheck'].focus();
        registerForm['passwordCheck'].select();
        alert('비밀번호 재설정 유효성 검사에 실패하였습니다. 잠시 후 다시 시도해주세요.');
        return false;
    }


    if (!emailRegEx.test(registerForm['email'].value)) {
        registerForm['email'].focus();
        registerForm['email'].select();
        alert('올바른 이메일을 입력해주세요.');
        return false;
    }

    if (!passwordRegEx.test(registerForm['password'].value)) {
        registerForm['password'].focus();
        registerForm['password'].select();
        alert('올바른 비밀번호를 입력해주세요.');
        return false;
    }

    if (registerForm['password'].value !== registerForm['passwordCheck'].value) {
        registerForm['passwordCheck'].focus();
        registerForm['passwordCheck'].select();
        alert('비밀번호가 일치하지 않습니다.');
        return false;
    }

    if (!idRegEx.test(registerForm['id'].value)) {
        registerForm['id'].focus();
        registerForm['id'].select();
        alert('올바른 닉네임을 입력해주세요.');
        return false;
    }

    if (!nameRegEx.test(registerForm['name'].value)) {
        registerForm['name'].focus();
        registerForm['name'].select();
        alert('올바른 이름을 입력해주세요.');
        return false;
    }

    if (!birthRegEx.test(registerForm['birth'].value)) {
        registerForm['birth'].focus();
        registerForm['birth'].select();
        alert('올바른 생년월일을 입력해주세요.');
        return false;
    }

    if (!genderRegEx.test(registerForm['gender'].value)) {
        registerForm['gender'].focus();
        registerForm['gender'].select();
        alert('올바른 성별을 입력해주세요.');
        return false;
    }

    if (!contactSecondRegEx.test(registerForm['contactSecond'].value)) {
        registerForm['contactSecond'].focus();
        registerForm['contactSecond'].select();
        alert('올바른 연락처를 입력해주세요.');
        return false;
    }

    if (!contactThirdRegEx.test(registerForm['contactThird'].value)) {
        registerForm['contactThird'].focus();
        registerForm['contactThird'].select();
        alert('올바른 연락처를 입력해주세요.');
        return false;
    }

    if (!addressPostalRegEx.test(registerForm['addressPostal'].value)) {
        registerForm['addressPostal'].focus();
        registerForm['addressPostal'].select();
        alert('올바른 주소를 입력해주세요.');
        return false;
    }

    if (!addressPrimaryRegEx.test(registerForm['addressPrimary'].value)) {
        registerForm['addressPrimary'].focus();
        registerForm['addressPrimary'].select();
        alert('올바른 기본 주소를 입력해주세요.');
        return false;
    }

    if (!addressSecondaryRegEx.test(registerForm['addressSecondary'].value)) {
        registerForm['addressSecondary'].focus();
        registerForm['addressSecondary'].select();
        alert('올바른 상세 주소를 입력해주세요.');
        return false;
    }

    if (!isEmailChecked) {
        registerForm['email'].focus();
        registerForm['email'].select();
        alert('이메일 유효성 검사에 실패하였습니다. 잠시 후 다시 시도해주세요.');
        return false;
    }

    // if(!registerForm['agreePrivacy'].checked){
    //     registerForm['agreePrivacy'].focus();
    //     registerForm['agreePrivacy'].select();
    //     alert('동의 실패');
    //     return false;
    // }

    for (let i = 1; i < checkbox.length; i++) {
        if (!checkbox[i].checked) {
            checkbox[i].focus();
            checkbox[i].select();
            alert("약관에 동의되지 않았습니다.");
            return false;
        }
    }

};




















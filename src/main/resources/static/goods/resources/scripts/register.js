// const registerForm = window.document.body.querySelector('[rel="register-form"]');
// ClassicEditor.create(registerForm['file'], {
//     toolbar : {
//         items: [
//
//             "imageUpload",
//             "undo",
//             "redo"
//         ]
//     },
//     language: "en",
//     image   : {
//         toolbar: [
//             "imageTextAlternative",
//             "imageStyle:inline",
//             "imageStyle:block",
//             "imageStyle:side"
//         ]
//     },
//     table   : {
//         contentToolbar: [
//             "tableColumn",
//             "tableRow",
//             "mergeTableCells"
//         ]
//     },
//     licenseKey: '',
//     simpleUpload: {
//         uploadUrl: 'http://127.0.0.1/goods/upload-image'
//     }
// });

const inputImage = document.getElementById("input-image");
function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        inputImage.innerText = input.files[0].name;
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
inputImage.addEventListener("change", e => {
    readImage(e.target)
})
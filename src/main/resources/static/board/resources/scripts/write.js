const writeForm = window.document.body.querySelector('[rel="write-form"]');

ClassicEditor.create(writeForm['content'], {
    toolbar : {
        items: [
            "heading",
            "|",
            "bold",
            "italic",
            "link",
            "bulletedList",
            "numberedList",
            "|",
            "outdent",
            "indent",
            "|",
            "imageUpload",
            "blockQuote",
            "insertTable",
            "mediaEmbed",
            "undo",
            "redo"
        ]
    },
    language: "en",
    image   : {
        toolbar: [
            "imageTextAlternative",
            "imageStyle:inline",
            "imageStyle:block",
            "imageStyle:side"
        ]
    },
    table   : {
        contentToolbar: [
            "tableColumn",
            "tableRow",
            "mergeTableCells"
        ]
    },
    licenseKey: '',
    simpleUpload: {
        uploadUrl: 'http://127.0.0.1/board/upload-image'
    }
});
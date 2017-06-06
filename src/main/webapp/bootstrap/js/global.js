$(document).ready(function() {

    $('#sourceData').fileinput({
        //minFileCount:2,
        // required: true,
        validateInitialCount: true,
        overwriteInitial: false,
        // isUploadable: true,
        showCaption: false,
        showUpload: false,
        showClose: false,
        previewClass: 'preview',
        allowedFileExtensions: ["java"]
    });

    render();

    var $source = $('#source');

    $source.keyup(render);
    $source.keydown(handleTab);

    $('#task-list').find('tr').click(function () {
        var span = $(this).find('.task-data span');
        var prevDisplay = span.css('display');
        var newDisplay = prevDisplay === 'none' ? 'block' : 'none';
        span.css({'display' : newDisplay});
    });
});

function render() {
    var $code = $('code');
    var text = $('#source').val();

    $code.text(text);
    $code.each(function(i, block) {
        hljs.highlightBlock(block);
    });
}

function handleTab (e) {
    if(e.key === 'Tab') {
        var index = $(this).prop("selectionStart");
        var val = $(this).val();
        var output = [val.slice(0, index), '\t', val.slice(index)].join('');

        $(this).val(output);
        $(this).prop("selectionStart", index + 1);
        $(this).prop("selectionEnd", index + 1);

        return false;
    }
}

function validate() {
    //todo: handle error
    return $('#sourceData').fileinput('getFilesCount') === 2;
}
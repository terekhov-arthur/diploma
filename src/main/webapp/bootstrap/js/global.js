$(document).ready(function() {
    render();
    $('#source').keyup(render);
});

function render() {
    var $code = $('code');
    var text = $('#source').val();

    $code.text(text);
    $code.each(function(i, block) {
        hljs.highlightBlock(block);
    });
}
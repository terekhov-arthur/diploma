$(document).ready(function() {

    $('#sourceData').fileinput({
        validateInitialCount: true,
        overwriteInitial: false,
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

    $('.labels')
        // don't navigate away from the field on tab when selecting an item
        .on( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
                event.preventDefault();
            }
        })
        .autocomplete({
            source: function( request, response ) {
                $.getJSON( "/label", {
                    term: extractLast( request.term )
                }, response );
            },
            search: function() {
                // custom minLength
                var term = extractLast( this.value );
                if ( term.length < 2 ) {
                    return false;
                }
            },
            focus: function() {
                // prevent value inserted on focus
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = Array.from(new Set(terms)).join( ", " );
                //todo: turn string into labels
                return false;
            }
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

function split( val ) {
    return val.split( /,\s*/ );
}
function extractLast( term ) {
    return split( term ).pop();
}
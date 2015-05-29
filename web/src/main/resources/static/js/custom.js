jQuery(document).ready(function($) {

    var converter = new showdown.Converter();

    $('.ui.sidebar').sidebar().sidebar('attach events', '.xwc-menu');
    $('.ui.checkbox').checkbox();
    $('.ui.accordion').accordion();
    $('.popup').popup({
        setFluidWidth: false,
        position: 'bottom right',
        inline: true
    });

    //load markdown file inner to html
    $('.markdown-file').each(function(index, el) {
        $.get($(el).attr('href'), function(data) {
            $(el).replaceWith($('<div class="markdown-body"></div>').html(converter.makeHtml(data)));
        });
    });

    // convert inner markdown text to html and replace it.
    $('.markdown-text').each(function(index, el) {
        $(el).replaceWith($('<div class="markdown-body"></div>').html(converter.makeHtml($(el).find('pre').text())));
    });

});

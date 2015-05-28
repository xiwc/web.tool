jQuery(document).ready(function($) {

    $('.ui.sidebar').sidebar({}).sidebar('attach events', '.xwc-menu');
    $('.ui.checkbox').checkbox();
    $('.ui.accordion.i18n').accordion({
        onOpen: function() {
            $.get('md/i18n.md', function(data) {
                $('.markdown-body').html(markdown.toHTML(data));
            });
        }
    });
    $('.ui.accordion.json2bean').accordion({
        onOpen: function() {
            $.get('md/json2bean.md', function(data) {
                $('.markdown-body').html(markdown.toHTML(data));
            });
        }
    });

});

fish.View.configure({ manage: true, syncRender: true });
require(['frm/portal/Portal'], function() {
    fish.setLanguage(window.portal.appGlobal.get('language'));

    require(['modules/main/views/IndexView'], function(MainView) {
        new MainView({
            el: $('#app') // 主视图选择器
        }).render();
    });
});
